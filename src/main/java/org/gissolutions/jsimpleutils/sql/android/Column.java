package org.gissolutions.jsimpleutils.sql.android;

/**
 * The class Column represents a column on a Table or View.
 * @author LBerrocal
 *
 */
public class Column extends SQLObject implements Comparable<Column>, Cloneable{
	public enum ColumnType{
		NONE, TEXT, NUMERIC, INTEGER, REAL;
	}
	private final ColumnType columnType;
	private String alias;
	private String tablePrefix;
	
	private String defaultValue;
	private int attributes;
	
	public Column(String name) {
		super(name, SQLObjectType.COLUMN);
		this.columnType = ColumnType.NONE;
	}
	public Column(String name, ColumnType columnType) {
		super(name, SQLObjectType.COLUMN);		
		this.columnType = columnType;
	}
	
	public Column(String name, ColumnType columnType, ColumnAttribute... columnAttributes) {
		super(name, SQLObjectType.COLUMN);		
		this.columnType = columnType;
		for (ColumnAttribute columnAttribute : columnAttributes) {
			this.addAttribute(columnAttribute);
		}
	}
	
	@Override
	public String getCreateSQLStatement() {		
		return null;
	}
	
	public void setAttribute(ColumnAttribute columnAttribute) {
		this.attributes = columnAttribute.getValue();// | this.attributes;
	}
	public void unSetAttribute(ColumnAttribute columnAttribute) {
		if(this.attributes != 0) {
			this.attributes = this.attributes ^ columnAttribute.getValue();
		}
	}
	public void addAttribute(ColumnAttribute columnAttribute) {
		this.attributes = columnAttribute.getValue() | this.attributes;
	}
	public void setDefaultValue(Integer defaultValue){
		this.setDefaultValue(defaultValue.toString(), true);
	}
	public void setDefaultValue(String defaultValue){
		this.setDefaultValue(defaultValue, false);
	}
	
	private void setDefaultValue(String defaultValue, boolean isNumeric){
		if(isNumeric) {
			this.defaultValue = defaultValue;
		}else {
			this.defaultValue = defaultValue == null? null : STRING_QUOTE + defaultValue + STRING_QUOTE;
		}
		if(defaultValue == null){
			this.unSetAttribute(ColumnAttribute.CHECK);
		}else {
			this.addAttribute(ColumnAttribute.CHECK);
		}
	}
	public boolean isPrimary() {
		return ColumnAttribute.PRIMARY_KEY.is(this.attributes);
	}

	public void setPrimary(boolean isPrimary) {
		if(isPrimary) {
			this.setAttribute(ColumnAttribute.PRIMARY_KEY);
		}else {
			this.unSetAttribute(ColumnAttribute.PRIMARY_KEY);
		}
	}

	public boolean isUnique() {
		return ColumnAttribute.UNIQUE.is(this.attributes);
		
	}

	public void setUnique(boolean isUnique) {
		if(isUnique) {
			this.setAttribute(ColumnAttribute.UNIQUE);
		}else {
			this.unSetAttribute(ColumnAttribute.UNIQUE);
		}
	}

	public boolean isCheck() {
		return ColumnAttribute.CHECK.is(this.attributes);
	}

	public void setCheck(boolean isCheck) {
		if(isCheck) {
			this.setAttribute(ColumnAttribute.CHECK);
		}else {
			this.unSetAttribute(ColumnAttribute.CHECK);
		}
	}
	
	public boolean isNotNull() {
		return ColumnAttribute.NOT_NULL.is(this.attributes);
	}
	public void setNotNull(boolean isNotNull) {
		if(isNotNull) {
			this.setAttribute(ColumnAttribute.NOT_NULL);
		}else {
			this.unSetAttribute(ColumnAttribute.NOT_NULL);
		}
	}
	
	
	public boolean isAutoIncrement() {
		return ColumnAttribute.AUTO_INCREMENT.is(this.attributes);
	}
	public void setAutoIncrement(boolean autoIncrement) {
		if(autoIncrement) {
			this.setAttribute(ColumnAttribute.AUTO_INCREMENT);
		}else {
			this.unSetAttribute(ColumnAttribute.AUTO_INCREMENT);
		}
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(15);
		sb.append(name);		
		if(!this.columnType.equals(ColumnType.NONE)){
			sb.append(" ");
			sb.append(this.columnType);			
		}
		if(isPrimary()){
			sb.append(" PRIMARY KEY");
			if(isAutoIncrement()){
				sb.append(" AUTOINCREMENT");
			}
		}
		if(isNotNull()){
			sb.append(" NOT NULL");
		}
		if(isUnique()){
			sb.append(" UNIQUE");
		}
		if(defaultValue != null){
			sb.append(" DEFAULT ");
			sb.append(this.defaultValue);
		}
		return sb.toString();
	}
	public String getQualifiedName(){
		String pr = this.tablePrefix == null ? "": this.tablePrefix;
		String qn = null;
		if(this.alias != null){
			qn = pr +"." + this.alias;
		}else{
			qn = pr +"." + this.name;
		}
		return qn;
	}
	
	public String getSelectName(){
		String pr = this.tablePrefix == null ? "": this.tablePrefix;
		String qn = null;
		if(this.alias == null){
			qn = pr +"." + this.name;
		}else{
			qn = String.format("%s.%s AS %s%s%s", pr ,this.name, STRING_QUOTE, this.alias, STRING_QUOTE);
		}
		return qn;
	}
	@Override
	public int compareTo(Column o) {
		
		return this.getPosition() - o.getPosition();
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}

}
