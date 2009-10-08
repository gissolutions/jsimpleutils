package org.gissolutions.jsimpleutils.admin;

import static org.junit.Assert.*;

import java.text.ParseException;


import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordGeneratorTest {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PasswordGeneratorTest.class);
	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGeneratePassword() {
		PasswordGenerator passGen = new PasswordGenerator();
		logger.debug("Password default: "+ passGen.getPassword());
		passGen.setLength(12);
		try {
			passGen.setTemplate("oAAannaonn");
			passGen.generatePassword();
			logger.debug("Password template: "+ passGen.getPassword());
		} catch (ParseException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
	}
	
	@Test
	public void testDisplayOtherChars() {
		for (int i = 0; i < 15; i++) {
			char c =(char) (33 + i);
			String msg ="Char(%s): '%s'";
			msg = String.format(msg, i, c);
			logger.debug(msg);
			
		}
	}

}
