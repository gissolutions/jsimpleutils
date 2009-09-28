package org.gissolutions.jsimpleutils;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationInfoTest {

	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenericAppInfo() {
		ApplicationInfo appInfo = new ApplicationInfo();
		TestConfiguration.writeAssertionsToFile(appInfo, "appInfo");
	}

}
