package org.gissolutions.jsimpleutils.sql.android;


public class View extends SQLObject {
	private String selectSQL;
	
	public View(String name) {
		super(name, SQLObjectType.VIEW);
	}

	@Override
	public String getCreateSQLStatement() {
		String template ="CREATE VIEW %s AS %s";
		String sql = String.format(template, name, selectSQL);
		return sql;
	}

	public String getSelectSQL() {
		return selectSQL;
	}

	public void setSelectSQL(String selectSQL) {
		this.selectSQL = selectSQL;
	}

}
