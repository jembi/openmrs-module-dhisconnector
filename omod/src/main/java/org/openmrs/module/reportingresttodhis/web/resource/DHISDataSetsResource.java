package org.openmrs.module.reportingresttodhis.web.resource;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hisp.dhis.dxf2.importsummary.ImportStatus;
import org.hisp.dhis.dxf2.importsummary.ImportSummary;
import org.openmrs.module.dhisreport.api.dhis.Dhis2Exception;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisreport.api.DHIS2ReportingService;
import org.openmrs.module.dhisreport.api.dhis.Dhis2Exception;
import org.openmrs.module.dhisreport.api.dhis.HttpDhis2Server;
import org.openmrs.module.reportingresttodhis.api.ReportingRESTtoDHISService;
import org.openmrs.module.reportingresttodhis.api.model.DHISDataSet;
import org.openmrs.module.reportingresttodhis.web.controller.ReportingRESTtoDHISRestController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Resource(name = RestConstants.VERSION_1 + ReportingRESTtoDHISRestController.REPORTINGREST_TO_DHIS_NAMESPACE + "/dhisdatasets", supportedClass = DHISDataSet.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*"})
public class DHISDataSetsResource extends DataDelegatingCrudResource implements Retrievable {

  public static final String DATASETS_PATH = "/api/dataSets";
  public static final String JSON_SUFFIX = ".json";
  public static final String NO_PAGING_PARAM = "paging=false";

  @Override
  public DHISDataSet getByUniqueId(String s) {

    ObjectMapper mapper = new ObjectMapper();

    String jsonResponse = Context.getService(ReportingRESTtoDHISService.class).getDataFromDHISEndpoint(DATASETS_PATH + "/" + s + JSON_SUFFIX );

    DHISDataSet ret = null;

    try {
      ret = mapper.readValue(jsonResponse, DHISDataSet.class);
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return ret;

  }

  @Override
  protected void delete(Object o, String s, RequestContext requestContext) throws ResponseException {
    // not supporting
  }

  @Override
  public Object newDelegate() {
    return null;
  }

  @Override
  public Object save(Object o) {
    return null; // not supporting
  }

  @Override
  public void purge(Object o, RequestContext requestContext) throws ResponseException {
    // not supporting
  }

  protected NeedsPaging<DHISDataSet> doGetAll(RequestContext context) {
//    HttpDhis2Server server = Context.getService(DHIS2ReportingService.class).getDhis2Server();
//
//
//    List<DHISDataSet> dataSets = new ArrayList<DHISDataSet>();
//    ObjectMapper mapper = new ObjectMapper();
//    String jsonResponse = new String();
//    JsonNode node;
//
//    String host = server.getUrl().getHost();
//    int port = server.getUrl().getPort();
//
//    HttpHost targetHost = new HttpHost( host, port, server.getUrl().getProtocol() );
//    DefaultHttpClient httpclient = new DefaultHttpClient();
//    BasicHttpContext localcontext = new BasicHttpContext();
//
//    try
//    {
//      HttpGet httpGet = new HttpGet( server.getUrl().getPath() + DATASETS_PATH + JSON_SUFFIX + NO_PAGING_PARAM );
//      Credentials creds = new UsernamePasswordCredentials( server.getUsername(), server.getPassword() );
//      Header bs = new BasicScheme().authenticate( creds, httpGet, localcontext );
//      httpGet.addHeader( "Authorization", bs.getValue() );
//      httpGet.addHeader( "Content-Type", "application/json" );
//      httpGet.addHeader( "Accept", "application/json" );
//
//      //httpPost.setEntity( new StringEntity( xmlReport.toString() ) );
//      HttpResponse response = httpclient.execute( targetHost, httpGet, localcontext );
//      HttpEntity entity = response.getEntity();
//
//      if ( response.getStatusLine().getStatusCode() != 200 )
//      {
//        //throw new Dhis2Exception( this, response.getStatusLine().getReasonPhrase(), null );
//
//      }
//
//      if ( entity != null )
//      {
////        JAXBContext jaxbImportSummaryContext = JAXBContext.newInstance( ImportSummary.class );
////        Unmarshaller importSummaryUnMarshaller = jaxbImportSummaryContext.createUnmarshaller();
////        summary = (ImportSummary) importSummaryUnMarshaller.unmarshal( entity.getContent() );
//
//
//        //dataSets = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<DHISDataSet>>(){});
//        //List<MyClass> myObjects = Arrays.asList(mapper.readValue(json, MyClass[].class))
//
//        jsonResponse = EntityUtils.toString(entity);
//        node = mapper.readTree(jsonResponse);
//
//        dataSets = Arrays.asList(mapper.readValue(node.get("dataSets").toString(), DHISDataSet[].class));
//      }
//      else
//      {
////        summary = new ImportSummary();
////        summary.setStatus( ImportStatus.ERROR );
//      }
//      // EntityUtils.consume( entity );
//
//      // TODO: fix these catches ...
//    }
//    catch ( Exception ex )
//    {
//      System.out.print(ex.getMessage());
//    }
//    finally
//    {
//      httpclient.getConnectionManager().shutdown();
//    }


    List<DHISDataSet> dataSets = new ArrayList<DHISDataSet>();

    ObjectMapper mapper = new ObjectMapper();
    String jsonResponse = new String();
    JsonNode node;

    jsonResponse = Context.getService(ReportingRESTtoDHISService.class).getDataFromDHISEndpoint(DATASETS_PATH + JSON_SUFFIX + NO_PAGING_PARAM);

    try {
      node = mapper.readTree(jsonResponse);
      dataSets = Arrays.asList(mapper.readValue(node.get("dataSets").toString(), DHISDataSet[].class));
    }catch ( Exception ex )
    {
      System.out.print(ex.getMessage());
    }



    return new NeedsPaging<DHISDataSet>(dataSets, context);
  }

  @Override
  public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
    DelegatingResourceDescription description = new DelegatingResourceDescription();

    if (representation instanceof FullRepresentation) {
      description.addProperty("id");
      description.addProperty("name");
      description.addProperty("code");
      description.addProperty("periodType");
      description.addProperty("code");
      description.addProperty("categoryCombo");
      description.addProperty("dataElements");
    } else {
      description.addProperty("id");
      description.addProperty("name");
      description.addProperty("code");
      description.addProperty("created");
      description.addProperty("lastUpdated");
      description.addProperty("href");
    }

    return description;
  }
}
