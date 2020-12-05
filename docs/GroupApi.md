# GroupApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addUserToGroup**](GroupApi.md#addUserToGroup) | **PUT** /groups/{key}/users/{userKey} | Add a User to a Group
[**createGroup**](GroupApi.md#createGroup) | **POST** /groups | Create a Group
[**deleteGroup**](GroupApi.md#deleteGroup) | **DELETE** /groups/{key} | Delete a Group
[**findGroup**](GroupApi.md#findGroup) | **POST** /groups/find | Find a Group
[**getAllGroups**](GroupApi.md#getAllGroups) | **GET** /groups | Get all Groups
[**getGroup**](GroupApi.md#getGroup) | **GET** /groups/{key} | Get a Group&#39;s Info
[**getGroupsForCurrentUser**](GroupApi.md#getGroupsForCurrentUser) | **GET** /users/current/groups | Get the Groups for the current User
[**updateGroup**](GroupApi.md#updateGroup) | **PUT** /groups/{key} | Update a Group
[**getGroupsForUser**](GroupsApi.md#getGroupsForUser) | **GET** /users/{key}/groups | Get Groups for a User


<a name="addUserToGroup"></a>
# **addUserToGroup**
> Object addUserToGroup(key, userKey)

Add a User to a Group

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    String key = "key_example"; // String | Key of Group
    String userKey = "userKey_example"; // String | Key of User to add to Group
    try {
      Object result = apiInstance.addUserToGroup(key, userKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#addUserToGroup");
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
 **key** | **String**| Key of Group |
 **userKey** | **String**| Key of User to add to Group |

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="createGroup"></a>
# **createGroup**
> Group createGroup(createGroupRequest)

Create a Group

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    CreateGroupRequest createGroupRequest = new CreateGroupRequest(); // CreateGroupRequest | Group information
    try {
      Group result = apiInstance.createGroup(createGroupRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#createGroup");
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
 **createGroupRequest** | [**CreateGroupRequest**](CreateGroupRequest.md)| Group information |

### Return type

[**Group**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A Group object |  -  |

<a name="deleteGroup"></a>
# **deleteGroup**
> Object deleteGroup(key)

Delete a Group

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    String key = "key_example"; // String | Key of Group to delete
    try {
      Object result = apiInstance.deleteGroup(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#deleteGroup");
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
 **key** | **String**| Key of Group to delete |

### Return type

**Object**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Empty response |  -  |

<a name="findGroup"></a>
# **findGroup**
> List&lt;Group&gt; findGroup(findGroupsRequest)

Find a Group

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    FindGroupsRequest findGroupsRequest = new FindGroupsRequest(); // FindGroupsRequest | Group query
    try {
      List<Group> result = apiInstance.findGroup(findGroupsRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#findGroup");
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
 **findGroupsRequest** | [**FindGroupsRequest**](FindGroupsRequest.md)| Group query |

### Return type

[**List&lt;Group&gt;**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Group objects |  -  |

<a name="getAllGroups"></a>
# **getAllGroups**
> List&lt;Group&gt; getAllGroups()

Get all Groups

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    try {
      List<Group> result = apiInstance.getAllGroups();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#getAllGroups");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Group&gt;**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Group objects |  -  |

<a name="getGroup"></a>
# **getGroup**
> Group getGroup(key)

Get a Group&#39;s Info

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    String key = "key_example"; // String | Key of Group to get info
    try {
      Group result = apiInstance.getGroup(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#getGroup");
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
 **key** | **String**| Key of Group to get info |

### Return type

[**Group**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Group object |  -  |

<a name="getGroupsForCurrentUser"></a>
# **getGroupsForCurrentUser**
> List&lt;Group&gt; getGroupsForCurrentUser(xHiarcUserKey)

Get the Groups for the current User

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

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

    GroupApi apiInstance = new GroupApi(defaultClient);
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      List<Group> result = apiInstance.getGroupsForCurrentUser(xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#getGroupsForCurrentUser");
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
 **xHiarcUserKey** | **String**|  | [optional]

### Return type

[**List&lt;Group&gt;**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Group object |  -  |

<a name="updateGroup"></a>
# **updateGroup**
> Group updateGroup(key, updateGroupRequest)

Update a Group

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupApi apiInstance = new GroupApi(defaultClient);
    String key = "key_example"; // String | Key of Group to update
    UpdateGroupRequest updateGroupRequest = new UpdateGroupRequest(); // UpdateGroupRequest | Group information
    try {
      Group result = apiInstance.updateGroup(key, updateGroupRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupApi#updateGroup");
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
 **key** | **String**| Key of Group to update |
 **updateGroupRequest** | [**UpdateGroupRequest**](UpdateGroupRequest.md)| Group information |

### Return type

[**Group**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Group object |  -  |

<a name="getGroupsForUser"></a>
# **getGroupsForUser**
> List&lt;Group&gt; getGroupsForUser(key, xHiarcUserKey)

Get Groups for a User

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.GroupsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    GroupsApi apiInstance = new GroupsApi(defaultClient);
    String key = "key_example"; // String | Key of user
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      List<Group> result = apiInstance.getGroupsForUser(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling GroupsApi#getGroupsForUser");
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
 **key** | **String**| Key of user |
 **xHiarcUserKey** | **String**|  | [optional]

### Return type

[**List&lt;Group&gt;**](Group.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Group objects |  -  |