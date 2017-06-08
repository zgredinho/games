package com.gladiatorgames.trainyourbrain.engine;

import java.util.ArrayList;

import com.gladiatorgames.trainyourbrain.R;
import com.gladiatorgames.trainyourbrain.enums.GameTypeEnum;
import com.gladiatorgames.trainyourbrain.gameboard.GameButton;
import com.gladiatorgames.trainyourbrain.gameboard.Gameboard;
import com.gladiatorgames.trainyourbrain.interfaces.ClickListener;
import com.gladiatorgames.trainyourbrain.interfaces.GameButtonClickListener;
import com.gladiatorgames.trainyourbrain.interfaces.Listener;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Engine extends Activity {

	public static final int GAME_THREAD_DELAY = 4000;
	private static GameTypeEnum gameType = GameTypeEnum.FIND_ELEMENTS;
	public static int level = 1;
	private int score = 0;
	private boolean completed = false;
	private ArrayList<GameButton> pressedButtons = new ArrayList<GameButton>();
	private int numberOfPairs = 15;

	public Gameboard gameboard;
	public Stopwatch stopwatch;

	boolean gameRunning = false;
	boolean gamePaused = false;
	
	int sum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game1);
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		stopwatch = new Stopwatch(new Listener() {
			
			@Override
			public void execute() {
				updateStopwatchDisplay();
			}
		});

		gameboard = new Gameboard(this);
		bindListeners();
		beforeGame();
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

	@Override
	protected void onPause() {
		super.onPause();
	}

	private void beforeGame() {
		gameboard.makeBoard(gameType);
		gameboard.showTapBtn(Integer.toString(score), gameType.toString(), Integer.toString(level), completed);
		completed = false;
	}

	private void bindListeners() {
		gameboard.bindClickListeners(new GameButtonClickListener() {

			@Override
			public void execute(GameButton gbtn) {
				gameLogic(gbtn);
			}
		});
		gameboard.addRefreshButtonListener(new ClickListener() {
			
			@Override
			public void execute() {
				completed = false;
				stopwatch.stop();
				afterGame();
			}
		});
		gameboard.addTapButtonListener(new ClickListener() {
			
			@Override
			public void execute() {
				startGame();
			}
		});
	}

	private void afterGame() {
		sum = 0;
		gameType = GameTypeEnum.getGameTypeByInt(gameboard.getRandomizer().nextInt(2)+1);
		gameboard.clearBoard();
		beforeGame();
	}
	
	private void startGame() {
		stopwatch.start();
	}

	private void gameLogic(GameButton gbtn) {
		switch (gameType) {
		case FIND_ELEMENTS:
			findElementsGameLogic(gbtn);
			break;
		case FIND_PAIRS:
			findPairsGameLogic(gbtn);
			break;
		case MONKEY_GAME:

			break;
		}
	}

	private void findElementsGameLogic(GameButton gbtn) {
		gbtn.clicked();
		if (gbtn.isClicked()) {
			sum += gbtn.getValue();
		} else {
			sum -= gbtn.getValue();
		}
		gameboard.getResultDisplay().setText(String.valueOf(gameboard.getResultValue() - sum));
		if (sum == gameboard.getResultValue()) {
			score += (36000000 / (System.currentTimeMillis() - stopwatch.getStartTime())) / 70 * getDifficulty();
			stopwatch.stop();
			level++;
			completed = true;
			gameRunning = false;
			afterGame();
		}
	}
	
	private void findPairsGameLogic(GameButton gbtn){
		if(pressedButtons.size() == 0){
			gbtn.show();
			pressedButtons.add(gbtn);
		} else if(pressedButtons.size() == 1){
			gbtn.show();
			if(pressedButtons.get(0).getValue() == gbtn.getValue() && !pressedButtons.get(0).equals(gbtn)){
				gbtn.getLayout().setVisibility(View.INVISIBLE);
				pressedButtons.get(0).getLayout().setVisibility(View.INVISIBLE);
				pressedButtons.clear();
				numberOfPairs--;
				if(numberOfPairs == 0){
					score += (36000000 / (System.currentTimeMillis() - stopwatch.getStartTime())) / 70 * getDifficulty();
					stopwatch.stop();
					level++;
					completed = true;
					afterGame();
					return;
				}
			} else {
				pressedButtons.add(gbtn);
			}
		} else if(pressedButtons.size() == 2){
			pressedButtons.get(0).hide();
			pressedButtons.get(1).hide();
			pressedButtons.clear();
			findPairsGameLogic(gbtn);
		}
	}
	
	private void updateStopwatchDisplay(){
		gameboard.getStopwatchDisplay().setText(stopwatch.getDisplayTxt());
	}
	
	private int getDifficulty(){
		return level < 5 ? 5 : level;
	}

	public int getRange() {
		int a = level * 5;
		return a;
	}

	public int getNumberOfElements() {
		int a = (getDifficulty() / 5) % 3 + 2;
		return a;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setSum(int sum){
		this.sum = sum;
	}

	public static void setGameType(GameTypeEnum gameType) {
		Engine.gameType = gameType;
	}

	public static GameTypeEnum getGameType() {
		return gameType;
	}

}
