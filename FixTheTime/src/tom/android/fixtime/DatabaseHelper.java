package tom.android.fixtime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String TABLE_TIMES = "times";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TIME = "time";

	private static final String DATABASE_NAME = "fixtimes.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table "
			+ TABLE_TIMES + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_TIME
			+ " integer not null);";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
