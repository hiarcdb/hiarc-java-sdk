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
import com.hiarcdb.client.model.Collection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CollectionItems
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-12-01T01:09:47.879763-06:00[America/Chicago]")
public class CollectionItems {
  public static final String SERIALIZED_NAME_CHILD_COLLECTIONS = "childCollections";
  @SerializedName(SERIALIZED_NAME_CHILD_COLLECTIONS)
  private List<Collection> childCollections = null;

  public static final String SERIALIZED_NAME_FILES = "files";
  @SerializedName(SERIALIZED_NAME_FILES)
  private List<HiarcFile> files = null;


  public CollectionItems childCollections(List<Collection> childCollections) {
    
    this.childCollections = childCollections;
    return this;
  }

  public CollectionItems addChildCollectionsItem(Collection childCollectionsItem) {
    if (this.childCollections == null) {
      this.childCollections = new ArrayList<Collection>();
    }
    this.childCollections.add(childCollectionsItem);
    return this;
  }

   /**
   * Get childCollections
   * @return childCollections
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Collection> getChildCollections() {
    return childCollections;
  }


  public void setChildCollections(List<Collection> childCollections) {
    this.childCollections = childCollections;
  }


  public CollectionItems files(List<HiarcFile> files) {
    
    this.files = files;
    return this;
  }

  public CollectionItems addFilesItem(HiarcFile filesItem) {
    if (this.files == null) {
      this.files = new ArrayList<HiarcFile>();
    }
    this.files.add(filesItem);
    return this;
  }

   /**
   * Get files
   * @return files
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<HiarcFile> getFiles() {
    return files;
  }


  public void setFiles(List<HiarcFile> files) {
    this.files = files;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionItems collectionItems = (CollectionItems) o;
    return Objects.equals(this.childCollections, collectionItems.childCollections) &&
        Objects.equals(this.files, collectionItems.files);
  }

  @Override
  public int hashCode() {
    return Objects.hash(childCollections, files);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionItems {\n");
    sb.append("    childCollections: ").append(toIndentedString(childCollections)).append("\n");
    sb.append("    files: ").append(toIndentedString(files)).append("\n");
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

