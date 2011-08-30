package org.gissolutions.jsimpleutils.io;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectoryScannerTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DirectoryScannerTest.class);
	private DirectoryScanner dp;
	private int acceptedCount;
	private int rejectedCount;
	//private List<File> ignored;
	private BufferedWriter writer;
	
	public DirectoryScannerTest() {
		super();
		TestConfiguration.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		String filename = TestConfiguration.getOutputFilenameWithDate("dwg-scan-delta2-gissvrd.csv");
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"UTF8"));
		
		
		
		//ignored = new ArrayList<File>();
		acceptedCount = 0;
		rejectedCount = 0;
		dp = new DirectoryScanner();
		dp.addAcceptPattern(Pattern.compile(".*\\.dwg$", Pattern.CASE_INSENSITIVE));
		//dp.addAcceptPattern(Pattern.compile(".*\\.dwg$", Pattern.CASE_INSENSITIVE));
		dp.addFileAcceptedListener(new IFileAcceptedListener() {

			@Override
			public void fileAcceptedOcurred(FileAcceptedEvent event) {
				acceptedCount++;
				System.out.println(String.format("%s. %s", acceptedCount, event
						.getAcceptedFile().getAbsolutePath()));
				String template ="%s,%s,%s";
				String path = event.getAcceptedFile().getPath();
				try {
					writer.write(String.format(template, acceptedCount, 
							path, event.getAcceptedFile().getName()));
					writer.write("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		});
		dp.addFileRejectedListener(new IFileRejectedListener() {

			@Override
			public void fileRejectedOcurred(FileRejectedEvent event) {
				rejectedCount++;

			}

		});
		
//		dp.addFileIgnoredListener(new IFileIgnoredListener() {
//
//			@Override
//			public void fileIgnoredOcurred(FileIgnoredEvent event) {
//				ignored.add(event.getFile());
//				
//			}
//			
//		});
	}

	@After
	public void tearDown() throws Exception {
		writer.close();
	}

	@Test
	public void testFindFile() {
		File directory = new File(
				"\\\\delta-2\\GISSVR_D/gis_projects/AE/AEA/AEAT");
		dp.find(directory);
		logger.debug("Rejected: " + this.rejectedCount);
//		int c=0;
//		for (File file : this.ignored) {
//			c++;
//			logger.debug(c + " Ignored: " + file.getName());
//		}
	}

}
