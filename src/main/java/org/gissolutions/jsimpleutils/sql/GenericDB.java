package org.gissolutions.jsimpleutils.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GenericDB {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(GenericDB.class);
	public String sUrl;
	protected String sDriverName;
	protected Connection conn;
	private Statement statement;
	public int iTimeout = 30;

	public GenericDB() {
		super();
	}
	
	protected void setConnection() {
		loadDriver();
		try {
			// create a database connection
			conn = DriverManager.getConnection(sUrl);
		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			//System.err.println(e);
		}
	}

	/**
	 * 
	 */
	private void loadDriver() {
		try {
			Class.forName(sDriverName);
		} catch (Exception e) {
			// connection failed.
			System.out.println("DriverName: " + sDriverName + " was not available ");
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			//System.err.println(e);
		}
	}
	protected void setConnection(String username, String password) {
		this.loadDriver();
		try {
			// create a database connection
			conn = DriverManager.getConnection(sUrl, username, password);
		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
	}
	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public ResultSet executeQuery(String instruction) throws SQLException {
		ResultSet rs = null;
		try {
			rs = getStatement().executeQuery(instruction);
		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw e;
		}
		return rs;
	} // end

	public void execute(String instruction) throws SQLException {
		try {
			getStatement().executeUpdate(instruction);
		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw e;
		}
	}
	public void execute(File sqlFile) throws SQLException, FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
		StringBuffer sb = new StringBuffer();
		String str;
	    try {
			while ((str = reader.readLine()) != null) {
			   sb.append(str);			   
			}
			 reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   String sql = sb.toString();
	   this.execute(sql);


	}
	/**
	 * @return the statement
	 */
	public Statement getStatement() {
		if (conn == null) {
			setConnection();
		}
		try {
			statement = conn.createStatement();
			statement.setQueryTimeout(iTimeout); // set timeout to 30 sec.
		} catch (Exception e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
	
		return statement;
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}