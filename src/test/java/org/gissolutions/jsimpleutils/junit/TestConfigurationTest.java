package org.gissolutions.jsimpleutils.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConfigurationTest {

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

}
