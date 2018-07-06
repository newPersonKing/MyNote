package com.gy.mynote.Notepad;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gy.mynote.Bean.Notepad;
import com.gy.mynote.Control.MyDB_operate;
import com.gy.mynote.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class SecondAtivity extends AppCompatActivity {

	EditText ed1,ed2;
	MyDB_operate myDatabase;
	Notepad cun;
	int ids;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("我的记事");
		setContentView(R.layout.activity_second);
		ed1=(EditText) findViewById(R.id.editText1);
		ed2=(EditText) findViewById(R.id.editText2);
		myDatabase=new MyDB_operate(this);
		
		Intent intent=this.getIntent();
		ids=intent.getIntExtra("ids", 0);

		if(ids!=0){
			cun=myDatabase.getTiandCon(ids);
			ed1.setText(cun.getTitle());
			ed2.setText(cun.getContent());
		}		
	}

	@Override
	public void onBackPressed() {
		//TODO Auto-generated method stub		
			Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
			startActivity(intent);
			SecondAtivity.this.finish();
		}

	private void isSave(){
				SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日  HH:mm");
				Date   curDate   =   new   Date(System.currentTimeMillis());
				String times   =   formatter.format(curDate);      
				String title=ed1.getText().toString();
				String content=ed2.getText().toString();

				if(ids!=0){
					cun=new Notepad(title,ids, content, times);
					myDatabase.toUpdate(cun);
					Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
					startActivity(intent);
					SecondAtivity.this.finish();
				}

				else{
					cun=new Notepad(title,content,times);
					myDatabase.toInsert(cun);
					Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
					startActivity(intent);
					SecondAtivity.this.finish();
				}
			}	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.second_ativity, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent=new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "标题："+ed1.getText().toString()+"  \n内容："+ed2.getText().toString());
			startActivity(intent);
			break;
		case R.id.save:
			isSave();
			Intent intent1 = new Intent(SecondAtivity.this,MainActivity.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
		return false;
	}
	

}
