package org.gissolutions.jsimpleutils.junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.gissolutions.jsimpleutils.admin.ApplicationUser;
import org.gissolutions.jsimpleutils.io.JSerializer;
import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.gissolutions.jsimpleutils.logging.FormattedLoggerTest;
import org.gissolutions.jsimpleutils.maven.Artifact;
import org.gissolutions.jsimpleutils.validation.BusinessError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObjectSpyTest {
	private static FormattedLogger logger =  (FormattedLogger) FormattedLogger.getLogger(ObjectSpyTest.class);
	private BusinessError<ApplicationUser<Integer>> be;
	//private String varName;
	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
//		JSerializer<BusinessError<ApplicationUser<Integer>>> serializer =
//			new JSerializer<BusinessError<ApplicationUser<Integer>>>();
//		String fn =TestConfiguration.getExistingTestData("BusinessError.dat");
//		be = serializer.read(new File(fn));
		
		ApplicationUser<Integer> user = new ApplicationUser<Integer>();
		user.setUserId(10);
		user.setPassword("password");
		user.setUsername("username");
		
		be = new BusinessError<ApplicationUser<Integer>>(user, "problem");
		be.setException(new IOException("IO Exception"));
		//varName = "be";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGetters() {
		List<Method> methods = ObjectSpy.getGetters(new Artifact());
		//Method met = methods.get(0);
		logger.debug("Methods count %s", methods.size() );
		assertEquals(4, methods.size());
		//TestConfiguration.writeAssertionsToFile(met, "met");
		
	}

	@Test
	public void testGetSetterForProperty() {
		Method met = ObjectSpy.getSetterForProperty(new Artifact(), "artifactId", String.class);
		assertEquals("setArtifactId",met.getName());
		
		//TestConfiguration.writeAssertionsToFile(met, "met");
	}

	@Test
	public void testListFields() {
		String fnout = TestConfiguration.getOutputFilenameWithDate("BusinessErrors_Fields.txt");
		ObjectSpy ospy;
		try {
			ospy = new ObjectSpy(new File(fnout));
			ospy.listFields(this.be);
			String md5 = TestConfiguration.calculateMD5Hash(fnout);
			logger.debug("md5: " + md5);
			//assertEquals("", md5);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
		
	}

}
