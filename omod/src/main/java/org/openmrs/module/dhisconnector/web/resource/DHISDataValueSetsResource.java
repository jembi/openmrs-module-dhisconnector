package org.openmrs.module.dhisconnector.web.resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.module.dhisconnector.api.model.DHISDataValue;
import org.openmrs.module.dhisconnector.api.model.DHISDataValueSet;
import org.openmrs.module.dhisconnector.api.model.DHISImportSummary;
import org.openmrs.module.dhisconnector.web.controller.DHISConnectorRestController;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Resource(name = RestConstants.VERSION_1 + DHISConnectorRestController.DHISCONNECTOR_NAMESPACE + "/dhisdatavaluesets", supportedClass = DHISDataValueSet.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*"})
public class DHISDataValueSetsResource extends DataDelegatingCrudResource implements Retrievable {

	@Override
	public Object getByUniqueId(String s) {
		return null;
	}

	@Override
	protected void delete(Object o, String s, RequestContext requestContext) throws ResponseException {

	}

	@Override
	public DHISDataValueSet newDelegate() {
		return new DHISDataValueSet();
	}

	@Override
	public Object save(Object o) {
		DHISImportSummary summary = Context.getService(DHISConnectorService.class).postDataValueSet((DHISDataValueSet)o);

		ObjectMapper mapper = new ObjectMapper();
		SimpleObject ret;

		try {
			ret = SimpleObject.parseJson(mapper.writeValueAsString(summary));
		} catch (Exception e) {
			return null;
		}

		return ret;
	}

	@PropertySetter("dataValues")
	public static void setDataValues(DHISDataValueSet dvs, Object value) {
		ArrayList<LinkedHashMap<String, String>> dataElements = (ArrayList<LinkedHashMap<String, String>>)value;

		for(LinkedHashMap<String, String> dataElement : dataElements) {
			Iterator it = dataElement.entrySet().iterator();

			DHISDataValue dv = new DHISDataValue();

			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();

				if(pair.getKey().equals("categoryOptionCombo")) {
					dv.setCategoryOptionCombo((String)pair.getValue());
				} else if(pair.getKey().equals("value")) {
					dv.setValue(pair.getValue().toString());
				} else if(pair.getKey().equals("comment")) {
					dv.setComment((String)pair.getValue());
				} else if(pair.getKey().equals("dataElement")) {
					dv.setDataElement((String)pair.getValue());
				}
			}

			dvs.addDataValue(dv);
		}
	}

	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("dataSet");
		description.addProperty("period");
		description.addProperty("orgUnit");
		description.addProperty("dataValues");
		return description;
	}

	@Override
	public void purge(Object o, RequestContext requestContext) throws ResponseException {

	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("dataSet");
		description.addProperty("period");
		description.addProperty("orgUnit");
		description.addProperty("dataValues");
		return description;
	}
}
