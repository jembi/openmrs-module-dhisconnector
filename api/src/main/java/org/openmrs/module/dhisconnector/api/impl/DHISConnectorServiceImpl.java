/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
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
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.dhisconnector.api.ReportingRESTtoDHISService;
import org.openmrs.module.dhisreport.api.DHIS2ReportingService;
import org.openmrs.module.dhisreport.api.dhis.HttpDhis2Server;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.openmrs.module.dhisconnector.api.db.ReportingRESTtoDHISDAO;

import java.util.List;

/**
 * It is a default implementation of {@link ReportingRESTtoDHISService}.
 */
public class DHISConnectorServiceImpl extends BaseOpenmrsService implements ReportingRESTtoDHISService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private ReportingRESTtoDHISDAO dao;

  public static final String REST_URL_PREFIX_GLOBAL_PROPERTY_NAME = "webservices.rest.uriPrefix";
  public static final String REPORT_DEFINITION_RESOURCE = "/ws/rest/v1/reportingrest/reportDefinition?v=full";
	
	/**
     * @param dao the dao to set
     */
    public void setDao(ReportingRESTtoDHISDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public ReportingRESTtoDHISDAO getDao() {
	    return dao;
    }

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
    HttpDhis2Server server = Context.getService(DHIS2ReportingService.class).getDhis2Server();
    String payload = "";

    String host = server.getUrl().getHost();
    int port = server.getUrl().getPort();

    HttpHost targetHost = new HttpHost( host, port, server.getUrl().getProtocol() );
    DefaultHttpClient httpclient = new DefaultHttpClient();
    BasicHttpContext localcontext = new BasicHttpContext();

    try
    {
      HttpGet httpGet = new HttpGet( server.getUrl().getPath() + endpoint );
      Credentials creds = new UsernamePasswordCredentials( server.getUsername(), server.getPassword() );
      Header bs = new BasicScheme().authenticate( creds, httpGet, localcontext );
      httpGet.addHeader( "Authorization", bs.getValue() );
      httpGet.addHeader( "Content-Type", "application/json" );
      httpGet.addHeader( "Accept", "application/json" );

      //httpPost.setEntity( new StringEntity( xmlReport.toString() ) );
      HttpResponse response = httpclient.execute( targetHost, httpGet, localcontext );
      HttpEntity entity = response.getEntity();

      if ( response.getStatusLine().getStatusCode() != 200 )
      {
        //throw new Dhis2Exception( this, response.getStatusLine().getReasonPhrase(), null );

      }

      if ( entity != null ) {
//        JAXBContext jaxbImportSummaryContext = JAXBContext.newInstance( ImportSummary.class );
//        Unmarshaller importSummaryUnMarshaller = jaxbImportSummaryContext.createUnmarshaller();
//        summary = (ImportSummary) importSummaryUnMarshaller.unmarshal( entity.getContent() );


        //dataSets = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<DHISDataSet>>(){});
        //List<MyClass> myObjects = Arrays.asList(mapper.readValue(json, MyClass[].class))

        payload = EntityUtils.toString(entity);
      }
      else
      {
//        summary = new ImportSummary();
//        summary.setStatus( ImportStatus.ERROR );
      }
      // EntityUtils.consume( entity );

      // TODO: fix these catches ...
    }
    catch ( Exception ex )
    {
      System.out.print(ex.getMessage());
    }
    finally
    {
      httpclient.getConnectionManager().shutdown();
    }

    return payload;
  }
}