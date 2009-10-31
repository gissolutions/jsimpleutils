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
			System.out.println(sw.getElapsedFormatted());
			System.out.println(sw.getElapsedTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
