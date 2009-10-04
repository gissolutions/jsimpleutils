package org.gissolutions.jsimpleutils.logging;

import java.util.Date;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FormattedLoggerTest {
	private static FormattedLogger flogger =  FormattedLogger.getLogger(FormattedLoggerTest.class);
	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDebugStringObjectArray() {
		flogger.debug("Test %s", "xxxxxx");
	}
	
	@Test
	public void testInfoStringObjectArray() {
		flogger.info("Today %s " , new Date());
	}

}
