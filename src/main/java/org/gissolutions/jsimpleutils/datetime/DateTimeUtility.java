package org.gissolutions.jsimpleutils.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The DateTmeUtility class contains various methods to make date calculations.
 * @author luisberrocal
 *
 */
public class DateTimeUtility {
	public static final SimpleDateFormat DATE_FORMAT =
        new SimpleDateFormat("dd-MMM-yyyy");
	
	public static final SimpleDateFormat DATETIME_FORMAT =
        new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	
	public static final SimpleDateFormat TIME_FORMAT =
        new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Drops the time portion of a date. If the date is 1957-01-14 04:12:37.441
	 * the method will return a date value for 1957-01-14 00:00:00.000
	 * 
	 * @param date
	 *            to be trimmed
	 * @return the date without the time portion.
	 */
	public static Date dropTime(Date date) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(date);
		int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);
		int month = gcal.get(GregorianCalendar.MONTH);
		int year = gcal.get(GregorianCalendar.YEAR);
		gcal.clear();
		gcal.set(year, month, day);
		return gcal.getTime();
	}
	public static int[] calculateAge(int bYear, int bMonth, int bDay, int cYear, int cMonth, int cDay){
		Calendar bd = new GregorianCalendar(bYear, bMonth+1, bDay);
		Calendar cd = new GregorianCalendar(cYear, cMonth+1, cDay);
		return calculateAge(bd.getTime(), cd.getTime());
	}
	/**
	 * Calculates the age in years, month and days between two dates
	 * @param bdate the date to calculate the age to. The birthday for example.
	 * @param cdate the date to calculate the date from. The current date for example.
	 * @return an array of integer values of the age. The first elements contain the years, the
	 * second the months and the third the days.
	 */
	public static int[] calculateAge(Date bdate, Date cdate) {
		int[] results = new int[3];
		int ageYears, ageMonths, ageDays;
		int  month, day;
		Calendar bd = new GregorianCalendar();
		bd.setTime(bdate);
		//year = bd.get(Calendar.YEAR);
		month = bd.get(Calendar.MONTH);
		day = bd.get(Calendar.DAY_OF_MONTH);

		Calendar cd = new GregorianCalendar();
		cd.setTime(cdate);
		ageYears = cd.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
		if (cd.before(new GregorianCalendar(cd.get(Calendar.YEAR), month, day))) {
			ageYears--;
//			ageMonths = (12 - (bd.get(Calendar.MONTH) + 1))
//					+ (bd.get(Calendar.MONTH));
			ageMonths =12- (bd.get(Calendar.MONTH) - cd.get(Calendar.MONTH));
			if (day > cd.get(Calendar.DAY_OF_MONTH)) {
				ageDays = day - cd.get(Calendar.DAY_OF_MONTH);
			} else if (day < cd.get(Calendar.DAY_OF_MONTH)) {
				ageDays = cd.get(Calendar.DAY_OF_MONTH) - day;
			} else {
				ageDays = 0;
			}
		} else if (cd.after(new GregorianCalendar(cd.get(Calendar.YEAR), month,
				day))) {
			ageMonths = (cd.get(Calendar.MONTH) - (bd.get(Calendar.MONTH)));
			if (day > cd.get(Calendar.DAY_OF_MONTH))
				ageDays = day - cd.get(Calendar.DAY_OF_MONTH) - day;
			else if (day < cd.get(Calendar.DAY_OF_MONTH)) {
				ageDays = cd.get(Calendar.DAY_OF_MONTH) - day;
			} else
				ageDays = 0;
		} else {
			ageYears = cd.get(Calendar.YEAR) - bd.get(Calendar.YEAR);
			ageMonths = 0;
			ageDays = 0;
		}
		results[0]= ageYears;
		results[1]= ageMonths;
		results[2]= ageDays;
		return results;
	}
	
	public static double parseForMinutes(String minutes){
		if(minutes == null) {
			throw new IllegalArgumentException("Minutes cannot be parsed for minutes if minutes are null");
		}
		double min=0;
		Pattern pattern = Pattern.compile("^([0-1][0-9]|[2][0-4])?:?([0-5][0-9]):([0-5][0-9])$");
		Matcher matcher = pattern.matcher(minutes);
		if(matcher.matches()){
			String strHrs = matcher.group(1);
			if(strHrs != null) {
				double hrs = Double.parseDouble(strHrs);
				min += hrs*60.0;
			}
			String strMin = matcher.group(2);
			String strSecs = matcher.group(3);
			min += Double.parseDouble(strMin);
			double secs = Double.parseDouble(strSecs);
			min += secs/60;			
		}
		return min;
	}
}
