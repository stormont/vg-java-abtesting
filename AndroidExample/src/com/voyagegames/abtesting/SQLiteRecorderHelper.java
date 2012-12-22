package com.voyagegames.abtesting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteRecorderHelper extends SQLiteOpenHelper {

	private static final String TAG = SQLiteRecorderHelper.class.getName();
	
    public static final String TABLE_NAME = "ABTestDB";
    public static final String KEY_ID = "_id";
    public static final String KEY_VALUE = "value";
    
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" + KEY_ID + " integer primary key, " + KEY_VALUE + " blob not null);";

	public SQLiteRecorderHelper(final Context context) {
		super(context, TABLE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        Log.w(TAG,"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
	}

}
