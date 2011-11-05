package org.gissolutions.jsimpleutils.sql.android;

public abstract  class SQLObject implements Comparable<SQLObject> {
	public enum SQLObjectType{
		TABLE, INDEX, TRIGGER, VIEW, COLUMN
	}
	protected final String name;
	protected final SQLObjectType objectType;
	private int position;
	public static char STRING_QUOTE = '\'';
		
	public SQLObject(String name, SQLObjectType objectType) {
		super();
		this.name = name;
		this.objectType = objectType;
	}
	public abstract String getCreateSQLStatement();
	
	public String getDropSQLStatement(){
		return String.format("DROP %s IF EXISTS %s;", this.objectType, this.name);
	}
	public String getName() {
		return name;
	}
	public SQLObjectType getObjectType() {
		return objectType;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Override
	public int compareTo(SQLObject o) {
		
		return this.getPosition() - o.getPosition();
	}
}
