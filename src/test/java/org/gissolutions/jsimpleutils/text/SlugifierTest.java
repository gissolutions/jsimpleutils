package org.gissolutions.jsimpleutils.text;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.gissolutions.jsimpleutils.text.Slugifier.SlugAttribute;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SlugifierTest {

	private final Map<String, String> testStandardData;
	private final Map<String, String> testSimpleData;
	public SlugifierTest() {
		testStandardData = new HashMap<String, String>();
		testStandardData.put("María de los Angeles", "maria-de-los-angeles");
		testStandardData.put("Cumpleaño de Víctor", "cumpleano-de-victor");
		testStandardData.put("Espacios    de      mas  ", "espacios-de-mas");
		testStandardData.put("   Espacios  de      mas   2", "espacios-de-mas-2");
		testStandardData.put("javier.huertas", "javier.huertas");
		testStandardData.put("localización:latitud:9.0098", "localizacion%3Alatitud%3A9.0098");
		
		testSimpleData = new HashMap<String, String>();
		testSimpleData.put("María de los Angeles", "maría-de-los-angeles");
		testSimpleData.put("Cumpleaño de Víctor", "cumpleaño-de-víctor");
		testSimpleData.put("Espacios    de      mas  ", "espacios-de-mas");
		testSimpleData.put("   Espacios  de      mas   2", "espacios-de-mas-2");
		testSimpleData.put("javier.huertas", "javier.huertas");
		testSimpleData.put("localización:latitud:9.0098", "localización:latitud:9.0098");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSlugify() throws UnsupportedEncodingException {
		
		for(Map.Entry<String, String> entry : testStandardData.entrySet()) {
			String slug = Slugifier.slugify(entry.getKey());
			assertEquals(entry.getValue(), slug);
		}
	}
	
	@Test
	public void testBuildSlug_All_Attributes() throws Exception {
		Slugifier slugbuilder = new Slugifier(SlugAttribute.CHANGE_ENCODING, SlugAttribute.REPLACE_MULTI_SPACES, SlugAttribute.REPLACE_SPECIAL_CHARS);
		for(Map.Entry<String, String> entry : testStandardData.entrySet()) {
			String slug = slugbuilder.buildSlug(entry.getKey());
			assertEquals(entry.getValue(), slug);
		}
	}
	
	@Test
	public void testBuildSlug_Multispace() throws Exception {
		Slugifier slugbuilder = new Slugifier(SlugAttribute.REPLACE_MULTI_SPACES);
		for(Map.Entry<String, String> entry : testSimpleData.entrySet()) {
			String slug = slugbuilder.buildSlug(entry.getKey());
			assertEquals(entry.getValue(), slug);
		}
	}

}
