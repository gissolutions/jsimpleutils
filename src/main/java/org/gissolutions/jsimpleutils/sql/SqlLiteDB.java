package org.gissolutions.jsimpleutils.sql;


public class SqlLiteDB extends GenericDB {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SqlLiteDB.class);
	//private String sDriver;
	
	public SqlLiteDB(String sDbFilename) {
		this.sDriverName= "org.sqlite.JDBC";
		sUrl = "jdbc:sqlite:" + sDbFilename;		
		this.setConnection();
	}

	

}
