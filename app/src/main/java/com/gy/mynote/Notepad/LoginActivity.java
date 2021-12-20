package com.gy.mynote.Notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.gy.mynote.R;
import com.gy.mynote.gloal.Global;

public class LoginActivity extends Activity {

	AppCompatEditText et_name,et_pw;
	AppCompatButton btn_login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		et_name = findViewById(R.id.et_name);
		et_pw = findViewById(R.id.et_pw);

		btn_login = findViewById(R.id.btn_login);


		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				login();
			}
		});
	}

	private void login(){
		String name = et_name.getText().toString();
		String pw = et_pw.getText().toString();
		if(name.isEmpty() || pw.isEmpty()){
			Toast.makeText(this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		Global.userName = name;
		finish();
	}
}
