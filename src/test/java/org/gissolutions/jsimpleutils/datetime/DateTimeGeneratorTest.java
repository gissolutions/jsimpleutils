package org.gissolutions.jsimpleutils.datetime;

import static org.junit.Assert.*;

import java.util.Date;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.Before;
import org.junit.Test;

public class DateTimeGeneratorTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DateTimeGeneratorTest.class);
	public DateTimeGeneratorTest() {
		TestConfiguration.getInstance();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGenerate() {
		DateTimeGenerator gen = new DateTimeGenerator();
		for (int i = 0; i < 10; i++) {
			Date dt = gen.generate();
			logger.debug("Date: " + i + ": " + DateTimeGenerator.DATE_FORMAT.format(dt));
		}
	}
	@Test
	public void testGenerate2() {
		DateTimeGenerator gen = new DateTimeGenerator();
		for (int i = 0; i < 10; i++) {
			Date dt = gen.generate(DateTimeGenerator.TIME);
			logger.debug("Date: " + i + ": " + DateTimeGenerator.DATE_FORMAT.format(dt));
		}
	}
	@Test
	public void testGenerate3() {
		DateTimeGenerator gen = new DateTimeGenerator();
		gen.setStartDate(1, 1, 1955);
		gen.setEndDate(1, 12, 1966);
		for (int i = 0; i < 10; i++) {
			Date dt = gen.generate(DateTimeGenerator.TIME);
			logger.debug("Date: " + i + ": " + DateTimeGenerator.DATE_FORMAT.format(dt));
		}
	}
	@Test
	public void testGenerateInt() {
		fail("Not yet implemented");
	}

}
