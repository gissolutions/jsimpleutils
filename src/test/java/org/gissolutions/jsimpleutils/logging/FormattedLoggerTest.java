package org.gissolutions.jsimpleutils.logging;

import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FormattedLoggerTest {
	private static FormattedLogger flogger =  (FormattedLogger) FormattedLogger.getLogger(FormattedLoggerTest.class);
	@Before
	public void setUp() throws Exception {
		//TestConfiguration.getInstance();
		String fnout = TestConfiguration.getOutputFilenameWithDate("formatted_logger.txt");
		 Logger root = Logger.getRootLogger();
		 root.removeAllAppenders();
	      Layout layout = new PatternLayout("%p [%t] %c (%F:%L) - %m%n");
	      root.addAppender(new ConsoleAppender(layout, ConsoleAppender.SYSTEM_OUT));
	      root.addAppender(new org.apache.log4j.RollingFileAppender(layout,fnout));
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
