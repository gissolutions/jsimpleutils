package org.gissolutions.jsimpleutils.io;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.Before;
import org.junit.Test;

public class FilenameUtilityTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(FilenameUtilityTest.class);
	private String filename;
	public FilenameUtilityTest() {
		TestConfiguration.getInstance();
		filename = "/Users/luisberrocal/Documents/ws_java/jsimpleutils/target/test-classes/log4jprops.xml";
	}
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFilename() {
		String fn = FilenameUtility.getFilename(filename);
		logger.debug("Filename: " + fn);
		assertEquals("log4jprops.xml", fn);
		
	}

	@Test
	public void testGetFileExtension() {
		String result = FilenameUtility.getFileExtension(filename);
		logger.debug("Extension: " + result);
		assertEquals("xml", result);
	}

	@Test
	public void testInsertDate() {
		String result = FilenameUtility.insertDate(filename);
		logger.debug("Name with date: " + result);
		//assertEquals("xml", result);
	}

	@Test
	public void testReplaceExtension() {
		String result = FilenameUtility.replaceExtension(filename, "old");
		logger.debug("File with new extension: " + result);
	}

	@Test
	public void testGetFileSizeStringFileSizeFormat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFileSizeStringFileSizeFormatInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRound() {
		fail("Not yet implemented");
	}

	@Test
	public void testMd5Hash() {
		fail("Not yet implemented");
	}

}
