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
package org.openmrs.module.dhisconnector.api;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openmrs.Location;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.dhisconnector.ReportToDataSetMapping;
import org.openmrs.module.dhisconnector.api.model.DHISDataValueSet;
import org.openmrs.module.dhisconnector.api.model.DHISMapping;
import org.openmrs.module.dhisconnector.api.model.DHISOrganisationUnit;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured
 * in moduleApplicationContext.xml. It can be accessed only via Context:<br>
 * <code>
 * Context.getService(DHISConnectorService.class).someMethod();
 * </code>
 *
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface DHISConnectorService extends OpenmrsService {
	
	
	/**
	 * Queries a DHIS endoint and returns the result as JSON
	 *
	 * @param endpoint The endpoint to query
	 * @return Result as JSON string
	 */
	public String getDataFromDHISEndpoint(String endpoint);
	
	public String postDataToDHISEndpoint(String endpoint, String jsonPayload);
	
	/**
	 * Tests to check if the given DHIS server details are correct
	 *
	 * @param url The URL of the server
	 * @param user The login username
	 * @param pass The login password
	 * @return True if the details are correct, false otherwise
	 */
	public boolean testDHISServerDetails(String url, String user, String pass);
	
	public Object saveMapping(DHISMapping mapping);
	
	public Object postDataValueSet(DHISDataValueSet dataValueSet);
	
	public List<DHISMapping> getMappings();
	
	public List<PeriodIndicatorReportDefinition> getReportWithMappings(List<DHISMapping> mappings);
	
	public List<DHISOrganisationUnit> getDHISOrgUnits();
	
	public String uploadMappings(MultipartFile mapping);
	
	public String[] exportSelectedMappings(String[] selectedMappings);
	
	public boolean dhis2BackupExists();
	
	public String getLastSyncedAt();
	
	public String getDHIS2APIBackupPath();
	
	public String uploadDHIS2APIBackup(MultipartFile dhis2APIBackup);
	
	public DHISMapping getMapping(String s);
	
	public boolean permanentlyDeleteMapping(DHISMapping mapping);
	
	String getAdxFromDxf(DHISDataValueSet dataValueSet);
	
	Integer getNumberOfFailedDataPosts();
	
	void postPreviouslyFailedData();
	
	List<ReportToDataSetMapping> getAllReportToDataSetMappings();
	
	ReportToDataSetMapping getReportToDataSetMappingByUuid(String uuid);
	
	ReportToDataSetMapping getReportToDataSetMapping(Integer id);
	
	void deleteReportToDataSetMapping(ReportToDataSetMapping reportToDataSetMapping);
	
	void saveReportToDataSetMapping(ReportToDataSetMapping reportToDataSetMapping);
	
	void deleteReportToDataSetMapping(Integer reportToDataSetMappingId);
	
	String runAndPushReportToDHIS(ReportToDataSetMapping reportToDatasetMapping, Location location, String orgUnitUid, boolean isEndOfFullMapping);
	
	String runAllAutomatedReportsAndPostToDHIS();
	
	String runReportsAndPostToDHIS(List<ReportToDataSetMapping> mps);

	String transformToDHISPeriod(Calendar startDate, Calendar endDate, String periodType, Date lastRun);
	
}
