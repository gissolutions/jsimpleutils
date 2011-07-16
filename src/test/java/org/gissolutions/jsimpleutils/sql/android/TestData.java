package org.gissolutions.jsimpleutils.sql.android;

import org.gissolutions.jsimpleutils.sql.android.Column.ColumnType;
import org.gissolutions.jsimpleutils.sql.android.ForeignKey.Behavior;
import org.gissolutions.jsimpleutils.sql.android.SelectStatement.SelectType;

public class TestData {
	public static EventTable EVENT_TABLE = EventTable.getInstance();
	public static SearchableEventsTable SEARCHABLE_EVENT_TABLE = SearchableEventsTable
			.getInstance();
	public static TagTable TAG_TABLE = TagTable.getInstance();
	public static TaggingTable TAGGING_TABLE = TaggingTable.getInstance();
	public static InsertEventTrigger INSERT_EVENT_TRIGGER = InsertEventTrigger
			.getInstance();
	public static DeleteEventTrigger DELETE_EVENT_TRIGGER = DeleteEventTrigger
			.getInstance();
	public static UpdateEventTrigger UPDATE_EVENT_TRIGGER = UpdateEventTrigger
			.getInstance();
	public static TaggedEventsView TAGGED_EVENTS_VIEW = TaggedEventsView.getInstance();
	
	static class EventTable extends Table {
		private static EventTable events;
		public final String EVENT_ID = "_id";
		public final String EVENT_IMAGE_URI = "image_uri";
		public final String EVENT_DATE = "date";
		public final String EVENT_LOCATION = "location";
		public final String EVENT_RATING = "rating";
		public final String EVENT_COMMENT = "comment";
		public final String EVENT_NAME = "name";
		public final String EVENT_ROTATION = "rotation";
		public final String EVENT_CREATED_ON = "created_on";
		public final String EVENT_UPDATED_ON = "updated_on";

		EventTable(String name, String alias) {
			super(name, alias);
		}

