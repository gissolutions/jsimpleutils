package org.gissolutions.jsimpleutils.aims;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServiceAdapterTest {
	IAIMSConnector connector;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ServiceAdapterTest.class);
	public ServiceAdapterTest() {
		TestConfiguration.getInstance();
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		connector = new HTTPConnector("manzanillo");
	}

	@After
	public void tearDown() throws Exception {
	}
 @Test
 public void testURLDecode() {
	 String data ="ArcXMLRequest=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22+%3F%3E%3CARCXML+version%3D%221.1%22%3E%0D%0A%3C" +
	 		"REQUEST%3E%0D%0A%3CGET_IMAGE%3E%3CPROPERTIES%3E%0D%0A%3CIMAGESIZE+height%3D%22142%22+width%3D%22329%22+%2F%3E%0D%0A%3C" +
	 		"BACKGROUND+color%3D%2251%2C153%2C255%22+%2F%3E%0D%0A%3C%2FPROPERTIES%3E%0D%0A%3C%2FGET_IMAGE%3E%0D%0A%3C%2F" +
	 		"REQUEST%3E%0D%0A%3C%2FARCXML%3E&JavaScriptFunction=parent.MapFrame.processXML&RedirectURL=&BgColor=%23000000&FormCharset=ISO-8859-1";
	 
	 try {
		String udata = URLDecoder.decode(data, "UTF-8");
		logger.debug(">>>DATA: " + udata);
	} catch (UnsupportedEncodingException e) {
		String msg = "%s: %s";
		msg = String.format(msg, e.getClass().getName(), e.getMessage());
		logger.error(msg);
		fail(msg);
		
	}
	 
 }
	@Test
	public void testGetServicesNames() {
		ServiceAdapter sa = new ServiceAdapter(connector);
		try {
			List<String> sers = sa.getServicesNames();
			for (String ser : sers) {
				logger.debug(ser);
			}
		} catch (AIMSConnectionException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}		
	}

}
