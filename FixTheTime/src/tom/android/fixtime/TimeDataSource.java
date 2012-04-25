package tom.android.fixtime;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TimeDataSource {

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_TIME };

	public TimeDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createTime(Date date) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_TIME, date.toString());
		return database.insert(DatabaseHelper.TABLE_TIMES, null,
				values);
	}

	public void deleteComment(Date date) {
		System.out.println("Time deleted with: " + date);
		database.delete(DatabaseHelper.TABLE_TIMES, DatabaseHelper.COLUMN_TIME
				+ " = " + date, null);
	}

	public List<Date> getAllDates() {
		List<Date> dates = new ArrayList<Date>();

		Cursor cursor = database.query(DatabaseHelper.TABLE_TIMES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Date date = cursorToDate(cursor);
			dates.add(date);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return dates;
	}

	private Date cursorToDate(Cursor cursor) {
		return getDateFromFormattedLong(cursor.getLong(1));
	}
	
	 public static final String DATE_FORMAT = "yyyyMMddHHmmss";
	    private static final SimpleDateFormat dateFormat = new
	       SimpleDateFormat(DATE_FORMAT);
	    
	    public static long formatDateAsLong(Date date){
	       return Long.parseLong(dateFormat.format(date));
	    }
	    
	    public static Date getDateFromFormattedLong(long l){
	       try {
             return dateFormat.parse(String.valueOf(l));
                 
          } catch (ParseException e) {
                 return null;
          }
	    }
}