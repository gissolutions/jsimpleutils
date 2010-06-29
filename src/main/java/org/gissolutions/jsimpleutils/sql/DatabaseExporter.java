package org.gissolutions.jsimpleutils.sql;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.util.search.SearchException;

public class DatabaseExporter {
	
	
	public void export(Properties dbprops, String filename) throws  SQLException, DataSetException, FileNotFoundException, IOException, SearchException {
		// database connection
        loadDriver(dbprops.getProperty("driverClassName"));
        String dbUrl = dbprops.getProperty("dbUrl");//"jdbc:hsqldb:sample";
		String username = dbprops.getProperty("username");//"sa";
		String password = dbprops.getProperty("password");//"";
		IDatabaseConnection connection = buildConnection(dbUrl, username,
				password);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
        partialDataSet.addTable("BAR");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream(filename));

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
