
package org.openmrs.module.dhisconnector.api.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "update",
    "externalize",
    "write",
    "delete",
    "read",
    "manage"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISAccess {

    @JsonProperty("update")
    private Boolean update;
    @JsonProperty("externalize")
    private Boolean externalize;
    @JsonProperty("write")
    private Boolean write;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("read")
    private Boolean read;
    @JsonProperty("manage")
    private Boolean manage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The update
     */
    @JsonProperty("update")
    public Boolean getUpdate() {
        return update;
    }

    /**
     * 
     * @param update
     *     The update
     */
    @JsonProperty("update")
    public void setUpdate(Boolean update) {
        this.update = update;
    }

    /**
     * 
     * @return
     *     The externalize
     */
    @JsonProperty("externalize")
    public Boolean getExternalize() {
        return externalize;
    }

    /**
     * 
     * @param externalize
     *     The externalize
     */
    @JsonProperty("externalize")
    public void setExternalize(Boolean externalize) {
        this.externalize = externalize;
    }

    /**
     * 
     * @return
     *     The write
     */
    @JsonProperty("write")
    public Boolean getWrite() {
        return write;
    }

    /**
     * 
     * @param write
     *     The write
     */
    @JsonProperty("write")
    public void setWrite(Boolean write) {
        this.write = write;
    }

    /**
     * 
     * @return
     *     The delete
     */
    @JsonProperty("delete")
    public Boolean getDelete() {
        return delete;
    }

    /**
     * 
     * @param delete
     *     The delete
     */
    @JsonProperty("delete")
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    /**
     * 
     * @return
     *     The read
     */
    @JsonProperty("read")
    public Boolean getRead() {
        return read;
    }

    /**
     * 
     * @param read
     *     The read
     */
    @JsonProperty("read")
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * 
     * @return
     *     The manage
     */
    @JsonProperty("manage")
    public Boolean getManage() {
        return manage;
    }

    /**
     * 
     * @param manage
     *     The manage
     */
    @JsonProperty("manage")
    public void setManage(Boolean manage) {
        this.manage = manage;
    }

}
