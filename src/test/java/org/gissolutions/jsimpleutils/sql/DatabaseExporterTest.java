package org.gissolutions.jsimpleutils.sql;

import static org.junit.Assert.*;

import java.util.Properties;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseExporterTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DatabaseExporterTest.class);
	
	public DatabaseExporterTest() {
		super();
		TestConfiguration.getInstance();
	}

	Properties props;
	@Before
	public void setUp() throws Exception {
		props = new Properties();
		props.setProperty("dbUrl", "jdbc:oracle:thin:@manzanillo:1671:TIGRE");
		props.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
		props.setProperty("username", "MAPADMIN");
		props.setProperty("password", "MAPGIS");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExportTable() {
		DatabaseExporter exporter = new DatabaseExporter(props);
		
		String tablename = "MAPADMIN.MAP_T_AUTHOR";
		String filename = TestConfiguration.getOutputFilenameWithDate(tablename + ".xml");
		exporter.exportTable(filename,tablename, null);
		logger.debug("Table " +  tablename + " written to " +filename);
		
		tablename = "MAPADMIN.MAP_T_CATEGORIES";
		filename = TestConfiguration.getOutputFilenameWithDate(tablename + ".xml");
		exporter.exportTable(filename,tablename, null);
		logger.debug("Table " +  tablename + " written to " +filename);
		
	}
	
	@Test
	public void testExportTable_With_Select() {
		DatabaseExporter exporter = new DatabaseExporter(props);
		
		String tablename = "MAPADMIN.MAP_T_MAPS";
		String sql ="Select mapid, mapcode from " + tablename + " WHERE MAPID = 727";
		String filename = TestConfiguration.getOutputFilenameWithDate(tablename + ".xml");
		exporter.exportTable(filename,tablename, null);
		logger.debug("Table " +  tablename + " written to " +filename);
		
	}

}
