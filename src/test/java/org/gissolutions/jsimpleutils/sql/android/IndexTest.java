package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.sql.android.Column.ColumnType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IndexTest {
	private Table eventTable;
	@Before
	public void setUp() throws Exception {
		eventTable = TestData.EventTable.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement() {
		Index idx = new Index("event_name_idx", this.eventTable, "name");
		String sql = idx.getCreateSQLStatement();
		System.out.println("SQL: " + sql);
	}

	@Test
	public void testGetColumnNames() {
		fail("Not yet implemented");
	}

}
