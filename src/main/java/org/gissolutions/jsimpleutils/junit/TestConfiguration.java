package org.gissolutions.jsimpleutils.junit;

import java.io.File;
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
	public static void main(String[] args) {
		System.out.println("Output path: " + TestConfiguration.getInstance().getOutputPath());
		System.out.println("Test Data path: " + TestConfiguration.getInstance().getTestDataPath());
	}

	public URL getLog4jConfigUrl() {
		return log4jConfigUrl;
	}
}
