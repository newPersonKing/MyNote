package com.gy.mynote.Notepad;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.gy.mynote.bean.Notepad;
import com.gy.mynote.control.NoteAdapter;
import com.gy.mynote.control.DBHelper;
import com.gy.mynote.R;


public class MainActivity extends AppCompatActivity {
	
	ListView listview;
	LayoutInflater inflater;
	ArrayList<Notepad> array;
	DBHelper mdb;
	AppCompatTextView tv_person,tv_exit;
	AppCompatImageView iv_add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView) findViewById(R.id.listView1);
		inflater=getLayoutInflater();
		tv_person = findViewById(R.id.tv_person);
		tv_exit = findViewById(R.id.tv_exit);
		iv_add = findViewById(R.id.iv_add);
		mdb=new DBHelper(this);
		array=mdb.getArray();
		NoteAdapter adapter=new NoteAdapter(inflater,array);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub				
				Intent intent=new Intent(getApplicationContext(),SecondAtivity.class);
				intent.putExtra("ids",array.get(position).getId() );
				startActivity(intent);
				MainActivity.this.finish();
			}
		});

        /*
         * 长按后判断是否删除当前记事
         */
        listview.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                //AlertDialog判断是否删除记事
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("确定删除该记事？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                mdb.toDelete(array.get(position).getId());
                                array=mdb.getArray();
                                NoteAdapter adapter=new NoteAdapter(inflater,array);
                                listview.setAdapter(adapter);
                            }
                        })
                        .create().show();
				return true;
			}
		});

		tv_exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		tv_person.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(getApplicationContext(),UserInfoActivity.class);
				startActivity(intent);
			}
		});

		iv_add.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent intent=new Intent(getApplicationContext(),SecondAtivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}

	
}
