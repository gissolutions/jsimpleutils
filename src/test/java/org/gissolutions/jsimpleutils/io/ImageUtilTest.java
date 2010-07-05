package org.gissolutions.jsimpleutils.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.gissolutions.jsimpleutils.datetime.StopWatch;
import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImageUtilTest {
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testResizeJPEG() {
		String sourceFilename ="C:/Documents and Settings/lberrocal/My Documents/3D_GIS/Edificios/Balboa/706_CCAA/fotos/IMG_3591.JPG";
		String outFilename = TestConfiguration.getOutputFilenameWithDate("IMG_3591.jpg");
		
		try {
			StopWatch sw = new StopWatch();			
			ImageUtil.resizeJPEG(new File(sourceFilename), new File(outFilename), 300, 1f);
			sw.stop();
			System.out.println("Time: " + sw.getElapsedFormatted());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(outFilename + " written");
		
	}

	@Test
	public void testResize() {
		fail("Not yet implemented");
	}

}
