package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FTSTableTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement() {
		System.out.println("**** testGetCreateSQLStatement");
		String sql = TestData.SEARCHABLE_EVENT_TABLE.getCreateSQLStatement();
		System.out.println("SQL: " +  sql);
		assertEquals("CREATE VIRTUAL TABLE searchable_events USING fts3(eventId, name, location, comment);", sql);
	}

}
