package org.openmrs.module.dhisconnector.adx;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A significant difference between adx and dxf2 is in the way that periods are encoded. Adx makes
 * strict use of ISO8601 and encodes the reporting period as (date|datetime)/(duration). So the
 * period in the example above is a period of 1 month (P1M) starting on 2015-06-01. So it is the
 * data for June 2015. The notation is a bit more verbose, but it is very flexible and allows us to
 * support all existing period types in DHIS2
 * 
 * @author k-joseph
 */
public class AdxDataValueGroupPeriod {
	
	
	private SimpleDateFormat dateFormat;
	
	private Date date;
	
	/*
	 * (date|datetime)/(duration)
	 * */
	@XmlAttribute(name = "period", required = true)
	@XmlJavaTypeAdapter(AdxDataValueGroupPeriodAdapter.class)
	protected String period;
	
	private Duration duration;
	
	public AdxDataValueGroupPeriod() {
	}
	
	public AdxDataValueGroupPeriod(Date date, SimpleDateFormat dateFormat, Duration duration) {
		setDate(date);
		setDateFormat(dateFormat);
		setDuration(duration);
		period = getDateFormat().format(getDate()) + "/" + getDuration();
	}
	
	public static enum Duration {
		P1D, // daily
		P7D, // weekly
		P1M, // monthly
		P2M, // bi-monthly
		P1Q, // quarterly
		P6M, // 6monthly (including 6monthlyApril)
		P1Y // yearly, financialApril, financialJuly, financialOctober
	}
	
	/**
	 * Gets the value of the period property.
	 * 
	 * @return possible object is {@link String }, evaluates within format:
	 *         (date|datetime)/(duration)
	 */
	public String getPeriod() {
		return period;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
