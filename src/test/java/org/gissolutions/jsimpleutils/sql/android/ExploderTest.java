package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExploderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExplodeStringArray() {
		System.out.println("*** testExplodeStringArray");
		String[] colNames = TestData.EVENT_TABLE.getColumnNames();
		Exploder<String> exp = new Exploder<String>();
		String res = exp.explode(colNames);
		System.out.println("Res: " + res);
		String eres="_id, name, date, location, rating, comment, image_uri, rotation, created_on, updated_on";
		assertEquals(eres, res);
	}
	
	@Test
	public void testExplodeStringArray_Prefix() {
		System.out.println("*** testExplodeStringArray");
		String[] colNames = TestData.EVENT_TABLE.getColumnNames();
		Exploder<String> exp = new Exploder<String>();
		String res = exp.explode(colNames, "ev.{0}");
		System.out.println("Res: " + res);
		String eres="ev._id, ev.name, ev.date, ev.location, ev.rating, ev.comment, ev.image_uri, " +
				"ev.rotation, ev.created_on, ev.updated_on";
		//String eres="";
		assertEquals(eres, res);
	}
	@Test
	public void testExplodeListOfColumn() {
		System.out.println("*** testExplodeListOfColumn");
		List<Column> columns = TestData.EVENT_TABLE.getColumns();
		Exploder<Column> exp = new Exploder<Column>();
		String res = exp.explode(columns);
		System.out.println("Res: " + res);
		String eres="_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, date TEXT NOT NULL, location TEXT, rating REAL, comment TEXT, image_uri TEXT NOT NULL, rotation INTEGER DEFAULT 0, created_on TEXT NOT NULL, updated_on TEXT NOT NULL";
		assertEquals(eres, res);
	}
	
	@Test
	public void testExplodeListOfColumn_GetName() {
		System.out.println("*** testExplodeListOfColumn_GetName");
		List<Column> columns = TestData.EVENT_TABLE.getColumns();
		Exploder<Column> exp = new Exploder<Column>();
		String res = exp.explode(columns, "getName");
		System.out.println("Res: " + res);
		String eres="_id, name, date, location, rating, comment, image_uri, rotation, created_on, updated_on";
		assertEquals(eres, res);
	}

}
