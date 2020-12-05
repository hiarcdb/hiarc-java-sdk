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
import com.hiarcdb.client.api.GroupApi;
import com.hiarcdb.client.model.AccessLevel;
import com.hiarcdb.client.model.AddFileToCollectionRequest;
import com.hiarcdb.client.model.AddGroupToCollectionRequest;
import com.hiarcdb.client.model.AddUserToCollectionRequest;
import com.hiarcdb.client.model.Collection;
import com.hiarcdb.client.model.CollectionItems;
import com.hiarcdb.client.model.CreateUserTokenRequest;
import com.hiarcdb.client.model.FindCollectionsRequest;
import com.hiarcdb.client.model.FindGroupsRequest;
import com.hiarcdb.client.model.Group;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.UpdateCollectionRequest;
import com.hiarcdb.client.model.UpdateGroupRequest;
import com.hiarcdb.client.model.User;
import com.hiarcdb.client.model.UserCredentials;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupTest {
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
     * Group CRUD
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void groupCRUDTest() throws ApiException {
        Group g1 = util.groups.createGroup(util.createGroup());
        Group gg = util.groups.getGroup(g1.getKey());
        Assert.assertEquals(g1, gg);

        String newName = "New Name";
        String newDescription = "New description";
        UpdateGroupRequest ugr = new UpdateGroupRequest();
        ugr.setName(newName);
        ugr.setDescription(newDescription);

        Group ug = util.groups.updateGroup(g1.getKey(), ugr);
        Assert.assertEquals(ug.getName(), newName);
        Assert.assertEquals(ug.getDescription(), newDescription);
        Assert.assertTrue(ug.getModifiedAt().isAfter(ug.getCreatedAt()));

        ugr.setKey("new key");
        ugr.setName(newName);
        ugr.setDescription(newDescription);

        Assert.assertThrows(ApiException.class, () -> {
            util.groups.updateGroup(g1.getKey(), ugr);
        });

        util.groups.deleteGroup(g1.getKey());
        Assert.assertThrows(ApiException.class, () -> {
            util.groups.getGroup(g1.getKey());
        });
    }

    /**
     * Get all Groups
     *
     *
     *
     * @throws ApiException if the Api call fails
     * 
     */
    @Test
    public void getAllGroupsTest() throws ApiException {
        for (int i = 0; i < util.LARGE_ENTITY_COUNT; i++) {
            util.groups.createGroup(util.createGroup());
        }
        List<Group> g = util.groups.getAllGroups();
        Assert.assertEquals(g.size(), util.LARGE_ENTITY_COUNT);
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
    public void createGroupWithMetadataTest() throws ApiException, ParseException {
        Group g1 = util.groups.createGroup(util.createGroup(util.getTestMetadata()));
        Group fg = util.groups.getGroup(g1.getKey());

        Assert.assertEquals(g1, fg);
        Assert.assertTrue(util.assertMetadataEqual(fg.getMetadata(), util.getTestMetadata()));
        Assert.assertTrue(util.assertMetadataEqual(g1.getMetadata(), fg.getMetadata()));
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
        Group g1 = util.groups.createGroup(util.createGroup(util.getTestMetadata()));

        UpdateGroupRequest ugr = new UpdateGroupRequest();
        ugr.setMetadata(util.updatedTestMetadata());
        Group updated = util.groups.updateGroup(g1.getKey(), ugr);

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
        Group g1 = util.groups.createGroup(util.createGroup(util.getTestMetadata()));

        UpdateGroupRequest ugr = new UpdateGroupRequest();
        ugr.putMetadataItem("department", null);
        ugr.putMetadataItem("quotaCarrying", null);
        Group updated = util.groups.updateGroup(g1.getKey(), ugr);
        Assert.assertTrue(updated.getMetadata().keySet().size() == 3);

        UpdateGroupRequest ugr2 = new UpdateGroupRequest();
        ugr2.putMetadataItem("targetRate", null);
        ugr2.putMetadataItem("level", null);
        ugr2.putMetadataItem("startDate", null);
        Group updated2 = util.groups.updateGroup(g1.getKey(), ugr2);
        Assert.assertNull(updated2.getMetadata());
    }

    /**
     * Get Groups for User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getGroupsForUserTest() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        Group g1 = util.groups.createGroup(util.createGroup());
        Group g2 = util.groups.createGroup(util.createGroup());
        Group g3 = util.groups.createGroup(util.createGroup());

        util.groups.addUserToGroup(g1.getKey(), u1.getKey());
        util.groups.addUserToGroup(g2.getKey(), u1.getKey());

        List<Group> gs = util.groups.getGroupsForUser(u1.getKey(), null);
        Assert.assertTrue(gs.size() == 2);
        assertThat(gs, hasItem(g1));
        assertThat(gs, hasItem(g2));
        assertThat(gs, not(hasItem(g3)));
    }

    /**
     * Get Groups for the current User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getGroupsForCurrentUserTest() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        Group g1 = util.groups.createGroup(util.createGroup());
        Group g2 = util.groups.createGroup(util.createGroup());
        Group g3 = util.groups.createGroup(util.createGroup());

        util.groups.addUserToGroup(g1.getKey(), u1.getKey());
        util.groups.addUserToGroup(g2.getKey(), u1.getKey());

        CreateUserTokenRequest cutr = new CreateUserTokenRequest();
        cutr.setKey(u1.getKey());
        UserCredentials token = util.tokens.createUserToken(cutr);
        GroupApi lu = new GroupApi(util.createJWTConfig(token.getBearerToken()));

        List<Group> gs1 = lu.getGroupsForCurrentUser(null);
        Assert.assertTrue(gs1.size() == 2);
        assertThat(gs1, hasItem(g1));
        assertThat(gs1, hasItem(g2));
        assertThat(gs1, not(hasItem(g3)));

        GroupApi au = new GroupApi(util.createAdminConfig());
        List<Group> gs2 = au.getGroupsForCurrentUser(u1.getKey());
        Assert.assertTrue(gs2.size() == 2);
        assertThat(gs2, hasItem(g1));
        assertThat(gs2, hasItem(g2));
        assertThat(gs2, not(hasItem(g3)));
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
        Group g1 = util.groups.createGroup(util.createGroup(m));

        m.put("quotaCarrying", false);
        util.groups.createGroup(util.createGroup(m));
        util.groups.createGroup(util.createGroup());

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

        FindGroupsRequest fgr = new FindGroupsRequest();
        fgr.setQuery(q);
        List<Group> response = util.groups.findGroup(fgr);

        Assert.assertTrue(response.size() == 1);
        Assert.assertEquals(response.get(0), g1);
    }
}
