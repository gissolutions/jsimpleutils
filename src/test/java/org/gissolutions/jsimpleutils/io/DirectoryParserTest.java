package org.gissolutions.jsimpleutils.io;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectoryParserTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DirectoryParserTest.class);
	private DirectoryParser dp;
	private int acceptedCount;
	private int rejectedCount;
	private List<File> ignored;
	
	public DirectoryParserTest() {
		super();
		TestConfiguration.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		ignored = new ArrayList<File>();
		acceptedCount = 0;
		rejectedCount = 0;
		dp = new DirectoryParser();
		dp.addAcceptPattern(Pattern.compile(".*\\.skp$", Pattern.CASE_INSENSITIVE));
		dp.addFileAcceptedListener(new IFileAcceptedListener() {

			@Override
			public void fileAcceptedOcurred(FileAcceptedEvent event) {
				acceptedCount++;
				System.out.println(String.format("%s. %s", acceptedCount, event
						.getAcceptedFile().getName()));
			}

		});
		dp.addFileRejectedListener(new IFileRejectedListener() {

			@Override
			public void fileRejectedOcurred(FileRejectedEvent event) {
				rejectedCount++;

			}

		});
		
		dp.addFileIgnoredListener(new IFileIgnoredListener() {

			@Override
			public void fileIgnoredOcurred(FileIgnoredEvent event) {
				ignored.add(event.getFile());
				
			}
			
		});
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFindFile() {
		File directory = new File(
				"C:/Temp");
		dp.find(directory);
		logger.debug("Rejected: " + this.rejectedCount);
//		int c=0;
//		for (File file : this.ignored) {
//			c++;
//			logger.debug(c + " Ignored: " + file.getName());
//		}
	}

}
