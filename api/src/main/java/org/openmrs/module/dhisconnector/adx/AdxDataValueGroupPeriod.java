package org.openmrs.module.dhisconnector.adx;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;
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
				
				if(q == 1)
					c.set(Calendar.MONTH, 0);
				if(q == 2)
					c.set(Calendar.MONTH, 3);
				if(q == 3)
					c.set(Calendar.MONTH, 6);
				if(q == 4)
					c.set(Calendar.MONTH, 9);
				cl.setTime(c.getTime());
				cl.add(Calendar.MONTH, 3);
			} else if (Duration.P6M.equals(getDuration())) {//SixMonthly: e.g. 2011S1
				int s = Integer.parseInt(getIsoPeriod().split("S")[1]);
				
				if(s == 1)
					c.set(Calendar.MONTH, 0);
				if(s == 2)
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
	
	/**
	 * DHIS dataset period types include: Monthly | Daily | Weekly | Quarterly | SixMontly | Yearly
	 * <br />
	 * 
	 * @see <a href=
	 *      "https://docs.dhis2.org/2.22/en/developer/html/dhis2_developer_manual_full.html">DHIS
	 *      2.22 Dev Manual</a> <br />
	 *      DHIS period types and examples
	 *      <table class="table" summary="Period format" border="1">
	 *      <colgroup><col width="60pt" class="c1"><col width="80pt" class="c2"><col width="80pt"
	 *      class="c3"><col width="200pt" class="c4"></colgroup><thead>
	 *      <tr>
	 *      <th>Interval</th>
	 *      <th>Format</th>
	 *      <th>Example</th>
	 *      <th>Description</th>
	 *      </tr>
	 *      </thead><tbody>
	 *      <tr>
	 *      <td>Day</td>
	 *      <td><span class="emphasis"><em>yyyyMMdd</em></span></td>
	 *      <td>20040315</td>
	 *      <td>March 15 2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Week</td>
	 *      <td><span class="emphasis"><em>yyyy</em></span>W<span class="emphasis">
	 *      <em>n</em></span></td>
	 *      <td>2004W10</td>
	 *      <td>Week 10 2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Month</td>
	 *      <td><span class="emphasis"><em>yyyyMM</em></span></td>
	 *      <td>200403</td>
	 *      <td>March 2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Quarter</td>
	 *      <td><span class="emphasis"><em>yyyy</em></span>Q<span class="emphasis">
	 *      <em>n</em></span></td>
	 *      <td>2004Q1</td>
	 *      <td>January-March 2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Six-month</td>
	 *      <td><span class="emphasis"><em>yyyy</em></span>S<span class="emphasis">
	 *      <em>n</em></span></td>
	 *      <td>2004S1</td>
	 *      <td>January-June 2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Six-month April</td>
	 *      <td><span class="emphasis"><em>yyyy</em></span>AprilSn</td>
	 *      <td>2004AprilS1</td>
	 *      <td>April-September 2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Year</td>
	 *      <td>yyyy</td>
	 *      <td>2004</td>
	 *      <td>2004</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Financial Year April</td>
	 *      <td>yyyyApril</td>
	 *      <td>2004April</td>
	 *      <td>Apr 2004-Mar 2005</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Financial Year July</td>
	 *      <td>yyyyJuly</td>
	 *      <td>2004July</td>
	 *      <td>July 2004-June 2005</td>
	 *      </tr>
	 *      <tr>
	 *      <td>Financial Year Oct</td>
	 *      <td>yyyyOct</td>
	 *      <td>2004Oct</td>
	 *      <td>Oct 2004-Sep 2005</td>
	 *      </tr>
	 *      </tbody>
	 *      </table>
	 */
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

	public enum DHISPeriodTypeUnit {
		DAILY("Daily", "\\b(\\d{4})(\\d{2})(\\d{2})\\b"),
	    WEEKLY("Weekly", "\\b(\\d{4})W(\\d[\\d]?)\\b"),
	    MONTHLY("Monthly", "\\b(\\d{4})[-]?(\\d{2})\\b"),
	    BI_MONTHLY("BiMonthly", "\\b(\\d{4})(\\d{2})B\\b"),
	    QUARTERLY("Quarterly", "\\b(\\d{4})Q(\\d)\\b"),
	    SIX_MONTHLY("SixMonthly", "\\b(\\d{4})S(\\d)\\b"),
	    SIX_MONTHLY_APRIL("SixMonthlyApril", "\\b(\\d{4})AprilS(\\d)\\b"),
	    YEARLY("Yearly", "\\b(\\d{4})\\b"),
	    FINANCIAL_APRIL("FinancialApril", "\\b(\\d{4})April\\b"),
	    FINANCIAL_JULY("FinancialJuly", "\\b(\\d{4})July\\b"),
	    FINANCIAL_OCTOBER("FinancialOct", "\\b(\\d{4})Oct\\b");
		
		private final String type;
		
		private final String format;
		
		public String getType() {
			return type;
		}
		
		public String getFormat() {
			return format;
		}
		
		DHISPeriodTypeUnit(String type, String format) {
			this.type = type;
			this.format = format;
		}
		
		public static DHISPeriodTypeUnit find(String format) {
			for (DHISPeriodTypeUnit type : DHISPeriodTypeUnit.values()) {
				if (format.matches(type.format)) {
					return type;
				}
			}
			
			return null;
		}
	}

	
	public String getdHISAdxEndDate() {
		return dHISAdxEndDate;
	}

	
	public void setdHISAdxEndDate(String dHISAdxEndDate) {
		this.dHISAdxEndDate = dHISAdxEndDate;
	}
}
