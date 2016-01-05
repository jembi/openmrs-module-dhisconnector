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

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"style",
		"name",
		"htmlCode",
		"format"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISDataEntryForm {

	@JsonProperty("style")
	private String style;

	@JsonProperty("name")
	private String name;

	@JsonProperty("htmlCode")
	private String htmlCode;

	@JsonProperty("format")
	private Integer format;

	/**
	 * @return The style
	 */
	@JsonProperty("style")
	public String getStyle() {
		return style;
	}

	/**
	 * @param style The style
	 */
	@JsonProperty("style")
	public void setStyle(String style) {
		this.style = style;
	}

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
	 * @return The htmlCode
	 */
	@JsonProperty("htmlCode")
	public String getHtmlCode() {
		return htmlCode;
	}

	/**
	 * @param htmlCode The htmlCode
	 */
	@JsonProperty("htmlCode")
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	/**
	 * @return The format
	 */
	@JsonProperty("format")
	public Integer getFormat() {
		return format;
	}

	/**
	 * @param format The format
	 */
	@JsonProperty("format")
	public void setFormat(Integer format) {
		this.format = format;
	}

}
