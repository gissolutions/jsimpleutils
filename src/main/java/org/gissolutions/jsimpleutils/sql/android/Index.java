package org.gissolutions.jsimpleutils.sql.android;

import java.util.ArrayList;
import java.util.List;


public class Index extends SQLObject {
	private boolean isUnique;
	private boolean ifNotExists;
	private final Table table;
	List<String>  columnNames;
	
	public Index(String name, Table table, String...columNames) {
		super(name, SQLObjectType.INDEX);
		this.table = table;
		columnNames = new ArrayList<String>();
		for (String colName : columNames) {
			if(!table.columnExists(colName)){
				throw new RuntimeException(
						String.format("Column %s does not exist on table %s cannot create index %s", 
								colName, table.getName(), name));
			}
			this.columnNames.add(colName);
		}
		
	}

	@Override
	public String getCreateSQLStatement() {
		StringBuffer sb = new StringBuffer(30);
		sb.append("CREATE");
		if(isUnique) {
			sb.append(" UNIQUE");
		}
		sb.append(" INDEX");
		if(ifNotExists){
			sb.append(" IF NOT EXISTS");
		}
		sb.append(" ");
		sb.append(this.name);
		
		sb.append(" ON ");
		sb.append(this.table.getName());
		//Columns
		sb.append(" (");
		int count = 1;	
		for (String columnName : this.getColumnNames()) {
			sb.append(columnName);
			if(count != columnNames.size()){
				sb.append(", ");
			}
			count++;
		}
		sb.append(")");		
		sb.append(";");
		return sb.toString();
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isIfNotExists() {
		return ifNotExists;
	}

	public void setIfNotExists(boolean ifNotExists) {
		this.ifNotExists = ifNotExists;
	}

	public Table getTable() {
		return table;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

}
