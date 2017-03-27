package org.openmrs.module.dhisconnector.adx;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.module.dhisconnector.DHISPeriodTypeUnit;
import org.openmrs.module.dhisconnector.api.model.DHISDataValueSet;

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
	
	/*
	 * (date|datetime)/(duration)
	 * */
	@XmlAttribute(name = "period", required = true)
	@XmlJavaTypeAdapter(AdxDataValueGroupPeriodAdapter.class)
	protected String period;
	
	private Duration duration;
	
	private String isoPeriod;
	
	private String dHISAdxEndDate;
	
	public AdxDataValueGroupPeriod() {
	}
	
	/**
	 * @param isoPeriod, {@link DHISDataValueSet#getPeriod()}
	 */
	public AdxDataValueGroupPeriod(String isoPeriod) {
		setIsoPeriod(isoPeriod);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		setDuration(getPeriodDuration(isoPeriod));
		period = getDHISAdxStartDate() + "/" + getDuration();
	}
	
	public String getDHISAdxStartDate() {
		String date = null;
		if (getDuration() != null && getDateFormat() != null) {
			Calendar c = Calendar.getInstance();
			Calendar cl = Calendar.getInstance();
			
			c.clear();
			c.set(Calendar.YEAR, Integer.parseInt(getIsoPeriod().substring(0, 4)));
			if (Duration.P7D.equals(getDuration())) {//Weekly: e.g. 2011W1
				c.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(getIsoPeriod().split("W")[1]));
				
				cl.setTime(c.getTime());
				cl.add(Calendar.WEEK_OF_YEAR, 1);
			} else if (Duration.P1Q.equals(getDuration())) {//Quarterly: e.g. 2011Q3
				int q = Integer.parseInt(getIsoPeriod().split("Q")[1]);
				
				if (q == 1)
					c.set(Calendar.MONTH, 0);
				if (q == 2)
					c.set(Calendar.MONTH, 3);
				if (q == 3)
					c.set(Calendar.MONTH, 6);
				if (q == 4)
					c.set(Calendar.MONTH, 9);
				cl.setTime(c.getTime());
				cl.add(Calendar.MONTH, 3);
			} else if (Duration.P6M.equals(getDuration())) {//SixMonthly: e.g. 2011S1
				int s = Integer.parseInt(getIsoPeriod().split("S")[1]);
				
				if (s == 1)
					c.set(Calendar.MONTH, 0);
				if (s == 2)
					c.set(Calendar.MONTH, 6);
				cl.setTime(c.getTime());
				cl.add(Calendar.MONTH, 6);
			} else if (Duration.P1M.equals(getDuration())) {//Monthly: e.g. 201101
				c.set(Calendar.MONTH, Integer.parseInt(getIsoPeriod().substring(4, 6)) - 1);
				cl.setTime(c.getTime());
				cl.add(Calendar.MONTH, 1);
			} else if (Duration.P1Y.equals(getDuration())) {//Yearly: e.g. 2011
				c.set(Calendar.MONTH, 0);
				cl.setTime(c.getTime());
				cl.add(Calendar.YEAR, 1);
			} else if (Duration.P1D.equals(getDuration())) {//Daily: e.g. 20110101
				c.set(Calendar.MONTH, Integer.parseInt(getIsoPeriod().substring(4, 6)) - 1);
				c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getIsoPeriod().substring(6, 8)));
				cl.setTime(c.getTime());
				cl.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			cl.add(Calendar.DAY_OF_MONTH, -1);
			setdHISAdxEndDate(getDateFormat().format(cl.getTime()));
			date = getDateFormat().format(c.getTime());
		}
		return date;
	}
	
	public static Duration getPeriodDuration(String period) {
		if (StringUtils.isNotBlank(period)) {
			//TODO support all Durations and fix any issues in this logic
			if ("Weekly".equals(AdxDataValueGroupPeriod.getPeriodTypeFromIsoString(period)))//weekly
				return Duration.P7D;
			if ("Quarterly".equals(AdxDataValueGroupPeriod.getPeriodTypeFromIsoString(period)))//quaterly
				return Duration.P1Q;
			if ("SixMonthly".equals(AdxDataValueGroupPeriod.getPeriodTypeFromIsoString(period)))//sixmonth
				return Duration.P6M;
			if ("Yearly".equals(AdxDataValueGroupPeriod.getPeriodTypeFromIsoString(period)))//annually
				return Duration.P1Y;
			if ("Monthly".equals(AdxDataValueGroupPeriod.getPeriodTypeFromIsoString(period)))//monthly
				return Duration.P1M;
			if ("Daily".equals(AdxDataValueGroupPeriod.getPeriodTypeFromIsoString(period)))//dailly
				return Duration.P1D;
		}
		return null;
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
	
	public static String getPeriodTypeFromIsoString(String isoPeriod) {
		DHISPeriodTypeUnit dateUnitType = DHISPeriodTypeUnit.find(isoPeriod);
		return dateUnitType != null ? dateUnitType.getType() : null;
	}
	
	public String getIsoPeriod() {
		return isoPeriod;
	}
	
	public void setIsoPeriod(String isoPeriod) {
		this.isoPeriod = isoPeriod;
	}
	
	public String getdHISAdxEndDate() {
		return dHISAdxEndDate;
	}
	
	public void setdHISAdxEndDate(String dHISAdxEndDate) {
		this.dHISAdxEndDate = dHISAdxEndDate;
	}
}
