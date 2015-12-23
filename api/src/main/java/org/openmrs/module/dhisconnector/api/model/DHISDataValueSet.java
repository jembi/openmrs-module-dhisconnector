
package org.openmrs.module.dhisconnector.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "dataSet",
    "completeData",
    "period",
    "orgUnit",
    "dataValues"
})
public class DHISDataValueSet {

    @JsonProperty("dataSet")
    private String dataSet;
    @JsonProperty("completeData")
    private String completeData;
    @JsonProperty("period")
    private String period;
    @JsonProperty("orgUnit")
    private String orgUnit;
    @JsonProperty("dataValues")
    private List<DHISDataValue> dataValues = new ArrayList<DHISDataValue>();

    /**
     * 
     * @return
     *     The dataSet
     */
    @JsonProperty("dataSet")
    public String getDataSet() {
        return dataSet;
    }

    /**
     * 
     * @param dataSet
     *     The dataSet
     */
    @JsonProperty("dataSet")
    public void setDataSet(String dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * 
     * @return
     *     The completeData
     */
    @JsonProperty("completeData")
    public String getCompleteData() {
        return completeData;
    }

    /**
     * 
     * @param completeData
     *     The completeData
     */
    @JsonProperty("completeData")
    public void setCompleteData(String completeData) {
        this.completeData = completeData;
    }

    /**
     * 
     * @return
     *     The period
     */
    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    /**
     * 
     * @param period
     *     The period
     */
    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * 
     * @return
     *     The orgUnit
     */
    @JsonProperty("orgUnit")
    public String getOrgUnit() {
        return orgUnit;
    }

    /**
     * 
     * @param orgUnit
     *     The orgUnit
     */
    @JsonProperty("orgUnit")
    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    /**
     * 
     * @return
     *     The dataValues
     */
    @JsonProperty("dataValues")
    public List<DHISDataValue> getDataValues() {
        return dataValues;
    }

    /**
     * 
     * @param dataValues
     *     The dataValues
     */
    @JsonProperty("dataValues")
    public void setDataValues(List<DHISDataValue> dataValues) {
        this.dataValues = dataValues;
    }

    public void addDataValue(DHISDataValue dataValue) {
        if(dataValues == null) {
            dataValues = new ArrayList<DHISDataValue>();
        }

        dataValues.add(dataValue);
    }
}
