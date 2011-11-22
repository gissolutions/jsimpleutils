package org.gissolutions.jsimpleutils.sql.android;

public class ForeignKey {
	public enum Behavior{
		SET_NULL("SET NULL"), 
		SET_DEFAULT("SET DEFAULT"),
		CASCADE("CASCADE"),
		RESTRICT("RESTRICT"),
		NO_ACTION("NO ACTION");
		
		private String sql;
		private Behavior(String sql){
			this.sql = sql;
		}
		@Override
		public String toString() {
			
			return sql;
		}
		
	}
	private final String keyColumnName;
	private final Table referenceTable;
	private final String referenceColumnName;
	private Behavior onDelete;
	private Behavior onUpdate;
	
	public ForeignKey(String keyColumnName, Table referenceTable,
			String referenceColumnName) {
		super();
		this.keyColumnName = keyColumnName;
		this.referenceTable = referenceTable;
		this.referenceColumnName = referenceColumnName;
	}
	public ForeignKey(String keyColumnName, Table referenceTable) {
		this(keyColumnName, referenceTable, null);
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("FOREIGN KEY (");
		sb.append(this.keyColumnName);
		sb.append(") REFERENCES ");
		sb.append(referenceTable.getName());
		sb.append(" (");
		if(referenceColumnName == null){
			sb.append(referenceTable.getPrimaryKeys().get(0).getName());
		}else{
			sb.append(referenceColumnName);
		}
		sb.append(")");
		if(this.onDelete != null){		
			sb.append(" ON DELETE ");
			sb.append(this.onDelete.toString());
		}
		if(this.onUpdate != null){
			sb.append(" ON UPDATE ");
			sb.append(this.onUpdate.toString());
		}
		return sb.toString();
	}
	public Behavior getOnDelete() {
		return onDelete;
	}
	public void setOnDelete(Behavior onDelete) {
		this.onDelete = onDelete;
	}
	public Behavior getOnUpdate() {
		return onUpdate;
	}
	public void setOnUpdate(Behavior onUpdate) {
		this.onUpdate = onUpdate;
	}
	public String getKeyColumnName() {
		return keyColumnName;
	}
	public Table getReferenceTable() {
		return referenceTable;
	}
	public String getReferenceColumnName() {
		return referenceColumnName;
	}
	
	
}
