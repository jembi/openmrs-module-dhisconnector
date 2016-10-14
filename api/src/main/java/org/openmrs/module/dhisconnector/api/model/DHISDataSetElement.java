package org.openmrs.module.dhisconnector.api.model;

import javax.annotation.Generated;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "id", "created", "lastUpdated", "dataElement", "dataSet" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISDataSetElement {
	
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("created")
	private String created;
	
	@JsonProperty("lastUpdated")
	private String lastUpdated;
	
	@JsonProperty("dataElement")
	private DHISDataElement dataElement;
	
	@JsonProperty("dataSet")
	private DHISDataSet dataSet;
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("created")
	public String getCreated() {
		return created;
	}
	
	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
	}
	
	@JsonProperty("lastUpdated")
	public String getLastUpdated() {
		return lastUpdated;
	}
	
	@JsonProperty("lastUpdated")
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@JsonProperty("dataElement")
	public DHISDataElement getDataElement() {
		return dataElement;
	}
	
	@JsonProperty("dataElement")
	public void setDataElement(DHISDataElement dataElement) {
		this.dataElement = getDataElementFromDataSetElement(dataElement.getId());
	}
	
	@JsonProperty("dataSet")
	public DHISDataSet getDataSet() {
		return dataSet;
	}
	
	@JsonProperty("dataSet")
	public void setDataSet(DHISDataSet dataSet) {
		//this.dataSet = getDataSetFromDataSetElement(dataSet.getId());//enable if needed
		this.dataSet = dataSet;
	}
	
	private DHISDataElement getDataElementFromDataSetElement(String dataElementId) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = Context.getService(DHISConnectorService.class).getDataFromDHISEndpoint(
		    "/api/dataElements/" + dataElementId + ".json?fields=id,name,categoryCombo[id,name]");
		DHISDataElement ret = null;
		
		try {
			if (StringUtils.isNotBlank(jsonResponse))
				ret = mapper.readValue(jsonResponse, DHISDataElement.class);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ret;
	}
	
	private DHISDataSet getDataSetFromDataSetElement(String dataSetId) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = Context.getService(DHISConnectorService.class)
		        .getDataFromDHISEndpoint("/api/dataSets/" + dataSetId + ".json?fields=*,dataElements[id,name]");
		DHISDataSet ret = null;
		
		try {
			ret = mapper.readValue(jsonResponse, DHISDataSet.class);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ret;
	}
	
}
