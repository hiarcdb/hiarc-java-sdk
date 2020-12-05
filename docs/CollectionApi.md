# CollectionApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addChildToCollection**](CollectionApi.md#addChildToCollection) | **PUT** /collections/{key}/children/{childKey} | Add a child item to a Collection
[**addFileToCollection**](CollectionApi.md#addFileToCollection) | **PUT** /collections/{key}/files | Add a File to a Collection
[**addGroupToCollection**](CollectionApi.md#addGroupToCollection) | **PUT** /collections/{key}/groups | Add a Group to a Collection
[**addUserToCollection**](CollectionApi.md#addUserToCollection) | **PUT** /collections/{key}/users | Add a User to a Collection
[**createCollection**](CollectionApi.md#createCollection) | **POST** /collections | Create a Collection
[**deleteCollection**](CollectionApi.md#deleteCollection) | **DELETE** /collections/{key} | Delete a Collection
[**findCollection**](CollectionApi.md#findCollection) | **POST** /collections/find | Find a Collection
[**getAllCollections**](CollectionApi.md#getAllCollections) | **GET** /collections | Get all Collections
[**getCollection**](CollectionApi.md#getCollection) | **GET** /collections/{key} | Get a Collection&#39;s Info
[**getCollectionChildren**](CollectionApi.md#getCollectionChildren) | **GET** /collections/{key}/children | Get a Collection&#39;s child Collections
[**getCollectionFiles**](CollectionApi.md#getCollectionFiles) | **GET** /collections/{key}/files | Get a Collection&#39;s Files
[**getCollectionItems**](CollectionApi.md#getCollectionItems) | **GET** /collections/{key}/items | Get a Collection&#39;s child items, including Collections and Files
[**removeFileFromCollection**](CollectionApi.md#removeFileFromCollection) | **DELETE** /collections/{key}/files/{fileKey} | Remove a File from a Collection
[**updateCollection**](CollectionApi.md#updateCollection) | **PUT** /collections/{key} | Update a Collection


<a name="addChildToCollection"></a>
# **addChildToCollection**
> Object addChildToCollection(key, childKey, xHiarcUserKey)

Add a child item to a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of Collection
    String childKey = "childKey_example"; // String | Key of item to add as child to Collection
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.addChildToCollection(key, childKey, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#addChildToCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of Collection |
 **childKey** | **String**| Key of item to add as child to Collection |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="addFileToCollection"></a>
# **addFileToCollection**
> Object addFileToCollection(key, addFileToCollectionRequest, xHiarcUserKey)

Add a File to a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of Collection
    AddFileToCollectionRequest addFileToCollectionRequest = new AddFileToCollectionRequest(); // AddFileToCollectionRequest | Add File request
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.addFileToCollection(key, addFileToCollectionRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#addFileToCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of Collection |
 **addFileToCollectionRequest** | [**AddFileToCollectionRequest**](AddFileToCollectionRequest.md)| Add File request |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="addGroupToCollection"></a>
# **addGroupToCollection**
> Object addGroupToCollection(key, addGroupToCollectionRequest, xHiarcUserKey)

Add a Group to a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of Collection
    AddGroupToCollectionRequest addGroupToCollectionRequest = new AddGroupToCollectionRequest(); // AddGroupToCollectionRequest | Add Group request
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.addGroupToCollection(key, addGroupToCollectionRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#addGroupToCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of Collection |
 **addGroupToCollectionRequest** | [**AddGroupToCollectionRequest**](AddGroupToCollectionRequest.md)| Add Group request |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="addUserToCollection"></a>
# **addUserToCollection**
> Object addUserToCollection(key, addUserToCollectionRequest, xHiarcUserKey)

Add a User to a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of Collection
    AddUserToCollectionRequest addUserToCollectionRequest = new AddUserToCollectionRequest(); // AddUserToCollectionRequest | Add User request
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.addUserToCollection(key, addUserToCollectionRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#addUserToCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of Collection |
 **addUserToCollectionRequest** | [**AddUserToCollectionRequest**](AddUserToCollectionRequest.md)| Add User request |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="createCollection"></a>
# **createCollection**
> Collection createCollection(createCollectionRequest, xHiarcUserKey)

Create a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    CreateCollectionRequest createCollectionRequest = new CreateCollectionRequest(); // CreateCollectionRequest | Collection information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Collection result = apiInstance.createCollection(createCollectionRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#createCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **createCollectionRequest** | [**CreateCollectionRequest**](CreateCollectionRequest.md)| Collection information |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**Collection**](Collection.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A Collection object |  -  |

<a name="deleteCollection"></a>
# **deleteCollection**
> Object deleteCollection(key, xHiarcUserKey)

Delete a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of Collection to delete
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.deleteCollection(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#deleteCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of Collection to delete |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="findCollection"></a>
# **findCollection**
> List&lt;Collection&gt; findCollection(findCollectionsRequest, xHiarcUserKey)

Find a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    FindCollectionsRequest findCollectionsRequest = new FindCollectionsRequest(); // FindCollectionsRequest | Collection query
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<Collection> result = apiInstance.findCollection(findCollectionsRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#findCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **findCollectionsRequest** | [**FindCollectionsRequest**](FindCollectionsRequest.md)| Collection query |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;Collection&gt;**](Collection.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Collection objects |  -  |

<a name="getAllCollections"></a>
# **getAllCollections**
> List&lt;Collection&gt; getAllCollections(xHiarcUserKey)

Get all Collections

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<Collection> result = apiInstance.getAllCollections(xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#getAllCollections");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;Collection&gt;**](Collection.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Collection objects |  -  |

<a name="getCollection"></a>
# **getCollection**
> Collection getCollection(key, xHiarcUserKey)

Get a Collection&#39;s Info

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of collection to get info
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Collection result = apiInstance.getCollection(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#getCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of collection to get info |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**Collection**](Collection.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Collection object |  -  |

<a name="getCollectionChildren"></a>
# **getCollectionChildren**
> List&lt;Collection&gt; getCollectionChildren(key, xHiarcUserKey)

Get a Collection&#39;s child Collections

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of collection
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<Collection> result = apiInstance.getCollectionChildren(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#getCollectionChildren");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of collection |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;Collection&gt;**](Collection.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A List of Collection objects |  -  |

<a name="getCollectionFiles"></a>
# **getCollectionFiles**
> List&lt;java.io.File&gt; getCollectionFiles(key, xHiarcUserKey)

Get a Collection&#39;s Files

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of collection
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<java.io.File> result = apiInstance.getCollectionFiles(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#getCollectionFiles");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of collection |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;java.io.File&gt;**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A List of File objects |  -  |

<a name="getCollectionItems"></a>
# **getCollectionItems**
> CollectionItems getCollectionItems(key, xHiarcUserKey)

Get a Collection&#39;s child items, including Collections and Files

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of collection
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      CollectionItems result = apiInstance.getCollectionItems(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#getCollectionItems");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of collection |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**CollectionItems**](CollectionItems.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Collection Item objects |  -  |

<a name="removeFileFromCollection"></a>
# **removeFileFromCollection**
> Object removeFileFromCollection(key, fileKey, xHiarcUserKey)

Remove a File from a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of Collection
    String fileKey = "fileKey_example"; // String | Key of File to remove from Collection
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.removeFileFromCollection(key, fileKey, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#removeFileFromCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of Collection |
 **fileKey** | **String**| Key of File to remove from Collection |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="updateCollection"></a>
# **updateCollection**
> Collection updateCollection(key, updateCollectionRequest, xHiarcUserKey)

Update a Collection

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.CollectionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: JWTBearerAuth
    HttpBearerAuth JWTBearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
    JWTBearerAuth.setBearerToken("BEARER TOKEN");

    CollectionApi apiInstance = new CollectionApi(defaultClient);
    String key = "key_example"; // String | Key of collection to get info
    UpdateCollectionRequest updateCollectionRequest = new UpdateCollectionRequest(); // UpdateCollectionRequest | Collection information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Collection result = apiInstance.updateCollection(key, updateCollectionRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectionApi#updateCollection");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| Key of collection to get info |
 **updateCollectionRequest** | [**UpdateCollectionRequest**](UpdateCollectionRequest.md)| Collection information |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**Collection**](Collection.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Collection object |  -  |

