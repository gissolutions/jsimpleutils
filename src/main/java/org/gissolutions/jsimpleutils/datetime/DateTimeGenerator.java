package org.gissolutions.jsimpleutils.datetime;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
/**
 * The class DateTimeGenerator generates random dates between a time period
 * specified.
 * @author lberrocal
 *
 */
public class DateTimeGenerator {
	public static final int DATE = 0;
	public static final int TIME = 1;
	private Random random = null;
	private BigDecimal minValue = new BigDecimal("0");
	private BigDecimal maxValue = new BigDecimal("1423453127");
	public static final SimpleDateFormat DATE_FORMAT =
		        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S Z");
	protected int nulls = 0;

	public DateTimeGenerator() {
		super();
		random = new Random();
	}
	
	 public Date generate() {
		 return generate(DATE);
	 }
	public Date generate(int returnedType ) {

		BigDecimal retValue = null;
		BigDecimal length = maxValue.subtract(minValue);
		BigDecimal factor = new BigDecimal(random.nextDouble());
		retValue = length.multiply(factor).add(minValue);
		Date rdate =new Date(retValue.toBigInteger().longValue());
		if(returnedType == DATE) {
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(rdate);
			int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);
			int month =gcal.get(GregorianCalendar.MONTH);
			int year =gcal.get(GregorianCalendar.YEAR);
			gcal.clear();
			gcal.set(year, month, day);
			return gcal.getTime();
		}else {
			return  rdate;
		}
		
	}
	public void setStartDate(Date sdate) {
		minValue = new BigDecimal(sdate.getTime());
	}
	public void setStartDate(int day, int month, int year) {
		GregorianCalendar cal = new GregorianCalendar(year, month-1, day);
		setStartDate(cal.getTime());
	}
	public void setEndDate(int day, int month, int year) {
		GregorianCalendar cal = new GregorianCalendar(year, month-1, day);		
		setEndDate(cal.getTime());
	}
	public void setEndDate(Date edate) {
		maxValue = new BigDecimal(edate.getTime());
	}
	

}
