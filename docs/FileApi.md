# FileApi

All URIs are relative to *http://localhost:5000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addClassificationToFile**](FileApi.md#addClassificationToFile) | **PUT** /files/{key}/classifications | Add a Classification to a File
[**addGroupToFile**](FileApi.md#addGroupToFile) | **PUT** /files/{key}/groups | Give a group access to a File
[**addRetentionPolicyToFile**](FileApi.md#addRetentionPolicyToFile) | **PUT** /files/{key}/retentionpolicies | Add a Retention Policy to a File
[**addUserToFile**](FileApi.md#addUserToFile) | **PUT** /files/{key}/users | Give a user access to a File
[**addVersion**](FileApi.md#addVersion) | **PUT** /files/{key}/versions | Add a new File Version
[**attachToExisitingFile**](FileApi.md#attachToExisitingFile) | **PUT** /files/{key}/attach | Attach to an existing File
[**copyFile**](FileApi.md#copyFile) | **PUT** /files/{key}/copy | Copy a File
[**createDirectUploadUrl**](FileApi.md#createDirectUploadUrl) | **POST** /files/directuploadurl | Create a direct upload url to a storage service
[**createFile**](FileApi.md#createFile) | **POST** /files | Create a File
[**deleteFile**](FileApi.md#deleteFile) | **DELETE** /files/{key} | Delete a File
[**downloadFile**](FileApi.md#downloadFile) | **GET** /files/{key}/download | Download a File
[**getCollectionsForFile**](FileApi.md#getCollectionsForFile) | **GET** /files/{key}/collections | Get a list of Collections for a File
[**getDirectDownloadUrl**](FileApi.md#getDirectDownloadUrl) | **GET** /files/{key}/directdownloadurl | Get a direct download URL to a File
[**getFile**](FileApi.md#getFile) | **GET** /files/{key} | Get a File&#39;s Info
[**getRetentionPolicies**](FileApi.md#getRetentionPolicies) | **GET** /files/{key}/retentionpolicies | Get a list of Retention Policies on a File
[**getVersions**](FileApi.md#getVersions) | **GET** /files/{key}/versions | Get a list of File Versions
[**updateFile**](FileApi.md#updateFile) | **PUT** /files/{key} | Update a File
[**filterAllowedFiles**](FileApi.md#filterAllowedFiles) | **POST** /files/allowed | Filter a list of File keys that a User can access


<a name="addClassificationToFile"></a>
# **addClassificationToFile**
> Object addClassificationToFile(key, addClassificationToFileRequest, xHiarcUserKey)

Add a Classification to a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file
    AddClassificationToFileRequest addClassificationToFileRequest = new AddClassificationToFileRequest(); // AddClassificationToFileRequest | Classification information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      Object result = apiInstance.addClassificationToFile(key, addClassificationToFileRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#addClassificationToFile");
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
 **key** | **String**| Key of file |
 **addClassificationToFileRequest** | [**AddClassificationToFileRequest**](AddClassificationToFileRequest.md)| Classification information |
 **xHiarcUserKey** | **String**|  | [optional]

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

<a name="addGroupToFile"></a>
# **addGroupToFile**
> Object addGroupToFile(key, addGroupToFileRequest, xHiarcUserKey)

Give a group access to a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file
    AddGroupToFileRequest addGroupToFileRequest = new AddGroupToFileRequest(); // AddGroupToFileRequest | Group information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      Object result = apiInstance.addGroupToFile(key, addGroupToFileRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#addGroupToFile");
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
 **key** | **String**| Key of file |
 **addGroupToFileRequest** | [**AddGroupToFileRequest**](AddGroupToFileRequest.md)| Group information |
 **xHiarcUserKey** | **String**|  | [optional]

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

<a name="addRetentionPolicyToFile"></a>
# **addRetentionPolicyToFile**
> Object addRetentionPolicyToFile(key, addRetentionPolicyToFileRequest, xHiarcUserKey)

Add a Retention Policy to a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file
    AddRetentionPolicyToFileRequest addRetentionPolicyToFileRequest = new AddRetentionPolicyToFileRequest(); // AddRetentionPolicyToFileRequest | Retention Policy information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      Object result = apiInstance.addRetentionPolicyToFile(key, addRetentionPolicyToFileRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#addRetentionPolicyToFile");
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
 **key** | **String**| Key of file |
 **addRetentionPolicyToFileRequest** | [**AddRetentionPolicyToFileRequest**](AddRetentionPolicyToFileRequest.md)| Retention Policy information |
 **xHiarcUserKey** | **String**|  | [optional]

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

<a name="addUserToFile"></a>
# **addUserToFile**
> Object addUserToFile(key, addUserToFileRequest, xHiarcUserKey)

Give a user access to a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file
    AddUserToFileRequest addUserToFileRequest = new AddUserToFileRequest(); // AddUserToFileRequest | User information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      Object result = apiInstance.addUserToFile(key, addUserToFileRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#addUserToFile");
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
 **key** | **String**| Key of file |
 **addUserToFileRequest** | [**AddUserToFileRequest**](AddUserToFileRequest.md)| User information |
 **xHiarcUserKey** | **String**|  | [optional]

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

<a name="addVersion"></a>
# **addVersion**
> java.io.File addVersion(key, xHiarcUserKey, request, file)

Add a new File Version

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to add a version
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    String request = "request_example"; // String | 
    File file = new File("/path/to/file"); // File | 
    try {
      java.io.File result = apiInstance.addVersion(key, xHiarcUserKey, request, file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#addVersion");
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
 **key** | **String**| Key of file to add a version |
 **xHiarcUserKey** | **String**|  | [optional]
 **request** | **String**|  | [optional]
 **file** | **File**|  | [optional]

### Return type

[**java.io.File**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A File object |  -  |

<a name="attachToExisitingFile"></a>
# **attachToExisitingFile**
> java.io.File attachToExisitingFile(key, attachToExistingFileRequest, xHiarcUserKey)

Attach to an existing File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to attach to
    AttachToExistingFileRequest attachToExistingFileRequest = new AttachToExistingFileRequest(); // AttachToExistingFileRequest | File information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      java.io.File result = apiInstance.attachToExisitingFile(key, attachToExistingFileRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#attachToExisitingFile");
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
 **key** | **String**| Key of file to attach to |
 **attachToExistingFileRequest** | [**AttachToExistingFileRequest**](AttachToExistingFileRequest.md)| File information |
 **xHiarcUserKey** | **String**|  | [optional]

### Return type

[**java.io.File**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A File object |  -  |

<a name="copyFile"></a>
# **copyFile**
> java.io.File copyFile(key, copyFileRequest, xHiarcUserKey)

Copy a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to attach to
    CopyFileRequest copyFileRequest = new CopyFileRequest(); // CopyFileRequest | File information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    try {
      java.io.File result = apiInstance.copyFile(key, copyFileRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#copyFile");
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
 **key** | **String**| Key of file to attach to |
 **copyFileRequest** | [**CopyFileRequest**](CopyFileRequest.md)| File information |
 **xHiarcUserKey** | **String**|  | [optional]

### Return type

[**java.io.File**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A File object |  -  |

<a name="createDirectUploadUrl"></a>
# **createDirectUploadUrl**
> FileDirectUpload createDirectUploadUrl(createDirectUploadUrlRequest, xHiarcUserKey, expiresInSeconds)

Create a direct upload url to a storage service

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    FileApi apiInstance = new FileApi(defaultClient);
    CreateDirectUploadUrlRequest createDirectUploadUrlRequest = new CreateDirectUploadUrlRequest(); // CreateDirectUploadUrlRequest | Storage service information
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    Integer expiresInSeconds = 56; // Integer | When access to the url should expire
    try {
      FileDirectUpload result = apiInstance.createDirectUploadUrl(createDirectUploadUrlRequest, xHiarcUserKey, expiresInSeconds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#createDirectUploadUrl");
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
 **createDirectUploadUrlRequest** | [**CreateDirectUploadUrlRequest**](CreateDirectUploadUrlRequest.md)| Storage service information |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]
 **expiresInSeconds** | **Integer**| When access to the url should expire | [optional]

### Return type

[**FileDirectUpload**](FileDirectUpload.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A direct upload object |  -  |

<a name="createFile"></a>
# **createFile**
> java.io.File createFile(xHiarcUserKey, request, file)

Create a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | 
    String request = "request_example"; // String | 
    File file = new File("/path/to/file"); // File | 
    try {
      java.io.File result = apiInstance.createFile(xHiarcUserKey, request, file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#createFile");
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
 **request** | **String**|  | [optional]
 **file** | **File**|  | [optional]

### Return type

[**java.io.File**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | A File object |  -  |

<a name="deleteFile"></a>
# **deleteFile**
> Object deleteFile(key, xHiarcUserKey)

Delete a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to delete
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      Object result = apiInstance.deleteFile(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#deleteFile");
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
 **key** | **String**| Key of file to delete |
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

<a name="downloadFile"></a>
# **downloadFile**
> File downloadFile(key, xHiarcUserKey)

Download a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to download
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      File result = apiInstance.downloadFile(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#downloadFile");
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
 **key** | **String**| Key of file to download |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**File**](File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A binary file |  -  |

<a name="getCollectionsForFile"></a>
# **getCollectionsForFile**
> List&lt;Collection&gt; getCollectionsForFile(key, xHiarcUserKey)

Get a list of Collections for a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to get all collections
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<Collection> result = apiInstance.getCollectionsForFile(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#getCollectionsForFile");
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
 **key** | **String**| Key of file to get all collections |
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
**200** | A list of Collections |  -  |

<a name="getDirectDownloadUrl"></a>
# **getDirectDownloadUrl**
> FileDirectDownload getDirectDownloadUrl(key, xHiarcUserKey, expiresInSeconds)

Get a direct download URL to a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to get download URL
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    Integer expiresInSeconds = 56; // Integer | When access to the url should expire
    try {
      FileDirectDownload result = apiInstance.getDirectDownloadUrl(key, xHiarcUserKey, expiresInSeconds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#getDirectDownloadUrl");
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
 **key** | **String**| Key of file to get download URL |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]
 **expiresInSeconds** | **Integer**| When access to the url should expire | [optional]

### Return type

[**FileDirectDownload**](FileDirectDownload.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A direct download URL object |  -  |

<a name="getFile"></a>
# **getFile**
> java.io.File getFile(key, xHiarcUserKey)

Get a File&#39;s Info

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to get info
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      java.io.File result = apiInstance.getFile(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#getFile");
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
 **key** | **String**| Key of file to get info |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**java.io.File**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A File object |  -  |

<a name="getRetentionPolicies"></a>
# **getRetentionPolicies**
> List&lt;RetentionPolicyApplication&gt; getRetentionPolicies(key, xHiarcUserKey)

Get a list of Retention Policies on a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to get all retention policies
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<RetentionPolicyApplication> result = apiInstance.getRetentionPolicies(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#getRetentionPolicies");
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
 **key** | **String**| Key of file to get all retention policies |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;RetentionPolicyApplication&gt;**](RetentionPolicyApplication.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of Retention Policies |  -  |

<a name="getVersions"></a>
# **getVersions**
> List&lt;FileVersion&gt; getVersions(key, xHiarcUserKey)

Get a list of File Versions

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

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

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to get all versions
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<FileVersion> result = apiInstance.getVersions(key, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#getVersions");
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
 **key** | **String**| Key of file to get all versions |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

[**List&lt;FileVersion&gt;**](FileVersion.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth), [JWTBearerAuth](../README.md#JWTBearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of File Versions |  -  |

<a name="updateFile"></a>
# **updateFile**
> java.io.File updateFile(key, updateFileRequest)

Update a File

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    FileApi apiInstance = new FileApi(defaultClient);
    String key = "key_example"; // String | Key of file to update
    UpdateFileRequest updateFileRequest = new UpdateFileRequest(); // UpdateFileRequest | File information
    try {
      java.io.File result = apiInstance.updateFile(key, updateFileRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#updateFile");
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
 **key** | **String**| Key of file to update |
 **updateFileRequest** | [**UpdateFileRequest**](UpdateFileRequest.md)| File information |

### Return type

[**java.io.File**](java.io.File.md)

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A File object |  -  |

<a name="filterAllowedFiles"></a>
# **filterAllowedFiles**
> List&lt;String&gt; filterAllowedFiles(allowedFilesRequest, xHiarcUserKey)

Filter a list of File keys that a User can access

### Example
```java
// Import classes:
import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.auth.*;
import com.hiarcdb.client.models.*;
import com.hiarcdb.client.api.FileApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:5000");
    
    // Configure API key authorization: AdminApiKeyAuth
    ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
    AdminApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //AdminApiKeyAuth.setApiKeyPrefix("Token");

    FileApi apiInstance = new FileApi(defaultClient);
    AllowedFilesRequest allowedFilesRequest = new AllowedFilesRequest(); // AllowedFilesRequest | File key list
    String xHiarcUserKey = "xHiarcUserKey_example"; // String | Optional key of user to impersonate
    try {
      List<String> result = apiInstance.filterAllowedFiles(allowedFilesRequest, xHiarcUserKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FileApi#filterAllowedFiles");
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
 **allowedFilesRequest** | [**AllowedFilesRequest**](AllowedFilesRequest.md)| File key list |
 **xHiarcUserKey** | **String**| Optional key of user to impersonate | [optional]

### Return type

**List&lt;String&gt;**

### Authorization

[AdminApiKeyAuth](../README.md#AdminApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A list of File keys |  -  |
