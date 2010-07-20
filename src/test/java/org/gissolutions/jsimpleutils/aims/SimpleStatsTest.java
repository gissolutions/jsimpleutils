package org.gissolutions.jsimpleutils.aims;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleStatsTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SimpleStatsTest.class);
	SimpleStats stats;
	
	public SimpleStatsTest() {
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
		stats = new SimpleStats("Dummy");
		stats.addData(0.0737797419400454);
		stats.addData(9.56450211564806);
		stats.addData(9.22438183981176);
		stats.addData(2.22332946373739);
		stats.addData(6.40065532363313);
		stats.addData(9.1432944073969);
		stats.addData(8.7643937767317);
		stats.addData(3.66392237098763);
		stats.addData(1.917310358049);
		stats.addData(5.08622151442233);
		stats.addData(8.07239635950058);
		stats.addData(9.53540987964227);
		stats.addData(9.33163304310973);
		stats.addData(5.84435954672081);
		stats.addData(4.54185361829813);
		stats.addData(9.32662653959784);
		stats.addData(8.32680807715587);
		stats.addData(0.487896815837374);
		stats.addData(3.14254683979237);
		stats.addData(4.79255904599706);


		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetCount() {
		assertEquals(20, stats.getCount());
	}

	@Test
	public void testGetTotal() {
		logger.debug("Total: " + stats.getTotal());
		assertEquals(119.46388067801, stats.getTotal());
	}

	@Test
	public void testGetAverage() {
		logger.debug("Average: " + stats.getAverage());
		assertEquals(5.9731940339005, stats.getAverage());
	}

	@Test
	public void testGetStandardDeviation() {
		double res = stats.getStandardDeviation();
		logger.debug("Standard Deviation: " +res);
		assertEquals(3.2415511889753974, res);
	}

}
