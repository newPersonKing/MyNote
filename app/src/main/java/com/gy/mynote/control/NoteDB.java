package com.gy.mynote.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NoteDB extends SQLiteOpenHelper{

	public NoteDB(Context context) {
		super(context, "mydate", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table mybook(ids integer PRIMARY KEY autoincrement,title text,content text,times text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	
}
