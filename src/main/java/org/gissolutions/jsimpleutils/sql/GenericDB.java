package org.gissolutions.jsimpleutils.sql;

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
			// connection failed.
			System.err.println(e);
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
			System.out.println("DriverName: " + sDriverName + " was not available");
			System.err.println(e);
		}
	}
	protected void setConnection(String username, String password) {
		this.loadDriver();
		try {
			// create a database connection
			conn = DriverManager.getConnection(sUrl, username, password);
		} catch (SQLException e) {
			// connection failed.
			System.err.println(e);
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
			System.err.println(e);
		}
	
		return statement;
	}

}