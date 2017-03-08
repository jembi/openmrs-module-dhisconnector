package org.openmrs.module.dhisconnector.adx;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.ihe.qrph.adx package.
 * <p>
 * An AdxObjectFactory allows you to programmatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema
 * derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class AdxObjectFactory {
	
	
	private final static QName _Adx_QNAME = new QName("urn:ihe:qrph:adx:2015", "adx");
	
	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes
	 * for package: org.ihe.qrph.adx
	 */
	public AdxObjectFactory() {
	}
	
	/**
	 * Create an instance of {@link AdxDataValueSet }
	 */
	public AdxDataValueSet createAdxType() {
		return new AdxDataValueSet();
	}
	
	/**
	 * Create an instance of {@link AdxDataValue }
	 */
	public AdxDataValue createDataValueType() {
		return new AdxDataValue();
	}
	
	/**
	 * Create an instance of {@link AdxDataValueGroup }
	 */
	public AdxDataValueGroup createGroupType() {
		return new AdxDataValueGroup();
	}
	
	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AdxDataValueSet }{@code >}}
	 */
	@XmlElementDecl(namespace = "urn:ihe:qrph:adx:2015", name = "adx")
	public JAXBElement<AdxDataValueSet> createAdx(AdxDataValueSet value) {
		return new JAXBElement<AdxDataValueSet>(_Adx_QNAME, AdxDataValueSet.class, null, value);
	}
	
	public String translateAdxDataValueSetIntoString(AdxDataValueSet adxValueSet) {
		try {
			StringWriter xmlReport = new StringWriter();
			JAXBContext jaxbDataValueSetContext = JAXBContext.newInstance(AdxDataValueSet.class);
			
			Marshaller adxTypeMarshaller = jaxbDataValueSetContext.createMarshaller();
			
			// output pretty printed
			adxTypeMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			adxTypeMarshaller.marshal(adxValueSet, xmlReport);
			
			return xmlReport.toString();
		}
		catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public AdxDataValueSet translateStringToAdxDataValueSet(String text) {
		try {
			JAXBContext jaxbDataValueSetContext = JAXBContext.newInstance(AdxDataValueSet.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbDataValueSetContext.createUnmarshaller();
			return (AdxDataValueSet) jaxbUnmarshaller.unmarshal(new StringReader(text));
		}
		catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
