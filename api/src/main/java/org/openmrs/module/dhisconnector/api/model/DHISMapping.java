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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

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
	private Long created;

	@JsonProperty("dataSetUID")
	private String dataSetUID;

	@JsonProperty("periodIndicatorReportGUID")
	private String periodIndicatorReportGUID;

	@JsonProperty("elements")
	private List<DHISMappingElement> elements = new ArrayList<DHISMappingElement>();

	/**
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * @param name The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The periodType
	 */
	@JsonProperty("periodType")
	public String getPeriodType() {
		return periodType;
	}

	/**
	 * @param periodType The periodType
	 */
	@JsonProperty("periodType")
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	/**
	 * @return The created
	 */
	@JsonProperty("created")
	public Long getCreated() {
		return created;
	}

	/**
	 * @param created The created
	 */
	@JsonProperty("created")
	public void setCreated(Long created) {
		this.created = created;
	}

	/**
	 * @return The dataSetUID
	 */
	@JsonProperty("dataSetUID")
	public String getDataSetUID() {
		return dataSetUID;
	}

	/**
	 * @param dataSetUID The dataSetUID
	 */
	@JsonProperty("dataSetUID")
	public void setDataSetUID(String dataSetUID) {
		this.dataSetUID = dataSetUID;
	}

	/**
	 * @return The periodIndicatorReportGUID
	 */
	@JsonProperty("periodIndicatorReportGUID")
	public String getPeriodIndicatorReportGUID() {
		return periodIndicatorReportGUID;
	}

	/**
	 * @param periodIndicatorReportGUID The periodIndicatorReportGUID
	 */
	@JsonProperty("periodIndicatorReportGUID")
	public void setPeriodIndicatorReportGUID(String periodIndicatorReportGUID) {
		this.periodIndicatorReportGUID = periodIndicatorReportGUID;
	}

	/**
	 * @return The elements
	 */
	@JsonProperty("elements")
	public List<DHISMappingElement> getElements() {
		return elements;
	}

	/**
	 * @param elements The elements
	 */
	@JsonProperty("elements")
	public void setElements(List<DHISMappingElement> elements) {
		this.elements = elements;
	}

	public void addElement(DHISMappingElement el) {
		if (this.elements == null) {
			this.elements = new ArrayList<DHISMappingElement>();
		}

		this.elements.add(el);
	}
}
