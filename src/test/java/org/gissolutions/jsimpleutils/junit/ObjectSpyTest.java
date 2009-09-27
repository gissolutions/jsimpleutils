package org.gissolutions.jsimpleutils.junit;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.gissolutions.jsimpleutils.logging.FormattedLoggerTest;
import org.gissolutions.jsimpleutils.maven.Artifact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObjectSpyTest {
	private static FormattedLogger logger =  FormattedLogger.getLogger(ObjectSpyTest.class);
	@Before
	public void setUp() throws Exception {
		TestConfiguration.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGetters() {
		List<Method> methods = ObjectSpy.getGetters(new Artifact());
		Method met = methods.get(0);
		logger.debug("Methods count %s", methods.size() );
		TestConfiguration.writeAssertionsToFile(met, "met");
		
	}

	@Test
	public void testGetSetterForProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsMethodGetter() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsMethodSetter() {
		fail("Not yet implemented");
	}

	@Test
	public void testPropertyName() {
		fail("Not yet implemented");
	}

}
