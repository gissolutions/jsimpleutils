package org.gissolutions.jsimpleutils.sql.android;

import org.gissolutions.jsimpleutils.sql.android.SelectStatement.SelectType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectStatementTest {
	private Table eventTable;
	private Table tagTable;
	private Table taggingTable;
	@Before
	public void setUp() throws Exception {
		eventTable = TestData.EVENT_TABLE;
		tagTable = TestData.TAG_TABLE;
		taggingTable = TestData.TAGGING_TABLE;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		SelectStatement sel = new SelectStatement();
		//Defining tables
		sel.addTable(eventTable, SelectType.FROM);
		sel.addTable(taggingTable, SelectType.INNER_JOIN);
		sel.addTable(tagTable, SelectType.INNER_JOIN);
		//Defining Columns
		sel.addColumn(eventTable, "_id", "event_id");
		sel.addColumn(eventTable, "name",null);
		sel.addColumn(tagTable, "_id", "tag_id");
		sel.addColumn(tagTable, "is_triple", null);
		//Joins
		sel.addInnerJoin(taggingTable.getAlias(), "event_id", eventTable.getAlias(), "_id");
		sel.addInnerJoin(tagTable.getAlias(), "_id", taggingTable.getAlias(), "tag_id");
		System.out.println("SQL: " + sel.toString());
	}

}
