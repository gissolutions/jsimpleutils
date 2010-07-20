package org.gissolutions.jsimpleutils.aims;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.gissolutions.jsimpleutils.datetime.DateTimeUtility;
import org.gissolutions.jsimpleutils.datetime.StopWatch;
import org.gissolutions.jsimpleutils.io.FilenameUtility;
import org.gissolutions.jsimpleutils.io.QuickWriter;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;

public class PerformanceTest implements Runnable{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PerformanceTest.class);
	private final IAIMSConnector connector;
	private final String server;
	private final String service;
	private int reps;
	public PerformanceTest(String server, String service, int reps) {
		super();
		this.server = server;
		this.service = service;
		this.connector = new HTTPConnector(server);
		this.reps = reps;
	}
	
	public double doGetImage() throws AIMSConnectionException {
		String axl ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ARCXML version=\"1.1\">" +
		"		<REQUEST>		<GET_IMAGE><PROPERTIES>" +
		"		<IMAGESIZE height=\"142\" width=\"329\" />" +
		"		<BACKGROUND color=\"51,153,255\" />" +
		"		</PROPERTIES>" +
		"		</GET_IMAGE>" +
		"		</REQUEST>" +
		"		</ARCXML>";
		StopWatch sw = new StopWatch();
		try {
			//String resp = 
			connector.sendRequest(service, axl);
		} catch (AIMSConnectionException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw e;
		}
		sw.stop();
		return sw.getElapsedSeconds();
	}
	
	@Override
	public void run() {
		String filename = FilenameUtility.insertDate("C:/Temp/" + server +"_" + service +".csv");
		QuickWriter qw = null;
		try {
			qw = new QuickWriter(new File(filename));
		} catch (UnsupportedEncodingException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		for (int i = 0; i < this.reps; i++) {
			long sleep = (long) (Math.random() * 5000.0);
			
			try {
				double t = this.doGetImage();
				Date dt = new Date();
				String dtstr = DateTimeUtility.DATETIME_FORMAT.format(dt);
				qw.writeln(service, "GET_IMAGE", dtstr, t);
				logger.debug( server.toUpperCase()+ "@" + service.toUpperCase() + 
						" rep " + i + " Sleeping " + sleep/1000);
				
				//Thread.currentThread();
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				String msg = "%s: %s";
				msg = String
						.format(msg, e.getClass().getName(), e.getMessage());
				logger.error(msg);
			} catch (AIMSConnectionException e) {
				String msg = "%s: %s";
				msg = String
						.format(msg, e.getClass().getName(), e.getMessage());
				logger.error(msg);
			} catch (IOException e) {
				String msg = "%s: %s";
				msg = String
						.format(msg, e.getClass().getName(), e.getMessage());
				logger.error(msg);
			}
		}
		try {
			qw.close();
			logger.debug("File writent to " + filename);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		
	}
	
	public static void main(String[] args) {
		int reps = 30;
		TestConfiguration.getInstance();
		Runnable r1 = new PerformanceTest("manzanillo", "bd_hidromet",reps);
		Thread t1 = new Thread(r1);
		t1.start();
		
		Runnable r2 = new PerformanceTest("arcgis-test", "bd_hidromet",reps);
		Thread t2 = new Thread(r2);
		t2.start();
		
//		Runnable r3 = new PerformanceTest("manzanillo", "bdh_ov",reps);
//		Thread t3 = new Thread(r3);
//		t3.start();
//		
//		Runnable r4= new PerformanceTest("arcgis-test", "bdh_ov",reps);
//		Thread t4 = new Thread(r4);
//		t4.start();
		
	}
	
}
