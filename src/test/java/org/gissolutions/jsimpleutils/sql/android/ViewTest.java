package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.sql.android.TestData.TaggedEventsView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ViewTest {

	private View taggedEvents;

	@Before
	public void setUp() throws Exception {
		taggedEvents = new View("tagged_events");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement() {
		
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
	
	@Test
	public void testGetCreateSQLStatement2() {
		TaggedEventsView tev = TestData.TAGGED_EVENTS_VIEW;
		String sql = tev.getCreateSQLStatement();
		System.out.println("SQL: " + sql);
		String esql = "CREATE VIEW tagged_events AS SELECT ev._id AS 'event_id', ev.name, tg._id AS 'tag_id', tg.is_triple FROM events ev INNER JOIN tagging tgg ON tgg.event_id = ev._id INNER JOIN tags tg ON tg._id = tgg.tag_id;";
		assertEquals(esql, sql);
		
	}
}
