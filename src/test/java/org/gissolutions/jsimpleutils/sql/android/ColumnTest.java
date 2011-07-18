package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

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
	public void testSetUnique() {
		Column tgcol = new Column("_id", ColumnType.INTEGER);
		tgcol.setUnique(true);
		assertTrue(tgcol.isUnique());
		assertFalse(tgcol.isAutoIncrement());
		assertFalse(tgcol.isPrimary());
		assertFalse(tgcol.isCheck());
		assertFalse(tgcol.isNotNull());
	}
}
