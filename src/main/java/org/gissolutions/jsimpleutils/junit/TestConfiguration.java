package org.gissolutions.jsimpleutils.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;
import org.gissolutions.jsimpleutils.io.FilenameUtility;

/**
 * 
 * @author LBerrocal
 *
 */
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
	/**
	 * Calculates a full path filename with a timestamp appended. The method appends
	 * to the relativePath supplied the outputPath (from {@link #getOutputPath()}) in front 
	 * and a timestamp at the end. The method uses the method {@link FilenameUtility#insertDate(String)}
	 * to calculate the date.   
	 * @param relativePath  relative path of the file
	 * @return full path from outputPath with timestamp insert.
	 */
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
	
	public static String calculateMD5Hash(String filename) {
		String md5 =null;
		try {
			md5= FilenameUtility.md5Hash(filename);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		
		return md5;
	}
	public static void main(String[] args) {
		System.out.println("Output path: " + TestConfiguration.getInstance().getOutputPath());
		System.out.println("Test Data path: " + TestConfiguration.getInstance().getTestDataPath());
	}

	public URL getLog4jConfigUrl() {
		return log4jConfigUrl;
	}
}
