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


package com.hiarcdb.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AddVersionToFileRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-12-01T01:09:47.879763-06:00[America/Chicago]")
public class AddVersionToFileRequest {
  public static final String SERIALIZED_NAME_KEY = "key";
  @SerializedName(SERIALIZED_NAME_KEY)
  private String key;

  public static final String SERIALIZED_NAME_STORAGE_SERVICE = "storageService";
  @SerializedName(SERIALIZED_NAME_STORAGE_SERVICE)
  private String storageService;


  public AddVersionToFileRequest key(String key) {
    
    this.key = key;
    return this;
  }

   /**
   * Get key
   * @return key
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getKey() {
    return key;
  }


  public void setKey(String key) {
    this.key = key;
  }


  public AddVersionToFileRequest storageService(String storageService) {
    
    this.storageService = storageService;
    return this;
  }

   /**
   * Get storageService
   * @return storageService
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getStorageService() {
    return storageService;
  }


  public void setStorageService(String storageService) {
    this.storageService = storageService;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddVersionToFileRequest addVersionToFileRequest = (AddVersionToFileRequest) o;
    return Objects.equals(this.key, addVersionToFileRequest.key) &&
        Objects.equals(this.storageService, addVersionToFileRequest.storageService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, storageService);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddVersionToFileRequest {\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    storageService: ").append(toIndentedString(storageService)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

