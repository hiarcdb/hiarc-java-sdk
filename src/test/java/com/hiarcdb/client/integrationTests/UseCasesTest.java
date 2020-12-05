package com.hiarcdb.client.integrationTests;

import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.api.UserApi;
import com.hiarcdb.client.model.AccessLevel;
import com.hiarcdb.client.model.AddFileToCollectionRequest;
import com.hiarcdb.client.model.AddUserToCollectionRequest;
import com.hiarcdb.client.model.AttachToExistingFileRequest;
import com.hiarcdb.client.model.Collection;
import com.hiarcdb.client.model.CreateUserRequest;
import com.hiarcdb.client.model.CreateUserTokenRequest;
import com.hiarcdb.client.model.FindUsersRequest;
import com.hiarcdb.client.model.Group;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.UpdateUserRequest;
import com.hiarcdb.client.model.User;
import com.hiarcdb.client.model.UserCredentials;

import org.junit.Test;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Ignore;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UseCasesTest {
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
     * Lots of User Test
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    @Ignore
    public void lotsOfUsersTest() throws ApiException {
        Random rand = new Random();
        int numUsers = 20000000;
        for (int i = 0; i < numUsers; i++) {
            User u = util.users.createUser(util.createUser());
            Collection c = util.collections.createCollection(util.createCollection(), null);
            AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
            autcr.setAccessLevel(AccessLevel.READ_WRITE);
            autcr.setUserKey(u.getKey());
            util.collections.addUserToCollection(c.getKey(), autcr, null);

            int numFiles = rand.nextInt(10);
            numFiles++;
            for (int j = 0; j < numFiles; j++) {
                AttachToExistingFileRequest atefr = new AttachToExistingFileRequest();
                atefr.setStorageService(util.AWS_EAST_STORAGE);
                HiarcFile f = util.files.attachToExisitingFile(util.generateKey("file"), atefr, null);
                AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
                aftcr.setFileKey(f.getKey());
                util.collections.addFileToCollection(c.getKey(), aftcr, null);
            }

            if(i % 1000 == 0) {
                System.out.println(i);
            }
        }

    }

}
