package org.gissolutions.jsimpleutils.sql;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlLiteDBTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SqlLiteDBTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecuteQuery() {
		String fn = TestConfiguration.getOutputFilenameWithDate("sqllite.db");
		GenericDB db = new SqlLiteDB(fn);
		String sMakeTable = "CREATE TABLE words (word text, count numeric)";
		try {
			db.execute(sMakeTable);
			db.execute("INSERT INTO words VALUES('Hello',2)");
			db.execute("INSERT INTO words VALUES('Test',4)");
			db.execute("INSERT INTO words VALUES('Tercio',8)");
			db.execute("INSERT INTO words VALUES('caballo',3)");
			ResultSet rs = db
					.executeQuery("SELECT word, count FROM words ORDER BY count");
			while (rs.next()) {
				String sResult = rs.getString("word");
				int c = rs.getInt("count");
				System.out.println(sResult + " : " + c);
			}

		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}

	}

	@Test
	public void testExecuteQuery2() {
		String fn = TestConfiguration
				.getOutputFilenameWithDate("sqllite_words.db");
		GenericDB db = new SqlLiteDB(fn);
		String sMakeTable = "CREATE TABLE words (word text, count numeric)";
		String source = "SQLite version 3 introduces the concept of manifest "
				+ "typing, where the type of a value is associated with the "
				+ "value itself, not the column that it is stored in. This "
				+ "page describes data typing for SQLite version 3 in further "
				+ "detail. "
				+ " /**  * A commented regular expression for fully-qualified type names which * follow the common naming conventions, for example, \"com.myappBlah.Thing\""
				+ "* Thus, the \"dot + capital letter\" is sufficient to define where the " 
				+ "  * This regular expression uses two groups, one for the package, and one";

		String[] words = source.split("\\s");
		try {
			db.execute(sMakeTable);
			for (String w : words) {
				String fs = "SELECT word, count FROM words WHERE word = '" + w
						+ "'";
				ResultSet rs = db.executeQuery(fs);
				if (rs.next()) {
					int c = rs.getInt("count") + 1;
					//rs.close();
					String us = "UPDATE words SET count = " + c
							+ " WHERE word = '" + w + "'";
					db.execute(us);
				} else {
					//String sResult = rs.getString("word");
					//rs.close();
					db.execute("INSERT INTO words VALUES('" + w + "',1)");
				}
			}
			ResultSet rs = db
					.executeQuery("SELECT word, count FROM words ORDER BY count DESC, word");
			while (rs.next()) {
				String sResult = rs.getString("word");
				int c = rs.getInt("count");
				  String format = "|%1$-25s|%2$-10s|";

				System.out.println(String.format(format, sResult, c));
			}

		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

}
