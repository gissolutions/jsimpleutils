package org.gissolutions.jsimpleutils.aims;

import static org.junit.Assert.*;

import java.io.File;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerformanceTestTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PerformanceTestTest.class);
	public PerformanceTestTest() {
		TestConfiguration.getInstance();
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGetImageAXL() {
		String ot = TestConfiguration.getExistingTestData("getimage.xml");
		String outputPath2= TestConfiguration.getInstance().getOutputPath();
		int reps=1;
		PerformanceTest prt = new PerformanceTest("manzanillo", "bd_hidromet", outputPath2, reps);
		String axl = prt.getGetImageAXL(new File(ot));
		logger.debug("AXL: " +axl);
	}

	@Test
	public void testDoGetImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindError() {
		fail("Not yet implemented");
	}

}
