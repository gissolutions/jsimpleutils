package org.gissolutions.jsimpleutils.text;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TagParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() {
		String line = "Fiesta de #quinceaño de #Javy";
		TagParser tp = new TagParser();
		List<String> tags = tp.parse(line);
		assertEquals(2, tags.size());
		assertEquals("quinceaño", tags.get(0));
		assertEquals("Javy", tags.get(1));
	}
	@Test
	public void testParse_Null() {
		String line = null;
		TagParser tp = new TagParser();
		List<String> tags = tp.parse(line);
		assertEquals(0, tags.size());
		
	}
	@Test
	public void testParse_dots() {
		String line = "Fiesta de #iso.echo de #javy.huertas";
		TagParser tp = new TagParser();
		List<String> tags = tp.parse(line);
		assertEquals(2, tags.size());
		assertEquals("iso.echo", tags.get(0));
		assertEquals("javy.huertas", tags.get(1));
	}
	
	@Test
	public void testParse_underscoress() {
		String line = "Fiesta de #iso_echo de #javy_huertas";
		TagParser tp = new TagParser();
		List<String> tags = tp.parse(line);
		assertEquals(2, tags.size());
		assertEquals("iso_echo", tags.get(0));
		assertEquals("javy_huertas", tags.get(1));
	}
	
	@Test
	public void testParse_numbers() {
		String line = "Fiesta de #iso1911 de #javyHuertas_52";
		TagParser tp = new TagParser();
		List<String> tags = tp.parse(line);
		assertEquals(2, tags.size());
		assertEquals("iso1911", tags.get(0));
		assertEquals("javyHuertas_52", tags.get(1));
	}

}
