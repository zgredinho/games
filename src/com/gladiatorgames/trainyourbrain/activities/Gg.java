package com.gladiatorgames.trainyourbrain.activities;

import com.gladiatorgames.trainyourbrain.R;
import com.gladiatorgames.trainyourbrain.R.layout;
import com.gladiatorgames.trainyourbrain.engine.Engine;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Gg extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.gg);
		
		new Handler().postDelayed(new Thread() {
			@Override
			public void run() {
				Intent tyb = new Intent(Gg.this,Tyb.class);
			Gg.this.startActivity(tyb);
			Gg.this.finish();
			overridePendingTransition(R.layout.fadein,R.layout.fadeout);
			}
		},Engine.GAME_THREAD_DELAY);
		}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}
}
