package org.openmrs.module.reportingresttodhis.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "created",
        "name",
        "href",
        "lastUpdated",
        "shortName",
        "zeroIsSignificant",
        "displayDescription",
        "aggregationType",
        "type",
        "dataDimension",
        "description",
        "externalAccess",
        "publicAccess",
        "aggregationOperator",
        "allItems",
        "url",
        "numberType",
        "domainType",
        "dimension",
        "optionSetValue",
        "displayName",
        "displayShortName",
        "access",
        "categoryCombo",
        "user",
        "aggregationLevels",
        "dataElementGroups",
        "dataSets",
        "attributeValues",
        "items",
        "userGroupAccesses"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISDataElement {

    @JsonProperty("id")
    private String id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("name")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("zeroIsSignificant")
    private Boolean zeroIsSignificant;
    @JsonProperty("displayDescription")
    private String displayDescription;
    @JsonProperty("aggregationType")
    private String aggregationType;
    @JsonProperty("type")
    private String type;
    @JsonProperty("dataDimension")
    private Boolean dataDimension;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalAccess")
    private Boolean externalAccess;
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("aggregationOperator")
    private String aggregationOperator;
    @JsonProperty("allItems")
    private Boolean allItems;
    @JsonProperty("url")
    private String url;
    @JsonProperty("numberType")
    private String numberType;
    @JsonProperty("domainType")
    private String domainType;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("optionSetValue")
    private Boolean optionSetValue;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("displayShortName")
    private String displayShortName;
    @JsonProperty("access")
    private DHISAccess access;
    @JsonProperty("categoryCombo")
    private DHISCategoryCombo categoryCombo;
    @JsonProperty("user")
    private DHISUser user;
    @JsonProperty("aggregationLevels")
    private List<Object> aggregationLevels = new ArrayList<Object>();
    @JsonProperty("dataElementGroups")
    private List<Object> dataElementGroups = new ArrayList<Object>();
    @JsonProperty("dataSets")
    private List<DHISDataSet> dataSets = new ArrayList<DHISDataSet>();
    @JsonProperty("attributeValues")
    private List<Object> attributeValues = new ArrayList<Object>();
    @JsonProperty("items")
    private List<Object> items = new ArrayList<Object>();
    @JsonProperty("userGroupAccesses")
    private List<Object> userGroupAccesses = new ArrayList<Object>();

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The href
     */
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     * The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     * The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     * The shortName
     */
    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     * The shortName
     */
    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     * The zeroIsSignificant
     */
    @JsonProperty("zeroIsSignificant")
    public Boolean getZeroIsSignificant() {
        return zeroIsSignificant;
    }

    /**
     *
     * @param zeroIsSignificant
     * The zeroIsSignificant
     */
    @JsonProperty("zeroIsSignificant")
    public void setZeroIsSignificant(Boolean zeroIsSignificant) {
        this.zeroIsSignificant = zeroIsSignificant;
    }

    /**
     *
     * @return
     * The displayDescription
     */
    @JsonProperty("displayDescription")
    public String getDisplayDescription() {
        return displayDescription;
    }

    /**
     *
     * @param displayDescription
     * The displayDescription
     */
    @JsonProperty("displayDescription")
    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    /**
     *
     * @return
     * The aggregationType
     */
    @JsonProperty("aggregationType")
    public String getAggregationType() {
        return aggregationType;
    }

    /**
     *
     * @param aggregationType
     * The aggregationType
     */
    @JsonProperty("aggregationType")
    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    /**
     *
     * @return
     * The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The dataDimension
     */
    @JsonProperty("dataDimension")
    public Boolean getDataDimension() {
        return dataDimension;
    }

    /**
     *
     * @param dataDimension
     * The dataDimension
     */
    @JsonProperty("dataDimension")
    public void setDataDimension(Boolean dataDimension) {
        this.dataDimension = dataDimension;
    }

    /**
     *
     * @return
     * The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The externalAccess
     */
    @JsonProperty("externalAccess")
    public Boolean getExternalAccess() {
        return externalAccess;
    }

    /**
     *
     * @param externalAccess
     * The externalAccess
     */
    @JsonProperty("externalAccess")
    public void setExternalAccess(Boolean externalAccess) {
        this.externalAccess = externalAccess;
    }

    /**
     *
     * @return
     * The publicAccess
     */
    @JsonProperty("publicAccess")
    public String getPublicAccess() {
        return publicAccess;
    }

    /**
     *
     * @param publicAccess
     * The publicAccess
     */
    @JsonProperty("publicAccess")
    public void setPublicAccess(String publicAccess) {
        this.publicAccess = publicAccess;
    }

    /**
     *
     * @return
     * The aggregationOperator
     */
    @JsonProperty("aggregationOperator")
    public String getAggregationOperator() {
        return aggregationOperator;
    }

    /**
     *
     * @param aggregationOperator
     * The aggregationOperator
     */
    @JsonProperty("aggregationOperator")
    public void setAggregationOperator(String aggregationOperator) {
        this.aggregationOperator = aggregationOperator;
    }

    /**
     *
     * @return
     * The allItems
     */
    @JsonProperty("allItems")
    public Boolean getAllItems() {
        return allItems;
    }

    /**
     *
     * @param allItems
     * The allItems
     */
    @JsonProperty("allItems")
    public void setAllItems(Boolean allItems) {
        this.allItems = allItems;
    }

    /**
     *
     * @return
     * The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The numberType
     */
    @JsonProperty("numberType")
    public String getNumberType() {
        return numberType;
    }

    /**
     *
     * @param numberType
     * The numberType
     */
    @JsonProperty("numberType")
    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    /**
     *
     * @return
     * The domainType
     */
    @JsonProperty("domainType")
    public String getDomainType() {
        return domainType;
    }

    /**
     *
     * @param domainType
     * The domainType
     */
    @JsonProperty("domainType")
    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    /**
     *
     * @return
     * The dimension
     */
    @JsonProperty("dimension")
    public String getDimension() {
        return dimension;
    }

    /**
     *
     * @param dimension
     * The dimension
     */
    @JsonProperty("dimension")
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     *
     * @return
     * The optionSetValue
     */
    @JsonProperty("optionSetValue")
    public Boolean getOptionSetValue() {
        return optionSetValue;
    }

    /**
     *
     * @param optionSetValue
     * The optionSetValue
     */
    @JsonProperty("optionSetValue")
    public void setOptionSetValue(Boolean optionSetValue) {
        this.optionSetValue = optionSetValue;
    }

    /**
     *
     * @return
     * The displayName
     */
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The displayName
     */
    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The displayShortName
     */
    @JsonProperty("displayShortName")
    public String getDisplayShortName() {
        return displayShortName;
    }

    /**
     *
     * @param displayShortName
     * The displayShortName
     */
    @JsonProperty("displayShortName")
    public void setDisplayShortName(String displayShortName) {
        this.displayShortName = displayShortName;
    }

    /**
     *
     * @return
     * The access
     */
    @JsonProperty("access")
    public DHISAccess getAccess() {
        return access;
    }

    /**
     *
     * @param access
     * The access
     */
    @JsonProperty("access")
    public void setAccess(DHISAccess access) {
        this.access = access;
    }

    /**
     *
     * @return
     * The categoryCombo
     */
    @JsonProperty("categoryCombo")
    public DHISCategoryCombo getCategoryCombo() {
        return categoryCombo;
    }

    /**
     *
     * @param categoryCombo
     * The categoryCombo
     */
    @JsonProperty("categoryCombo")
    public void setCategoryCombo(DHISCategoryCombo categoryCombo) {
        this.categoryCombo = categoryCombo;
    }

    /**
     *
     * @return
     * The user
     */
    @JsonProperty("user")
    public DHISUser getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    @JsonProperty("user")
    public void setUser(DHISUser user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The aggregationLevels
     */
    @JsonProperty("aggregationLevels")
    public List<Object> getAggregationLevels() {
        return aggregationLevels;
    }

    /**
     *
     * @param aggregationLevels
     * The aggregationLevels
     */
    @JsonProperty("aggregationLevels")
    public void setAggregationLevels(List<Object> aggregationLevels) {
        this.aggregationLevels = aggregationLevels;
    }

    /**
     *
     * @return
     * The dataElementGroups
     */
    @JsonProperty("dataElementGroups")
    public List<Object> getDataElementGroups() {
        return dataElementGroups;
    }

    /**
     *
     * @param dataElementGroups
     * The dataElementGroups
     */
    @JsonProperty("dataElementGroups")
    public void setDataElementGroups(List<Object> dataElementGroups) {
        this.dataElementGroups = dataElementGroups;
    }

    /**
     *
     * @return
     * The dataSets
     */
    @JsonProperty("dataSets")
    public List<DHISDataSet> getDataSets() {
        return dataSets;
    }

    /**
     *
     * @param dataSets
     * The dataSets
     */
    @JsonProperty("dataSets")
    public void setDataSets(List<DHISDataSet> dataSets) {
        this.dataSets = dataSets;
    }

    /**
     *
     * @return
     * The attributeValues
     */
    @JsonProperty("attributeValues")
    public List<Object> getAttributeValues() {
        return attributeValues;
    }

    /**
     *
     * @param attributeValues
     * The attributeValues
     */
    @JsonProperty("attributeValues")
    public void setAttributeValues(List<Object> attributeValues) {
        this.attributeValues = attributeValues;
    }

    /**
     *
     * @return
     * The items
     */
    @JsonProperty("items")
    public List<Object> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    @JsonProperty("items")
    public void setItems(List<Object> items) {
        this.items = items;
    }

    /**
     *
     * @return
     * The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public List<Object> getUserGroupAccesses() {
        return userGroupAccesses;
    }

    /**
     *
     * @param userGroupAccesses
     * The userGroupAccesses
     */
    @JsonProperty("userGroupAccesses")
    public void setUserGroupAccesses(List<Object> userGroupAccesses) {
        this.userGroupAccesses = userGroupAccesses;
    }

}