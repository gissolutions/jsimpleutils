package org.gissolutions.jsimpleutils.datetime;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.gissolutions.jsimpleutils.utils.Pair;
import org.junit.Before;
import org.junit.Test;

public class DateTimeUtilityTest {
	class DTUData {
		private final Date currentDate;
		private final Date birthDate;
		private int[] results;

		public DTUData(int bdYear, int bdMonth, int bdDay, int curYear,
				int curMonth, int curDay) {
			Calendar ccal = new GregorianCalendar(bdYear, bdMonth - 1, bdDay);
			birthDate = ccal.getTime();
			ccal = new GregorianCalendar(curYear, curMonth - 1, curDay);
			currentDate = ccal.getTime();
			results = new int[3];

		}

		public int[] getResults() {
			return results;
		}

		public void setResults(int[] results) {
			this.results = results;
		}

		public void setResults(int years, int months, int days) {
			results[0] = years;
			results[1] = months;
			results[2] = days;
		}

		public Date getCurrentDate() {
			return currentDate;
		}

		public String getCurrentDateFormatted() {
			String str = DateTimeUtility.DATE_FORMAT.format(getCurrentDate());
			return str;
		}

		public Date getBirthDate() {
			return birthDate;
		}

		public String getBirthDateFormatted() {
			String str = DateTimeUtility.DATE_FORMAT.format(getBirthDate());
			return str;
		}

	}

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DateTimeUtilityTest.class);

	public DateTimeUtilityTest() {
		TestConfiguration.getInstance();
	}

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testParseForMinutes(){
		List<Pair<String, Double>> data = new ArrayList<Pair<String, Double>>();
		data.add(new Pair<String, Double>("01:30", 1.5));
		data.add(new Pair<String, Double>("18:45", 18.75));
		data.add(new Pair<String, Double>("26:55", 26.916666666666668));
		data.add(new Pair<String, Double>("01:26:55", 86.916666666666668));
		for (Pair<String, Double> time : data) {
			double minutes = DateTimeUtility.parseForMinutes(time.getKey());
			logger.debug("Expeted minutes " + time.getValue());
			//double exp = time.getValue().doubleValue();
			assertEquals(time.getValue().toString(), new Double(minutes).toString());
			//assertTrue(exp == minutes);
		}
		
	}
	@Test
	public void testDropTime() {
		Calendar cal = new GregorianCalendar(2010, 6, 19, 19, 30, 45);
		String str = DateTimeUtility.DATETIME_FORMAT.format(cal.getTime());
		logger.debug("Date         :" + str);
		Date dtrim = DateTimeUtility.dropTime(cal.getTime());
		str = DateTimeUtility.DATETIME_FORMAT.format(dtrim);
		logger.debug("Date Trimmed :" + str);
		assertEquals("19-Jul-2010 00:00:00", str);
	}
	@Test
	public void testCalculateAge2() {
		int[] res = DateTimeUtility.calculateAge(1966, 9, 8, 2011, 11, 11);
		String msg = "%s years %s months %s days";
		msg = String.format(msg, res[0], res[1], res[2]);
		logger.debug("Age: " + msg);
		assertEquals("45 years 2 months 3 days", msg);
		
		res = DateTimeUtility.calculateAge(1966, 9, 8, 2011, 9, 8);
		 msg = "%s years %s months %s days";
		msg = String.format(msg, res[0], res[1], res[2]);
		logger.debug("Age: " + msg);
		assertEquals("45 years 0 months 0 days", msg);
	}
	@Test
	public void testCalculateAge() {
		List<DTUData> testData = new ArrayList<DTUData>();
		DTUData data = new DTUData(1966, 9, 8, 2010, 6, 19);
		data.setResults(43, 9, 11);
		testData.add(data);
		data = new DTUData(1966, 9, 8, 2010, 9, 19);
		data.setResults(44, 0, 11);
		testData.add(data);

		for (DTUData dtuData : testData) {
			// Current date
			logger.debug("Current date: " + dtuData.getCurrentDateFormatted());
			// Birthday
			logger.debug("BD date     : " + dtuData.getBirthDateFormatted());

			int[] res = DateTimeUtility.calculateAge(dtuData.getBirthDate(),
					dtuData.getCurrentDate());
			String msg = "%s years %s months %s days";
			msg = String.format(msg, res[0], res[1], res[2]);
			logger.debug("Age: " + msg);
			assertEquals(dtuData.getResults()[0], res[0]);
			assertEquals(dtuData.getResults()[1], res[1]);
			assertEquals(dtuData.getResults()[2], res[2]);
		}

	}
}
