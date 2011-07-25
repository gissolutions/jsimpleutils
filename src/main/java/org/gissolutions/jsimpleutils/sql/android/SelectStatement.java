package org.gissolutions.jsimpleutils.sql.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectStatement {
	public enum SelectType {
		FROM, INNER_JOIN, LEFT_JOIN;
		//select e.*,  t.* from events e left join tagging tg on tg.event_id = e._id inner join tags t on tg.tag_id = t._id
	}

	Map<String, SelectableTable> tables;
	List<Join> joins;
	List<Column> columns;

	public SelectStatement() {
		this.tables = new HashMap<String, SelectableTable>();
		this.joins = new ArrayList<Join>();
		this.columns = new ArrayList<Column>();
	}

	public void addTable(Table tbl, SelectType selectType) {
		SelectableTable st = new SelectableTable(tbl, selectType);
		// st.setSelectType(selectType);
		this.tables.put(st.getTable().getAlias(), st);
	}

	public void addColumn(Table tbl, String columnName, String columnAlias) {
		int pos = this.columns.size() + 1;
		SelectableTable stbl = this.tables.get(tbl.getAlias());
		try {
			Column col = (Column) stbl.getTable().getColumn(columnName).clone();
			col.setAlias(columnAlias);
			col.setPosition(pos);
			this.columns.add(col);
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void addInnerJoin(String joinedTableAlias, String joinedTableColumn,
			String tableToJoinAlias, String tableToJoinColumn) {
		
		SelectableTable jtstbl = this.tables.get(joinedTableAlias);
		Column jtCol = jtstbl.getTable().getColumn(joinedTableColumn);
		SelectableTable ttjstbl = this.tables.get(tableToJoinAlias);
		Column ttjCol = ttjstbl.getTable().getColumn(tableToJoinColumn);
		InnerJoin ij = new InnerJoin(jtstbl.getTable(), jtCol, ttjCol);
		this.joins.add(ij);
	}
	
	public void addLeftJoin(String joinedTableAlias, String joinedTableColumn,
			String tableToJoinAlias, String tableToJoinColumn) {
		
		SelectableTable jtstbl = this.tables.get(joinedTableAlias);
		Column jtCol = jtstbl.getTable().getColumn(joinedTableColumn);
		SelectableTable ttjstbl = this.tables.get(tableToJoinAlias);
		Column ttjCol = ttjstbl.getTable().getColumn(tableToJoinColumn);
		InnerJoin leftJoin = new InnerJoin(jtstbl.getTable(), jtCol, ttjCol);
		this.joins.add(leftJoin);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		// String sql = null;
		int c = 1;
		for (Column col : this.columns) {
			sb.append(" ");
			sb.append(col.getSelectName());
			if (c != columns.size()) {
				sb.append(",");
			}
			c++;
		}
		sb.append(" FROM");
		c = 1;
		for (Table tbl : this.getFromTables()) {

			sb.append(" ");
			sb.append(tbl.getName());
			sb.append(" ");
			sb.append(tbl.getAlias());
			if (c != getFromTables().size()) {
				sb.append(",");
			}
			c++;

		}
		if (this.joins.size() > 0) {
			for (Join ij : this.joins) {
				sb.append(" ");
				sb.append(ij.toString());
			}
		}
		sb.append(";");
		return sb.toString();
	}

	public List<Table> getFromTables() {
		List<Table> ftables = new ArrayList<Table>();
		for (SelectableTable tbl : this.tables.values()) {
			if (tbl.getSelectType() == SelectType.FROM) {
				ftables.add(tbl.getTable());
			}
		}
		return ftables;
	}
	public List<Column> getColumns(){
		//List<Column> orderedColumns = new LinkedList<Column>(this.columns.values());
		Collections.sort(columns);
		return columns;
	}
	public String[] getColumnNames(){
		List<Column> orderedColumns = this.getColumns();
		String[] names = new String[orderedColumns.size()];
		int i=0;
		for (Column column : orderedColumns) {
			if(column.getAlias() == null) {
				names[i] =column.getName();
			}else {
				names[i] =column.getAlias();
			}
			
			i++;
		}
		return names;
	}
	public class SelectableTable {
		private SelectType selectType;
		private final Table table;

		public SelectableTable(Table table, SelectType stype) {
			this.table = table;
			this.selectType = stype;
		}

		public SelectType getSelectType() {
			return selectType;
		}

		public void setSelectType(SelectType selectType) {
			this.selectType = selectType;
		}

		public Table getTable() {
			return table;
		}

	}
	class Join {
		protected final Table joinedTable;
		protected final Column joinedTableColumn;
		protected final Column tableToJoinColumn;
		protected final SelectType joinType;
		// private final Table tableToJoin;

		public Join(Table joinedTable, Column joinedTableColumn,
				Column tableToJoinColumn, SelectType joinType) {
			super();
			this.joinedTable = joinedTable;
			this.joinedTableColumn = joinedTableColumn;
			this.tableToJoinColumn = tableToJoinColumn;
			this.joinType = joinType;
		}

		public Table getJoinedTable() {
			return joinedTable;
		}

		@Override
		public String toString() {
			String strjointype=null;
			if(this.joinType == SelectType.INNER_JOIN) {
				strjointype ="INNER";
			}else if(this.joinType == SelectType.LEFT_JOIN){
				strjointype ="LEFT";
			}else {
				throw new RuntimeException("JOIN type is not defined");
			}
			String template = "%s JOIN %s %s ON %s = %s";

			return String.format(template, strjointype,
					this.joinedTable.getName(),
					this.joinedTable.getAlias(), this.joinedTableColumn
							.getQualifiedName(), this.tableToJoinColumn
							.getQualifiedName());
		}

	}
	class InnerJoin extends Join{
		
		public InnerJoin(Table joinedTable, Column joinedTableColumn,
				Column tableToJoinColumn) {
			super(joinedTable, joinedTableColumn, tableToJoinColumn, SelectType.INNER_JOIN);

		}
	}
	
   class LeftJoin extends Join{		
		public LeftJoin(Table joinedTable, Column joinedTableColumn,
				Column tableToJoinColumn) {
			super(joinedTable, joinedTableColumn, tableToJoinColumn, SelectType.LEFT_JOIN);

		}
	}
}
