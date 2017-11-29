package org.openmrs.module.dhisconnector.api;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Created by k-joseph on 04/07/2017.
 */
public class DHISConnectorServiceTest extends BaseModuleContextSensitiveTest {
	
	
	@Test
	public void testServiceInit() {
		Assert.assertNotNull(Context.getService(DHISConnectorService.class));
	}
	
	@Test
	public void testReportToDataSetMappingService() {
		Assert.assertEquals(0, Context.getService(DHISConnectorService.class).getAllReportToDataSetMappings().size());
	}
}
