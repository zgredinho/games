package com.gladiatorgames.trainyourbrain.gameboard;


import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameButton {
	int value;
	boolean isVisible, isHide, isClicked = false;
	TextView txtValue;
	ImageView imgMask, imgMakeUp, imgFrame;
	RelativeLayout layout;
	
	GameButton(int value, TextView txtValue, boolean isVisible, boolean isHide, ImageView imgFrame,
			ImageView imgMask, ImageView imgMakeUp, RelativeLayout layout){
		this.value = value;
		this.txtValue = txtValue;
		this.isVisible = isVisible;
		this.isHide = isHide;
		this.imgFrame = imgFrame;
		this.imgMask = imgMask;
		this.imgMakeUp = imgMakeUp;
		this.layout = layout;
		
		if(isVisible==true){
			layout.setVisibility(View.VISIBLE);
		}else{
			layout.setVisibility(View.INVISIBLE);
		}
		
		if(isHide==true){
			imgMask.setVisibility(View.VISIBLE);
			txtValue.setVisibility(View.INVISIBLE);
		}else{
			imgMask.setVisibility(View.INVISIBLE);
			txtValue.setVisibility(View.VISIBLE);
		}
		
		
	}
	
	public GameButton() {
		
	}
	
	public void hide(){
		imgMask.setVisibility(View.VISIBLE);
		txtValue.setVisibility(View.INVISIBLE);
	}
	public void show(){
		imgMask.setVisibility(View.INVISIBLE);
		txtValue.setVisibility(View.VISIBLE);
	}
	public void clicked(){
		isClicked = !isClicked;
		if(isClicked==true){
			imgMakeUp.setVisibility(View.VISIBLE);
		}else{
			imgMakeUp.setVisibility(View.INVISIBLE);
		}
	}
	
	public void updateTxtValue(){
		txtValue.setText(String.valueOf(value));
	}
	
	public void resetState(){
		value = 0;
		updateTxtValue();
		txtValue.setTextColor(Color.WHITE);
		imgMakeUp.setVisibility(View.INVISIBLE);
		imgMask.setVisibility(View.INVISIBLE);
		layout.setVisibility(View.VISIBLE);
		isClicked = false;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public TextView getTxtValue() {
		return txtValue;
	}

	public void setTxtValue(TextView txtValue) {
		this.txtValue = txtValue;
	}

	public ImageView getImgMask() {
		return imgMask;
	}

	public void setImgMask(ImageView imgMask) {
		this.imgMask = imgMask;
	}

	public ImageView getImgMakeUp() {
		return imgMakeUp;
	}

	public void setImgMakeUp(ImageView imgMakeUp) {
		this.imgMakeUp = imgMakeUp;
	}

	public ImageView getImgFrame() {
		return imgFrame;
	}

	public void setImgFrame(ImageView imgFrame) {
		this.imgFrame = imgFrame;
	}

	public RelativeLayout getLayout() {
		return layout;
	}

	public void setLayout(RelativeLayout layout) {
		this.layout = layout;
	}
}
