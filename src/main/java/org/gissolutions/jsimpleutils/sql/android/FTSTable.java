package org.gissolutions.jsimpleutils.sql.android;

import java.util.List;

public class FTSTable extends Table {

	public FTSTable(String name, String alias) {
		super(name, alias);
		
	}

	@Override
	public String getCreateSQLStatement() {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE VIRTUAL TABLE ");
		sb.append(this.name);
		sb.append(" USING fts3(");
		int count=1;
		List<Column> columns = this.getColumns();
		for (Column column : columns) {
			sb.append(column.toString());
			if(count != columns.size()){
				sb.append(", ");
			}
			count++;
		}
		sb.append(");");
		return sb.toString();
	}

}