		EventTable() {
			this("events", "ev");
			// eventTable = new Table("events");
			// id 1
			Column col = new Column(EVENT_ID, ColumnType.INTEGER);
			col.setPrimary(true);
			col.setAutoIncrement(true);
			this.addColumn(col);
			// name 2
			col = new Column(EVENT_NAME, ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
			// date 3
			col = new Column(EVENT_DATE, ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
			// location 4
			col = new Column(EVENT_LOCATION, ColumnType.TEXT);
			this.addColumn(col);
			// rating 5
			col = new Column(EVENT_RATING, ColumnType.REAL);
			this.addColumn(col);
			// comment 6
			col = new Column(EVENT_COMMENT, ColumnType.TEXT);
			this.addColumn(col);
			// image_uri 7
			col = new Column(EVENT_IMAGE_URI, ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
			// rotation 8
			col = new Column(EVENT_ROTATION, ColumnType.INTEGER);
			col.setDefaultValue(0);
			this.addColumn(col);
			// created_on 9
			col = new Column(EVENT_CREATED_ON, ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
			// updated_on 10
			col = new Column(EVENT_UPDATED_ON, ColumnType.TEXT);
			col.setNotNull(true);
			this.addColumn(col);
		}

		public static EventTable getInstance() {
			if (events == null) {
				events = new EventTable();
			}
			return events;
		}

	}

	static class TagTable extends Table {
		public final String TAG_ID = "_id";
		public final String TAG_NAME = "name";
		public final String TAG_IS_TRIPLE = "is_triple";
		public final String TAG_TRIPLE_NAMESPACE = "triple_namespace";
		public final String TAG_TRIPLE_KEY = "triple_key";
		public final String TAG_TRIPLE_VALUE = "triple_value";

		private static TagTable tags;

		TagTable(String name, String alias) {
			super(name, alias);

		}

		public TagTable() {
			this("tags", "tg");
			// tagTable = new Table("tags");
			// id 1
			Column tcol = new Column(TAG_ID, ColumnType.INTEGER);
			tcol.setPrimary(true);
			tcol.setAutoIncrement(true);
			this.addColumn(tcol);
			// name 2
			tcol = new Column(TAG_NAME, ColumnType.TEXT);
			this.addColumn(tcol);
			// is_triple 3
			tcol = new Column(TAG_IS_TRIPLE, ColumnType.INTEGER);
			this.addColumn(tcol);
			// triple_namespace 4
			tcol = new Column(TAG_TRIPLE_NAMESPACE, ColumnType.TEXT);
			this.addColumn(tcol);
			// triple_key 5
			tcol = new Column(TAG_TRIPLE_KEY, ColumnType.TEXT);
			this.addColumn(tcol);
			// triple_value 6
			tcol = new Column(TAG_TRIPLE_VALUE, ColumnType.TEXT);
			this.addColumn(tcol);
		}

		public static TagTable getInstance() {
			if (tags == null) {
				tags = new TagTable();
			}
			return tags;
		}

	}

	static class TaggingTable extends Table {
		public final String TAGGING_ID = "_id";
		public final String TAGGING_TAG_ID = "tag_id";
		public final String TAGGING_EVENT_ID = "event_id";

		private static TaggingTable instance;

		TaggingTable(String name, String alias) {
			super(name, alias);

		}

		public TaggingTable() {
			this("tagging", "tgg");
			// id 1
			Column tgcol = new Column(TAGGING_ID, ColumnType.INTEGER);
			tgcol.setPrimary(true);
			tgcol.setAutoIncrement(true);
			this.addColumn(tgcol);
			// tag_id 2
			tgcol = new Column(TAGGING_TAG_ID, ColumnType.INTEGER);
			this.addColumn(tgcol);
			// event_id 3
			tgcol = new Column(TAGGING_EVENT_ID, ColumnType.INTEGER);
			this.addColumn(tgcol);
			// Foreing Ke1 tag_id
			ForeignKey fk1 = new ForeignKey(TAGGING_TAG_ID, TestData.TagTable
					.getInstance());
			fk1.setOnDelete(Behavior.CASCADE);
			this.addForeignKey(fk1);
			// Foreing Ke1 tag_id
			ForeignKey fk2 = new ForeignKey(TAGGING_EVENT_ID,
					TestData.EventTable.getInstance());
			fk1.setOnDelete(Behavior.CASCADE);
			this.addForeignKey(fk2);
		}

		public static TaggingTable getInstance() {
			if (instance == null) {
				instance = new TaggingTable();
			}
			return instance;
		}
	}

	static class SearchableEventsTable extends FTSTable {
		public final String SEARCHABLE_EVENT_KEY = "eventId";
		public final String SEARCHABLE_EVENT_LOCATION = "location";
		public final String SEARCHABLE_EVENT_COMMENT = "comment";
		public final String SEARCHABLE_EVENT_NAME = "name";

		private static SearchableEventsTable instance;

		SearchableEventsTable(String name, String alias) {
			super(name, alias);

		}

		SearchableEventsTable() {
			this("searchable_events", "sev");
			this.addColumn(new Column(SEARCHABLE_EVENT_KEY));
			this.addColumn(new Column(SEARCHABLE_EVENT_NAME));
			this.addColumn(new Column(SEARCHABLE_EVENT_LOCATION));
			this.addColumn(new Column(SEARCHABLE_EVENT_COMMENT));

		}

		public static SearchableEventsTable getInstance() {
			if (instance == null) {
				instance = new SearchableEventsTable();
			}
			return instance;
		}
	}

	static class InsertEventTrigger extends Trigger {
		private static InsertEventTrigger instance;

		InsertEventTrigger(String name, TriggerType triggerType,
				TriggerAction action, Table table) {
			super(name, triggerType, action, table);
		}

		public InsertEventTrigger() {
			this("insert_event_trigger", TriggerType.AFTER,
					TriggerAction.INSERT, TestData.EVENT_TABLE);
			String sqlTemplate = "INSERT INTO %s (%s) VALUES (%s);";
			// Columns
			String[] columns = TestData.SEARCHABLE_EVENT_TABLE.getColumnNames();
			String colList = (new Exploder<String>()).explode(columns);
			// Values
			String[] values = new String[columns.length];
			values[0] = "new." + TestData.EVENT_TABLE.EVENT_ID;
			values[1] = "new." + TestData.EVENT_TABLE.EVENT_NAME;
			values[2] = "new." + TestData.EVENT_TABLE.EVENT_LOCATION;
			values[3] = "new." + TestData.EVENT_TABLE.EVENT_COMMENT;
			String valueList = (new Exploder<String>()).explode(values);
			// SQL
			String sql = String.format(sqlTemplate,
					TestData.SEARCHABLE_EVENT_TABLE.getName(), colList,
					valueList);

			this.setSqlStatement(sql);
		}

		public static InsertEventTrigger getInstance() {
			if (instance == null) {
				instance = new InsertEventTrigger();
			}
			return instance;
		}
	}

	static class DeleteEventTrigger extends Trigger {

		private static DeleteEventTrigger instance;

		DeleteEventTrigger(String name, TriggerType triggerType,
				TriggerAction action, Table table) {
			super(name, triggerType, action, table);
		}

		DeleteEventTrigger() {
			this("delete_event_trigger", TriggerType.NONE,
					TriggerAction.DELETE, TestData.EVENT_TABLE);
			String sqlTemplate = "DELETE FROM %s WHERE %s = old.%s;";
			String sql = String.format(sqlTemplate,
					TestData.SEARCHABLE_EVENT_TABLE.getName(),
					TestData.SEARCHABLE_EVENT_TABLE.SEARCHABLE_EVENT_KEY,
					TestData.EVENT_TABLE.EVENT_ID);
			this.setSqlStatement(sql);
		}

		public static DeleteEventTrigger getInstance() {
			if (instance == null) {
				instance = new DeleteEventTrigger();
			}
			return instance;
		}

	}

	static class UpdateEventTrigger extends Trigger {

		private static UpdateEventTrigger instance;

		UpdateEventTrigger(String name, TriggerType triggerType,
				TriggerAction action, Table table) {
			super(name, triggerType, action, table);
		}

		UpdateEventTrigger() {
			this("update_event_trigger", TriggerType.NONE,
					TriggerAction.UPDATE, TestData.EVENT_TABLE);

			String sqlTemplate = "UPDATE %s SET %s WHERE %s = old.%s;";
			String[] values = new String[3];
			values[0] = String.format("%s = new.%s",
					TestData.SEARCHABLE_EVENT_TABLE.SEARCHABLE_EVENT_NAME,
					TestData.EVENT_TABLE.EVENT_NAME);
			values[1] = String.format("%s = new.%s",
					TestData.SEARCHABLE_EVENT_TABLE.SEARCHABLE_EVENT_LOCATION,
					TestData.EVENT_TABLE.EVENT_LOCATION);
			values[2] = String.format("%s = new.%s",
					TestData.SEARCHABLE_EVENT_TABLE.SEARCHABLE_EVENT_COMMENT,
					TestData.EVENT_TABLE.EVENT_COMMENT);
			String valuesToUpdate = (new Exploder<String>()).explode(values);
			String sql = String.format(sqlTemplate,
					TestData.SEARCHABLE_EVENT_TABLE.getName(), valuesToUpdate,
					TestData.SEARCHABLE_EVENT_TABLE.SEARCHABLE_EVENT_KEY,
					TestData.EVENT_TABLE.EVENT_ID);
			this.setSqlStatement(sql);
		}

		public static UpdateEventTrigger getInstance() {
			if (instance == null) {
				instance = new UpdateEventTrigger();
			}
			return instance;
		}

	}

	static class TaggedEventsView extends View {

		private static TaggedEventsView instance;

		TaggedEventsView(String name) {
			super(name);
		}
		public TaggedEventsView() {
			this("tagged_events");
			SelectStatement sel = new SelectStatement();
			//Defining tables
			sel.addTable(EVENT_TABLE, SelectType.FROM);
			sel.addTable(TAGGING_TABLE, SelectType.INNER_JOIN);
			sel.addTable(TAG_TABLE, SelectType.INNER_JOIN);
			//Defining Columns
			sel.addColumn(EVENT_TABLE, "_id", "event_id");
			sel.addColumn(EVENT_TABLE, "name",null);
			sel.addColumn(TAG_TABLE, "_id", "tag_id");
			sel.addColumn(TAG_TABLE, "is_triple", null);
			//Joins
			sel.addInnerJoin(TAGGING_TABLE.getAlias(), TAGGING_TABLE.TAGGING_EVENT_ID, EVENT_TABLE.getAlias(), "_id");
			sel.addInnerJoin(TAG_TABLE.getAlias(), "_id", TAGGING_TABLE.getAlias(), "tag_id");
			this.setSelectSQL(sel.toString());
		}

		public static TaggedEventsView getInstance() {
			if (instance == null) {
				instance = new TaggedEventsView();
			}
			return instance;
		}
	}
}
