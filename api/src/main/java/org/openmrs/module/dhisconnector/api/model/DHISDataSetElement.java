package org.openmrs.module.dhisconnector.api.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "id", "dataElement", "dataSet", "categoryCombo" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISDataSetElement {

	@JsonProperty("id")
	private String id;

	@JsonProperty("dataElement")
	private DHISDataElement dataElement;
	
	@JsonProperty("dataSet")
	private DHISDataSet dataSet;

	@JsonProperty("categoryCombo")
	private DHISCategoryCombo categoryCombo;
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("categoryCombo")
	public DHISCategoryCombo getCategoryCombo() {
		return categoryCombo;
	}
	
	@JsonProperty("categoryCombo")
	public void setCategoryCombo(DHISCategoryCombo created) {
		this.categoryCombo = created;
	}

	@JsonProperty("dataElement")
	public DHISDataElement getDataElement() {
		return dataElement;
	}

	@JsonProperty("dataElement")
	public void setDataElement(DHISDataElement dataElement) {
		this.dataElement = dataElement;
	}
	
	@JsonProperty("dataSet")
	public DHISDataSet getDataSet() {
		return dataSet;
	}
	
	@JsonProperty("dataSet")
	public void setDataSet(DHISDataSet dataSet) {
		this.dataSet = dataSet;
	}

}
