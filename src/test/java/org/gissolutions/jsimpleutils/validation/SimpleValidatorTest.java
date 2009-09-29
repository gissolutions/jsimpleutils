package org.gissolutions.jsimpleutils.validation;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.admin.ApplicationUser;
import org.gissolutions.jsimpleutils.admin.IApplicationUser;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleValidatorTest {

	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidate() {
		IValidator<ApplicationUser<Integer>> validator = new SimpleValidator<ApplicationUser<Integer>>();
		ApplicationUser<Integer> user = new ApplicationUser<Integer>();
		user.setPassword("Conundrum09");
		user.setUsername("lberrocal");
		user.setUserId(30);
		validator.validate(user);
	}

}
