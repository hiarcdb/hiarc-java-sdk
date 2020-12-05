# ClassificationApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createClassification**](ClassificationApi.md#createClassification) | **POST** /classifications | Create a Classification
[**deleteClassification**](ClassificationApi.md#deleteClassification) | **DELETE** /classifications/{key} | Delete a Classification
[**findClassification**](ClassificationApi.md#findClassification) | **POST** /classifications/find | Find a Classification
[**getAllClassifications**](ClassificationApi.md#getAllClassifications) | **GET** /classifications | Get all Classifications
[**getClassification**](ClassificationApi.md#getClassification) | **GET** /classifications/{key} | Get a Classification&#39;s Info
[**updateClassification**](ClassificationApi.md#updateClassification) | **PUT** /classifications/{key} | Update a Classification


<a name="createClassification"></a>
# **createClassification**
> Classification createClassification(createClassificationRequest, xHiarcUserKey)

Create a Classification

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.ClassificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    ClassificationApi apiInstance = new ClassificationApi(defaultClient);
    CreateClassificationRequest createClassificationRequest = new CreateClassificationRequest(); // CreateClassificationRequest | Classification information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Classification result = apiInstance.createClassification(createClassificationRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClassificationApi#createClassification");
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
 **createClassificationRequest** | [**CreateClassificationRequest**](CreateClassificationRequest.md)| Classification information |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**Classification**](Classification.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A Classification object |  -  |

<a name="deleteClassification"></a>
# **deleteClassification**
> Object deleteClassification(key, xHiarcUserKey)

Delete a Classification

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.ClassificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    ClassificationApi apiInstance = new ClassificationApi(defaultClient);
    String key = "key_example"; // String | Key of Classification to delete
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.deleteClassification(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClassificationApi#deleteClassification");
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
 **key** | **String**| Key of Classification to delete |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

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

<a name="findClassification"></a>
# **findClassification**
> List&lt;Classification&gt; findClassification(findClassificationsRequest, xHiarcUserKey)

Find a Classification

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.ClassificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    ClassificationApi apiInstance = new ClassificationApi(defaultClient);
    FindClassificationsRequest findClassificationsRequest = new FindClassificationsRequest(); // FindClassificationsRequest | Classification query
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<Classification> result = apiInstance.findClassification(findClassificationsRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClassificationApi#findClassification");
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
 **findClassificationsRequest** | [**FindClassificationsRequest**](FindClassificationsRequest.md)| Classification query |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;Classification&gt;**](Classification.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Classification objects |  -  |

<a name="getAllClassifications"></a>
# **getAllClassifications**
> List&lt;Classification&gt; getAllClassifications(xHiarcUserKey)

Get all Classifications

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.ClassificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    ClassificationApi apiInstance = new ClassificationApi(defaultClient);
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<Classification> result = apiInstance.getAllClassifications(xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClassificationApi#getAllClassifications");
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

[**List&lt;Classification&gt;**](Classification.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Classification objects |  -  |

<a name="getClassification"></a>
# **getClassification**
> Classification getClassification(key, xHiarcUserKey)

Get a Classification&#39;s Info

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.ClassificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    ClassificationApi apiInstance = new ClassificationApi(defaultClient);
    String key = "key_example"; // String | Key of Classification to get info
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Classification result = apiInstance.getClassification(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClassificationApi#getClassification");
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
 **key** | **String**| Key of Classification to get info |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**Classification**](Classification.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Classification object |  -  |

<a name="updateClassification"></a>
# **updateClassification**
> Classification updateClassification(key, updateClassificationRequest, xHiarcUserKey)

Update a Classification

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.ClassificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    ClassificationApi apiInstance = new ClassificationApi(defaultClient);
    String key = "key_example"; // String | Key of Classification to get info
    UpdateClassificationRequest updateClassificationRequest = new UpdateClassificationRequest(); // UpdateClassificationRequest | Classification information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Classification result = apiInstance.updateClassification(key, updateClassificationRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClassificationApi#updateClassification");
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
 **key** | **String**| Key of Classification to get info |
 **updateClassificationRequest** | [**UpdateClassificationRequest**](UpdateClassificationRequest.md)| Classification information |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**Classification**](Classification.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Classification object |  -  |

