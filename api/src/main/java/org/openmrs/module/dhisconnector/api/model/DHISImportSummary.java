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
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "responseType",
    "status",
    "description",
    "importCount",
    "conflicts",
    "dataSetComplete"
})
//TODO dhis upgrade
public class DHISImportSummary {

    @JsonProperty("responseType")
    private String responseType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("description")
    private String description;
    @JsonProperty("importCount")
    private DHISImportSummaryImportCount importCount;
    @JsonProperty("conflicts")
    private List<DHISImportSummaryImportConflict> conflicts = new ArrayList<DHISImportSummaryImportConflict>();
    @JsonProperty("dataSetComplete")
    private String dataSetComplete;

    /**
     * 
     * @return
     *     The responseType
     */
    @JsonProperty("responseType")
    public String getResponseType() {
        return responseType;
    }

    /**
     * 
     * @param responseType
     *     The responseType
     */
    @JsonProperty("responseType")
    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The importCount
     */
    @JsonProperty("importCount")
    public DHISImportSummaryImportCount getImportCount() {
        return importCount;
    }

    /**
     * 
     * @param importCount
     *     The importCount
     */
    @JsonProperty("importCount")
    public void setImportCount(DHISImportSummaryImportCount importCount) {
        this.importCount = importCount;
    }

    /**
     * 
     * @return
     *     The conflicts
     */
    @JsonProperty("conflicts")
    public List<DHISImportSummaryImportConflict> getConflicts() {
        return conflicts;
    }

    /**
     * 
     * @param conflicts
     *     The conflicts
     */
    @JsonProperty("conflicts")
    public void setConflicts(List<DHISImportSummaryImportConflict> conflicts) {
        this.conflicts = conflicts;
    }

    /**
     * 
     * @return
     *     The dataSetComplete
     */
    @JsonProperty("dataSetComplete")
    public String getDataSetComplete() {
        return dataSetComplete;
    }

    /**
     * 
     * @param dataSetComplete
     *     The dataSetComplete
     */
    @JsonProperty("dataSetComplete")
    public void setDataSetComplete(String dataSetComplete) {
        this.dataSetComplete = dataSetComplete;
    }

}

