package org.gissolutions.jsimpleutils.sql.android;

import java.util.ArrayList;
import java.util.List;

import org.gissolutions.jsimpleutils.utils.Pair;



public class OrderByStatement {
	
	List<Pair<Column, Order>> columns;
	
	public enum Order{
		ASC, DESC;
	}
	
	public OrderByStatement() {
		columns = new ArrayList<Pair<Column, Order>>();
	}
	
	public void addColumn(Column column, Order order){
		columns.add(new Pair<Column, Order>(column, order));
	}
	
	public void addColumn(Column column){
		addColumn(column, null);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(columns.size() > 0){
			sb.append("ORDER BY ");
			int i=1;
			for(Pair<Column, Order> col : this.columns){
				sb.append(col.getKey().getName());
				if(col.getValue() != null){
					sb.append(" ");
					sb.append(col.getValue().toString());
				}				
				if(!(i==columns.size())){
					sb.append(", ");
				}
				i++;
			}
		}else{
			throw new IllegalArgumentException("Order by must have a least one column");
		}
		return sb.toString();
	}
	
	
}
