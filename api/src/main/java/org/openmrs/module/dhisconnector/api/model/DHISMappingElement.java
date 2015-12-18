package org.openmrs.module.dhisconnector.api.model;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"indicator",
		"dataElement",
		"comboOption"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DHISMappingElement {

	@JsonProperty("indicator")
	private String indicator;
	@JsonProperty("dataElement")
	private String dataElement;
	@JsonProperty("comboOption")
	private String comboOption;

	/**
	 *
	 * @return
	 * The indicator
	 */
	@JsonProperty("indicator")
	public String getIndicator() {
		return indicator;
	}

	/**
	 *
	 * @param indicator
	 * The indicator
	 */
	@JsonProperty("indicator")
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	/**
	 *
	 * @return
	 * The dataElement
	 */
	@JsonProperty("dataElement")
	public String getDataElement() {
		return dataElement;
	}

	/**
	 *
	 * @param dataElement
	 * The dataElement
	 */
	@JsonProperty("dataElement")
	public void setDataElement(String dataElement) {
		this.dataElement = dataElement;
	}

	/**
	 *
	 * @return
	 * The comboOption
	 */
	@JsonProperty("comboOption")
	public String getComboOption() {
		return comboOption;
	}

	/**
	 *
	 * @param comboOption
	 * The comboOption
	 */
	@JsonProperty("comboOption")
	public void setComboOption(String comboOption) {
		this.comboOption = comboOption;
	}

}
