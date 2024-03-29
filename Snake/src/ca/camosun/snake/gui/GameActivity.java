package ca.camosun.snake.gui;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ca.camosun.snake.Bomb;
import ca.camosun.snake.Fruit;
import ca.camosun.snake.Pipe;
import ca.camosun.snake.R;
import ca.camosun.snake.SingleScore;
import ca.camosun.snake.Snake;
import ca.camosun.snake.Snake.Direction;
import ca.camosun.snake.SnakeBoard;
import ca.camosun.snake.SnakeSegment;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements SensorEventListener, OnTouchListener {

	private GridLayout boardGrid;
	
	private Timer mTimer;
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float tiltX;
	private float tiltY;
	private int rowCount;
	private int columnCount;
	private boolean inPlay;
	private SnakeBoard board;
	private SingleScore score;
	private TextView tvScore;
	private TextView tvHighScore;

	private static enum GameState {
		GAME_OVER, NEXT_LEVEL, PAUSED, RESUMED;
	}
	
	public static enum ObstacleType {
		BOMB, PIPE, TRAP_DOOR;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game);

		inPlay = false;

		// Set up the accelerometer service
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mTimer = null; // init
        
		boardGrid = (GridLayout) findViewById(R.layout.activity_game);

		boardGrid.setOnTouchListener(this);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int cellSize;
		
		switch(metrics.densityDpi) {
		case DisplayMetrics.DENSITY_LOW:
			cellSize = 16;
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			cellSize = 24;
			break;
		case DisplayMetrics.DENSITY_HIGH:
			cellSize = 32;
			break;
		default:
			cellSize = 32;
			break;
		}
		
		int dpi = 20;
		rowCount = height / cellSize;
		columnCount = width / cellSize;

		addGrid(boardGrid, columnCount, rowCount, cellSize, dpi);
		score = new SingleScore("Player", 0);
		
		tvScore = (TextView) findViewById(R.id.tvScore);
		tvScore.setText("Score: " + String.valueOf(score.getScore()));
		
		tvHighScore = (TextView) findViewById(R.id.tvHighScore);
		if (MainActivity.highScores.size() > 0) {
			SingleScore topScore = MainActivity.highScores.getFirstOnList();
			
			tvHighScore.setText("High Score: " + String.valueOf(topScore.getScore()));
		} else {
			tvHighScore.setText("High Score: 0");
		}
		
		newGame();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	private void addGrid(GridLayout grid, int columnCount, int rowCount,
			int cellSize, int dpi) {

		grid.setRowCount(rowCount);
		grid.setColumnCount(columnCount);

		for (int row = 0; row < rowCount; row++) {
			makeGridColumn(row, columnCount, grid, cellSize);
		}
	}

	private void makeGridColumn(int row, int columnSize, GridLayout grid,
			int buttonSize) {
		for (int column = 0; column < columnSize; column++) {
			GridImage aCell = new GridImage(this, row, column);
			aCell.setImageResource(R.drawable.boardbackground);
			aCell.setAdjustViewBounds(true);
			aCell.setMaxHeight(buttonSize);
			aCell.setMaxWidth(buttonSize);
			grid.addView(aCell);
		}
	}

	private GridImage imageAt(int column, int row) {
		int index = (row * columnCount + column);
		return (GridImage) boardGrid.getChildAt(index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

		// Not supported but the compiler insists we have this method.

	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this, mSensor);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		tiltX = event.values[0];
		tiltY = event.values[1];
	}

	private Direction getTilt() {
		if (Math.abs(tiltX) > Math.abs(tiltY)) {
			// tilt is horizontal
			if (tiltX > 0) {
				return Direction.WEST;
			}
			return Direction.EAST;
		}
		// tilt is vertical
		if (tiltY > 0) {
			return Direction.SOUTH; // tilt up = snake gravitates down (south)
		}
		return Direction.NORTH;
	}

	public void startTimer(int interval) {
		if (mTimer != null) {
			mTimer.cancel(); // Stop the timer!! Otherwise we will have more
								// than one timer ticking,leading to problems.
			mTimer = null;
		}
		
		mTimer = new Timer();

		mTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						if (inPlay) {
							makeMove();
						}
					}
				});
			}
		}, 0, interval);
	}

	private void makeMove() {
		Direction whereNext;
		Snake snake = board.getSnake();
		boolean ateFruit = false;

		whereNext = getTilt();
		SnakeSegment oldTail = snake.getTail();
		snake.moveSnake(whereNext);

		// See if the snake has done anything interesting
		// collisions? game over? update score? All that stuff goes here.

		// Went off board?
		if (board.wentOffBoard()) {
			inPlay = false; // game over
			createAlertMessage("Game Over",
					"You hit the wall dude! Never hit the wall.", "Ah, sh*t.",
					GameState.GAME_OVER);
			if(isHighScore() == true){
				showSaveHighScoreDialog();
			}
			return;
		}

		// Self collision?
		if (snake.collidedSelf()) {
			inPlay = false;
			createAlertMessage("Game Over",
					"You bit yourself! Cannibalism not allowed.", "Ouch!",
					GameState.GAME_OVER);
			if(isHighScore() == true){
				showSaveHighScoreDialog();
			}
			return;
		}

		// Ate fruit?
		if (board.foundFruit()) {
			ateFruit = true;
			Toast.makeText(this, "Ate Fruit!", Toast.LENGTH_SHORT).show();
			score.ateFruit();
			tvScore.setText("Score: " + String.valueOf(score.getScore()));
			
			if(MainActivity.highScores.size() > 0) {
				SingleScore topScore = MainActivity.highScores.getFirstOnList();
			
				if(score.getScore() > topScore.getScore()) {
					tvHighScore.setText("High Score: " + String.valueOf(score.getScore()));
				}
			} else {
				tvHighScore.setText("High Score: " + String.valueOf(score.getScore()));
			}
			
			// Are all the fruits gone? Time to level up!
			if (board.getFruits().size() == 0) {
				// Toast.makeText(this, "No Fruit Left!",
				// Toast.LENGTH_LONG).show();
				inPlay = false;
				Toast.makeText(this, "Next Level", Toast.LENGTH_SHORT).show();
				GameLevels.nextLevel(GameActivity.this);
			}

			// We probably want to grow the snake here.
			snake.grow(oldTail);

		}
		
		if(board.hitBomb()) {
			inPlay = false;
			createAlertMessage("Game Over",
					"You hit a bomb", "Oops!",
					GameState.GAME_OVER);
		}
		
		if(board.hitPipe()) {
			inPlay = false;
			createAlertMessage("Game Over",
					"You hit a pipe!", "Oops!",
					GameState.GAME_OVER);
		}

		// Draw the snake
		if (ateFruit == false) {
			GridImage image = imageAt(oldTail.getPositionX(),
					oldTail.getPositionY());
			image.setImageResource(R.drawable.boardbackground);
		}
		drawSnake(snake);
		drawFruit();
		drawBombs();
		drawPipe();
	}
	
	private boolean isHighScore(){
		if(MainActivity.highScores.size() < MainActivity.MAXIMUM_NUMBER_OF_SCORES){
			return true;
		}
		
		SingleScore lowestScore = MainActivity.highScores.getLastOnList();
		
		if(score.getScore() > lowestScore.getScore()){
			return true;
		}
		
		return false;
	}
	
	private void showSaveHighScoreDialog(){
		AlertDialog.Builder nameEntryDialog = new AlertDialog.Builder(this);
		nameEntryDialog.setTitle("You got a high score!");
		nameEntryDialog.setMessage("Enter your name:");
		final EditText textEntry = new EditText(this);
		nameEntryDialog.setView(textEntry);
		
		textEntry.setText(score.getName());
		
		nameEntryDialog.setPositiveButton("Ok",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String nameEntered = textEntry.getText().toString();

						score.setName(nameEntered);
						
						MainActivity.highScores.addScore(score);
					}
				});

		nameEntryDialog.show();
		
		
	}

	private void drawSnake(Snake snake) {
        GridImage image;

        // draw the head
        SnakeSegment head = snake.getHead();
        image = imageAt(head.getPositionX(), head.getPositionY());
        image.setImageResource(R.drawable.snakehead);

        // draw the segments
        for (int i = 1; i < snake.size(); i++) {
                SnakeSegment segment = snake.get(i);
                image = imageAt(segment.getPositionX(), segment.getPositionY());
                image.setImageResource(R.drawable.snakebody);
        }
	}

	private void drawFruit() {
		List<Fruit> fruitlist = board.getFruits();
		for (Fruit afruit : fruitlist) {

			GridImage image;
			image = imageAt(afruit.getPositionX(), afruit.getPositionY());
			image.setImageResource(R.drawable.fruit);

		}
	}
	
	private void drawBombs() {
		List<Bomb> thebombs = board.getBombs();
		for (Bomb abomb : thebombs) {

			GridImage image;
			image = imageAt(abomb.getPositionX(), abomb.getPositionY());
			image.setImageResource(R.drawable.bomb);

		}
	}
	
	private void drawPipe() {
		List<Pipe> thepipes = board.getPipes();
		for (Pipe apipe : thepipes) {

			GridImage image;
			image = imageAt(apipe.getPositionX(), apipe.getPositionY());
			image.setImageResource(R.drawable.pipe);

		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		pauseGame();
		
		createAlertMessage("Game Paused",
				"", "Resume",
				GameState.PAUSED);
		
		return false;
	}
	
	public void pauseGame() {
		inPlay = false;
	}
	
	public void resumeGame() {
		inPlay = true;
	}
	

	private void createAlertMessage(String titleText, String messageText,
			String buttonText, final GameState state) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(titleText);
		alertDialogBuilder.setMessage(messageText);
		alertDialogBuilder.setCancelable(true);

		alertDialogBuilder.setPositiveButton(buttonText,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						switch (state) {

						case GAME_OVER:

							GameActivity.this.finish();
							break;

						case PAUSED:

							resumeGame();
							break;

						default:
							// Generic message action
						}
					}
				});
		
				if(state == GameState.PAUSED) {
					alertDialogBuilder.setNegativeButton("Quit",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									GameActivity.this.finish();
								}
					});
				}
				
		AlertDialog ad = alertDialogBuilder.create();
		ad.show();
	}

	private void newGame() {			
		GameLevels.reset();
		startTimer(GameLevels.INITIAL_REFRESH_MS);	
		board = new SnakeBoard(columnCount, rowCount);	
		board.addRandomFruits(GameLevels.INITIAL_FRUITS);
		drawFruit();
		TextView tv = (TextView) findViewById(R.id.tvLevel);
		tv.setText("Level 1");
		inPlay = true;
	}
	
	private static class GameLevels {
		public static final int INITIAL_REFRESH_MS = 500;
		public static final int INITIAL_FRUITS = 3;
		private static int currentLevel = 1;
		private static int timerMs = INITIAL_REFRESH_MS;
		private static int numFruits = INITIAL_FRUITS;
		private static int numBombs = 4;
		private static int pipeLength = 3;

		public static void reset() {
			currentLevel = 1;
			timerMs = INITIAL_REFRESH_MS;
			numFruits = INITIAL_FRUITS;
		}
		
		private static void nextLevel(GameActivity thisGame) {
			
			currentLevel++;
            timerMs = timerMs - 100;
            if (timerMs < 400) { timerMs = 400; }
            numFruits = numFruits + 2;
            if (numFruits > 20) { numFruits = 20; }
            
            
            thisGame.startTimer(timerMs);
            thisGame.board.addRandomFruits(numFruits);
            
            if(currentLevel > 1) {
              thisGame.board.addObstacle(ObstacleType.BOMB, numBombs);                
              numBombs += 2;
            }
            
            //Can make multiple calls to addObstacle to make more than one vertical pipe
            if(currentLevel == 2) {
                thisGame.board.addObstacle(ObstacleType.PIPE, pipeLength); 
                
              }
            
            if(currentLevel == 3) {
            	pipeLength += 2;
                thisGame.board.addObstacle(ObstacleType.PIPE, pipeLength); 
                thisGame.board.addObstacle(ObstacleType.PIPE, pipeLength); 
                
              }
            

			TextView tv = (TextView) thisGame.findViewById(R.id.tvLevel);
			tv.setText("Level " + currentLevel);

			thisGame.inPlay = true;
		}

	}

}