package org.gissolutions.jsimpleutils.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;
import org.gissolutions.jsimpleutils.io.FilenameUtility;


public class TestConfiguration {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TestConfiguration.class);
	private static TestConfiguration testConfig = null;
	private final String outputPath;
	private final String testDataPath;
	private final URL log4jConfigUrl;
	
	protected TestConfiguration() {
		File out = new File("output");
		File data = new File("test_data");
		
		this.outputPath = out.getAbsolutePath();
		this.testDataPath = data.getAbsolutePath();
		String configfile = "/log4jprops.xml";
		log4jConfigUrl = this.getClass().getResource(configfile);
		DOMConfigurator.configure(log4jConfigUrl);
		logger.info("Log4j configured with " + log4jConfigUrl.getFile());
		if(!out.exists()) {
			out.mkdirs();
			logger.info("Created output path " + this.outputPath);
		}
		if(!data.exists()) {
			data.mkdirs();
			logger.info("Created test data path " + this.testDataPath);
		}
		
	}

	public static TestConfiguration getInstance() {
		if (testConfig == null) {
			testConfig = new TestConfiguration();
		}
		return testConfig;
	}

	public String getOutputPath() {
		return outputPath;
	}
	
	public String getTestDataPath() {
		return testDataPath;
	}
	
	public static String getExistingTestData(String relativePath) {
		String tpath = TestConfiguration.getInstance().getTestDataPath();
		File f = new File(tpath + File.separator + relativePath);
		if(f.exists()) {
			return f.getAbsolutePath();
		}else {
			throw new IllegalArgumentException(f.getAbsolutePath() + " no existe");
		}
	}
	public static String getOutputFilenameWithDate(String relativePath) {
		String tpath = TestConfiguration.getInstance().getOutputPath();
		File f = new File(tpath + File.separator + relativePath);
		String fn = FilenameUtility.insertDate(f.getAbsolutePath());
		
		return fn;
	}
	
	public static void writeAssertionsToFile(Object obj, String varName) {
		String fnout = getOutputFilenameWithDate(varName + ".txt");
		AssertionUtility au;
		try {
			au = new AssertionUtility(new File(fnout));
			au.writeAssertions(obj, varName);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		
	}
	public static void main(String[] args) {
		System.out.println("Output path: " + TestConfiguration.getInstance().getOutputPath());
		System.out.println("Test Data path: " + TestConfiguration.getInstance().getTestDataPath());
	}

	public URL getLog4jConfigUrl() {
		return log4jConfigUrl;
	}
}
