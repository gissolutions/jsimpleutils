package org.gissolutions.jsimpleutils.sql.android;


public enum ColumnAttribute {
		PRIMARY_KEY(1),
		AUTO_INCREMENT(3), 
		UNIQUE(8),
		CHECK(16),
		NOT_NULL(32);
		
		private final int value;
		
		private ColumnAttribute(int flag) {
			this.value = flag;
		}

		public int getValue() {
			return value;
		}
		public String toBinary(){
			String binaryStr = Long.toString(value,2);
			return String.format("%8s", binaryStr).replace(' ', '0');	
		}
		public boolean is(int attribute){
			return (this.value & attribute) == this.value;
		}
}
