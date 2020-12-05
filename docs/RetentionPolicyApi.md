# RetentionPolicyApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createRetentionPolicy**](RetentionPolicyApi.md#createRetentionPolicy) | **POST** /retentionpolicies | Create a Retention Policy
[**findRetentionPolicies**](RetentionPolicyApi.md#findRetentionPolicies) | **POST** /retentionpolicies/find | Find a Retention Policy
[**getAllRetentionPolicies**](RetentionPolicyApi.md#getAllRetentionPolicies) | **GET** /retentionpolicies | Get all Retention Policies
[**getRetentionPolicy**](RetentionPolicyApi.md#getRetentionPolicy) | **GET** /retentionpolicies/{key} | Get a Retention Policy&#39;s Info
[**updateRetentionPolicy**](RetentionPolicyApi.md#updateRetentionPolicy) | **PUT** /retentionpolicies/{key} | Update a Retention Policy


<a name="createRetentionPolicy"></a>
# **createRetentionPolicy**
> RetentionPolicy createRetentionPolicy(createRetentionPolicyRequest)

Create a Retention Policy

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.RetentionPolicyApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    RetentionPolicyApi apiInstance = new RetentionPolicyApi(defaultClient);
    CreateRetentionPolicyRequest createRetentionPolicyRequest = new CreateRetentionPolicyRequest(); // CreateRetentionPolicyRequest | Retention Policy information
    try {
      RetentionPolicy result = apiInstance.createRetentionPolicy(createRetentionPolicyRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RetentionPolicyApi#createRetentionPolicy");
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
 **createRetentionPolicyRequest** | [**CreateRetentionPolicyRequest**](CreateRetentionPolicyRequest.md)| Retention Policy information |

### Return type

[**RetentionPolicy**](RetentionPolicy.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A Retention Policy object |  -  |

<a name="findRetentionPolicies"></a>
# **findRetentionPolicies**
> List&lt;RetentionPolicy&gt; findRetentionPolicies(findRetentionPoliciesRequest)

Find a Retention Policy

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.RetentionPolicyApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    RetentionPolicyApi apiInstance = new RetentionPolicyApi(defaultClient);
    FindRetentionPoliciesRequest findRetentionPoliciesRequest = new FindRetentionPoliciesRequest(); // FindRetentionPoliciesRequest | Retention Policy query
    try {
      List<RetentionPolicy> result = apiInstance.findRetentionPolicies(findRetentionPoliciesRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RetentionPolicyApi#findRetentionPolicies");
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
 **findRetentionPoliciesRequest** | [**FindRetentionPoliciesRequest**](FindRetentionPoliciesRequest.md)| Retention Policy query |

### Return type

[**List&lt;RetentionPolicy&gt;**](RetentionPolicy.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Retention Policy objects |  -  |

<a name="getAllRetentionPolicies"></a>
# **getAllRetentionPolicies**
> List&lt;RetentionPolicy&gt; getAllRetentionPolicies()

Get all Retention Policies

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.RetentionPolicyApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    RetentionPolicyApi apiInstance = new RetentionPolicyApi(defaultClient);
    try {
      List<RetentionPolicy> result = apiInstance.getAllRetentionPolicies();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RetentionPolicyApi#getAllRetentionPolicies");
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

[**List&lt;RetentionPolicy&gt;**](RetentionPolicy.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Retention Policy objects |  -  |

<a name="getRetentionPolicy"></a>
# **getRetentionPolicy**
> RetentionPolicy getRetentionPolicy(key)

Get a Retention Policy&#39;s Info

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.RetentionPolicyApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    RetentionPolicyApi apiInstance = new RetentionPolicyApi(defaultClient);
    String key = "key_example"; // String | Key of Retention Policy to get info
    try {
      RetentionPolicy result = apiInstance.getRetentionPolicy(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RetentionPolicyApi#getRetentionPolicy");
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
 **key** | **String**| Key of Retention Policy to get info |

### Return type

[**RetentionPolicy**](RetentionPolicy.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Retention Policy object |  -  |

<a name="updateRetentionPolicy"></a>
# **updateRetentionPolicy**
> RetentionPolicy updateRetentionPolicy(key, updateRetentionPolicyRequest)

Update a Retention Policy

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.RetentionPolicyApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    RetentionPolicyApi apiInstance = new RetentionPolicyApi(defaultClient);
    String key = "key_example"; // String | Key of Retention Policy to update
    UpdateRetentionPolicyRequest updateRetentionPolicyRequest = new UpdateRetentionPolicyRequest(); // UpdateRetentionPolicyRequest | RetentionPolicy information
    try {
      RetentionPolicy result = apiInstance.updateRetentionPolicy(key, updateRetentionPolicyRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RetentionPolicyApi#updateRetentionPolicy");
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
 **key** | **String**| Key of Retention Policy to update |
 **updateRetentionPolicyRequest** | [**UpdateRetentionPolicyRequest**](UpdateRetentionPolicyRequest.md)| RetentionPolicy information |

### Return type

[**RetentionPolicy**](RetentionPolicy.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Retention Policy object |  -  |

