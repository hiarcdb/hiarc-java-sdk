package com.hiarcdb.client.integrationTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.api.CollectionApi;
import com.hiarcdb.client.api.FileApi;
import com.hiarcdb.client.model.AccessLevel;
import com.hiarcdb.client.model.AddFileToCollectionRequest;
import com.hiarcdb.client.model.AddGroupToCollectionRequest;
import com.hiarcdb.client.model.AddUserToCollectionRequest;
import com.hiarcdb.client.model.Classification;
import com.hiarcdb.client.model.Collection;
import com.hiarcdb.client.model.CollectionItems;
import com.hiarcdb.client.model.CreateUserTokenRequest;
import com.hiarcdb.client.model.FindClassificationsRequest;
import com.hiarcdb.client.model.FindCollectionsRequest;
import com.hiarcdb.client.model.Group;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.UpdateClassificationRequest;
import com.hiarcdb.client.model.UpdateCollectionRequest;
import com.hiarcdb.client.model.User;
import com.hiarcdb.client.model.UserCredentials;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ClassificationTest {
    private final HiarcTestUtil util = new HiarcTestUtil();

    @Before
    public void setup() {
        try {
            util.admin.resetDB();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Classification CRUD
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void classificationCRUDTest() throws ApiException {
        Classification c1 = util.classifications.createClassification(util.createClassification(), null);
        Classification gc = util.classifications.getClassification(c1.getKey(), null);
        Assert.assertEquals(c1, gc);

        String newName = "New Name";
        String newDescription = "New description";
        UpdateClassificationRequest ucr = new UpdateClassificationRequest();
        ucr.setName(newName);
        ucr.setDescription(newDescription);

        Classification uc = util.classifications.updateClassification(c1.getKey(), ucr, null);
        Assert.assertEquals(uc.getName(), newName);
        Assert.assertEquals(uc.getDescription(), newDescription);
        Assert.assertTrue(uc.getModifiedAt().isAfter(uc.getCreatedAt()));

        ucr.setKey("new key");
        ucr.setName(newName);
        ucr.setDescription(newDescription);

        Assert.assertThrows(ApiException.class, () -> {
            util.classifications.updateClassification(c1.getKey(), ucr, null);
        });
    }

    /**
     * Get all Classifications
     *
     *
     *
     * @throws ApiException if the Api call fails
     * 
     */
    @Test
    public void getAllClassificationsTest() throws ApiException {
        for (int i = 0; i < util.LARGE_ENTITY_COUNT; i++) {
            util.classifications.createClassification(util.createClassification(), null);
        }
        List<Classification> c = util.classifications.getAllClassifications(null);
        Assert.assertEquals(c.size(), util.LARGE_ENTITY_COUNT);
    }

    /**
     * Create a Classification with Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void createClassificationWithMetadataTest() throws ApiException, ParseException {
        Classification c1 = util.classifications.createClassification(util.createClassification(util.getTestMetadata()), null);
        Classification gc = util.classifications.getClassification(c1.getKey(), null);

        Assert.assertEquals(c1, gc);
        Assert.assertTrue(util.assertMetadataEqual(gc.getMetadata(), util.getTestMetadata()));
        Assert.assertTrue(util.assertMetadataEqual(c1.getMetadata(), gc.getMetadata()));
    }

    /**
     * Update a Classification with Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void updateClassificationWithMetadataTest() throws ApiException, ParseException {
        Classification c1 = util.classifications.createClassification(util.createClassification(util.getTestMetadata()), null);

        UpdateClassificationRequest ucr = new UpdateClassificationRequest();
        ucr.setMetadata(util.updatedTestMetadata());
        Classification updated = util.classifications.updateClassification(c1.getKey(), ucr, null);

        Assert.assertTrue(util.assertMetadataEqual(updated.getMetadata(), util.updatedTestMetadata()));
    }

    /**
     * Null Out Metadata
     *
     *
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void nullOutMetadataTest() throws ApiException, ParseException {
        Classification c1 = util.classifications.createClassification(util.createClassification(util.getTestMetadata()), null);

        UpdateClassificationRequest ucr = new UpdateClassificationRequest();
        ucr.putMetadataItem("department", null);
        ucr.putMetadataItem("quotaCarrying", null);
        Classification updated = util.classifications.updateClassification(c1.getKey(), ucr, null);
        Assert.assertTrue(updated.getMetadata().keySet().size() == 3);

        UpdateClassificationRequest ucr2 = new UpdateClassificationRequest();
        ucr2.putMetadataItem("targetRate", null);
        ucr2.putMetadataItem("level", null);
        ucr2.putMetadataItem("startDate", null);
        Classification updated2 = util.classifications.updateClassification(c1.getKey(), ucr2, null);
        Assert.assertNull(updated2.getMetadata());
    }

    /**
     * Find a Group
     *
     *
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void findGroupTest() throws ApiException, ParseException {
        Map<String, Object> m = new HashMap<>();
        m = util.getTestMetadata();
        Classification c1 = util.classifications.createClassification(util.createClassification(m), null);

        m.put("quotaCarrying", false);
        util.classifications.createClassification(util.createClassification(m), null);
        util.classifications.createClassification(util.createClassification(), null);

        Map<String, Object> prop = new HashMap<>();
        prop.put("prop", "department");
        prop.put("op", "starts with");
        prop.put("value", "sal");

        Map<String, Object> b = new HashMap<>();
        b.put("bool", "and");

        Map<String, Object> paren = new HashMap<>();
        paren.put("parens", "(");

        Map<String, Object> prop2 = new HashMap<>();
        prop2.put("prop", "targetRate");
        prop2.put("op", ">=");
        prop2.put("value", 4.22);

        Map<String, Object> prop3 = new HashMap<>();
        prop3.put("prop", "quotaCarrying");
        prop3.put("op", "=");
        prop3.put("value", true);

        Map<String, Object> closeParen = new HashMap<>();
        closeParen.put("parens", ")");

        List<Map<String, Object>> q = new ArrayList<Map<String, Object>>(
                Arrays.asList(prop, b, paren, prop2, b, prop3, closeParen));

        FindClassificationsRequest fcr = new FindClassificationsRequest();
        fcr.setQuery(q);
        List<Classification> response = util.classifications.findClassification(fcr, null);

        Assert.assertTrue(response.size() == 1);
        Assert.assertEquals(response.get(0), c1);
    }
}
