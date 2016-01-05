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
		"imported",
		"updated",
		"ignored",
		"deleted"
})
public class DHISImportSummaryImportCount {

	@JsonProperty("imported")
	private Integer imported;

	@JsonProperty("updated")
	private Integer updated;

	@JsonProperty("ignored")
	private Integer ignored;

	@JsonProperty("deleted")
	private Integer deleted;

	/**
	 * @return The imported
	 */
	@JsonProperty("imported")
	public Integer getImported() {
		return imported;
	}

	/**
	 * @param imported The imported
	 */
	@JsonProperty("imported")
	public void setImported(Integer imported) {
		this.imported = imported;
	}

	/**
	 * @return The updated
	 */
	@JsonProperty("updated")
	public Integer getUpdated() {
		return updated;
	}

	/**
	 * @param updated The updated
	 */
	@JsonProperty("updated")
	public void setUpdated(Integer updated) {
		this.updated = updated;
	}

	/**
	 * @return The ignored
	 */
	@JsonProperty("ignored")
	public Integer getIgnored() {
		return ignored;
	}

	/**
	 * @param ignored The ignored
	 */
	@JsonProperty("ignored")
	public void setIgnored(Integer ignored) {
		this.ignored = ignored;
	}

	/**
	 * @return The deleted
	 */
	@JsonProperty("deleted")
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted The deleted
	 */
	@JsonProperty("deleted")
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
