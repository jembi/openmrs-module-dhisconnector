
package org.openmrs.module.reportingresttodhis.api.model;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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
     * 
     * @return
     *     The style
     */
    @JsonProperty("style")
    public String getStyle() {
        return style;
    }

    /**
     * 
     * @param style
     *     The style
     */
    @JsonProperty("style")
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The htmlCode
     */
    @JsonProperty("htmlCode")
    public String getHtmlCode() {
        return htmlCode;
    }

    /**
     * 
     * @param htmlCode
     *     The htmlCode
     */
    @JsonProperty("htmlCode")
    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    /**
     * 
     * @return
     *     The format
     */
    @JsonProperty("format")
    public Integer getFormat() {
        return format;
    }

    /**
     * 
     * @param format
     *     The format
     */
    @JsonProperty("format")
    public void setFormat(Integer format) {
        this.format = format;
    }

}
