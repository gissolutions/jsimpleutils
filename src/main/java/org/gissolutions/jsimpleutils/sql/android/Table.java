package org.gissolutions.jsimpleutils.sql.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Table extends SQLObject {
	private Map<String, Column> columns;
	private List<Column> primaryKeys;
	private List<ForeignKey> foreignKeys;
	private final String alias;
	
	private boolean isTemporary;
	private boolean ifNotExists;
	
	public Table(String name, String alias) {
		super(name, SQLObjectType.TABLE);
		this.alias = alias;
		this.columns = new HashMap<String, Column>();
		this.primaryKeys = new ArrayList<Column>();
		this.foreignKeys = new ArrayList<ForeignKey>();
	}

	@Override
	public String getCreateSQLStatement() {
		StringBuffer sb = new StringBuffer(30);
		sb.append("CREATE");
		if(isTemporary){
			sb.append(" TEMPORARY");
		}
		sb.append(" TABLE");
		if(ifNotExists){
			sb.append(" IF NOT EXISTS");
		}
		sb.append(" ");
		sb.append(this.name);
		//Columns
		sb.append(" (");		
		int count = 1;
		List<Column> columns = this.getColumns();
		for (Column column : columns) {
			sb.append(column.toString());
			if(count != columns.size()){
				sb.append(", ");
			}
			count++;
		}
		count = 1;
		for (ForeignKey fk : this.foreignKeys) {
			if(count == 1){
				sb.append(", ");
			}
			sb.append(fk.toString());
			if(count != foreignKeys.size()){
				sb.append(", ");
			}
			count++;
		}
		sb.append(")");
		sb.append(";");
		return sb.toString();
	}
	
	public void addColumn(Column col){
		int pos = columns.size() +1;
		String name = col.getName();// "";//((SQLObject) col).getName();
		col.setPosition(pos);
		col.setTablePrefix(this.alias);
		if(col.isPrimary() && !columns.containsKey(name)){
			this.primaryKeys.add(col);
		}
		this.columns.put(name, col);
		
	}
	public List<Column> getColumns(){
		List<Column> orderedColumns = new LinkedList<Column>(this.columns.values());
		Collections.sort(orderedColumns);
		return orderedColumns;
	}
	public String[] getColumnNames(){
		List<Column> orderedColumns = this.getColumns();
		String[] names = new String[orderedColumns.size()];
		int i=0;
		for (Column column : orderedColumns) {
			names[i] =column.getName();
			i++;
		}
		return names;
	}
	public Column getColumn(String columnName){
		return this.columns.get(columnName);
	}
	public boolean columnExists(String name){
		return this.columns.containsKey(name);
	}

	public List<Column> getPrimaryKeys() {
		return primaryKeys;
	}
	
	public void addForeignKey(ForeignKey fk){
		this.foreignKeys.add(fk);
	}
	
	public ForeignKey getForeignKey(String columnName) {
		for (ForeignKey fk : this.foreignKeys) {
			if(fk.getKeyColumnName().equals(columnName)) {
				return fk;
			}
		}
		return null;
	}
	public String getAlias() {
		return alias;
	}
	
	public String getPagingSQL(int rows,  int offset, String orderBy) {
		String template ="SELECT %s FROM %s %s LIMIT %s OFFSET %s";
		if(orderBy == null) {
			orderBy ="";
		}
		String sql = String.format(template, "*", this.name, orderBy, rows, offset);
		return sql;
	}
	
	
	
}
