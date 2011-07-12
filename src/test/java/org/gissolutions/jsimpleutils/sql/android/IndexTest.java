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
		eventTable = new Table("events");
		//id 1
		Column col = new Column("_id", ColumnType.INTEGER);
		col.setPrimary(true);
		col.setAutoIncrement(true);
		eventTable.addColumn(col);
		//name 2
		col = new Column("name", ColumnType.TEXT);
		col.setUnique(true);
		eventTable.addColumn(col);
		//date 3
		col = new Column("date", ColumnType.TEXT);
		col.setNotNull(true);
		eventTable.addColumn(col);
		//location 4
		col = new Column("location", ColumnType.TEXT);		
		eventTable.addColumn(col);
		//rating 5
		col = new Column("rating", ColumnType.REAL);		
		eventTable.addColumn(col);
		//comment 6
		col = new Column("comment", ColumnType.TEXT);		
		eventTable.addColumn(col); 
		//image_uri 7
		col = new Column("image_uri", ColumnType.TEXT);		
		col.setNotNull(true);
		eventTable.addColumn(col);
		//rotation 8
		col = new Column("rotation", ColumnType.INTEGER);		
		col.setDefaultValue(0);
		eventTable.addColumn(col);
		//created_on 9
		col = new Column("created_on", ColumnType.TEXT);		
		col.setNotNull(true);
		eventTable.addColumn(col);
		//updated_on 10
		col = new Column("updated_on", ColumnType.TEXT);		
		col.setNotNull(true);
		eventTable.addColumn(col);
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
