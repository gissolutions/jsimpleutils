package org.gissolutions.jsimpleutils.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlLiteDB {
	public String sUrl; // for advertising and debug purposes
	private String sDriverName = "org.sqlite.JDBC";
	private String sDriver;
	private Connection conn;
	private Statement statement;
	public int iTimeout = 30;
	
	public SqlLiteDB(String sDbFilename) {
		sUrl = "jdbc:sqlite:" + sDbFilename;
		// sDriverName = getDriverString(sDbUrl); // i.e. we will need a
		// function to split the
		// driver string from the
		// passed URL
		/*
		 * which in itself suggests we may provide a constructor overload which
		 * takes the full URL and the DriverName....
		 */
		setConnection();
	}

	private void setConnection() {
		try {
			Class.forName(sDriverName);
		} catch (Exception e) {
			// connection failed.
			System.out.println("DriverName: " + sDriver + " was not available");
			System.err.println(e);
		}
		try {
			// create a database connection
			conn = DriverManager.getConnection(sUrl);
		} catch (SQLException e) {
			// connection failed.
			System.err.println(e);
		}
	}

	// this method should undoubtedly be public as we'll want to call this
	// to close connections externally to the class
	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	// and we will definitely want to be able to call the following two
	// functions externally since they expose the database
	// behaviour which we are trying to access
	public ResultSet executeQuery(String instruction) throws SQLException {
		ResultSet rs = null;
		try {
			rs = getStatement().executeQuery(instruction);
		} catch (SQLException e) {
			// query failed.
			System.err.println(e);
			return null;
		}
		return rs;
	} // end

	public void execute(String instruction) throws SQLException {
		try {
			getStatement().executeUpdate(instruction);
		} catch (SQLException e) {
			// execution failed.
			System.err.println(e);
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
