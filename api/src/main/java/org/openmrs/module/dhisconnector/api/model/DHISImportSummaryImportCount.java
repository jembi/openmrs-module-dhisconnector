package org.openmrs.module.dhisconnector.api.model;

import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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
     *
     * @return
     *     The imported
     */
    @JsonProperty("imported")
    public Integer getImported() {
        return imported;
    }

    /**
     *
     * @param imported
     *     The imported
     */
    @JsonProperty("imported")
    public void setImported(Integer imported) {
        this.imported = imported;
    }

    /**
     *
     * @return
     *     The updated
     */
    @JsonProperty("updated")
    public Integer getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     *     The updated
     */
    @JsonProperty("updated")
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     *     The ignored
     */
    @JsonProperty("ignored")
    public Integer getIgnored() {
        return ignored;
    }

    /**
     *
     * @param ignored
     *     The ignored
     */
    @JsonProperty("ignored")
    public void setIgnored(Integer ignored) {
        this.ignored = ignored;
    }

    /**
     *
     * @return
     *     The deleted
     */
    @JsonProperty("deleted")
    public Integer getDeleted() {
        return deleted;
    }

    /**
     *
     * @param deleted
     *     The deleted
     */
    @JsonProperty("deleted")
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
