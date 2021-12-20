package com.gy.mynote.Notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.gy.mynote.R;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcome);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startPush(WelcomeActivity.this);
				Intent mainIntent = new Intent(WelcomeActivity.this,LoginActivity.class);
				WelcomeActivity.this.startActivity(mainIntent);
				WelcomeActivity.this.finish();
			}
			private void startPush(WelcomeActivity welcome) {
				// TODO Auto-generated method stub
			}
		},  2500);
	}
	}
