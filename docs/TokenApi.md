# TokenApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createUserToken**](TokenApi.md#createUserToken) | **POST** /tokens/user | Create a Token for a User


<a name="createUserToken"></a>
# **createUserToken**
> UserCredentials createUserToken(createUserTokenRequest)

Create a Token for a User

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.TokenApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    TokenApi apiInstance = new TokenApi(defaultClient);
    CreateUserTokenRequest createUserTokenRequest = new CreateUserTokenRequest(); // CreateUserTokenRequest | User information
    try {
      UserCredentials result = apiInstance.createUserToken(createUserTokenRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TokenApi#createUserToken");
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
 **createUserTokenRequest** | [**CreateUserTokenRequest**](CreateUserTokenRequest.md)| User information |

### Return type

[**UserCredentials**](UserCredentials.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A User Token object |  -  |

