package org.gissolutions.jsimpleutils.admin;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.gissolutions.jsimpleutils.validation.BusinessError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationUserValidatorTest {
	private static FormattedLogger logger = (FormattedLogger) FormattedLogger
			.getLogger(ApplicationUserValidatorTest.class);
	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidate() {
		ApplicationUserValidator<Integer> validator = new ApplicationUserValidator<Integer>();
		ApplicationUser<Integer> user = new ApplicationUser<Integer>();
		user.setPassword("_Wolf09");
		user.setUserId(2);
		user.setUsername("lberrocal");
		
		validator.validate(user);
		
		for (BusinessError<ApplicationUser<Integer>> be : validator.getErrors()) {
			logger.debug("Error: %s", be.getDescription());
			//TestConfiguration.writeAssertionsToFile(be, "be");
			assertNull(be.getException());

			// Variable source1 level 1
			ApplicationUser<Integer> source1 = be.getSource();

			// Variable userId1 level 2
			//Integer userId1 = source1.getUserId();
			assertEquals("lberrocal",source1.getUsername());
			assertEquals("_Wolf09",source1.getPassword());
			assertEquals("El valor '_Wolf09' del campo 'password' no cumple con la expresión '(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$'",be.getDescription());
		}
	}

}
