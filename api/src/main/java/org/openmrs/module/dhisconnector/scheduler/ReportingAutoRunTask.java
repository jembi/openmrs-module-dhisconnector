package org.openmrs.module.dhisconnector.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.Configurations;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.scheduler.tasks.AbstractTask;

/**
 * daily task
 * 
 * @author k-joseph
 */
public class ReportingAutoRunTask extends AbstractTask {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void execute() {
		if (new Configurations().automationEnabled()) {
			log.info(Context.getService(DHISConnectorService.class).runAllAutomatedReportsAndPostToDHIS());
		}
	}
}
