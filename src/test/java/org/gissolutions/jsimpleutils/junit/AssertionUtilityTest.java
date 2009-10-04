package org.gissolutions.jsimpleutils.junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.gissolutions.jsimpleutils.admin.ApplicationUser;
import org.gissolutions.jsimpleutils.io.JSerializer;
import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.gissolutions.jsimpleutils.validation.BusinessError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AssertionUtilityTest {
	private static FormattedLogger logger = (FormattedLogger) FormattedLogger
			.getLogger(AssertionUtilityTest.class);
	private BusinessError<ApplicationUser<Integer>> be;
	private String varName;
	@Before
	public void setUp() throws Exception {
		JSerializer<BusinessError<ApplicationUser<Integer>>> serializer =
			new JSerializer<BusinessError<ApplicationUser<Integer>>>();
		String fn =TestConfiguration.getExistingTestData("BusinessError.dat");
		be = serializer.read(new File(fn));
		
		varName = "be";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWriteAssertions() {
		
		String fnout = TestConfiguration.getOutputFilenameWithDate(varName  + ".txt");
		AssertionUtility au;
		try {
			au = new AssertionUtility(new File(fnout));
			au.writeAssertions(be, varName);
			String md5 = TestConfiguration.calculateMD5Hash(fnout);
			logger.debug("md5: " + md5);
			assertEquals("3b50224f879162930b8ad00a0e428f9f", md5);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
	}
	
	

}
