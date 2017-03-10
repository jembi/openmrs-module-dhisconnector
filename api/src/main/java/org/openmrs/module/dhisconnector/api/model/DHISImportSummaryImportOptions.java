package org.openmrs.module.dhisconnector.api.model;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.simple.JSONObject;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
public class DHISImportSummaryImportOptions {
	
	
	@JsonProperty("dryRun")
	private boolean dryRun;
	
	@JsonProperty("async")
	private boolean async;
	
	@JsonProperty("importStrategy")
	private String importStrategy;
	
	@JsonProperty("mergeMode")
	private String mergeMode;
	
	@JsonProperty("skipExistingCheck")
	private boolean skipExistingCheck;
	
	@JsonProperty("sharing")
	private boolean sharing;
	
	@JsonProperty("skipNotifications")
	private boolean skipNotifications;
	
	@JsonProperty("datasetAllowsPeriods")
	private boolean datasetAllowsPeriods;
	
	@JsonProperty("strictPeriods")
	private boolean strictPeriods;
	
	@JsonProperty("strictCategoryOptionCombos")
	private boolean strictCategoryOptionCombos;
	
	@JsonProperty("strictAttributeOptionCombos")
	private boolean strictAttributeOptionCombos;
	
	@JsonProperty("strictOrganisationUnits")
	private boolean strictOrganisationUnits;
	
	@JsonProperty("requireCategoryOptionCombo")
	private boolean requireCategoryOptionCombo;
	
	@JsonProperty("requireAttributeOptionCombo")
	private boolean requireAttributeOptionCombo;
	
	@JsonProperty("idSchemes")
	private JSONObject idSchemes;

	
	public boolean isDryRun() {
		return dryRun;
	}

	
	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}

	
	public boolean isAsync() {
		return async;
	}

	
	public void setAsync(boolean async) {
		this.async = async;
	}

	
	public String getImportStrategy() {
		return importStrategy;
	}

	
	public void setImportStrategy(String importStrategy) {
		this.importStrategy = importStrategy;
	}

	
	public String getMergeMode() {
		return mergeMode;
	}

	
	public void setMergeMode(String mergeMode) {
		this.mergeMode = mergeMode;
	}

	
	public boolean isSkipExistingCheck() {
		return skipExistingCheck;
	}

	
	public void setSkipExistingCheck(boolean skipExistingCheck) {
		this.skipExistingCheck = skipExistingCheck;
	}

	
	public boolean isSharing() {
		return sharing;
	}

	
	public void setSharing(boolean sharing) {
		this.sharing = sharing;
	}

	
	public boolean isSkipNotifications() {
		return skipNotifications;
	}

	
	public void setSkipNotifications(boolean skipNotifications) {
		this.skipNotifications = skipNotifications;
	}

	
	public boolean isDatasetAllowsPeriods() {
		return datasetAllowsPeriods;
	}

	
	public void setDatasetAllowsPeriods(boolean datasetAllowsPeriods) {
		this.datasetAllowsPeriods = datasetAllowsPeriods;
	}

	
	public boolean isStrictPeriods() {
		return strictPeriods;
	}

	
	public void setStrictPeriods(boolean strictPeriods) {
		this.strictPeriods = strictPeriods;
	}

	
	public boolean isStrictCategoryOptionCombos() {
		return strictCategoryOptionCombos;
	}

	
	public void setStrictCategoryOptionCombos(boolean strictCategoryOptionCombos) {
		this.strictCategoryOptionCombos = strictCategoryOptionCombos;
	}

	
	public boolean isStrictAttributeOptionCombos() {
		return strictAttributeOptionCombos;
	}

	
	public void setStrictAttributeOptionCombos(boolean strictAttributeOptionCombos) {
		this.strictAttributeOptionCombos = strictAttributeOptionCombos;
	}

	
	public boolean isStrictOrganisationUnits() {
		return strictOrganisationUnits;
	}

	
	public void setStrictOrganisationUnits(boolean strictOrganisationUnits) {
		this.strictOrganisationUnits = strictOrganisationUnits;
	}

	
	public boolean isRequireCategoryOptionCombo() {
		return requireCategoryOptionCombo;
	}

	
	public void setRequireCategoryOptionCombo(boolean requireCategoryOptionCombo) {
		this.requireCategoryOptionCombo = requireCategoryOptionCombo;
	}

	
	public boolean isRequireAttributeOptionCombo() {
		return requireAttributeOptionCombo;
	}

	
	public void setRequireAttributeOptionCombo(boolean requireAttributeOptionCombo) {
		this.requireAttributeOptionCombo = requireAttributeOptionCombo;
	}

	
	public JSONObject getIdSchemes() {
		return idSchemes;
	}

	
	public void setIdSchemes(JSONObject idSchemes) {
		this.idSchemes = idSchemes;
	}
	
}
