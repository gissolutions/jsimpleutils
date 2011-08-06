package org.gissolutions.jsimpleutils.junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConfigurationTest {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TestConfigurationTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		TestConfiguration.getInstance();
	}

	@Test
	public void testMain() {
		TestConfiguration.main(null);
	}

	@Test
	public void testGetExistingTestData() {
		String fn = TestConfiguration.getExistingTestData("pom.xml");
		String md5 = TestConfiguration.calculateMD5Hash(fn);
		logger.debug("md5: " + md5);
		assertEquals("c2827798eca02e9bc0ce6ac1bfeb44d1", md5);
	}

	@Test
	public void testCalculateMD5Hash() {
		String fn = TestConfiguration.getExistingTestData("pom.xml");
		String md5 = TestConfiguration.calculateMD5Hash(fn);
		logger.debug("md5: " + md5);
		assertEquals("c2827798eca02e9bc0ce6ac1bfeb44d1", md5);
	}
	public void testc() {
		String o = TestConfiguration.getInstance().getOutputPath();
		logger.debug("Output path: " +  o);
	}
	@Test
	public void testSQLite() {
		try {
			Class.forName("org.sqlite.JDBC");
			String f = TestConfiguration.getOutputFilenameWithDate("test.db");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+f);
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");
			PreparedStatement prep = conn
					.prepareStatement("insert into people values (?, ?);");

			prep.setString(1, "Gandhi");
			prep.setString(2, "politics");
			prep.addBatch();
			prep.setString(1, "Turing");
			prep.setString(2, "computers");
			prep.addBatch();
			prep.setString(1, "Wittgenstein");
			prep.setString(2, "smartypants");
			prep.addBatch();

			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);

			ResultSet rs = stat.executeQuery("select * from people;");
			while (rs.next()) {
				System.out.println("name = " + rs.getString("name"));
				System.out.println("job = " + rs.getString("occupation"));
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			fail(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
