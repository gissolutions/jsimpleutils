package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ViewTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement() {
		View taggedEvents  = new View("tagged_events");
		String select = "SELECT " +
				"e._id as 'event_id',  t._id as 'tag_id', " +
			    "t.name, t.is_triple, t.triple_namespace," +
				" t.triple_key, t.triple_value " +
				"FROM events e" +
				" INNER JOIN tagging tg ON tg.event_id = e._id"+
				" INNER JOIN tags t ON tg.tag_id = t._id";
		taggedEvents.setSelectSQL(select);
		String sql = taggedEvents.getCreateSQLStatement();
		System.out.println("SQL: " + sql);
	}

	@Test
	public void testGetDropSQLStatement() {
		fail("Not yet implemented");
	}

}
