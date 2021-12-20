package com.gy.mynote.control;

import java.util.ArrayList;

import com.gy.mynote.bean.Notepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {
	Context context;
	NoteDB mydb;
	SQLiteDatabase myDatabase;

	public DBHelper(Context context){
		this.context=context;
		mydb=new NoteDB(context);
	}

	public ArrayList<Notepad> getArray(){
		ArrayList<Notepad> array=new ArrayList<Notepad>();
		ArrayList<Notepad> array1=new ArrayList<Notepad>();
		myDatabase=mydb.getWritableDatabase();
		Cursor cursor=myDatabase.rawQuery("select ids,title,times from mybook" , null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			int id=cursor.getInt(cursor.getColumnIndex("ids"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			String times=cursor.getString(cursor.getColumnIndex("times"));
			Notepad note=new Notepad(id, title, times);
			array.add(note);
			cursor.moveToNext();
		}
		myDatabase.close();

		for (int i = array.size(); i >0; i--) {
			array1.add(array.get(i-1));
		}
		return array1;		
	}

	public Notepad getTiandCon(int id){
		myDatabase=mydb.getWritableDatabase();
		Cursor cursor=myDatabase.rawQuery("select title,content from mybook where ids='"+id+"'" , null);
		cursor.moveToFirst();
		String title=cursor.getString(cursor.getColumnIndex("title"));
		String content=cursor.getString(cursor.getColumnIndex("content"));
		Notepad cun=new Notepad(title,content);
		myDatabase.close();
		return cun;
	}

	public void toUpdate(Notepad note){
		myDatabase=mydb.getWritableDatabase();
		myDatabase.execSQL("update mybook set title='"+ note.getTitle()+"',times='"+note.getTimes()+"',content='"+note.getContent() +"' where ids='"+ note.getId()+"'");
		myDatabase.close();
	}

	public void toInsert(Notepad cun){
		myDatabase=mydb.getWritableDatabase();
		myDatabase.execSQL("insert into mybook(title,content,times)values('"+ cun.getTitle()+"','"+cun.getContent()+"','"+cun.getTimes()+"')");
		myDatabase.close();
	}

	public void toDelete(int ids){
		myDatabase=mydb.getWritableDatabase();
		myDatabase.execSQL("delete  from mybook where ids="+ids+"");
		myDatabase.close();
	}
}
