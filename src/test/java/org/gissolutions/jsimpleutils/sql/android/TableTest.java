package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TableTest {

	private Table eventTable;
	private Table tagTable;
	private Table taggingTable;
	
	@Before
	public void setUp() throws Exception {
		eventTable = TestData.EVENT_TABLE; //new Table("events");
//		//id 1
//		Column col = new Column("_id", ColumnType.INTEGER);
//		col.setPrimary(true);
//		col.setAutoIncrement(true);
//		eventTable.addColumn(col);
//		//name 2
//		col = new Column("name", ColumnType.TEXT);
//		col.setUnique(true);
//		eventTable.addColumn(col);
//		//date 3
//		col = new Column("date", ColumnType.TEXT);
//		col.setNotNull(true);
//		eventTable.addColumn(col);
//		//location 4
//		col = new Column("location", ColumnType.TEXT);		
//		eventTable.addColumn(col);
//		//rating 5
//		col = new Column("rating", ColumnType.REAL);		
//		eventTable.addColumn(col);
//		//comment 6
//		col = new Column("comment", ColumnType.TEXT);		
//		eventTable.addColumn(col); 
//		//image_uri 7
//		col = new Column("image_uri", ColumnType.TEXT);		
//		col.setNotNull(true);
//		eventTable.addColumn(col);
//		//rotation 8
//		col = new Column("rotation", ColumnType.INTEGER);		
//		col.setDefaultValue(0);
//		eventTable.addColumn(col);
//		//created_on 9
//		col = new Column("created_on", ColumnType.TEXT);		
//		col.setNotNull(true);
//		eventTable.addColumn(col);
//		//updated_on 10
//		col = new Column("updated_on", ColumnType.TEXT);		
//		col.setNotNull(true);
//		eventTable.addColumn(col);
		//********************************************************
		tagTable = TestData.TAG_TABLE; //.getInstance(); //new Table("tags");
//		//id 1
//		Column tcol = new Column("_id", ColumnType.INTEGER);
//		tcol.setPrimary(true);
//		tcol.setAutoIncrement(true);
//		tagTable.addColumn(tcol);
//		//name 2
//		tcol = new Column("name", ColumnType.TEXT);
//		tagTable.addColumn(tcol);
//		//is_triple 3
//		tcol = new Column("is_triple", ColumnType.INTEGER);
//		tagTable.addColumn(tcol);
//		//triple_namespace 4
//		tcol = new Column("triple_namespace", ColumnType.TEXT);
//		tagTable.addColumn(tcol);
//		//triple_key 5
//		tcol = new Column("triple_key", ColumnType.TEXT);
//		tagTable.addColumn(tcol);
//		//triple_value 6
//		tcol = new Column("triple_value", ColumnType.TEXT);
//		tagTable.addColumn(tcol);
		//********************************************************
		taggingTable = TestData.TAGGING_TABLE;//new Table("tagging");
//		//id 1
//		Column tgcol = new Column("_id", ColumnType.INTEGER);
//		tgcol.setPrimary(true);
//		tgcol.setAutoIncrement(true);
//		taggingTable.addColumn(tgcol);
//		//tag_id 2
//		tgcol = new Column("tag_id", ColumnType.INTEGER);
//		taggingTable.addColumn(tgcol);
//		//event_id 3
//		tgcol = new Column("event_id", ColumnType.INTEGER);
//		taggingTable.addColumn(tgcol);
//		//Foreing Ke1 tag_id
//		ForeignKey fk1 = new ForeignKey("tag_id", tagTable);
//		fk1.setOnDelete(Behavior.CASCADE);
//		taggingTable.addForeignKey(fk1);
//		//Foreing Ke1 tag_id
//		ForeignKey fk2 = new ForeignKey("event_id", eventTable);
//		fk1.setOnDelete(Behavior.CASCADE);
//		taggingTable.addForeignKey(fk2);

		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement_Events() {
		System.out.println("Method: testGetCreateSQLStatement_Events");
		System.out.println("**** testGetCreateSQLStatement_Events");
		String expectedSQL = "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, date TEXT NOT NULL, location TEXT, rating REAL, comment TEXT, image_uri TEXT NOT NULL, rotation INTEGER DEFAULT 0, created_on TEXT NOT NULL, updated_on TEXT NOT NULL);";
		String sql = eventTable.getCreateSQLStatement();
		System.out.println("SQL : " + sql);
		assertEquals(expectedSQL, sql);
		
	}
	
	@Test
	public void testGetCreateSQLStatement_Tags() {
		System.out.println("**** testGetCreateSQLStatement_Tags");
		String expectedSQL = "CREATE TABLE tags (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, is_triple INTEGER, triple_namespace TEXT, triple_key TEXT, triple_value TEXT);";
		String sql = tagTable.getCreateSQLStatement();
		System.out.println("SQL : " + sql);
		assertEquals(expectedSQL, sql);
		
	}
	@Test
	public void testGetCreateSQLStatement_Tagging() {
		System.out.println("**** testGetCreateSQLStatement_Tagging");
		String expectedSQL = "CREATE TABLE tagging (_id INTEGER PRIMARY KEY AUTOINCREMENT, tag_id INTEGER, event_id INTEGER, FOREIGN KEY (tag_id) REFERENCES tags (_id) ON DELETE CASCADE, FOREIGN KEY (event_id) REFERENCES events (_id));";
		String sql = taggingTable.getCreateSQLStatement();
		System.out.println("SQL : " + sql);
		assertEquals(expectedSQL, sql);
		
	}
	@Test
	public void testGetColumns() {
		System.out.println("**** testGetColumns");
		List<Column> columns = this.eventTable.getColumns();
		int p =1;
		for (Column column : columns) {
			//System.out.println(String.format("Column : %s pos: %s", column.getName(), column.getPosition()));
			assertEquals(p, column.getPosition());
			p++;
		}
				
	}
	@Test
	public void testGetColumnNames() {
		System.out.println("**** testGetColumnNames");
		String[] columns = this.eventTable.getColumnNames();
		List<Column> cols = this.eventTable.getColumns();
		int i =0;
		for (String name : columns) {
			assertEquals(cols.get(i).getName(), name);
			//System.out.println(String.format("Column : %s pos: %s", name, p));			
			i++;
		}
				
	}
	
	@Test
	public void testDropSQLStatement() {
		System.out.println("**** testDropSQLStatement");
		String sql = this.eventTable.getDropSQLStatement();
		System.out.println("SQL: " +  sql);
		assertEquals("DROP TABLE IF EXISTS events;", sql);
				
	}
	
	@Test
	public void testGetPagingSQL() {
		String sql = this.eventTable.getPagingSQL(1, 2,"ORDER BY _id");
		assertEquals("SELECT * FROM events ORDER BY _id LIMIT 1 OFFSET 2", sql);
	}
}
