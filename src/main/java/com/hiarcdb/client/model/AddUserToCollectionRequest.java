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
import com.hiarcdb.client.model.AccessLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AddUserToCollectionRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-12-01T01:09:47.879763-06:00[America/Chicago]")
public class AddUserToCollectionRequest {
  public static final String SERIALIZED_NAME_USER_KEY = "userKey";
  @SerializedName(SERIALIZED_NAME_USER_KEY)
  private String userKey;

  public static final String SERIALIZED_NAME_ACCESS_LEVEL = "accessLevel";
  @SerializedName(SERIALIZED_NAME_ACCESS_LEVEL)
  private AccessLevel accessLevel;


  public AddUserToCollectionRequest userKey(String userKey) {
    
    this.userKey = userKey;
    return this;
  }

   /**
   * Get userKey
   * @return userKey
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUserKey() {
    return userKey;
  }


  public void setUserKey(String userKey) {
    this.userKey = userKey;
  }


  public AddUserToCollectionRequest accessLevel(AccessLevel accessLevel) {
    
    this.accessLevel = accessLevel;
    return this;
  }

   /**
   * Get accessLevel
   * @return accessLevel
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public AccessLevel getAccessLevel() {
    return accessLevel;
  }


  public void setAccessLevel(AccessLevel accessLevel) {
    this.accessLevel = accessLevel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddUserToCollectionRequest addUserToCollectionRequest = (AddUserToCollectionRequest) o;
    return Objects.equals(this.userKey, addUserToCollectionRequest.userKey) &&
        Objects.equals(this.accessLevel, addUserToCollectionRequest.accessLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userKey, accessLevel);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddUserToCollectionRequest {\n");
    sb.append("    userKey: ").append(toIndentedString(userKey)).append("\n");
    sb.append("    accessLevel: ").append(toIndentedString(accessLevel)).append("\n");
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

