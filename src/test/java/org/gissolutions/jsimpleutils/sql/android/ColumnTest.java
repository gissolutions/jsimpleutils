package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gissolutions.jsimpleutils.sql.android.Column.ColumnType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ColumnTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClone() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setPrimary(true);
		tgcol.setAutoIncrement(true);
		
		try {
			Column clone = (Column) tgcol.clone();
			String alias = "tag_id";
			clone.setAlias(alias);
			assertNull(tgcol.getAlias());			
			assertEquals(alias, clone.getAlias());
			int oldPos = tgcol.getPosition();
			assertEquals(oldPos, clone.getPosition());
			int newPos = 10;
			clone.setPosition(newPos);
			assertEquals(newPos, clone.getPosition());
			assertEquals(oldPos, tgcol.getPosition());
			
		} catch (CloneNotSupportedException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testSetPrimary() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setPrimary(true);
		assertTrue(tgcol.isPrimary());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isNotNull());
		assertFalse(tgcol.isUnique());
		
	}
	
	@Test
	public void testContructor_PrimaryKey() {
		Column tgcol = new Column("_id", ColumnType.INTEGER, ColumnAttribute.PRIMARY_KEY);
		//tgcol.setPrimary(true);
		assertTrue(tgcol.isPrimary());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isNotNull());
		assertFalse(tgcol.isUnique());
		
	}
	
	@Test
	public void testSetAutoIncrement() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setAutoIncrement(true);
		assertTrue(tgcol.isPrimary());
		assertTrue(tgcol.isAutoIncrement());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isNotNull());
		assertFalse(tgcol.isUnique());		
	}

	@Test
	public void testContructor_AutoIncrement() {
		Column tgcol = new Column("_id", ColumnType.INTEGER, ColumnAttribute.AUTO_INCREMENT);
		//tgcol.setAutoIncrement(true);
		assertTrue(tgcol.isPrimary());
		assertTrue(tgcol.isAutoIncrement());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isNotNull());
		assertFalse(tgcol.isUnique());		
	}
	
	@Test
	public void testSetCheck() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setCheck(true);
		assertTrue(tgcol.isCheck());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isNotNull());
		assertFalse(tgcol.isUnique());
	}
	
	@Test
	public void testContructor_Check() {
		Column tgcol = new Column("_id", ColumnType.INTEGER, ColumnAttribute.CHECK);
		//tgcol.setCheck(true);
		assertTrue(tgcol.isCheck());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isNotNull());
		assertFalse(tgcol.isUnique());
	}
	
	@Test
	public void testSetNotNull() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setNotNull(true);
		assertTrue(tgcol.isNotNull());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isUnique());
	}
	
	@Test
	public void testSetNotNull_False() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setNotNull(false);
		assertFalse(tgcol.isNotNull()); 

		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isUnique());
		tgcol.setNotNull(true);
		assertTrue(tgcol.isNotNull()); 
		
		tgcol.setNotNull(false);
		assertFalse(tgcol.isNotNull()); 
		
	}
	
	@Test
	public void testContructor_NotNull() {
		Column tgcol = new Column("_id", ColumnType.INTEGER, ColumnAttribute.NOT_NULL);
		//tgcol.setNotNull(true);
		assertTrue(tgcol.isNotNull());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isUnique());
	}
	
	
	@Test
	public void testSetUnique() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setUnique(true);
		assertTrue(tgcol.isUnique());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isNotNull());
	}
	@Test
	public void testSetPosition() {
		List<Column> cols = new ArrayList<Column>();
		
		Column col1 = new Column("id", ColumnType.INTEGER);
		col1.setPosition(3);
		cols.add(col1);
		Column col2 = new Column("name", ColumnType.TEXT);
		col2.setPosition(1);
		cols.add(col2);
		Column col3 = new Column("date", ColumnType.TEXT);
		col3.setPosition(0);
		cols.add(col3);
		Column col4 = new Column("hour", ColumnType.TEXT);
		col4.setPosition(2);
		cols.add(col4);
		
		assertEquals(col1, cols.get(0));
		Collections.sort(cols);
		assertEquals(col3, cols.get(0));
	}
}
