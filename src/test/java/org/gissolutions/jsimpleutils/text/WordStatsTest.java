package org.gissolutions.jsimpleutils.text;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordStatsTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(WordStatsTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		String fn = TestConfiguration.getExistingTestData("HSurface.txt");
		String db = TestConfiguration.getOutputFilenameWithDate("HSurface.db");
		try {
			WordStats ws = new WordStats(new File(fn), new File(db));
			ws.run();
			ResultSet rs = ws.getStatsResultSet();
			while (rs.next()) {
				String sResult = rs.getString("word");
				int p = rs.getInt("points");
				int c = rs.getInt("count");
				int imp = rs.getInt("importance");
				String format = "|%1$-25s|%2$-5s|%3$-5s|%4$-5s|";

				System.out.println(String.format(format, sResult, p, c, imp));
			}
		} catch (SQLException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
	}

}
