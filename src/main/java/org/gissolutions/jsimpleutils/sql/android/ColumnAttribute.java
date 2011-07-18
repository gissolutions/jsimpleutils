package org.gissolutions.jsimpleutils.sql.android;

import java.math.BigInteger;

public enum ColumnAttribute {
		PRIMARY_KEY(1),
		AUTO_INCREMENT(3), 
		UNIQUE(8),
		CHECK(16),
		NOT_NULL(32);
		
		private final byte value;
		
		private ColumnAttribute(int flag) {
			this.value = (byte) flag;
		}

		public int getValue() {
			return value;
		}
		public String toBinary(){
			BigInteger bi = new BigInteger(new byte[]{value});
			return bi.toString(2);	
		}
		public boolean is(int attribute){
			return (this.value & attribute) == this.value;
		}
}
