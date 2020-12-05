package com.hiarcdb.client.integrationTests;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;

import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.model.AddRetentionPolicyToFileRequest;
import com.hiarcdb.client.model.FindRetentionPoliciesRequest;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.RetentionPolicy;
import com.hiarcdb.client.model.RetentionPolicyApplication;
import com.hiarcdb.client.model.UpdateRetentionPolicyRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RetentionPolicyTest {
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
     * Retention Policy CRUD
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void retentionPolicyCRUDTest() throws ApiException {
        RetentionPolicy rp1 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(60L));
        RetentionPolicy grp = util.retentionPolicies.getRetentionPolicy(rp1.getKey());
        Assert.assertEquals(rp1, grp);

        String newName = "New Name";
        String newDescription = "New description";
        UpdateRetentionPolicyRequest urpr = new UpdateRetentionPolicyRequest();
        urpr.setName(newName);
        urpr.setDescription(newDescription);

        RetentionPolicy urp = util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);
        Assert.assertEquals(urp.getName(), newName);
        Assert.assertEquals(urp.getDescription(), newDescription);
        Assert.assertTrue(urp.getModifiedAt().isAfter(urp.getCreatedAt()));

        urpr.setKey("new key");
        urpr.setName(newName);
        urpr.setDescription(newDescription);

        Assert.assertThrows(ApiException.class, () -> {
            util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);
        });
    }

    /**
     * Retention Policy Apply to File
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void applyToFileTest() throws ApiException, InterruptedException {
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), upload, null);
        RetentionPolicy rp1 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(1L));

        AddRetentionPolicyToFileRequest arptfr = new AddRetentionPolicyToFileRequest();
        arptfr.setRetentionPolicyKey(rp1.getKey());
        util.files.addRetentionPolicyToFile(f1.getKey(), arptfr, null);

        RetentionPolicy rp2 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(3L));
        arptfr.setRetentionPolicyKey(rp2.getKey());
        util.files.addRetentionPolicyToFile(f1.getKey(), arptfr, null);

        List<RetentionPolicyApplication> rps = util.files.getRetentionPolicies(f1.getKey(), null);
        Assert.assertTrue(rps.get(0).getAppliedAt().isBefore(rps.get(1).getAppliedAt()));
        Assert.assertEquals(rp1.getKey(), rps.get(0).getRetentionPolicy().getKey());
        Assert.assertTrue(1 == rps.get(0).getRetentionPolicy().getSeconds());
        Assert.assertEquals(rp2.getKey(), rps.get(1).getRetentionPolicy().getKey());
        Assert.assertTrue(3 == rps.get(1).getRetentionPolicy().getSeconds());

        TimeUnit.SECONDS.sleep(4);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Retention Policy Delete with Single Policy
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteSinglePolicyTest() throws ApiException, InterruptedException {
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), upload, null);
        RetentionPolicy rp1 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(2L));

        AddRetentionPolicyToFileRequest arptfr = new AddRetentionPolicyToFileRequest();
        arptfr.setRetentionPolicyKey(rp1.getKey());
        util.files.addRetentionPolicyToFile(f1.getKey(), arptfr, null);

        Assert.assertThrows(ApiException.class, () -> {
            util.files.deleteFile(f1.getKey(), null);
        });

        TimeUnit.SECONDS.sleep(3);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Retention Policy Delete with Multiple Policies
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteFileMultiplePoliciesTest() throws ApiException, InterruptedException {
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), upload, null);
        RetentionPolicy rp1 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(2L));

        AddRetentionPolicyToFileRequest arptfr = new AddRetentionPolicyToFileRequest();
        arptfr.setRetentionPolicyKey(rp1.getKey());
        util.files.addRetentionPolicyToFile(f1.getKey(), arptfr, null);

        RetentionPolicy rp2 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(5L));
        arptfr.setRetentionPolicyKey(rp2.getKey());
        util.files.addRetentionPolicyToFile(f1.getKey(), arptfr, null);

        Assert.assertThrows(ApiException.class, () -> {
            util.files.deleteFile(f1.getKey(), null);
        });

        TimeUnit.SECONDS.sleep(3);

        Assert.assertThrows(ApiException.class, () -> {
            util.files.deleteFile(f1.getKey(), null);
        });

        TimeUnit.SECONDS.sleep(3);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Update Retention Period
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateRetentionPeriodTest() throws ApiException {
        RetentionPolicy rp1 = util.retentionPolicies
                .createRetentionPolicy(util.createRetentionPolicy(util.RETENTION_PERIOD_MONTH));

        UpdateRetentionPolicyRequest urpr = new UpdateRetentionPolicyRequest();
        urpr.setSeconds(util.RETENTION_PERIOD_DAY);

        Assert.assertThrows(ApiException.class, () -> {
            util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);
        });

        urpr.setSeconds(util.RETENTION_PERIOD_MAX);
        util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);
    }

    /**
     * Delete File With Updated Retention Period
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteFileWithUpdatedRetentionPeriodTest() throws ApiException, InterruptedException {
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), upload, null);
        RetentionPolicy rp1 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(3L));

        AddRetentionPolicyToFileRequest arptfr = new AddRetentionPolicyToFileRequest();
        arptfr.setRetentionPolicyKey(rp1.getKey());
        util.files.addRetentionPolicyToFile(f1.getKey(), arptfr, null);

        Assert.assertThrows(ApiException.class, () -> {
            util.files.deleteFile(f1.getKey(), null);
        });

        UpdateRetentionPolicyRequest urpr = new UpdateRetentionPolicyRequest();
        urpr.setSeconds(10L);
        util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);

        TimeUnit.SECONDS.sleep(5);

        Assert.assertThrows(ApiException.class, () -> {
            util.files.deleteFile(f1.getKey(), null);
        });

        TimeUnit.SECONDS.sleep(7);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Get all Retention Policies
     *
     *
     *
     * @throws ApiException if the Api call fails
     *
     */
    @Test
    public void getAllRetentionPoliciesTest() throws ApiException {
        for (int i = 0; i < util.LARGE_ENTITY_COUNT; i++) {
            util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(60L));
        }
        List<RetentionPolicy> rps = util.retentionPolicies.getAllRetentionPolicies();
        Assert.assertEquals(rps.size(), util.LARGE_ENTITY_COUNT);
    }

    /**
     * Create a Group with Metadata
     *
     *
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void createRetentionPolicyWithMetadataTest() throws ApiException, ParseException {
        RetentionPolicy rp1 = util.retentionPolicies
                .createRetentionPolicy(util.createRetentionPolicy(60L, util.getTestMetadata()));
        RetentionPolicy frp = util.retentionPolicies.getRetentionPolicy(rp1.getKey());

        Assert.assertEquals(rp1, frp);
        Assert.assertTrue(util.assertMetadataEqual(frp.getMetadata(), util.getTestMetadata()));
        Assert.assertTrue(util.assertMetadataEqual(rp1.getMetadata(), frp.getMetadata()));
    }

    /**
     * Update a Group with Metadata
     *
     *
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void updateGroupWithMetadataTest() throws ApiException, ParseException {
        RetentionPolicy rp1 = util.retentionPolicies
                .createRetentionPolicy(util.createRetentionPolicy(60L, util.getTestMetadata()));

        UpdateRetentionPolicyRequest urpr = new UpdateRetentionPolicyRequest();
        urpr.setMetadata(util.updatedTestMetadata());
        RetentionPolicy updated = util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);

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
        RetentionPolicy rp1 = util.retentionPolicies
                .createRetentionPolicy(util.createRetentionPolicy(60L, util.getTestMetadata()));

        UpdateRetentionPolicyRequest urpr = new UpdateRetentionPolicyRequest();
        urpr.putMetadataItem("department", null);
        urpr.putMetadataItem("quotaCarrying", null);
        RetentionPolicy updated = util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr);
        Assert.assertTrue(updated.getMetadata().keySet().size() == 3);

        UpdateRetentionPolicyRequest urpr2 = new UpdateRetentionPolicyRequest();
        urpr2.putMetadataItem("targetRate", null);
        urpr2.putMetadataItem("level", null);
        urpr2.putMetadataItem("startDate", null);
        RetentionPolicy updated2 = util.retentionPolicies.updateRetentionPolicy(rp1.getKey(), urpr2);
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
        RetentionPolicy rp1 = util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(60L, m));

        m.put("quotaCarrying", false);
        util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(60L, m));
        util.retentionPolicies.createRetentionPolicy(util.createRetentionPolicy(60L));

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

        FindRetentionPoliciesRequest frpr = new FindRetentionPoliciesRequest();
        frpr.setQuery(q);
        List<RetentionPolicy> response = util.retentionPolicies.findRetentionPolicies(frpr);

        Assert.assertTrue(response.size() == 1);
        Assert.assertEquals(response.get(0), rp1);
    }
}
