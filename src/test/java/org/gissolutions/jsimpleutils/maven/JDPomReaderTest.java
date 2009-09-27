package org.gissolutions.jsimpleutils.maven;

import static org.junit.Assert.*;

import java.io.File;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JDPomReaderTest {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(JDPomReaderTest.class);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		String fn = TestConfiguration.getExistingTestData("pom.xml");
		IPomReader reader = new JDPomReader();
		try {
			Artifact art = reader.read(new File(fn));
			assertEquals("org.gissolutions",art.getGroupId());
			assertEquals("jsimpleutils",art.getArtifactId());
			assertEquals("0.0.1-SNAPSHOT",art.getVersion());
			//TestConfiguration.writeAssertionsToFile(art, "art");
		} catch (PomParsingException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
		
	}

}
