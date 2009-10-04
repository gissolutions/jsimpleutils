package org.gissolutions.jsimpleutils.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.gissolutions.jsimpleutils.admin.ApplicationUser;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.gissolutions.jsimpleutils.validation.BusinessError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JSerializerTest {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(JSerializerTest.class);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		JSerializer<BusinessError<ApplicationUser<Integer>>> serializer =
			new JSerializer<BusinessError<ApplicationUser<Integer>>>();
		String fn =TestConfiguration.getExistingTestData("BusinessError.ser");
		BusinessError<ApplicationUser<Integer>> be = serializer.read(new File(fn));
		//TestConfiguration.writeAssertionsToFile(be, "be");
		// Variable exception1 level 1
		java.lang.Exception exception1 = be.getException();
		assertNull(exception1.getCause());
		assertEquals("IO Exception",exception1.getMessage());
		assertEquals("IO Exception",exception1.getLocalizedMessage());

		
		// Variable source1 level 1
		ApplicationUser<Integer> source1 = be.getSource();

		// Variable userId1 level 2
		Integer userId1 = source1.getUserId();
		assertEquals(10, userId1);
		assertEquals("username",source1.getUsername());
		assertEquals("password",source1.getPassword());
		assertEquals("problem",be.getDescription());
	}

	@Test
	public void testWrite() {
		JSerializer<BusinessError<ApplicationUser<Integer>>> serializer =
			new JSerializer<BusinessError<ApplicationUser<Integer>>>();
		ApplicationUser<Integer> user = new ApplicationUser<Integer>();
		user.setUserId(10);
		user.setPassword("password");
		user.setUsername("username");
		
		BusinessError<ApplicationUser<Integer>> be = new BusinessError<ApplicationUser<Integer>>(user, "problem");
		be.setException(new IOException("IO Exception"));
		String fn = TestConfiguration.getOutputFilenameWithDate("BusinessError.ser");
		serializer.write(be, new File(fn));
		String md5 = TestConfiguration.calculateMD5Hash(fn);
		logger.debug("md5: " + md5);
		assertEquals("d5f5caeb469b204ceeda1ba95806dc8c", md5);
		
	}

}
