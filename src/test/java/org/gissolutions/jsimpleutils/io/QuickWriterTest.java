package org.gissolutions.jsimpleutils.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickWriterTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(QuickWriterTest.class);
	
	public QuickWriterTest() {
		super();
		TestConfiguration.getInstance();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWriteln() {
		File dir= new File("C:/Documents and Settings/luisberrocal/workspace/jsimpleutils/src/test/java/org/gissolutions/jsimpleutils");
		File[] files =dir.listFiles();
		
		String out = TestConfiguration.getOutputFilenameWithDate("quick_w.csv");
		try {
			QuickWriter qw = new QuickWriter(new File(out));
			int c=1;
			for (File file : files) {
				qw.writeln(c,file.getName(),file.getAbsolutePath(), file.isFile());
				c++;
			}
			qw.close();
		} catch (UnsupportedEncodingException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
		
	}

}
