package com.gladiatorgames.trainyourbrain.engine;

import com.gladiatorgames.trainyourbrain.interfaces.Listener;

import android.os.Handler;

public class Stopwatch {
	private long startTime =0, stopTime, checkTime, displayTime, elapsedTime;
	private boolean isRunning = false;
	private String displayTxt="";
	private Listener updateListener;
	private Handler handler = new Handler();
	private Runnable update = new Runnable() {
		@Override
		public void run() {
			update(System.currentTimeMillis());
			if(isRunning){
				updateListener.execute();
			}
			handler.postDelayed(this, 0);
		}

	};
	
	public Stopwatch(Listener updateListener){
		this.updateListener = updateListener;
	}
	
	public void start(){
			startTime = System.currentTimeMillis();
			handler.postDelayed(update, 0);
			isRunning = true;
	}
	
	public void stop(){
			elapsedTime = startTime-System.currentTimeMillis();
			handler.removeCallbacks(update);
			isRunning = false;
	}
	
	public void pause(){
			elapsedTime = startTime-System.currentTimeMillis();
			isRunning = false;
	}	
	
	public void resume(){
			startTime = System.currentTimeMillis()+elapsedTime;
			isRunning = true;
	}
	
	public long getStartTime(){
		return startTime;
	}
	
	public String getDisplayTxt(){
		return displayTxt;
	}
	
	void update(long time){
		if(time-checkTime>=100){
			checkTime=time;
			displayTime=time-startTime;
			long t=(displayTime/100)%10;
			long m = 0, s = 0;
			if((displayTime/100)>10){
				s=(displayTime/1000)%60;
			}
			if((displayTime/1000)>60){
				m=(displayTime/60000);
			}
			displayTxt=String.valueOf(m)+":"+String.valueOf(s)+"."+String.valueOf(t);
			updateListener.execute();
		}
	}
}
