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
package org.openmrs.module.dhisconnector.api.impl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.module.dhisconnector.api.model.DHISDataSet;
import org.openmrs.module.dhisconnector.api.model.DHISMapping;
import org.openmrs.module.dhisconnector.api.model.DHISOrganisationUnit;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.openmrs.util.OpenmrsUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It is a default implementation of {@link DHISConnectorService}.
 */
public class DHISConnectorServiceImpl extends BaseOpenmrsService implements DHISConnectorService {

	public static final String DHISCONNECTOR_MAPPINGS_FOLDER = "dhisconnector/mappings";

	public static final String DHISCONNECTOR_MAPPING_FILE_SUFFIX = ".mapping.json";

	protected final Log log = LogFactory.getLog(this.getClass());

	public static final String REST_URL_PREFIX_GLOBAL_PROPERTY_NAME = "webservices.rest.uriPrefix";

	public static final String REPORT_DEFINITION_RESOURCE = "/ws/rest/v1/reportingrest/reportDefinition?v=full";

	public static final String  DHISCONNECTOR_ORGUNIT_RESOURCE = "/api/organisationUnits.json?paging=false";

	@Override
	public List<String> getPeriodIndicatorReports() {
		//    String restUriPefixProperty = Context.getService(AdministrationService.class).getGlobalProperty(REST_URL_PREFIX_GLOBAL_PROPERTY_NAME);
		//
		//    HttpGet httpGet = new HttpGet(restUriPefixProperty + REPORT_DEFINITION_RESOURCE );
		//    httpGet.addHeader( "Content-Type", "application/json" );
		//
		//    HttpHost targetHost = new HttpHost( "localhost", 8080, "http" );
		//    DefaultHttpClient httpclient = new DefaultHttpClient();
		//    BasicHttpContext localcontext = new BasicHttpContext();
		//
		//    try {
		//      HttpResponse response = httpclient.execute(targetHost, httpGet, localcontext);
		//
		//      System.out.println(EntityUtils.toString(response.getEntity()));
		//    } catch (Exception e) {
		//      System.out.println("## TEST: " + e.getMessage());
		//    }

		List<ReportDefinition> reportSchemas = Context.getService(ReportDefinitionService.class).getAllDefinitions(false);

		//    Credentials creds = new UsernamePasswordCredentials( username, password );
		//    Header bs = new BasicScheme().authenticate( creds, httpPost, localcontext );
		//    httpPost.addHeader( "Authorization", bs.getValue() );
		//    httpPost.addHeader( "Content-Type", "application/xml" );
		//    httpPost.addHeader( "Accept", "application/xml" );
		//
		//    httpPost.setEntity( new StringEntity( xmlReport.toString() ) );
		//    HttpResponse response = httpclient.execute( targetHost, httpPost, localcontext );
		//    HttpEntity entity = response.getEntity();
		//
		//    if ( response.getStatusLine().getStatusCode() != 200 )
		//    {
		//      throw new Dhis2Exception( this, response.getStatusLine().getReasonPhrase(), null );
		//    }
		return null;
	}

	@Override
	public String getDataFromDHISEndpoint(String endpoint) {
		String url = Context.getAdministrationService().getGlobalProperty("dhisconnector.url");
		String user = Context.getAdministrationService().getGlobalProperty("dhisconnector.user");
		String pass = Context.getAdministrationService().getGlobalProperty("dhisconnector.pass");

		DefaultHttpClient client = null;
		String payload = "";

		try {
			URL dhisURL = new URL(url);

			String host = dhisURL.getHost();
			int port = dhisURL.getPort();

			HttpHost targetHost = new HttpHost(host, port, dhisURL.getProtocol());
			client = new DefaultHttpClient();
			BasicHttpContext localcontext = new BasicHttpContext();

			HttpGet httpGet = new HttpGet(dhisURL.getPath() + endpoint);
			Credentials creds = new UsernamePasswordCredentials(user, pass);
			Header bs = new BasicScheme().authenticate(creds, httpGet, localcontext);
			httpGet.addHeader("Authorization", bs.getValue());
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Accept", "application/json");
			HttpResponse response = client.execute(targetHost, httpGet, localcontext);
			HttpEntity entity = response.getEntity();

			if (response.getStatusLine().getStatusCode() != 200) {
				//throw new Dhis2Exception( this, response.getStatusLine().getReasonPhrase(), null );

			}

			if (entity != null) {
				//        JAXBContext jaxbImportSummaryContext = JAXBContext.newInstance( ImportSummary.class );
				//        Unmarshaller importSummaryUnMarshaller = jaxbImportSummaryContext.createUnmarshaller();
				//        summary = (ImportSummary) importSummaryUnMarshaller.unmarshal( entity.getContent() );

				//dataSets = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<DHISDataSet>>(){});
				//List<MyClass> myObjects = Arrays.asList(mapper.readValue(json, MyClass[].class))

				payload = EntityUtils.toString(entity);
			} else {
				//        summary = new ImportSummary();
				//        summary.setStatus( ImportStatus.ERROR );
			}
			// EntityUtils.consume( entity );

			// TODO: fix these catches ...
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(client != null) {
				client.getConnectionManager().shutdown();
			}
		}

		return payload;
	}

