package org.gissolutions.jsimpleutils.sql;

import java.util.Properties;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;

public class DatabaseExporterTest2 {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DatabaseExporterTest2.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestConfiguration.getInstance();
		Properties props = new Properties();
		props.setProperty("dbUrl", "jdbc:oracle:thin:@manzanillo:1671:TIGRE");
		props.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
		props.setProperty("username", "MAPADMIN");
		props.setProperty("password", "MAPGIS");
		
		DatabaseExporter exporter = new DatabaseExporter(props);
		
		String tablename = "MAPADMIN.MAP_T_MAPS";
		String sql ="Select mapid, mapcode from " + tablename + " WHERE MAPID = 727";
		String filename = TestConfiguration.getOutputFilenameWithDate(tablename + ".xml");
		exporter.exportTable(filename,tablename, null);
		logger.debug("Table " +  tablename + " written to " +filename);

	}

}
