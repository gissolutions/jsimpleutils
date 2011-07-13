package org.gissolutions.jsimpleutils.sql.android;

import java.util.HashMap;
import java.util.Map;

public class SelectStatement {
	Map<String, Table> fromTables;
	
	public SelectStatement() {
		this.fromTables = new HashMap<String, Table>();
	}
	
	public void addFrom(Table ft, String alias){
		this.fromTables.put(alias, ft);
	}
}
