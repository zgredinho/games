package com.gladiatorgames.trainyourbrain.activities;

import com.gladiatorgames.trainyourbrain.R;
import com.gladiatorgames.trainyourbrain.engine.Engine;
import com.gladiatorgames.trainyourbrain.enums.GameTypeEnum;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Choosemenu extends Activity {
	
	RelativeLayout btn1, btn2;
	ImageView btnMask1, btnMask2;
	TextView btn1txt, btn2txt;
	public static byte mode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.choosemenu);
		
		btn1 = (RelativeLayout) findViewById(R.id.relativeLayout2);
		btn1txt = (TextView) findViewById(R.id.gbtn15);
		btn1txt.setText(GameTypeEnum.FIND_ELEMENTS.toString());
		btnMask1 = (ImageView) findViewById(R.id.imageView2);
		
		btn2 = (RelativeLayout) findViewById(R.id.RelativeLayout01);
		btn2txt = (TextView) findViewById(R.id.TextView01);
		btn2txt.setText(GameTypeEnum.FIND_PAIRS.toString());
		btnMask2 = (ImageView) findViewById(R.id.ImageView01);
		
		btn1.setOnTouchListener(new OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event){
				btnMask1.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				Engine.setGameType(GameTypeEnum.FIND_ELEMENTS);
				btnMask1.setVisibility(View.INVISIBLE);
				Intent mode1 = new Intent(Choosemenu.this,Engine.class);
				Choosemenu.this.startActivity(mode1);
			}
		});
		
		btn2.setOnTouchListener(new OnTouchListener(){
			
			@Override
			public boolean onTouch(View v, MotionEvent event){
				btnMask2.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				Engine.setGameType(GameTypeEnum.FIND_PAIRS);
				btnMask2.setVisibility(View.INVISIBLE);
				Intent mode1 = new Intent(Choosemenu.this,Engine.class);
				Choosemenu.this.startActivity(mode1);
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
