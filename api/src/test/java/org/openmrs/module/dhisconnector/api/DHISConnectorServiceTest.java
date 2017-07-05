package org.openmrs.module.dhisconnector.api;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Created by k-joseph on 04/07/2017.
 */
@Ignore
public class DHISConnectorServiceTest extends BaseModuleContextSensitiveTest {

    @Test
    public void testServiceInit() {
        Assert.assertNotNull(Context.getService(DHISConnectorService.class));
    }
}
