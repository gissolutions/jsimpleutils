package org.gissolutions.jsimpleutils.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConfigurationTest {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TestConfigurationTest.class);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		TestConfiguration.getInstance();
	}

	@Test
	public void testMain() {
		TestConfiguration.main(null);
	}
	
	@Test
	public void testGetExistingTestData() {
		String fn = TestConfiguration.getExistingTestData("pom.xml");
		String md5 = TestConfiguration.calculateMD5Hash(fn);
		logger.debug("md5: "+ md5 );
		assertEquals("c2827798eca02e9bc0ce6ac1bfeb44d1", md5);
	}
	
	@Test
	public void testCalculateMD5Hash() {
		String fn = TestConfiguration.getExistingTestData("pom.xml");
		String md5 = TestConfiguration.calculateMD5Hash(fn);
		logger.debug("md5: " + md5 );
		//assertEquals("c2827798eca02e9bc0ce6ac1bfeb44d1", md5);
	}
}
