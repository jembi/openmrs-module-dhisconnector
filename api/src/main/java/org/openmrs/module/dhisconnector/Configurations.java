package org.openmrs.module.dhisconnector;

import org.openmrs.api.context.Context;

public class Configurations {
	
	
	private static String ADX_DXF_SWITCH = "dhisreporting.config.dxfToAdxSwitch";
	
	public boolean useAdxInsteadOfDxf() {
		return "true".equals(Context.getAdministrationService().getGlobalProperty(ADX_DXF_SWITCH));
	}
}
