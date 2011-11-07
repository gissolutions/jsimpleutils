package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.gissolutions.jsimpleutils.sql.android.OrderByStatement.Order;
import org.junit.Test;

public class OrderByStatementTest {

	@Test
	public void testToString() {
		Column col = TestData.EVENT_TABLE.getColumns().get(1);
		Column col2 = TestData.EVENT_TABLE.getColumns().get(2);
		OrderByStatement ob = new OrderByStatement();
		ob.addColumn(col, Order.ASC);
		ob.addColumn(col2, Order.DESC);
		assertEquals("ORDER BY name ASC, date DESC", ob.toString());
	}

	@Test
	public void testAddColumn() {
		Column col = TestData.EVENT_TABLE.getColumns().get(1);
		Column col2 = TestData.EVENT_TABLE.getColumns().get(2);
		OrderByStatement ob = new OrderByStatement();
		ob.addColumn(col);
		ob.addColumn(col2);
		assertEquals("ORDER BY name, date", ob.toString());
	}
}
