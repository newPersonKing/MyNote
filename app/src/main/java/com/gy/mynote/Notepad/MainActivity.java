package com.gy.mynote.Notepad;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.gy.mynote.Bean.Notepad;
import com.gy.mynote.Control.MyAdapter;
import com.gy.mynote.Control.MyDB_operate;
import com.gy.mynote.R;


public class MainActivity extends AppCompatActivity {
	
	ListView listview;
	LayoutInflater inflater;
	ArrayList<Notepad> array;
	MyDB_operate mdb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView) findViewById(R.id.listView1);
		inflater=getLayoutInflater();
		
		mdb=new MyDB_operate(this);
		array=mdb.getArray();
		MyAdapter adapter=new MyAdapter(inflater,array);
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
                                // TODO Auto-generated method stub

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                mdb.toDelete(array.get(position).getId());
                                array=mdb.getArray();
                                MyAdapter adapter=new MyAdapter(inflater,array);
                                listview.setAdapter(adapter);
                            }
                        })
                        .create().show();
				return true;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			Intent intent=new Intent(getApplicationContext(),SecondAtivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.item2:
			MainActivity.this.finish();
			break;
		default:
			break;
			}
		return true;
	}
	
}