	@Override
	public boolean testDHISServerDetails(String url, String user, String pass) {
		URL testURL;
		Boolean success = true;

		// Check if the URL makes sense
		try {
			testURL = new URL(url + "/api"); // Add the root API endpoint to the URL
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}

		HttpHost targetHost = new HttpHost(testURL.getHost(), testURL.getPort(), testURL.getProtocol());
		DefaultHttpClient httpclient = new DefaultHttpClient();
		BasicHttpContext localcontext = new BasicHttpContext();

		try {
			HttpGet httpGet = new HttpGet(testURL.toURI());
			Credentials creds = new UsernamePasswordCredentials(user, pass);
			Header bs = new BasicScheme().authenticate(creds, httpGet, localcontext);
			httpGet.addHeader("Authorization", bs.getValue());
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(targetHost, httpGet, localcontext); // Execute the test query

			if (response.getStatusLine().getStatusCode() != 200) {
				success = false;

			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			success = false;
		}
		finally {
			httpclient.getConnectionManager().shutdown();
		}

		return success;
	}

	@Override
	public Object saveMapping(DHISMapping mapping) {
		String mappingsDirecoryPath = OpenmrsUtil.getApplicationDataDirectory() + DHISCONNECTOR_MAPPINGS_FOLDER;

		File mappingsDirecory = new File(mappingsDirecoryPath);

		if(!mappingsDirecory.exists()) {
			try {
				if(!mappingsDirecory.mkdirs()) {
					return null;
				}
			} catch(Exception e) {
				e.printStackTrace();
				return e;
			}
		}

		String filename = mapping.getName() + "." + mapping.getCreated() + ".mapping.json";

		File newMappingFile = new File(mappingsDirecoryPath + "/" + filename);

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValue(newMappingFile, mapping);
		} catch(Exception e) {
			e.printStackTrace();
			return e;
		}

		return mapping;
	}

	@Override
	public List<DHISMapping> getMappings() {
		List<DHISMapping> mappings = new ArrayList<DHISMapping>();

		ObjectMapper mapper = new ObjectMapper();


		String mappingsDirecoryPath = OpenmrsUtil.getApplicationDataDirectory() + DHISCONNECTOR_MAPPINGS_FOLDER;

		File mappingsDirecory = new File(mappingsDirecoryPath);

		File[] files = mappingsDirecory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(DHISCONNECTOR_MAPPING_FILE_SUFFIX);
			}
		});

		for(File f : files) {
			try {
				mappings.add(mapper.readValue(f, DHISMapping.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mappings;
	}

	@Override
	public List<PeriodIndicatorReportDefinition> getReportWithMappings(List<DHISMapping> mappings) {
		List<ReportDefinition> all = Context.getService(ReportDefinitionService.class).getAllDefinitions(false);

		List<PeriodIndicatorReportDefinition> pireports = new ArrayList<PeriodIndicatorReportDefinition>();

		for(ReportDefinition r : all) {
			if(r instanceof PeriodIndicatorReportDefinition && mappingsHasGUID(mappings, r.getUuid())) {
				pireports.add((PeriodIndicatorReportDefinition)r);
			}
		}

		return pireports;
	}

	@Override
	public List<DHISOrganisationUnit> getDHISOrgUnits() {
		List<DHISOrganisationUnit> orgUnits = new ArrayList<DHISOrganisationUnit>();

		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = new String();
		JsonNode node;

		jsonResponse = Context.getService(DHISConnectorService.class).getDataFromDHISEndpoint(DHISCONNECTOR_ORGUNIT_RESOURCE);

		try {
			node = mapper.readTree(jsonResponse);
			orgUnits = Arrays.asList(mapper.readValue(node.get("organisationUnits").toString(), DHISOrganisationUnit[].class));
		}catch ( Exception ex )
		{
			System.out.print(ex.getMessage());
		}

		return orgUnits;
	}

	private boolean mappingsHasGUID(List<DHISMapping> mappings, String GUID) {
		for(DHISMapping mapping : mappings) {
			if(mapping.getPeriodIndicatorReportGUID().equals(GUID)) {
				return true;
			}
		}
		return false;
	}

}
