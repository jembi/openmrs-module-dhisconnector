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
package org.openmrs.module.dhisconnector.web.resource;

import org.codehaus.jackson.map.DeserializationConfig;
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

@Resource(name = RestConstants.VERSION_1 + DHISConnectorRestController.DHISCONNECTOR_NAMESPACE
		+ "/dhiscategorycombos", supportedClass = DHISCategoryCombo.class, supportedOpenmrsVersions = { "1.8.*",
		"1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*" ,"2.1.*"})
public class DHISCategoryCombosResource extends DataDelegatingCrudResource implements Retrievable {

	public static final String CATEGORYCOMBOS_PATH = "/api/categoryCombos";

	private static final String COC_FIELDS_PARAM = "?paging=false&fields=:identifiable,displayName,code,categoryOptionCombos[:identifiable,displayName,code],categories[:identifiable,displayName,code]";

	@Override
	public DHISCategoryCombo getByUniqueId(String s) {
		ObjectMapper mapper = new ObjectMapper();

		String jsonResponse = Context.getService(DHISConnectorService.class)
				.getDataFromDHISEndpoint(CATEGORYCOMBOS_PATH + "/" + s + DHISDataSetsResource.JSON_SUFFIX + COC_FIELDS_PARAM);

		DHISCategoryCombo ret = null;

		try {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ret = mapper.readValue(jsonResponse, DHISCategoryCombo.class);
		}
		catch (Exception e) {
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
