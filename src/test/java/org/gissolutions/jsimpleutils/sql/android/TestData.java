package org.gissolutions.jsimpleutils.sql.android;

import org.gissolutions.jsimpleutils.sql.android.Column.ColumnType;
import org.gissolutions.jsimpleutils.sql.android.ForeignKey.Behavior;

public class TestData {
	public static EventTable EVENT_TABLE = EventTable.getInstance();
	public static SearchableEventsTable SEARCHABLE_EVENT_TABLE = SearchableEventsTable.getInstance();
	public static TagTable TAG_TABLE = TagTable.getInstance();
	public static TaggingTable TAGGING_TABLE = TaggingTable.getInstance();
	
	private static class EventTable extends Table{
		private  static EventTable events;
		public  final String EVENT_ID = "_id";
        public  final String EVENT_IMAGE_URI = "image_uri";
        public  final String EVENT_DATE = "date";
        public  final String EVENT_LOCATION = "location";
        public  final String EVENT_RATING = "rating";
        public  final String EVENT_COMMENT = "comment";
        public  final String EVENT_NAME = "name";
        public  final String EVENT_ROTATION = "rotation";
        public  final String EVENT_CREATED_ON = "created_on";
        public  final String EVENT_UPDATED_ON = "updated_on";

		EventTable(String name, String alias) {
			super(name, alias);
		}
		
		EventTable() {
			this("events", "ev");
			//eventTable = new Table("events");
			//id 1
			Column col = new Column(EVENT_ID, ColumnType.INTEGER);
			col.setPrimary(true);
			col.setAutoIncrement(true);
			this.addColumn(col);
			//name 2
			col = new Column(EVENT_NAME, ColumnType.TEXT);
			col.setUnique(true);
			this.addColumn(col);
			//date 3
			col = new Column(EVENT_DATE, ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
			//location 4
			col = new Column(EVENT_LOCATION, ColumnType.TEXT);		
			this.addColumn(col);
			//rating 5
			col = new Column(EVENT_RATING, ColumnType.REAL);		
			this.addColumn(col);
			//comment 6
			col = new Column(EVENT_COMMENT, ColumnType.TEXT);		
			this.addColumn(col); 
			//image_uri 7
			col = new Column(EVENT_IMAGE_URI, ColumnType.TEXT);		
			col.setNotNull(true);
			this.addColumn(col);
			//rotation 8
			col = new Column(EVENT_ROTATION, ColumnType.INTEGER);		
			col.setDefaultValue(0);
			this.addColumn(col);
			//created_on 9
			col = new Column(EVENT_CREATED_ON, ColumnType.TEXT);		
			col.setNotNull(true);
			this.addColumn(col);
			//updated_on 10
			col = new Column(EVENT_UPDATED_ON, ColumnType.TEXT);		
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
	
	private static class TagTable extends Table{
		private static TagTable tags;
		 TagTable(String name, String alias) {
			super(name, alias);
		
		}
		 public TagTable() {
			this("tags", "tg");
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

	private static class TaggingTable extends Table{
		public static final String TAGGING_ID = "_id";
        public static final String TAGGING_TAG_ID = "tag_id";
        public static final String TAGGING_EVENT_ID = "event_id";

		private static TaggingTable instance;
		 TaggingTable(String name, String alias) {
			super(name, alias);

		}
		 public TaggingTable() {
			this("tagging", "tgg");
			//id 1
			Column tgcol = new Column(TAGGING_ID, ColumnType.INTEGER);
			tgcol.setPrimary(true);
			tgcol.setAutoIncrement(true);
			this.addColumn(tgcol);
			//tag_id 2
			tgcol = new Column(TAGGING_ID, ColumnType.INTEGER);
			this.addColumn(tgcol);
			//event_id 3
			tgcol = new Column(TAGGING_EVENT_ID, ColumnType.INTEGER);
			this.addColumn(tgcol);
			//Foreing Ke1 tag_id
			ForeignKey fk1 = new ForeignKey(TAGGING_TAG_ID, TestData.TagTable.getInstance());
			fk1.setOnDelete(Behavior.CASCADE);
			this.addForeignKey(fk1);
			//Foreing Ke1 tag_id
			ForeignKey fk2 = new ForeignKey(TAGGING_EVENT_ID, TestData.EventTable.getInstance());
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
	
	public static class SearchableEventsTable extends FTSTable{
		public static final String SEARCHABLE_EVENT_KEY = "eventId";
		public static final String SEARCHABLE_EVENT_LOCATION = "location";
        public static final String SEARCHABLE_EVENT_COMMENT = "comment";
        public static final String SEARCHABLE_EVENT_NAME = "name";
        
        

		private static SearchableEventsTable instance;

		SearchableEventsTable(String name, String alias) {
			super(name, alias);
			
		}
		SearchableEventsTable() {
			this("searchable_events", "sev");
			this.addColumn(new Column(SEARCHABLE_EVENT_KEY));
			this.addColumn(new Column(SEARCHABLE_EVENT_LOCATION));
			this.addColumn(new Column(SEARCHABLE_EVENT_COMMENT));
			this.addColumn(new Column(SEARCHABLE_EVENT_NAME));
		}
		
		public static SearchableEventsTable getInstance(){
			if(instance == null){
				instance = new SearchableEventsTable();
			}
			return instance;
		}
	}
	private static class InsertEventTrigger extends Trigger{
		private static InsertEventTrigger instance;
		InsertEventTrigger(String name, TriggerType triggerType,
				TriggerAction action, Table table) {
			super(name, triggerType, action, table);
			// TODO Auto-generated constructor stub
		}
		public InsertEventTrigger() {
			this("insert_event_trigger", TriggerType.AFTER, TriggerAction.INSERT, TestData.EVENT_TABLE);
			String sql = "INSERT INTO " + TestData.SEARCHABLE_EVENT_TABLE.getName()
            + " (eventId, name, location, comment) VALUES" + " (new."
            + TestData.EVENT_TABLE.EVENT_ID + ", new." + EVENT_TABLE.EVENT_NAME
            + ", new." + EVENT_TABLE.EVENT_LOCATION + ", new."
            + EVENT_TABLE.EVENT_COMMENT + ");" ;

		}
		public static InsertEventTrigger getInstance(){
			if(instance == null){
				instance = new InsertEventTrigger();
			}
			return instance;
		}
	}
}
