package org.openmrs.module.dhisconnector.adx;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdxDataValueGroupPeriodAdapter extends XmlAdapter<String, AdxDataValueGroupPeriod> {
	
	@Override
	public String marshal(AdxDataValueGroupPeriod p) throws Exception {
		return p.getPeriod();
	}
	
	@Override
	public AdxDataValueGroupPeriod unmarshal(String arg0) throws Exception {
		return null;
	}
}
