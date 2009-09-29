package org.gissolutions.jsimpleutils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommonsConfigTest {
	private static FormattedLogger logger = FormattedLogger
			.getLogger(CommonsConfigTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadConfig() {
		try {
			String fn = TestConfiguration
					.getExistingTestData("TestApplicationUser.rules.xml");
			XMLConfiguration.setDelimiter('~');
			XMLConfiguration config = new XMLConfiguration(new File(fn));
			Object prop = config.getProperty("rules.regexprule[@name]");
			int count = ((Collection) prop).size();
			for (int i = 0; i < count; i++) {
				String template ="rules.regexprule(%s)[@name]";
				String propName = String.format(template, i);
				String nameVal = config.getString(propName);
				logger.debug("name %s", nameVal);
				
				template ="rules.regexprule(%s)[@pattern]";
				String strPattern = String.format(template, i);
				String patVal = config.getString(strPattern);
				logger.debug("Pattern %s", patVal);
				
			}

//			String usernamepat = config.getString("username-pattern[@pattern]");
//			String passwordpat = config.getString("password-pattern[@pattern]");
//
//			logger.debug("usernamepat %s", usernamepat);
//			logger.debug("passwordpat %s", passwordpat);

		} catch (ConfigurationException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
	}
}
