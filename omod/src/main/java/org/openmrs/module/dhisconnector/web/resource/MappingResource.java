package org.openmrs.module.dhisconnector.web.resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.module.dhisconnector.api.model.DHISCategoryCombo;
import org.openmrs.module.dhisconnector.api.model.DHISMapping;
import org.openmrs.module.dhisconnector.web.controller.DHISConnectorRestController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + DHISConnectorRestController.DHISCONNECTOR_NAMESPACE + "/mappings", supportedClass = DHISMapping.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*"})
public class MappingResource extends DataDelegatingCrudResource implements Retrievable {

	@Override
	public DHISMapping getByUniqueId(String s) {
//		ObjectMapper mapper = new ObjectMapper();
//
//		String jsonResponse = Context
//				.getService(DHISConnectorService.class).getDataFromDHISEndpoint(CATEGORYCOMBOS_PATH + "/" + s + DHISDataSetsResource.JSON_SUFFIX );
//
//		DHISCategoryCombo ret = null;
//
//		try {
//			ret = mapper.readValue(jsonResponse, DHISCategoryCombo.class);
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}

		return null;
	}

	@Override
	protected void delete(Object o, String s, RequestContext requestContext) throws ResponseException {

	}

	@Override
	public void purge(Object o, RequestContext requestContext) throws ResponseException {

	}

	/**
	 * Annotated setter for Concept
	 *
	 * @param obs
	 * @param value
	 */
	@PropertySetter("elements")
	public static void setConcept(DHISMapping obs, Object value) {
		System.out.println("test");
	}

	protected NeedsPaging<DHISMapping> doGetAll(RequestContext context) {

		List<DHISMapping> mappings = new ArrayList<DHISMapping>();

		return new NeedsPaging<DHISMapping>(mappings, context);
	}

	@Override
	public DHISMapping newDelegate() {
		return new DHISMapping();
	}

	@Override
	public Object save(Object o) {

		System.out.println("testsetet");
		return null;
	}

//	@Override
//	public DHISMapping save(DHISMapping o) {
//		System.out.println("test");
//		return null;
//	}

	public DelegatingResourceDescription getCreatableProperties() {

		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("name");
		description.addProperty("created");
		description.addProperty("dataSetUID");
		description.addProperty("periodIndicatorReportGUID");
		description.addProperty("periodType");
		description.addProperty("elements");

		return description;
	}




	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("name");
		description.addProperty("created");
		description.addProperty("dataSetUID");
		description.addProperty("periodIndicatorReportGUID");
		description.addProperty("periodType");
		description.addProperty("elements");
		return description;
	}
}
