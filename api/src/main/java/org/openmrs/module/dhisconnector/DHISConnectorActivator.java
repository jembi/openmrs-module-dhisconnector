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
package org.openmrs.module.dhisconnector;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;
import org.openmrs.util.OpenmrsUtil;

/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class DHISConnectorActivator implements ModuleActivator {
	
	protected Log log = LogFactory.getLog(getClass());
	
	public static final String DHISCONNECTOR_MAPPINGS_FOLDER = File.separator + "dhisconnector" + File.separator
	        + "mappings";
	
	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	@Override
	public void willRefreshContext() {
		log.info("Refreshing DHIS Connector Module");
	}
	
	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	@Override
	public void contextRefreshed() {
		log.info("DHIS Connector Module refreshed");
	}
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	@Override
	public void willStart() {
		log.info("Starting DHIS Connector Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	@Override
	public void started() {
		String mappingsDirecoryPath = OpenmrsUtil.getApplicationDataDirectory() + DHISCONNECTOR_MAPPINGS_FOLDER;
		File mappingsDirecory = new File(mappingsDirecoryPath);
		
		if (!mappingsDirecory.exists()) {
			try {
				if (!mappingsDirecory.mkdirs()) {
					log.warn("Not able to create resource folder");
				} else {
					File directory = new File(getClass().getClassLoader().getResource("mappings").getFile());
					FileUtils.copyDirectory(directory, mappingsDirecory);
					log.debug("Copiyed all the mapping files to:" + mappingsDirecory);
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("Error while creating " + DHISCONNECTOR_MAPPINGS_FOLDER + "Directory");
			}
		}
		log.info("DHIS Connector started");
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	@Override
	public void willStop() {
		log.info("Stopping DHIS Connector Module");
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	@Override
	public void stopped() {
		log.info("DHIS Connector Module stopped");
	}
	
}
