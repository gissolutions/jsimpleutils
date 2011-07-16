package org.gissolutions.jsimpleutils.sql.android;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ColumnAttributeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToBinary() {
		for (ColumnAttribute ca : ColumnAttribute.values()) {
			System.out.println(ca.toString() + ": " + ca.toBinary());
		}
		int permission = ColumnAttribute.PRIMARY_KEY.getValue();
		boolean isPK = (permission & ColumnAttribute.PRIMARY_KEY.getValue()) == ColumnAttribute.PRIMARY_KEY
				.getValue();
		assertTrue(isPK);
		isPK = false;
		permission = ColumnAttribute.AUTO_INCREMENT.getValue();
		isPK = (permission & ColumnAttribute.AUTO_INCREMENT.getValue()) == ColumnAttribute.AUTO_INCREMENT
				.getValue();
		assertTrue(isPK);

		// System.out.println("*****************************************");
		// for (int pow = 0; pow < 9; pow++) {
		// int res = (int) Math.pow(2.0, pow);
		// System.out.println("Position: " + pow + " " + res);
		//				
		// }
	}

	@Test
	public void testIsPrimaryKey() {
		int attribute = ColumnAttribute.PRIMARY_KEY.getValue();
		boolean isPK = ColumnAttribute.PRIMARY_KEY.is(attribute);
		assertTrue(isPK);
		isPK = false;
		attribute = 0;
		attribute = ColumnAttribute.AUTO_INCREMENT.getValue();
		isPK = ColumnAttribute.PRIMARY_KEY.is(attribute);
		assertTrue(isPK);
		assertTrue(ColumnAttribute.AUTO_INCREMENT.is(attribute));
		assertFalse(ColumnAttribute.UNIQUE.is(attribute));
	}

}
