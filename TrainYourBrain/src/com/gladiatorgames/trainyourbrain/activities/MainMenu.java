package com.gladiatorgames.trainyourbrain.activities;

import com.gladiatorgames.trainyourbrain.R;
import com.gladiatorgames.trainyourbrain.R.id;
import com.gladiatorgames.trainyourbrain.R.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainMenu extends Activity {
	
	RelativeLayout btn_NG;
	ImageView ImageButton, btn_NG_MakeUp;
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_main_menu);
		
		btn_NG = (RelativeLayout) findViewById(R.id.relativeLayout2);
		btn_NG_MakeUp = (ImageView) findViewById(R.id.imageView4);
		
		btn_NG.setOnTouchListener(new OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event){
				btn_NG_MakeUp.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		btn_NG.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				Intent choosemenu = new Intent(MainMenu.this,Choosemenu.class);
				MainMenu.this.startActivity(choosemenu);
				btn_NG_MakeUp.setVisibility(View.INVISIBLE);
			}
		});
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
