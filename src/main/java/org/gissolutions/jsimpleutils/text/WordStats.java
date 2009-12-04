package org.gissolutions.jsimpleutils.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.gissolutions.jsimpleutils.sql.GenericDB;
import org.gissolutions.jsimpleutils.sql.SqlLiteDB;

public class WordStats {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(WordStats.class);
	private File source;
	//private int wordCount;
	private final File statsDatabse;
	private GenericDB db;

	public WordStats(File source, File statsDatabse) throws SQLException {
		super();
		this.source = source;
		this.statsDatabse = statsDatabse;
		String sMakeTable = "CREATE TABLE words (word text, count numeric, importance numeric)";
		db = new SqlLiteDB(this.statsDatabse.getAbsolutePath());
		db.execute(sMakeTable);

	}

	public void run() {
		int lineNum=1;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(source));
			String str;
			while ((str = reader.readLine()) != null) {
				process(str);
				lineNum++;
			}
			reader.close();

		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (SQLException e) {
			String msg = "Error on line %s for file %s:  %s";
			msg = String.format(msg, lineNum, this.source.getName(),  e.getMessage());
			logger.error(msg);
		}

	}

	private void process(String line) throws SQLException {
		String[] words = line.split("\\s");
		for (String word : words) {
			processWord(word);
		}
	}

	private void processWord(String word) throws SQLException {
		int wordCount = getWordCount(word);
		if (wordCount != 0) {
			incrementCounter(word, wordCount);
		} else {
			insertWord(word);
		}
	}

	private int getWordCount(String word) throws SQLException {
		String fs = "SELECT count FROM words WHERE word = '" + word + "'";
		ResultSet rs = db.executeQuery(fs);
		if (rs.next()) {
			return rs.getInt("count");
		} else {
			return 0;
		}
	}

	private void insertWord(String word) throws SQLException {
		int imp = word.length();
		db.execute("INSERT INTO words VALUES('" + word + "',1,"+ imp +")");

	}

	private void incrementCounter(String word, int wCount) throws SQLException {
		wCount++;
		String us = "UPDATE words SET count = " + wCount + " WHERE word = '"
				+ word + "'";
		db.execute(us);

	}

	ResultSet getStatsResultSet() throws SQLException {
		ResultSet rs = db.executeQuery("SELECT word, count, importance, count*importance  'points'" +
				" FROM words ORDER BY points DESC, word");
		return rs;
	}

}
