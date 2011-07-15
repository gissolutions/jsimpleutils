package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IndexTest {
	private Table eventTable;
	private Index idx;
	@Before
	public void setUp() throws Exception {
		eventTable = TestData.EVENT_TABLE;
		idx = new Index("event_name_idx", this.eventTable, "name");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement() {
		
		String sql = idx.getCreateSQLStatement();
		System.out.println("SQL: " + sql);
		assertEquals("CREATE INDEX event_name_idx ON events (name);", sql);
	}

	@Test
	public void testGetColumnNames() {
		String sql = idx.getDropSQLStatement();
		System.out.println("SQL: " + sql);
		assertEquals("DROP INDEX IF EXISTS event_name_idx;", sql);
	}

}
