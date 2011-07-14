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

}
