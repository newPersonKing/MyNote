package com.gy.mynote.Notepad;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gy.mynote.bean.Notepad;
import com.gy.mynote.control.DBHelper;
import com.gy.mynote.R;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SecondAtivity extends AppCompatActivity {

	EditText ed1,ed2;
	DBHelper myDatabase;
	Notepad cun;
	int ids;
	AppCompatTextView tv_save,tv_share;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ed1=(EditText) findViewById(R.id.editText1);
		ed2=(EditText) findViewById(R.id.editText2);
		myDatabase=new DBHelper(this);

		tv_save = findViewById(R.id.tv_save);
		tv_share = findViewById(R.id.tv_share);

		Intent intent=this.getIntent();
		ids=intent.getIntExtra("ids", 0);

		if(ids!=0){
			cun=myDatabase.getTiandCon(ids);
			ed1.setText(cun.getTitle());
			ed2.setText(cun.getContent());
		}

		tv_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!isSave())return;
				Intent intent1 = new Intent(SecondAtivity.this,MainActivity.class);
				startActivity(intent1);
			}
		});

		tv_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, "标题："+ed1.getText().toString()+"  \n内容："+ed2.getText().toString());
				startActivity(intent);
			}
		});
	}

	@Override
	public void onBackPressed() {
		Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
		startActivity(intent);
		SecondAtivity.this.finish();
	}

	private boolean isSave(){
		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日  HH:mm");
		Date   curDate   =   new   Date(System.currentTimeMillis());
		String times   =   formatter.format(curDate);
		String title=ed1.getText().toString();
		String content=ed2.getText().toString();

		if(title.isEmpty()){
			Toast.makeText(this,"标题不能为空",Toast.LENGTH_SHORT).show();
			return false;
		}

		if(content.isEmpty()){
			Toast.makeText(this,"内容不能为空",Toast.LENGTH_SHORT).show();
			return false;
		}

		if(ids!=0){
			cun=new Notepad(title,ids, content, times);
			myDatabase.toUpdate(cun);
			Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
			startActivity(intent);
			SecondAtivity.this.finish();
		}else{
			cun=new Notepad(title,content,times);
			myDatabase.toInsert(cun);
			Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
			startActivity(intent);
			SecondAtivity.this.finish();
		}
		return true;
	}




}
