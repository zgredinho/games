package com.gladiatorgames.trainyourbrain.gameboard;

import java.util.Random;

import com.gladiatorgames.trainyourbrain.R;
import com.gladiatorgames.trainyourbrain.engine.Engine;
import com.gladiatorgames.trainyourbrain.enums.GameTypeEnum;
import com.gladiatorgames.trainyourbrain.interfaces.ClickListener;
import com.gladiatorgames.trainyourbrain.interfaces.GameButtonClickListener;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Gameboard {

	private Engine engine;

	private TextView resultDisplay;
	private TextView greatJob;
	private TextView stopwatchDisplay;
	private TextView scoreDisplay;
	private TextView modeDisplay;
	private TextView difficultyDisplay;
	private RelativeLayout refresh;
	private RelativeLayout tapbtn;

	private Integer resultValue;

	private Random r = new Random(); //TODO przniesc random do Engine

	private GameButton[][] gbtn = new GameButton[6][];

	public Gameboard(Engine engine) {
		this.engine = engine;

		greatJob = (TextView) engine.findViewById(R.id.textView1);
		resultDisplay = (TextView) engine.findViewById(R.id.result);
		stopwatchDisplay = (TextView) engine.findViewById(R.id.TextView01);
		scoreDisplay = (TextView) engine.findViewById(R.id.scoreDisplay);
		modeDisplay = (TextView) engine.findViewById(R.id.modeDisplay);
		difficultyDisplay = (TextView) engine.findViewById(R.id.difficultyDisplay);
		refresh = (RelativeLayout) engine.findViewById(R.id.refresh);
		tapbtn = (RelativeLayout) engine.findViewById(R.id.tapbtn);
		scoreDisplay.setText(String.valueOf(engine.getScore()));
		modeDisplay.setText(String.valueOf(Engine.getGameType()));
		difficultyDisplay.setText(String.valueOf(Engine.level));

		initBoard();
	}

	public void initBoard() {
		for (int i = 0; i < 6; i++) {
			gbtn[i] = new GameButton[5];
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				gbtn[i][j] = new GameButton();
				String FrameID = "gbtnFrame" + i + j;
				int resFrameID = engine.getResources().getIdentifier(FrameID, "id", "com.gladiatorgames.trainyourbrain");
				gbtn[i][j].setImgFrame((ImageView) engine.findViewById(resFrameID));

				String MaskID = "gbtnMask" + i + j;
				int resMaskID = engine.getResources().getIdentifier(MaskID, "id", "com.gladiatorgames.trainyourbrain");
				gbtn[i][j].setImgMask((ImageView) engine.findViewById(resMaskID));

				String MakeUpID = "gbtnMakeUp" + i + j;
				int resMakeUpID = engine.getResources().getIdentifier(MakeUpID, "id",
						"com.gladiatorgames.trainyourbrain");
				gbtn[i][j].setImgMakeUp((ImageView) engine.findViewById(resMakeUpID));

				String TextID = "gbtnTxt" + i + j;
				int resTextID = engine.getResources().getIdentifier(TextID, "id", "com.gladiatorgames.trainyourbrain");
				gbtn[i][j].setTxtValue((TextView) engine.findViewById(resTextID));

				String LayoutID = "gbtnLayout" + i + j;
				int resLayoutID = engine.getResources().getIdentifier(LayoutID, "id", "com.gladiatorgames.trainyourbrain");
				gbtn[i][j].setLayout((RelativeLayout) engine.findViewById(resLayoutID));

				gbtn[i][j].resetState();
			}
		}
	}

	public void clearBoard() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				gbtn[i][j].resetState();
				resultDisplay.setTextColor(Color.WHITE);
			}
		}
	}

	public void makeBoard(GameTypeEnum game) {
		switch (game) {
		case FIND_ELEMENTS:
			findElements();
			break;
		case FIND_PAIRS:
			findPairs();
			break;
		case MONKEY_GAME:
			
			break;
		}
	}

	public void bindClickListeners(final GameButtonClickListener gameButtonClickListener) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				final int I = i;
				final int J = j;
				gbtn[i][j].getLayout().setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						gameButtonClickListener.execute(gbtn[I][J]);
					}
				});
			}
		}
	}
	
	public void addRefreshButtonListener(final ClickListener listener){
		refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.execute();
			}
		});
	}
	
	public void addTapButtonListener(final ClickListener listener){
		tapbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tapbtn.setVisibility(View.INVISIBLE);
				listener.execute();
			}
		});
	}
	
	public void showTapBtn(String score, String gameType, String difficulty, boolean completed){
		if(score != null){
			scoreDisplay.setText(score);
		}
		if (gameType != null) {
			modeDisplay.setText(gameType);
		}
		if (difficulty != null) {
			difficultyDisplay.setText(difficulty);
		}
		if(completed){
			greatJob.setVisibility(View.VISIBLE);
		} else {
			greatJob.setVisibility(View.INVISIBLE);
		}
		tapbtn.setVisibility(View.VISIBLE);
	}
	
	public TextView getResultDisplay(){
		return resultDisplay;
	}
	
	public TextView getStopwatchDisplay(){
		return stopwatchDisplay;
	}
	
	public Random getRandomizer(){
		return r;
	}
	
	public int getResultValue(){
		return resultValue;
	}

	private void findElements() {
		int[] element = new int[11];
		int localNumberOfElements = engine.getNumberOfElements();
		resultValue = r.nextInt(engine.getRange()) + 5;
		resultDisplay.setText(String.valueOf(resultValue));
		int helpfulVariable = resultValue;
		do {
			if (localNumberOfElements > 1) {
				int resultPart = helpfulVariable / localNumberOfElements;
				element[localNumberOfElements] = r.nextInt(resultPart) + 1;
				helpfulVariable -= element[localNumberOfElements];
			} else {
				element[localNumberOfElements] = helpfulVariable;
			}
			boolean finished = false;
			do {
				int i = r.nextInt(5) + 1;
				int j = r.nextInt(4) + 1;
				if (gbtn[i][j].getValue() == 0) {
					gbtn[i][j].setValue(element[localNumberOfElements]);
					gbtn[i][j].updateTxtValue();
					finished = true;
				}
			} while (finished == false);
			localNumberOfElements--;
		} while (localNumberOfElements > 0);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				gbtn[i][j].show();
				if (gbtn[i][j].getValue() == 0) {
					do {
						gbtn[i][j].setValue(r.nextInt(engine.getRange()) + 1);// ustawic
																				// odpowiedni
																				// przedzial
					} while (gbtn[i][j].getValue() >= resultValue);
					gbtn[i][j].updateTxtValue();
				}
			}
		}
	}

	private void findPairs() {
		for (int i = 0; i < 5; i += 2) {
			for (int j = 0; j < 5; j++) {
				int localValue = r.nextInt(50) + 1;
				gbtn[i][j].setValue(localValue);
				gbtn[i][j].updateTxtValue();
				gbtn[i + 1][j].setValue(localValue);
				gbtn[i + 1][j].updateTxtValue();
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				gbtn[i][j].getLayout().setVisibility(View.VISIBLE);
				gbtn[i][j].hide();
			}
		}
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 5; j++) {
					int V1 = gbtn[i][j].getValue();
					int ii, jj;
					ii = r.nextInt(5) + 1;
					jj = r.nextInt(4) + 1;
					gbtn[i][j].setValue(gbtn[ii][jj].getValue());
					gbtn[i][j].updateTxtValue();
					gbtn[ii][jj].setValue(V1);
					gbtn[ii][jj].updateTxtValue();
				}
			}
		}
		resultDisplay.setText("");
	}
}
