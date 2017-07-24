package org.openmrs.module.dhisconnector;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.api.context.Context;

public class Configurations {

	private static String STORE_PERIOD = "dhisreporting.config.dataAndLogsStoragePeriod";

	private static String DXF_TO_ADX_SWITCH = "dhisreporting.config.dxfToAdxSwitch";
	
	//TODO check dhis version to be above 2.20 when adx was introduced
	public boolean useAdxInsteadOfDxf() {
		return "true".equals(Context.getAdministrationService().getGlobalProperty(DXF_TO_ADX_SWITCH));
	}

	/**
	 * @return number of months for dataAndLogsStoragePeriod
	 */
	public Integer getDataAndLogsStoragePeriod() {
		String v = Context.getAdministrationService().getGlobalProperty(STORE_PERIOD);

		if(StringUtils.isNotBlank(v))
			return Integer.parseInt(v);
		return 1;
	}
}
