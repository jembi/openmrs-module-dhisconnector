/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dhisconnector.api.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "lastUpdated",
    "id",
    "created",
    "name",
    "code",
    "href",
    "skipTotal",
    "dataDimensionType",
    "externalAccess",
    "displayName",
    "access",
    "categoryOptionCombos",
    "categories",
    "userGroupAccesses"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISCategoryCombo {

    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("code")
    private String code;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("name")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("skipTotal")
    private Boolean skipTotal;
    @JsonProperty("dataDimensionType")
    private String dataDimensionType;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("access")
    private DHISAccess access;
    @JsonProperty("categoryOptionCombos")
    private List<DHISCategoryOptionCombo> categoryOptionCombos = new ArrayList<DHISCategoryOptionCombo>();
    @JsonProperty("categories")
    private List<DHISCategory> categories = new ArrayList<DHISCategory>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     *     The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     *     The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     *
     * @param lastUpdated
     *     The lastUpdated
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *     The created
     */
    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The href
     */
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    /**
     * 
     * @param href
     *     The href
     */
    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 
     * @return
     *     The skipTotal
     */
    @JsonProperty("skipTotal")
    public Boolean getSkipTotal() {
        return skipTotal;
    }

    /**
     * 
     * @param skipTotal
     *     The skipTotal
     */
    @JsonProperty("skipTotal")
    public void setSkipTotal(Boolean skipTotal) {
        this.skipTotal = skipTotal;
    }

    /**
     * 
     * @return
     *     The dataDimensionType
     */
    @JsonProperty("dataDimensionType")
    public String getDataDimensionType() {
        return dataDimensionType;
    }

    /**
     * 
     * @param dataDimensionType
     *     The dataDimensionType
     */
    @JsonProperty("dataDimensionType")
    public void setDataDimensionType(String dataDimensionType) {
        this.dataDimensionType = dataDimensionType;
    }

    /**
     * 
     * @return
     *     The externalAccess
     */
    @JsonProperty("externalAccess")
    public Boolean getExternalAccess() {
        return externalAccess;
    }

    /**
     * 
     * @param externalAccess
     *     The externalAccess
     */
    @JsonProperty("externalAccess")
    public void setExternalAccess(Boolean externalAccess) {
        this.externalAccess = externalAccess;
    }

    /**
     * 
     * @return
     *     The displayName
     */
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     *     The displayName
     */
    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 
     * @return
     *     The access
     */
    @JsonProperty("access")
    public DHISAccess getAccess() {
        return access;
    }

    /**
     * 
     * @param access
     *     The access
     */
    @JsonProperty("access")
    public void setAccess(DHISAccess access) {
        this.access = access;
    }

    /**
     * 
     * @return
     *     The categoryOptionCombos
     */
    @JsonProperty("categoryOptionCombos")
    public List<DHISCategoryOptionCombo> getCategoryOptionCombos() {
        return categoryOptionCombos;
    }

    /**
     * 
     * @param categoryOptionCombos
     *     The categoryOptionCombos
     */
    @JsonProperty("categoryOptionCombos")
    public void setCategoryOptionCombos(List<DHISCategoryOptionCombo> categoryOptionCombos) {
        this.categoryOptionCombos = categoryOptionCombos;
    }

    /**
     * 
     * @return
     *     The categories
     */
    @JsonProperty("categories")
    public List<DHISCategory> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<DHISCategory> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public List<Object> getUserGroupAccesses() {
        return userGroupAccesses;
    }

    /**
     * 
     * @param userGroupAccesses
     *     The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public void setUserGroupAccesses(List<Object> userGroupAccesses) {
        this.userGroupAccesses = userGroupAccesses;
    }

}
