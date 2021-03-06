/*
 * Hiarc API
 * Welcome to the Hiarc API documentation.
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.hiarcdb.client.api;

import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.model.AddFileToCollectionRequest;
import com.hiarcdb.client.model.AddGroupToCollectionRequest;
import com.hiarcdb.client.model.AddUserToCollectionRequest;
import com.hiarcdb.client.model.Collection;
import com.hiarcdb.client.model.CollectionItems;
import com.hiarcdb.client.model.CreateCollectionRequest;
import com.hiarcdb.client.model.CreateOrUpdateEntityRequest;
import com.hiarcdb.client.model.FindCollectionsRequest;
import com.hiarcdb.client.model.FindEntityRequest;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.UpdateCollectionRequest;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for CollectionApi
 */
@Ignore
public class CollectionApiTest {

    private final CollectionApi api = new CollectionApi();

    
    /**
     * Add a child item to a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addChildToCollectionTest() throws ApiException {
        String key = null;
        String childKey = null;
        String xHiarcUserKey = null;
        Object response = api.addChildToCollection(key, childKey, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Add a File to a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addFileToCollectionTest() throws ApiException {
        String key = null;
        AddFileToCollectionRequest addFileToCollectionRequest = null;
        String xHiarcUserKey = null;
        Object response = api.addFileToCollection(key, addFileToCollectionRequest, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Add a Group to a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addGroupToCollectionTest() throws ApiException {
        String key = null;
        AddGroupToCollectionRequest addGroupToCollectionRequest = null;
        String xHiarcUserKey = null;
        Object response = api.addGroupToCollection(key, addGroupToCollectionRequest, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Add a User to a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addUserToCollectionTest() throws ApiException {
        String key = null;
        AddUserToCollectionRequest addUserToCollectionRequest = null;
        String xHiarcUserKey = null;
        Object response = api.addUserToCollection(key, addUserToCollectionRequest, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Create a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createCollectionTest() throws ApiException {
        CreateCollectionRequest createCollectionRequest = null;
        String xHiarcUserKey = null;
        Collection response = api.createCollection(createCollectionRequest, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Delete a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteCollectionTest() throws ApiException {
        String key = null;
        String xHiarcUserKey = null;
        Object response = api.deleteCollection(key, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Find a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findCollectionTest() throws ApiException {
        FindCollectionsRequest findCollectionsRequest = null;
        String xHiarcUserKey = null;
        List<Collection> response = api.findCollection(findCollectionsRequest, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Get all Collections
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAllCollectionsTest() throws ApiException {
        String xHiarcUserKey = null;
        List<Collection> response = api.getAllCollections(xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Get a Collection&#39;s Info
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCollectionTest() throws ApiException {
        String key = null;
        String xHiarcUserKey = null;
        Collection response = api.getCollection(key, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Get a Collection&#39;s child Collections
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCollectionChildrenTest() throws ApiException {
        String key = null;
        String xHiarcUserKey = null;
        List<Collection> response = api.getCollectionChildren(key, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Get a Collection&#39;s Files
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCollectionFilesTest() throws ApiException {
        String key = null;
        String xHiarcUserKey = null;
        List<HiarcFile> response = api.getCollectionFiles(key, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Get a Collection&#39;s child items, including Collections and Files
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCollectionItemsTest() throws ApiException {
        String key = null;
        String xHiarcUserKey = null;
        CollectionItems response = api.getCollectionItems(key, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Remove a File from a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeFileFromCollectionTest() throws ApiException {
        String key = null;
        String fileKey = null;
        String xHiarcUserKey = null;
        Object response = api.removeFileFromCollection(key, fileKey, xHiarcUserKey);

        // TODO: test validations
    }
    
    /**
     * Update a Collection
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateCollectionTest() throws ApiException {
        String key = null;
        UpdateCollectionRequest updateCollectionRequest = null;
        String xHiarcUserKey = null;
        Collection response = api.updateCollection(key, updateCollectionRequest, xHiarcUserKey);

        // TODO: test validations
    }
    
}
