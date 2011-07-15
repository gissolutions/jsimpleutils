package org.gissolutions.jsimpleutils.sql.android;

public class Trigger extends SQLObject {
	public enum TriggerType {
		BEFORE("BEFORE"), AFTER("AFTER"), INSTEAD_OF("INSTEAD OF"), NONE("");
		private final String name;

		private TriggerType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum TriggerAction {
		DELETE("DELETE"), INSERT("INSERT"), UPDATE("UPDATE");
		private final String name;

		private TriggerAction(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private final TriggerType triggerType;
	private final TriggerAction action;
	private final Table table;
	private String sqlStatement;

	public Trigger(String name, TriggerType triggerType, TriggerAction action,
			Table table) {
		super(name, SQLObjectType.TRIGGER);
		this.triggerType = triggerType;
		this.action = action;
		this.table = table;
	}

	@Override
	public String getCreateSQLStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TRIGGER ");
		sb.append(this.name);
		sb.append(" ");
		if (!triggerType.equals(TriggerType.NONE)) {
			sb.append(triggerType);
			sb.append(" ");
		}
		sb.append(this.action);
		sb.append(" ON ");
		sb.append(this.table.getName());
		sb.append(" BEGIN ");
		sb.append(sqlStatement);
		sb.append(" END;");
		return sb.toString();
	}

	public TriggerType getTriggerType() {
		return triggerType;
	}

	public TriggerAction getAction() {
		return action;
	}

	public Table getTable() {
		return table;
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

}
