package org.gissolutions.jsimpleutils.sql;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DatabaseExporter {
	
	
	private String dbUrl;
	private String username;
	private String password;
	private String driverClassName;
	
	public DatabaseExporter(Properties dbprops) {
		super();
		dbUrl = dbprops.getProperty("dbUrl");
		username = dbprops.getProperty("username");
		password = dbprops.getProperty("password");
		driverClassName = dbprops.getProperty("driverClassName");
	}

	public void exportTable(String filename, String table, String sql)  {
		// database connection
        loadDriver(driverClassName);
        
		IDatabaseConnection connection = buildConnection(dbUrl, username,
				password);
		DatabaseConfig config = connection.getConfig();
		String id ="http://www.dbunit.org/features/qualifiedTableNames";
		config.setFeature(id, true);
		
        // partial database export
        QueryDataSet partialDataSet = null;
		try {
			partialDataSet = new QueryDataSet(connection);
			if(sql ==null) {
	        	partialDataSet.addTable(table);
	        }else {
	        	partialDataSet.addTable(table, sql);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
       // partialDataSet.addTable("BAR");
        try {
			FlatXmlDataSet.write(partialDataSet, new FileOutputStream(filename));
		} catch (DataSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//        // full database export
//        IDataSet fullDataSet = connection.createDataSet();
//        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
//        
//        // dependent tables database export: export table X and all tables that
//        // have a PK which is a FK on X, in the right order for insertion
//        String[] depTableNames = 
//          TablesDependencyHelper.getAllDependentTables( connection, "X" );
//        
//        IDataSet depDataset = connection.createDataSet( depTableNames );
//        
//        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml")); 
	}

	private IDatabaseConnection buildConnection(String dbUrl, String username,
			String password)  {
		IDatabaseConnection connection = null;
		try {
			Connection jdbcConnection = DriverManager.getConnection(dbUrl,
					username, password);
			connection = new DatabaseConnection(jdbcConnection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	private void loadDriver(String driverClassName)  {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
