package com.gy.mynote.Notepad;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.gy.mynote.R;

public class welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startPush(welcome.this);
				Intent mainIntent = new Intent(welcome.this,MainActivity.class);
				welcome.this.startActivity(mainIntent);
				welcome.this.finish();
			}
			private void startPush(welcome welcome) {
				// TODO Auto-generated method stub
			}
		},  2500);
	}
	}
