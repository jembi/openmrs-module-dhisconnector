package org.openmrs.module.dhisconnector.adx.importsummary;

import java.util.List;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "importSummaries")
public class ImportSummaries {
	
	
	@XmlElement(required = true)
	private int imported;
	
	@XmlElement(required = true)
	private int updated;
	
	@XmlElement(required = true)
	private int deleted;
	
	@XmlElement(required = true)
	private int ignored;
	
	@XmlElementWrapper(name = "importSummaryList", required = true)
	@XmlElement(name = "importSummary")
	private List<ImportSummary> importSummaryList;
	
	public int getImported() {
		return imported;
	}
	
	public void setImported(int imported) {
		this.imported = imported;
	}
	
	public int getUpdated() {
		return updated;
	}
	
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	
	public int getDeleted() {
		return deleted;
	}
	
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	public int getIgnored() {
		return ignored;
	}
	
	public void setIgnored(int ignored) {
		this.ignored = ignored;
	}
	
	public List<ImportSummary> getImportSummaryList() {
		return importSummaryList;
	}
	
	public void setImportSummaryList(List<ImportSummary> importSummaryList) {
		this.importSummaryList = importSummaryList;
	}
}
