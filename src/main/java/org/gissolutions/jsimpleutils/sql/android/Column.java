package org.gissolutions.jsimpleutils.sql.android;


public class Column extends SQLObject implements Comparable<Column>{
	public enum ColumnType{
		NONE, TEXT, NUMERIC, INTEGER, REAL;
	}
	private final ColumnType columnType;
	private int position;
	private boolean isPrimary;
	private boolean isUnique;
	private boolean isCheck;
	private boolean autoIncrement;
	//private boolean isForeignKey;
	private boolean isNotNull;
	private String defaultValue;
	
	public Column(String name) {
		super(name, SQLObjectType.COLUMN);
		this.columnType = ColumnType.NONE;
	}
	public Column(String name, ColumnType columnType) {
		super(name, SQLObjectType.COLUMN);		
		this.columnType = columnType;
	}
	@Override
	public String getCreateSQLStatement() {		
		return null;
	}
	public void setDefaultValue(Integer defaultValue){
		this.defaultValue = defaultValue.toString();
		if(this.defaultValue != null){
			this.isCheck=false;
			this.isPrimary=false;
			this.isUnique =false;
			this.isNotNull =false;
		}
	}
	public void setDefaultValue(String defaultValue){
		this.defaultValue = STRING_QUOTE + defaultValue + STRING_QUOTE;
		if(this.defaultValue != null){
			this.isCheck=false;
			this.isPrimary=false;
			this.isUnique =false;
			this.isNotNull =false;
		}
	}
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
		if(this.isPrimary){
			this.isCheck=false;
			//this.isForeignKey=false;
			this.isUnique =false;
			this.isNotNull =false;
		}else{
			this.autoIncrement =false;
		}
	}

	public boolean isUnique() {
		return isUnique;
		
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
		if(this.isUnique){
			this.isCheck=false;
			//this.isForeignKey=false;
			this.isPrimary =false;
			this.isNotNull =false;
		}
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
		if(this.isCheck){
			this.isUnique=false;
			//this.isForeignKey=false;
			this.isPrimary =false;
			this.isNotNull =false;
		}
	}
	
	public boolean isNotNull() {
		return isNotNull;
	}
	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
		if(this.isNotNull){
			this.isUnique=false;
			this.isCheck=false;
			this.isPrimary =false;
			//this.isForeignKey =false;
		}
	}
	
	
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
		if(autoIncrement){
			this.setPrimary(true);
		}
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(15);
		sb.append(name);		
		if(!this.columnType.equals(ColumnType.NONE)){
			sb.append(" ");
			sb.append(this.columnType);			
		}
		if(isPrimary){
			sb.append(" PRIMARY KEY");
			if(autoIncrement){
				sb.append(" AUTOINCREMENT");
			}
		}
		if(isNotNull){
			sb.append(" NOT NULL");
		}
		if(isUnique){
			sb.append(" UNIQUE");
		}
		if(defaultValue != null){
			sb.append(" DEFAULT ");
			sb.append(this.defaultValue);
		}
		return sb.toString();
	}
	@Override
	public int compareTo(Column o) {
		
		return this.getPosition() - o.getPosition();
	}

}
