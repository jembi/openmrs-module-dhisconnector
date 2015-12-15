package org.openmrs.module.dhisconnector.web.resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.module.dhisconnector.api.model.DHISCategoryCombo;
import org.openmrs.module.dhisconnector.web.controller.DHISConnectorRestController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + DHISConnectorRestController.REPORTINGREST_TO_DHIS_NAMESPACE + "/dhiscategorycombos", supportedClass = DHISCategoryCombo.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*"})
public class DHISCategoryCombosResource extends DataDelegatingCrudResource implements Retrievable {

  public static final String CATEGORYCOMBOS_PATH = "/api/categoryCombos";

  @Override
  public DHISCategoryCombo getByUniqueId(String s) {
    ObjectMapper mapper = new ObjectMapper();

    String jsonResponse = Context.getService(DHISConnectorService.class).getDataFromDHISEndpoint(CATEGORYCOMBOS_PATH + "/" + s + DHISDataSetsResource.JSON_SUFFIX );

    DHISCategoryCombo ret = null;

    try {
      ret = mapper.readValue(jsonResponse, DHISCategoryCombo.class);
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
    description.addProperty("categoryOptionCombos");
    description.addProperty("categories");
    return description;
  }
}
