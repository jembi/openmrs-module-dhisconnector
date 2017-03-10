package org.openmrs.module.dhisconnector;

import org.openmrs.api.context.Context;

public class Configurations {
	
	
	private static String DXF_TO_ADX_SWITCH = "dhisreporting.config.dxfToAdxSwitch";
	
	//TODO check dhis version to be above 2.20 when adx was introduced
	public boolean useAdxInsteadOfDxf() {
		return "true".equals(Context.getAdministrationService().getGlobalProperty(DXF_TO_ADX_SWITCH));
	}
}
