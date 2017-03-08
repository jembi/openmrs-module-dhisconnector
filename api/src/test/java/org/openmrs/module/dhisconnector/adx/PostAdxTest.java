package org.openmrs.module.dhisconnector.adx;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openmrs.module.dhisconnector.adx.AdxDataValueGroupPeriod.Duration;

import junit.framework.Assert;

public class PostAdxTest {
	
	
	@Test
	public void testOutSideObjectCreator() throws DatatypeConfigurationException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AdxDataValue dv1 = new AdxDataValue("D1", new BigDecimal(32.0));
		AdxDataValue dv2 = new AdxDataValue("D2", new BigDecimal(120.0));
		Calendar date = new GregorianCalendar(2016, 06, 14);
		AdxDataValueGroupPeriod period = new AdxDataValueGroupPeriod(date.getTime(), sdf, Duration.P1D);
		XMLGregorianCalendar exported = DatatypeFactory.newInstance()
		        .newXMLGregorianCalendar(new GregorianCalendar(2016, 06, 14));
		AdxObjectFactory factory = new AdxObjectFactory();
		String str = null;
		
		AdxDataValueGroup g = new AdxDataValueGroup(dv1, period, "LOC1", "DS1");
		AdxDataValueGroup g2 = new AdxDataValueGroup(dv2, period, "LOC2", "DS2");
		AdxDataValueSet adx = new AdxDataValueSet(g, exported);
		
		str = factory.translateAdxDataValueSetIntoString(adx);
		
		System.out.println("\nXML:::::::\n\n" + str + "\nXML:::::::\n");
		g2.getDataValues().add(dv1);
		adx.getGroups().add(g2);
		
		str = factory.translateAdxDataValueSetIntoString(adx);
		System.out.println("\nXML:::::::\n\n" + str + "\nXML:::::::\n");
		
		try {
			String text = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("adx-data-sample.xml"), "UTF-8");
			AdxDataValueSet tAdx = factory.translateStringToAdxDataValueSet(text);
			Object annotation = tAdx.getGroups().get(1).getDataValues().get(2).getAnnotation();
			
			System.out.println("\nXML:::::::\n\n" + annotation + "\nXML:::::::\n");
			Assert.assertEquals(2, tAdx.getGroups().size());
			Assert.assertEquals(2, tAdx.getGroups().get(0).getOtherAttributes().size());
			Assert.assertNotNull(tAdx.getGroups().get(1).getDataValues().get(2).getAnnotation());
			Assert.assertEquals(0, tAdx.getGroups().get(1).getDataValues().get(2).getOtherAttributes().size());
			//Assert.assertEquals("Some qualifying text here on the datavalue", annotation);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
