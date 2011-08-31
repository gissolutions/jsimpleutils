package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.assertEquals;

import org.gissolutions.jsimpleutils.sql.android.SelectStatement.SelectType;
import org.gissolutions.jsimpleutils.sql.android.TestData.EventTable;
import org.gissolutions.jsimpleutils.sql.android.TestData.TagTable;
import org.gissolutions.jsimpleutils.sql.android.TestData.TaggingTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectStatementTest {
	private EventTable eventTable;
	private TagTable tagTable;
	private TaggingTable taggingTable;
	private SelectStatement sel;
	private SelectStatement selectStmt;
	
	@Before
	public void setUp() throws Exception {
		eventTable = TestData.EVENT_TABLE;
		tagTable = TestData.TAG_TABLE;
		taggingTable = TestData.TAGGING_TABLE;
		
		sel = new SelectStatement();
		//Defining tables
		sel.addTable(eventTable, SelectType.FROM);
		sel.addTable(taggingTable, SelectType.INNER_JOIN);
		sel.addTable(tagTable, SelectType.INNER_JOIN);
		//Defining Columns
		sel.addColumn(eventTable, "_id", "event_id");
		sel.addColumn(eventTable, "name",null);
		sel.addColumn(tagTable, "_id", "tag_id");
		sel.addColumn(tagTable, "is_triple", null);
		sel.addColumn(tagTable, "triple_namespace", null);
		sel.addColumn(tagTable, "triple_key", null);
		sel.addColumn(tagTable, "triple_value", null);
		//Joins
		sel.addInnerJoin(taggingTable.getAlias(), taggingTable.TAGGING_EVENT_ID, eventTable.getAlias(), "_id");
		sel.addInnerJoin(tagTable.getAlias(), "_id", taggingTable.getAlias(), "tag_id");
		
		//SELECT ev._id, ev.name, ev.date, ev.location, ev.rating, ev.comment, ev.image_uri, ev.rotation, ev.created_on, 
		//ev.updated_on, tgg.tag_id, tg.name, tg.is_triple, tg.triple_namespace, tg.triple_key, tg.triple_value
		//FROM events ev
		//LEFT JOIN tagging tgg ON ev._id = tgg.event_id
		//INNER JOIN tags tg ON tgg.tag_id = tg._id
		selectStmt = new SelectStatement();
		//Defining tables
		selectStmt.addTable(eventTable, SelectType.FROM);
		selectStmt.addTable(taggingTable, SelectType.LEFT_JOIN);
		selectStmt.addTable(tagTable, SelectType.INNER_JOIN);
		//Defining Columns
		selectStmt.addColumn(eventTable, "_id", null);
		selectStmt.addColumn(eventTable, "name",null);
		selectStmt.addColumn(eventTable, "date",null);
		selectStmt.addColumn(eventTable, "location",null);
		selectStmt.addColumn(eventTable, "rating",null);
		selectStmt.addColumn(eventTable, "comment",null);
		selectStmt.addColumn(eventTable, "image_uri",null);
		selectStmt.addColumn(eventTable, "rotation",null);
		selectStmt.addColumn(eventTable, "created_on",null);
		selectStmt.addColumn(eventTable, "updated_on",null);
		selectStmt.addColumn(taggingTable, "tag_id",null);
		selectStmt.addColumn(tagTable, "name", null);
		selectStmt.addColumn(tagTable, "is_triple", null);
		selectStmt.addColumn(tagTable, "triple_namespace", null);
		selectStmt.addColumn(tagTable, "triple_key", null);
		selectStmt.addColumn(tagTable, "triple_value", null);
		//joins
		selectStmt.addLeftJoin(taggingTable.getAlias(), taggingTable.TAGGING_EVENT_ID, eventTable.getAlias(), "_id");
		selectStmt.addInnerJoin(tagTable.getAlias(), "_id", taggingTable.getAlias(), "tag_id");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		System.out.println("Method: testToString");
		System.out.println("SQL: " + sel.toString());
		//String esql ="SELECT ev._id AS 'event_id', ev.name, tg._id AS 'tag_id', tg.is_triple FROM events ev INNER JOIN tagging tgg ON tgg.event_id = ev._id INNER JOIN tags tg ON tg._id = tgg.tag_id;";
		String esql ="SELECT ev._id AS 'event_id', ev.name, tg._id AS 'tag_id', tg.is_triple, tg.triple_namespace, tg.triple_key, tg.triple_value FROM events ev INNER JOIN tagging tgg ON tgg.event_id = ev._id INNER JOIN tags tg ON tg._id = tgg.tag_id;";
		//String esql ="SELECT ev._id AS 'event_id', ev.name, tg._id AS 'tag_id', tg.is_triple, tg.triple_namespace, tg.triple_key, tg.triple_value FROM events ev INNER_JOIN JOIN tagging tgg ON tgg.event_id = ev._id INNER_JOIN JOIN tags tg ON tg._id = tgg.tag_id;";
		assertEquals(esql, sel.toString());
		
	}
	@Test
	public void testToString2() {
		System.out.println("Method: testToString2");
		System.out.println("SQL: " + selectStmt.toString());
		String esql = "SELECT ev._id, ev.name, ev.date, ev.location, ev.rating, ev.comment, ev.image_uri, ev.rotation, ev.created_on, "+
		"ev.updated_on, tgg.tag_id, tg.name, tg.is_triple, tg.triple_namespace, tg.triple_key, tg.triple_value " +
		"FROM events ev "+
		"LEFT JOIN tagging tgg ON tgg.event_id = ev._id "+
		"INNER JOIN tags tg ON tg._id = tgg.tag_id;";
		
		assertEquals(esql, selectStmt.toString());
		
	}
	@Test
	public void testGetFromTables() {
		System.out.println("Method: testGetFromTables");
		int c = sel.getFromTables().size();
		System.out.println("From count: " + c);
		int ec=1;
		assertEquals(ec, c);
		
	}
	@Test
	public void testGetColumNames() {
		System.out.println("Method: testGetColumNames");
		String[] cn = sel.getColumnNames();
		String cns = (new Exploder<String>()).explode(cn);
		System.out.println("Column Names: " + cns);
		String ecns = "event_id, name, tag_id, is_triple, triple_namespace, triple_key, triple_value";
		assertEquals(ecns, cns);
//		int ec=1;
//		assertEquals(ec, c);
		
	}
}
