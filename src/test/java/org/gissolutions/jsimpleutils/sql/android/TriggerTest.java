package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.sql.android.TestData.DeleteEventTrigger;
import org.gissolutions.jsimpleutils.sql.android.TestData.InsertEventTrigger;
import org.gissolutions.jsimpleutils.sql.android.TestData.UpdateEventTrigger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TriggerTest {
	private InsertEventTrigger ieTrigger;
	private DeleteEventTrigger delTrigger;
	private UpdateEventTrigger upTrigger;
	@Before
	public void setUp() throws Exception {
		this.ieTrigger = new InsertEventTrigger();
		this.delTrigger = TestData.DELETE_EVENT_TRIGGER;
		this.upTrigger = TestData.UPDATE_EVENT_TRIGGER;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreateSQLStatement_InsertEventTrigger() {
		System.out.println("*** testGetCreateSQLStatement_InsertEventTrigger");
		String sql = this.ieTrigger.getCreateSQLStatement();
		String esql ="CREATE TRIGGER insert_event_trigger AFTER INSERT ON events BEGIN INSERT INTO searchable_events (eventId, name, location, comment) VALUES (new._id, new.name, new.location, new.comment); END;";
		System.out.println("SQL: " + sql);
		assertEquals(esql, sql);
		
	}

	@Test
	public void testGetDropSQLStatement() {
		System.out.println("*** testGetDropSQLStatement");
		String esql = "DROP TRIGGER IF EXISTS insert_event_trigger;";
		String sql = this.ieTrigger.getDropSQLStatement();
		System.out.println("SQL: " + sql);
		assertEquals(esql, sql);
	}
	
	@Test
	public void testGetCreateSQLStatement_DeleteEventTrigger() {
		System.out.println("*** testGetCreateSQLStatement_DeleteEventTrigger");
		String sql = this.delTrigger.getCreateSQLStatement();
		String esql ="CREATE TRIGGER delete_event_trigger DELETE ON events BEGIN DELETE FROM searchable_events WHERE eventId = old._id; END;";
		System.out.println("SQL: " + sql);
		assertEquals(esql, sql);
		
	}
	
	@Test
	public void testGetCreateSQLStatement_UpdateEventTrigger() {
		System.out.println("*** testGetCreateSQLStatement_UpdateEventTrigger");
		String sql = this.upTrigger.getCreateSQLStatement();
		String esql ="CREATE TRIGGER update_event_trigger UPDATE ON events BEGIN UPDATE searchable_events SET name = new.name, location = new.location, comment = new.comment WHERE eventId = old._id; END;";
		System.out.println("SQL: " + sql);
		assertEquals(esql, sql);
		
	}


}
