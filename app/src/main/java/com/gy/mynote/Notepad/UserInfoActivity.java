package com.gy.mynote.Notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.gy.mynote.R;
import com.gy.mynote.gloal.Global;

public class UserInfoActivity extends Activity {

	AppCompatTextView tv_name;
	AppCompatTextView tv_exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_user_info);

		tv_name = findViewById(R.id.tv_name);
		tv_exit = findViewById(R.id.tv_exit);

		tv_name.setText(Global.userName);
		tv_exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

}
