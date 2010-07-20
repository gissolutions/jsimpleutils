package org.gissolutions.jsimpleutils.aims;

import static org.junit.Assert.*;

import java.util.List;

import org.gissolutions.jsimpleutils.datetime.StopWatch;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HTTPConnectorTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HTTPConnectorTest.class);
	public HTTPConnectorTest() {
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetClientServices() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendRequest() {
		String axl ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ARCXML version=\"1.1\">" +
				"		<REQUEST>		<GET_IMAGE><PROPERTIES>" +
				"		<IMAGESIZE height=\"142\" width=\"329\" />" +
				"		<BACKGROUND color=\"51,153,255\" />" +
				"		</PROPERTIES>" +
				"		</GET_IMAGE>" +
				"		</REQUEST>" +
				"		</ARCXML>";
		IAIMSConnector connector = new HTTPConnector("manzanillo");
		ServiceAdapter sa = new ServiceAdapter(connector);
		try {
			List<String> services = sa.getServicesNames();
			for (String ser : services) {
				double totalTime = 0;
				for (int i = 0; i < 50; i++) {
					StopWatch sw = new StopWatch();
					String resp = connector.sendRequest(ser, axl);
					sw.stop();
					totalTime += sw.getElapsedSeconds();
					
					logger.debug(i + "Service: " +ser + " time: " +  sw.getElapsedSeconds());
				}
				double average = totalTime /50;
			}
		} catch (AIMSConnectionException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
		
		
	}

	@Test
	public void testCleanResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testPing() {
		fail("Not yet implemented");
	}

}
