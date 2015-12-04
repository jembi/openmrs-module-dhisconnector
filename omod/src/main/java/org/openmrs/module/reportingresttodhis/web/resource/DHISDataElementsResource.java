package org.openmrs.module.reportingresttodhis.web.resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.module.reportingresttodhis.api.ReportingRESTtoDHISService;
import org.openmrs.module.reportingresttodhis.api.model.DHISDataElement;
import org.openmrs.module.reportingresttodhis.api.model.DHISDataSet;
import org.openmrs.module.reportingresttodhis.web.controller.ReportingRESTtoDHISRestController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + ReportingRESTtoDHISRestController.REPORTINGREST_TO_DHIS_NAMESPACE + "/dhisdataelements", supportedClass = DHISDataElement.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*"})
public class DHISDataElementsResource extends DataDelegatingCrudResource implements Retrievable {


  public static final String DATAELEMENTS_PATH = "/api/dataElements";

  @Override
  public DHISDataElement getByUniqueId(String s) {
    ObjectMapper mapper = new ObjectMapper();

    String jsonResponse = Context.getService(ReportingRESTtoDHISService.class).getDataFromDHISEndpoint(DATAELEMENTS_PATH + "/" + s + DHISDataSetsResource.JSON_SUFFIX );

    DHISDataElement ret = null;

    try {
      ret = mapper.readValue(jsonResponse, DHISDataElement.class);
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return ret;
  }

  @Override
  protected void delete(Object o, String s, RequestContext requestContext) throws ResponseException {

  }

  @Override
  public Object newDelegate() {
    return null;
  }

  @Override
  public Object save(Object o) {
    return null;
  }

  @Override
  public void purge(Object o, RequestContext requestContext) throws ResponseException {

  }

  @Override
  public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
    DelegatingResourceDescription description = new DelegatingResourceDescription();
    description.addProperty("id");
    description.addProperty("name");
    description.addProperty("categoryCombo");
    return description;
  }
}
