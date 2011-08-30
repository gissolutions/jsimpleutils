package org.gissolutions.jsimpleutils.datetime;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StopWatchTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetElapsedFormatted() {
		StopWatch sw = new StopWatch();
		try {
			Thread.sleep(15500);
			sw.stop();
			String res =sw.getElapsedFormatted();
			
			System.out.println(sw.getElapsedFormatted());
			//System.out.println(sw.getElapsedTime());
			assertTrue(res.startsWith("00:00:15.50"));
		} catch (InterruptedException e) {			
			fail(e.getMessage());
		}
		
	}

}
