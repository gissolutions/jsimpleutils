package org.gissolutions.jsimpleutils.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PairTest {
	private Pair<String, Long> strLongPair;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testGetValue() {
		strLongPair = new Pair<String, Long>("main key", 45l);
		assertEquals(45l, strLongPair.getValue());
		
	}

	@Test
	public void testEqualsObject() {
		strLongPair = new Pair<String, Long>("main key", 45l);
		Pair<String, Long> strLongPair2 = new Pair<String, Long>("main key", 45l);
		Pair<String, Long> strLongPair3 = new Pair<String, Long>("main key", 435l);
		assertTrue(strLongPair2.equals(strLongPair));
		assertFalse(strLongPair3.equals(strLongPair));
	}

}
