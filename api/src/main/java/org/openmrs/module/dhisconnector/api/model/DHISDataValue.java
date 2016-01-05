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

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"dataElement",
		"value",
		"categoryOptionCombo",
		"comment"
})
public class DHISDataValue {

	@JsonProperty("dataElement")
	private String dataElement;

	@JsonProperty("value")
	private String value;

	@JsonProperty("categoryOptionCombo")
	private String categoryOptionCombo;

	@JsonProperty("comment")
	private String comment;

	@JsonProperty("categoryOptionCombo")
	public String getCategoryOptionCombo() {
		return categoryOptionCombo;
	}

	@JsonProperty("categoryOptionCombo")
	public void setCategoryOptionCombo(String categoryOptionCombo) {
		this.categoryOptionCombo = categoryOptionCombo;
	}

	@JsonProperty("comment")
	public String getComment() {
		return comment;
	}

	@JsonProperty("comment")
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return The dataElement
	 */
	@JsonProperty("dataElement")
	public String getDataElement() {
		return dataElement;
	}

	/**
	 * @param dataElement The dataElement
	 */
	@JsonProperty("dataElement")
	public void setDataElement(String dataElement) {
		this.dataElement = dataElement;
	}

	/**
	 * @return The value
	 */
	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	/**
	 * @param value The value
	 */
	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

}
