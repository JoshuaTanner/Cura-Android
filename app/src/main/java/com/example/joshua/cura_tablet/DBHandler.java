package com.example.joshua.cura_tablet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.List;


 /* Created by SenaK on 3/04/2017. */

public class DBHandler  extends SQLiteOpenHelper {
    // Database Version
    public static final String DATABASE_NAME = "dbcura";
    private static final int DATABASE_VERSION = 1;

    // Contacts table name
    public static final String SCHEDULE_TABLE_NAME = "Cura_Schedule";
    // Shops Table Columns names
    public static final String SCHEDULE_COLUMN_ID = "_id";
    public static final String SCHEDULE_COLUMN_NAME = "name";
    public static final String SCHEDULE_COLUMN_START = "start_time";
    public static final String SCHEDULE_COLUMN_END = "end_time";
    public static final String SCHEDULE_COLUMN_DESCRIPTION = "description";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SCHEDULE_TABLE_NAME + "(" +
                SCHEDULE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                SCHEDULE_COLUMN_NAME + " TEXT, " +
                SCHEDULE_COLUMN_START + " TEXT)" +
                SCHEDULE_COLUMN_END + " TEXT)" +
                SCHEDULE_COLUMN_DESCRIPTION + " TEXT, ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE_NAME);
        // Creating tables again
        onCreate(db);
    }
    /* Created by SenaK on 3/04/2017. */
    /* INSERTING A NEW RECORD. */
    public void insertSchedule(ScheduleClass schedule) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCHEDULE_COLUMN_NAME, schedule.getName()); // Schedule Name
        values.put(SCHEDULE_COLUMN_START, schedule.getStart_time()); // Schedule Phone Number
        values.put(SCHEDULE_COLUMN_END, schedule.getEnd_time()); // Schedule Phone Number
        values.put(SCHEDULE_COLUMN_DESCRIPTION, schedule.getDescription()); // Schedule Phone Number

        // Inserting Row
        db.insert(SCHEDULE_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public boolean updateSchedule(ScheduleClass schedule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULE_COLUMN_NAME, schedule.getName()); // Schedule Name
        contentValues.put(SCHEDULE_COLUMN_START, schedule.getStart_time()); // Schedule Phone Number
        contentValues.put(SCHEDULE_COLUMN_END, schedule.getEnd_time()); // Schedule Phone Number
        contentValues.put(SCHEDULE_COLUMN_DESCRIPTION, schedule.getDescription()); // Schedule Phone Number

        db.update(SCHEDULE_TABLE_NAME, contentValues, SCHEDULE_COLUMN_ID + " = ? ", new String[]  { Integer.toString(schedule.getId()) } );
        return true;
    }

    public Cursor getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + SCHEDULE_COLUMN_NAME + " WHERE " +
                SCHEDULE_COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + SCHEDULE_COLUMN_NAME, null );
        return res;
    }

    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SCHEDULE_TABLE_NAME,
                SCHEDULE_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

}