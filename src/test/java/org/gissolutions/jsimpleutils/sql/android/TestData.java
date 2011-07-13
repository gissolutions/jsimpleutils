package org.gissolutions.jsimpleutils.sql.android;

import org.gissolutions.jsimpleutils.sql.android.Column.ColumnType;
import org.gissolutions.jsimpleutils.sql.android.ForeignKey.Behavior;

public class TestData {
	public static class EventTable extends Table{
		private  static EventTable events;
		
		EventTable(String name) {
			super(name);
		}
		public EventTable() {
			this("events");
			//eventTable = new Table("events");
			//id 1
			Column col = new Column("_id", ColumnType.INTEGER);
			col.setPrimary(true);
			col.setAutoIncrement(true);
			this.addColumn(col);
			//name 2
			col = new Column("name", ColumnType.TEXT);
			col.setUnique(true);
			this.addColumn(col);
			//date 3
			col = new Column("date", ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
			//location 4
			col = new Column("location", ColumnType.TEXT);		
			this.addColumn(col);
			//rating 5
			col = new Column("rating", ColumnType.REAL);		
			this.addColumn(col);
			//comment 6
			col = new Column("comment", ColumnType.TEXT);		
			this.addColumn(col); 
			//image_uri 7
			col = new Column("image_uri", ColumnType.TEXT);		
			col.setNotNull(true);
			this.addColumn(col);
			//rotation 8
			col = new Column("rotation", ColumnType.INTEGER);		
			col.setDefaultValue(0);
			this.addColumn(col);
			//created_on 9
			col = new Column("created_on", ColumnType.TEXT);		
			col.setNotNull(true);
			this.addColumn(col);
			//updated_on 10
			col = new Column("updated_on", ColumnType.TEXT);		
			col.setNotNull(true);
			this.addColumn(col);
		}
		public static EventTable getInstance(){
			if(events == null){
				events = new EventTable();
			}
			return events;
		}
		
	}
	
	public static class TagTable extends Table{
		private static TagTable tags;
		 TagTable(String name) {
			super(name);
		
		}
		 public TagTable() {
			this("tags");
			//tagTable = new Table("tags");
			//id 1
			Column tcol = new Column("_id", ColumnType.INTEGER);
			tcol.setPrimary(true);
			tcol.setAutoIncrement(true);
			this.addColumn(tcol);
			//name 2
			tcol = new Column("name", ColumnType.TEXT);
			this.addColumn(tcol);
			//is_triple 3
			tcol = new Column("is_triple", ColumnType.INTEGER);
			this.addColumn(tcol);
			//triple_namespace 4
			tcol = new Column("triple_namespace", ColumnType.TEXT);
			this.addColumn(tcol);
			//triple_key 5
			tcol = new Column("triple_key", ColumnType.TEXT);
			this.addColumn(tcol);
			//triple_value 6
			tcol = new Column("triple_value", ColumnType.TEXT);
			this.addColumn(tcol);
		}
		 public static TagTable getInstance(){
				if(tags == null){
					tags = new TagTable();
				}
				return tags;
			}
		
	}

	public static class TaggingTable extends Table{
		private static TaggingTable instance;
		 TaggingTable(String name) {
			super(name);

		}
		 public TaggingTable() {
			this("tagging");
			//id 1
			Column tgcol = new Column("_id", ColumnType.INTEGER);
			tgcol.setPrimary(true);
			tgcol.setAutoIncrement(true);
			this.addColumn(tgcol);
			//tag_id 2
			tgcol = new Column("tag_id", ColumnType.INTEGER);
			this.addColumn(tgcol);
			//event_id 3
			tgcol = new Column("event_id", ColumnType.INTEGER);
			this.addColumn(tgcol);
			//Foreing Ke1 tag_id
			ForeignKey fk1 = new ForeignKey("tag_id", TestData.TagTable.getInstance());
			fk1.setOnDelete(Behavior.CASCADE);
			this.addForeignKey(fk1);
			//Foreing Ke1 tag_id
			ForeignKey fk2 = new ForeignKey("event_id", TestData.EventTable.getInstance());
			fk1.setOnDelete(Behavior.CASCADE);
			this.addForeignKey(fk2);
		}
		 
		 public static TaggingTable getInstance(){
				if(instance == null){
					instance = new TaggingTable();
				}
				return instance;
			}
	}
}
