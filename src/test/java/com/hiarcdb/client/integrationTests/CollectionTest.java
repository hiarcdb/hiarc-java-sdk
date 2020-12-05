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
import com.hiarcdb.client.model.Collection;
import com.hiarcdb.client.model.CollectionItems;
import com.hiarcdb.client.model.CreateUserTokenRequest;
import com.hiarcdb.client.model.FindCollectionsRequest;
import com.hiarcdb.client.model.Group;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.UpdateCollectionRequest;
import com.hiarcdb.client.model.User;
import com.hiarcdb.client.model.UserCredentials;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollectionTest {
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
     * Collection CRUD
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void collectionCRUDTest() throws ApiException {
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection gc = util.collections.getCollection(c1.getKey(), null);
        Assert.assertEquals(c1, gc);

        String newName = "New Name";
        String newDescription = "New description";
        UpdateCollectionRequest ucr = new UpdateCollectionRequest();
        ucr.setName(newName);
        ucr.setDescription(newDescription);

        Collection uc = util.collections.updateCollection(c1.getKey(), ucr, null);
        Assert.assertEquals(uc.getName(), newName);
        Assert.assertEquals(uc.getDescription(), newDescription);
        Assert.assertTrue(uc.getModifiedAt().isAfter(uc.getCreatedAt()));

        Assert.assertThrows(ApiException.class, () -> {
            ucr.setKey("new key");
            ucr.setName(newName);
            ucr.setDescription(newDescription);

            util.collections.updateCollection(c1.getKey(), ucr, null);
        });

        util.collections.deleteCollection(c1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollection(c1.getKey(), null);
        });
    }

    /**
     * Create Access and Update Collection As User
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createAccessAndUpdateCollectionAsUserTest() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        Collection c1 = util.collections.createCollection(util.createCollection(), u1.getKey());
        Collection gc = util.collections.getCollection(c1.getKey(), u1.getKey());
        Assert.assertEquals(c1, gc);

        User u2 = util.users.createUser(util.createUser());
        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u2.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        Collection gc2 = util.collections.getCollection(c1.getKey(), u2.getKey());
        Assert.assertEquals(c1, gc2);

        User u3 = util.users.createUser(util.createUser());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollection(c1.getKey(), u3.getKey());
        });

        Collection gc3 = util.collections.getCollection(c1.getKey(), null);
        Assert.assertEquals(c1, gc3);

        String newName = "New Name";
        String newDescription = "New description";
        UpdateCollectionRequest ucr = new UpdateCollectionRequest();
        ucr.setName(newName);
        ucr.setDescription(newDescription);

        Collection uc = util.collections.updateCollection(c1.getKey(), ucr, u1.getKey());
        Assert.assertEquals(uc.getName(), newName);
        Assert.assertEquals(uc.getDescription(), newDescription);
        Assert.assertTrue(uc.getModifiedAt().isAfter(uc.getCreatedAt()));

        newName = "New Name 2";
        newDescription = "New description 2";
        ucr.setName(newName);
        ucr.setDescription(newDescription);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.updateCollection(c1.getKey(), ucr, u2.getKey());
        });

        AddUserToCollectionRequest autcr3 = new AddUserToCollectionRequest();
        autcr3.setAccessLevel(AccessLevel.READ_WRITE);
        autcr3.setUserKey(u3.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr3, null);

        newName = "New Name 3";
        newDescription = "New description 3";
        ucr.setName(newName);
        ucr.setDescription(newDescription);
        Collection uc3 = util.collections.updateCollection(c1.getKey(), ucr, u3.getKey());
        Assert.assertEquals(uc3.getName(), newName);
        Assert.assertEquals(uc3.getDescription(), newDescription);
        Assert.assertTrue(uc3.getModifiedAt().isAfter(uc3.getCreatedAt()));

        User u4 = util.users.createUser(util.createUser());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.updateCollection(c1.getKey(), ucr, u4.getKey());
        });

        newName = "New Name 4";
        newDescription = "New description 4";
        ucr.setName(newName);
        ucr.setDescription(newDescription);
        Collection uc4 = util.collections.updateCollection(c1.getKey(), ucr, null);
        Assert.assertEquals(uc4.getName(), newName);
        Assert.assertEquals(uc4.getDescription(), newDescription);
        Assert.assertTrue(uc4.getModifiedAt().isAfter(uc4.getCreatedAt()));
    }

    /**
     * Hierarchy Test
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void hierarchyTest() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);
        Collection c4 = util.collections.createCollection(util.createCollection(), null);
        Collection c5 = util.collections.createCollection(util.createCollection(), null);
        Collection c6 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);
        util.collections.addChildToCollection(c3.getKey(), c4.getKey(), null);
        util.collections.addChildToCollection(c4.getKey(), c5.getKey(), null);
        util.collections.addChildToCollection(c6.getKey(), c4.getKey(), null);

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c2.getKey(), autcr, null);

        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollection(c1.getKey(), u1.getKey());
        });

        Collection fc = util.collections.getCollection(c2.getKey(), u1.getKey());
        Assert.assertEquals(c2, fc);

        fc = util.collections.getCollection(c3.getKey(), u1.getKey());
        Assert.assertEquals(c3, fc);

        fc = util.collections.getCollection(c4.getKey(), u1.getKey());
        Assert.assertEquals(c4, fc);

        fc = util.collections.getCollection(c5.getKey(), u1.getKey());
        Assert.assertEquals(c5, fc);

        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollection(c6.getKey(), u1.getKey());
        });
    }

    /**
     * Prevent Cycle Test
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void preventCycleTest() throws ApiException {
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);
        Collection c4 = util.collections.createCollection(util.createCollection(), null);
        Collection c5 = util.collections.createCollection(util.createCollection(), null);
        Collection c6 = util.collections.createCollection(util.createCollection(), null);
        Collection c7 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);
        util.collections.addChildToCollection(c3.getKey(), c4.getKey(), null);
        util.collections.addChildToCollection(c4.getKey(), c5.getKey(), null);
        util.collections.addChildToCollection(c6.getKey(), c4.getKey(), null);
        util.collections.addChildToCollection(c6.getKey(), c7.getKey(), null);

        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addChildToCollection(c5.getKey(), c1.getKey(), null);
        });
    }

    /**
     * Get Child Collections
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getChildCollections() throws ApiException {
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);
        Collection c4 = util.collections.createCollection(util.createCollection(), null);
        Collection c5 = util.collections.createCollection(util.createCollection(), null);
        Collection c6 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c1.getKey(), c3.getKey(), null);
        util.collections.addChildToCollection(c1.getKey(), c4.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c5.getKey(), null);
        util.collections.addChildToCollection(c4.getKey(), c6.getKey(), null);

        List<Collection> collections = util.collections.getCollectionChildren(c1.getKey(), null);
        Assert.assertEquals(collections.size(), 3);
        assertThat(collections, hasItem(c2));
        assertThat(collections, hasItem(c3));
        assertThat(collections, hasItem(c4));

        assertThat(collections, not(hasItem(c5)));
        assertThat(collections, not(hasItem(c6)));
    }

    /**
     * Get Items on Collections
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getItemCollections() throws ApiException {
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);
        Collection c4 = util.collections.createCollection(util.createCollection(), null);
        Collection c5 = util.collections.createCollection(util.createCollection(), null);
        Collection c6 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c1.getKey(), c3.getKey(), null);
        util.collections.addChildToCollection(c1.getKey(), c4.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c5.getKey(), null);
        util.collections.addChildToCollection(c4.getKey(), c6.getKey(), null);

        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f2 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f3 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f4 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f5 = util.files.createFile(util.createFile(), f, null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();

        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        aftcr.setFileKey(f2.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        aftcr.setFileKey(f3.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        aftcr.setFileKey(f4.getKey());
        util.collections.addFileToCollection(c2.getKey(), aftcr, null);

        aftcr.setFileKey(f5.getKey());
        util.collections.addFileToCollection(c3.getKey(), aftcr, null);

        CollectionItems collectionItems = util.collections.getCollectionItems(c1.getKey(), null);

        List<Collection> collections = collectionItems.getChildCollections();
        Assert.assertEquals(collections.size(), 3);
        assertThat(collections, hasItem(c2));
        assertThat(collections, hasItem(c3));
        assertThat(collections, hasItem(c4));

        assertThat(collections, not(hasItem(c5)));
        assertThat(collections, not(hasItem(c6)));

        List<HiarcFile> files = collectionItems.getFiles();
        List<String> fileKeys = files.stream().map(HiarcFile::getKey).collect(Collectors.toList());
        assertThat(fileKeys, hasItem(f1.getKey()));
        assertThat(fileKeys, hasItem(f2.getKey()));
        assertThat(fileKeys, hasItem(f3.getKey()));
        assertThat(fileKeys, not(hasItem(f4.getKey())));
        assertThat(fileKeys, not(hasItem(f5.getKey())));

        for (String k : Arrays.asList(f1.getKey(), f2.getKey(), f3.getKey(), f4.getKey(), f5.getKey())) {
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Add Child Collection As User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addChildCollectionAsUser() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addChildToCollection(c1.getKey(), c2.getKey(), u1.getKey());
        });

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addChildToCollection(c1.getKey(), c2.getKey(), u1.getKey());
        });
        User u2 = util.users.createUser(util.createUser());
        autcr.setAccessLevel(AccessLevel.READ_WRITE);
        autcr.setUserKey(u2.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);
        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), u2.getKey());
    }

    /**
     * Delete with File
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteWithFileTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        Collection c1 = util.collections.createCollection(util.createCollection(), null);

        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        util.collections.deleteCollection(c1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollection(c1.getKey(), null);
        });

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Delete with File as User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteWithFileAsUserTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        User u1 = util.users.createUser(util.createUser());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.deleteCollection(c1.getKey(), u1.getKey());
        });

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.deleteCollection(c1.getKey(), u1.getKey());
        });

        User u2 = util.users.createUser(util.createUser());
        autcr.setAccessLevel(AccessLevel.READ_WRITE);
        autcr.setUserKey(u2.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        util.collections.deleteCollection(c1.getKey(), u2.getKey());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollection(c1.getKey(), null);
        });

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Add Remove Files
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addRemoveFilesTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f2 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f3 = util.files.createFile(util.createFile(), f, null);

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);
        aftcr.setFileKey(f2.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);
        aftcr.setFileKey(f3.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        List<HiarcFile> files = util.collections.getCollectionFiles(c1.getKey(), null);
        Assert.assertEquals(files.size(), 3);

        util.collections.removeFileFromCollection(c1.getKey(), f3.getKey(), null);
        files = util.collections.getCollectionFiles(c1.getKey(), null);
        Assert.assertEquals(files.size(), 2);

        for (String k : Arrays.asList(f1.getKey(), f2.getKey(), f3.getKey())) {
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Add Remove Files as User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addRemoveFilesAsUserTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addFileToCollection(c1.getKey(), aftcr, u1.getKey());
        });

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addFileToCollection(c1.getKey(), aftcr, u1.getKey());
        });

        User u2 = util.users.createUser(util.createUser());
        autcr.setAccessLevel(AccessLevel.READ_WRITE);
        autcr.setUserKey(u2.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);
        util.collections.addFileToCollection(c1.getKey(), aftcr, u2.getKey());

        List<HiarcFile> files = util.collections.getCollectionFiles(c1.getKey(), null);
        Assert.assertEquals(files.size(), 1);

        User u3 = util.users.createUser(util.createUser());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.removeFileFromCollection(c1.getKey(), f1.getKey(), u3.getKey());
        });
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.removeFileFromCollection(c1.getKey(), f1.getKey(), u1.getKey());
        });

        util.collections.removeFileFromCollection(c1.getKey(), f1.getKey(), u2.getKey());
        files = util.collections.getCollectionFiles(c1.getKey(), null);
        Assert.assertEquals(files.size(), 0);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Get Files as User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getFilesForUserTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f2 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f3 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);
        aftcr.setFileKey(f2.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);
        aftcr.setFileKey(f3.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getCollectionFiles(c1.getKey(), u1.getKey());
        });

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        List<HiarcFile> files = util.collections.getCollectionFiles(c1.getKey(), u1.getKey());
        Assert.assertEquals(files.size(), 3);

        CreateUserTokenRequest cutr = new CreateUserTokenRequest();
        cutr.setKey(u1.getKey());
        UserCredentials token = util.tokens.createUserToken(cutr);
        CollectionApi lc = new CollectionApi(util.createJWTConfig(token.getBearerToken()));

        List<HiarcFile> ufiles = lc.getCollectionFiles(c1.getKey(), null);
        Assert.assertEquals(ufiles.size(), 3);

        for (String k : Arrays.asList(f1.getKey(), f2.getKey(), f3.getKey())) {
            util.files = new FileApi(util.createAdminConfig());
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Add User to Collection as User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addUserAsUserTest() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        User u2 = util.users.createUser(util.createUser());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u2.getKey());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addUserToCollection(c1.getKey(), autcr, u1.getKey());
        });
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u2.getKey());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addUserToCollection(c1.getKey(), autcr, u1.getKey());
        });

        User u3 = util.users.createUser(util.createUser());
        autcr.setAccessLevel(AccessLevel.READ_WRITE);
        autcr.setUserKey(u3.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u2.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, u3.getKey());
    }

    /**
     * Add Group to Collection as User
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addGroupAsUserTest() throws ApiException {
        User u1 = util.users.createUser(util.createUser());
        Group g1 = util.groups.createGroup(util.createGroup());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);

        AddGroupToCollectionRequest agtcr = new AddGroupToCollectionRequest();
        agtcr.setAccessLevel(AccessLevel.READ_ONLY);
        agtcr.setGroupKey(g1.getKey());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addGroupToCollection(c1.getKey(), agtcr, u1.getKey());
        });

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        Assert.assertThrows(ApiException.class, () -> {
            util.collections.addGroupToCollection(c1.getKey(), agtcr, u1.getKey());
        });

        User u3 = util.users.createUser(util.createUser());
        autcr.setAccessLevel(AccessLevel.READ_WRITE);
        autcr.setUserKey(u3.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        util.collections.addGroupToCollection(c1.getKey(), agtcr, u3.getKey());
    }

    /**
     * Get All Collections
     *
     *
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAllCollectionsTest() throws ApiException {
        for (int i = 0; i < util.LARGE_ENTITY_COUNT; i++) {
            util.collections.createCollection(util.createCollection(), null);
        }
        List<Collection> cs = util.collections.getAllCollections(null);
        Assert.assertEquals(cs.size(), util.LARGE_ENTITY_COUNT);

        User u1 = util.users.createUser(util.createUser());
        Assert.assertThrows(ApiException.class, () -> {
            util.collections.getAllCollections(u1.getKey());
        });

        CreateUserTokenRequest cutr = new CreateUserTokenRequest();
        cutr.setKey(u1.getKey());
        UserCredentials token = util.tokens.createUserToken(cutr);
        CollectionApi lc = new CollectionApi(util.createJWTConfig(token.getBearerToken()));
        Assert.assertThrows(ApiException.class, () -> {
            lc.getAllCollections(null);
        });
    }

    /**
     * Create a Collection with Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void createCollectionWithMetadataTest() throws ApiException, ParseException {
        Collection c1 = util.collections.createCollection(util.createCollection(util.getTestMetadata()), null);
        Collection fc = util.collections.getCollection(c1.getKey(), null);

        Assert.assertEquals(c1, fc);
        Assert.assertTrue(util.assertMetadataEqual(fc.getMetadata(), util.getTestMetadata()));
        Assert.assertTrue(util.assertMetadataEqual(c1.getMetadata(), fc.getMetadata()));
    }

    /**
     * Update a Collection with Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void updateCollectionWithMetadataTest() throws ApiException, ParseException {
        Collection c1 = util.collections.createCollection(util.createCollection(util.getTestMetadata()), null);

        UpdateCollectionRequest ucr = new UpdateCollectionRequest();
        ucr.setMetadata(util.updatedTestMetadata());
        Collection updated = util.collections.updateCollection(c1.getKey(), ucr, null);

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
        Collection c1 = util.collections.createCollection(util.createCollection(util.getTestMetadata()), null);

        UpdateCollectionRequest ucr = new UpdateCollectionRequest();
        ucr.putMetadataItem("department", null);
        ucr.putMetadataItem("quotaCarrying", null);
        Collection updated = util.collections.updateCollection(c1.getKey(), ucr, null);
        Assert.assertTrue(updated.getMetadata().keySet().size() == 3);

        UpdateCollectionRequest ucr2 = new UpdateCollectionRequest();
        ucr2.putMetadataItem("targetRate", null);
        ucr2.putMetadataItem("level", null);
        ucr2.putMetadataItem("startDate", null);
        Collection updated2 = util.collections.updateCollection(c1.getKey(), ucr2, null);
        Assert.assertNull(updated2.getMetadata());
    }

    /**
     * Find a Collection
     *
     *
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void findCollectionTest() throws ApiException, ParseException {
        Map<String, Object> m = new HashMap<>();
        m = util.getTestMetadata();
        Collection c1 = util.collections.createCollection(util.createCollection(m), null);
        
        m.put("quotaCarrying", false);
        util.collections.createCollection(util.createCollection(m), null);
        util.collections.createCollection(util.createCollection(), null);

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

        List<Map<String, Object>> q = new ArrayList<Map<String, Object>>(Arrays.asList(
            prop, b, paren, prop2, b, prop3, closeParen
        ));
        
        FindCollectionsRequest fcr = new FindCollectionsRequest();
        fcr.setQuery(q);
        List<Collection> response = util.collections.findCollection(fcr, null);

        Assert.assertTrue(response.size() == 1);
        Assert.assertEquals(response.get(0), c1);
    }
}
