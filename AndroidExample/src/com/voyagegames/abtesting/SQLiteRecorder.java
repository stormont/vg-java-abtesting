package com.voyagegames.abtesting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLiteRecorder implements IRecorder {
	
	private static final String TAG = SQLiteRecorder.class.getName();
	
	private final SQLiteRecorderHelper mHelper;
	private final SQLiteDatabase mDB;
	
	public SQLiteRecorder(final Context context) {
		mHelper = new SQLiteRecorderHelper(context);
		mDB = mHelper.getWritableDatabase();
	}

	@Override
	public void recordResult(final ABResult result) {
		final byte[] bytes = serializeResult(result);
		
		if (bytes == null) {
			return;
		}
		
		final ContentValues values = new ContentValues();
		
		values.put(SQLiteRecorderHelper.KEY_VALUE, bytes);
		mDB.insert(SQLiteRecorderHelper.TABLE_NAME, null, values);
	}
	
	public List<ABResult> select() {
		final String[] columns = new String[] {
				SQLiteRecorderHelper.KEY_VALUE
			};
		
		final List<ABResult> results = new ArrayList<ABResult>();
		
		Cursor cursor = null;
		
		try {
			cursor = mDB.query(true, SQLiteRecorderHelper.TABLE_NAME, columns, null, null, null, null, null, null);
			
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					results.add(deserializeResult(cursor.getBlob(cursor.getColumnIndex(SQLiteRecorderHelper.KEY_VALUE))));
				}
				
				while (cursor.moveToNext()) {
					results.add(deserializeResult(cursor.getBlob(cursor.getColumnIndex(SQLiteRecorderHelper.KEY_VALUE))));
				}
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		
		return results;
	}
	
	private byte[] serializeResult(final ABResult result) {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		ObjectOutput out = null;
		
		try {
			out = new ObjectOutputStream(bos);   
			out.writeObject(result);
			return bos.toByteArray();
		} catch (final IOException e) {
			Log.w(TAG, "Failed to serialize result: " + e.toString(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				
				if (bos != null) {
					bos.close();
				}
			} catch (final IOException e) {
				Log.w(TAG, "Failed to close output streams: " + e.toString(), e);
			}
		}
		
		return null;
	}
	
	private ABResult deserializeResult(final byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInput in = null;
		ABResult result = null;
		
		try {
			in = new ObjectInputStream(bis);
			result = (ABResult)in.readObject();
		} catch (final IOException e) {
			Log.w(TAG, "Failed to read object: " + e.toString(), e);
		} catch (final ClassNotFoundException e) {
			Log.w(TAG, "Could not find deserialization class: " + e.toString(), e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				
				if (in != null) {
					in.close();
				}
			} catch (final IOException e) {
				Log.w(TAG, "Failed to close input streams: " + e.toString(), e);
			}
		}
		
		return result;
	}

}
