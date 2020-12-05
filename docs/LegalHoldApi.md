# LegalHoldApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createLegalHold**](LegalHoldApi.md#createLegalHold) | **POST** /legalholds | Create a Legal Hold
[**getLegalHold**](LegalHoldApi.md#getLegalHold) | **GET** /legalholds/{key} | Get a Legal Hold&#39;s Info


<a name="createLegalHold"></a>
# **createLegalHold**
> LegalHold createLegalHold(createLegalHoldRequest)

Create a Legal Hold

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.LegalHoldApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    LegalHoldApi apiInstance = new LegalHoldApi(defaultClient);
    CreateLegalHoldRequest createLegalHoldRequest = new CreateLegalHoldRequest(); // CreateLegalHoldRequest | Legal Hold information
    try {
      LegalHold result = apiInstance.createLegalHold(createLegalHoldRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LegalHoldApi#createLegalHold");
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
 **createLegalHoldRequest** | [**CreateLegalHoldRequest**](CreateLegalHoldRequest.md)| Legal Hold information |

### Return type

[**LegalHold**](LegalHold.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A Legal Hold object |  -  |

<a name="getLegalHold"></a>
# **getLegalHold**
> LegalHold getLegalHold(key)

Get a Legal Hold&#39;s Info

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.LegalHoldApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    LegalHoldApi apiInstance = new LegalHoldApi(defaultClient);
    String key = "key_example"; // String | Key of Legal Hold to get info
    try {
      LegalHold result = apiInstance.getLegalHold(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LegalHoldApi#getLegalHold");
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
 **key** | **String**| Key of Legal Hold to get info |

### Return type

[**LegalHold**](LegalHold.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A Legal Hold object |  -  |

