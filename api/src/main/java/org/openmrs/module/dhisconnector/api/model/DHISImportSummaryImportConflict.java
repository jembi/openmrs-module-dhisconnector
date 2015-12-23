package org.openmrs.module.dhisconnector.api.model;

import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "object",
        "value"
})
public class DHISImportSummaryImportConflict {

    @JsonProperty("object")
    private String object;
    @JsonProperty("value")
    private String value;

    /**
     *
     * @return
     *     The object
     */
    @JsonProperty("object")
    public String getObject() {
        return object;
    }

    /**
     *
     * @param object
     *     The object
     */
    @JsonProperty("object")
    public void setObject(String object) {
        this.object = object;
    }

    /**
     *
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

}
