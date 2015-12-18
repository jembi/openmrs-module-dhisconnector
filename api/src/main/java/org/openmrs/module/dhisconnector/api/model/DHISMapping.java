package org.openmrs.module.dhisconnector.api.model;

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
		"name",
		"periodType",
		"created",
		"dataSetUID",
		"periodIndicatorReportGUID",
		"elements"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISMapping {

	@JsonProperty("name")
	private String name;
	@JsonProperty("periodType")
	private String periodType;
	@JsonProperty("created")
	private long created;
	@JsonProperty("dataSetUID")
	private String dataSetUID;
	@JsonProperty("periodIndicatorReportGUID")
	private String periodIndicatorReportGUID;
	@JsonProperty("elements")
	private List<DHISMappingElement> elements = new ArrayList<DHISMappingElement>();

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
	 * The periodType
	 */
	@JsonProperty("periodType")
	public String getPeriodType() {
		return periodType;
	}

	/**
	 *
	 * @param periodType
	 * The periodType
	 */
	@JsonProperty("periodType")
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	/**
	 *
	 * @return
	 * The created
	 */
	@JsonProperty("created")
	public long getCreated() {
		return created;
	}

	/**
	 *
	 * @param created
	 * The created
	 */
	@JsonProperty("created")
	public void setCreated(long created) {
		this.created = created;
	}

	/**
	 *
	 * @return
	 * The dataSetUID
	 */
	@JsonProperty("dataSetUID")
	public String getDataSetUID() {
		return dataSetUID;
	}

	/**
	 *
	 * @param dataSetUID
	 * The dataSetUID
	 */
	@JsonProperty("dataSetUID")
	public void setDataSetUID(String dataSetUID) {
		this.dataSetUID = dataSetUID;
	}

	/**
	 *
	 * @return
	 * The periodIndicatorReportGUID
	 */
	@JsonProperty("periodIndicatorReportGUID")
	public String getPeriodIndicatorReportGUID() {
		return periodIndicatorReportGUID;
	}

	/**
	 *
	 * @param periodIndicatorReportGUID
	 * The periodIndicatorReportGUID
	 */
	@JsonProperty("periodIndicatorReportGUID")
	public void setPeriodIndicatorReportGUID(String periodIndicatorReportGUID) {
		this.periodIndicatorReportGUID = periodIndicatorReportGUID;
	}

	/**
	 *
	 * @return
	 * The elements
	 */
	@JsonProperty("elements")
	public List<DHISMappingElement> getElements() {
		return elements;
	}

	/**
	 *
	 * @param elements
	 * The elements
	 */
	@JsonProperty("elements")
	public void setElements(List<DHISMappingElement> elements) {
		this.elements = elements;
	}

}
