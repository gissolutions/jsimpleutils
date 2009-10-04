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
	private static FormattedLogger logger = FormattedLogger
			.getLogger(AssertionUtilityTest.class);
	private BusinessError<ApplicationUser<Integer>> be;
	private String varName;
	@Before
	public void setUp() throws Exception {
		JSerializer<BusinessError<ApplicationUser<Integer>>> serializer =
			new JSerializer<BusinessError<ApplicationUser<Integer>>>();
		String fn =TestConfiguration.getExistingTestData("BusinessError.ser");
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
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
	}
	
	

}
