package ca.camosun.snake.gui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ca.camosun.snake.Fruit;
import ca.camosun.snake.R;
import ca.camosun.snake.Snake;
import ca.camosun.snake.Snake.Direction;
import ca.camosun.snake.SnakeBoard;
import ca.camosun.snake.SnakeSegment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameActivity extends Activity implements SensorEventListener {

	private GridLayout boardGrid;
	private static final int INITIAL_REFRESH_MS = 1500;
	private Timer mTimer;
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float tiltX;
	private float tiltY;
	private int rowCount;
	private int columnCount;
	private boolean inPlay;
	private SnakeBoard board;
	private TextView tvDebug;
	private Fruit fruita;
	private Fruit fruitb;
	private Fruit fruitc;
	private ArrayList<Fruit> fruitlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game);

		inPlay = false;

		// Set up the timer
		mTimer = new Timer();
		startTimer(INITIAL_REFRESH_MS);

		// Set up the accelerometer service
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		tvDebug = (TextView) findViewById(R.id.tvDebug);
		boardGrid = (GridLayout) findViewById(R.layout.activity_game);

		rowCount = 18;
		columnCount = 12;
		int cellSize = 16;
		int dpi = 20;

		addGrid(boardGrid, columnCount, rowCount, cellSize, dpi);
		board = new SnakeBoard(columnCount, rowCount);
		inPlay = true;
		
		fruitlist = new ArrayList<Fruit>();
		
		fruita = new Fruit(6,2);
		fruitb = new Fruit(3,9);
		fruitc = new Fruit(10,8);
		
		fruitlist.add(fruita);
		fruitlist.add(fruitb);
		fruitlist.add(fruitc);
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

	private void startTimer(int interval) {
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
		System.out.println("makeMove called.");
		Direction whereNext;
		Snake snake = board.getSnake();

		whereNext = getTilt();
		SnakeSegment oldTail = snake.getTail();
		board.getSnake().moveSnake(whereNext, false);

		// test
		switch (whereNext) {
		case EAST:
			tvDebug.setText("East");
			break;
		case WEST:
			tvDebug.setText("West");
			break;
		case NORTH:
			tvDebug.setText("North");
			break;
		case SOUTH:
			tvDebug.setText("South");
		}

		// See if the snake has done anything interesting
		// collisions?
		// game over?
		// update score?
		
		
		
		if (board.wentOffBoard()) {
			return;
		}
		
		// Draw the snake
		GridImage image = imageAt(oldTail.getPositionX(),
				oldTail.getPositionY());
		image.setImageResource(R.drawable.boardbackground);
		drawSnake(snake);
		
		drawFruit(fruitlist);
		detectFruitCollision(snake, fruitlist);
		
		
		
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
	
	private void drawFruit(ArrayList<Fruit> fruitlist) {
		for(Fruit afruit : fruitlist) {
			
		GridImage image;
		image = imageAt(afruit.getPositionX(), afruit.getPositionY());
		image.setImageResource(R.drawable.fruit);
		
		}
	}
	
	private void detectFruitCollision(Snake snake, ArrayList<Fruit> fruitlist) {
		int snakeXPosition = snake.getHead().getPositionX();
		int snakeYPosition = snake.getHead().getPositionY();
		
		for(Fruit afruit: fruitlist) {
			if(snakeXPosition == afruit.getPositionX() && snakeYPosition == afruit.getPositionY()) {
				tvDebug.setText("Collision");
				
				//draw the snake head on top of the fruit
				GridImage image;
				image = imageAt(snake.getHead().getPositionX(), snake.getHead().getPositionY());
				image.setImageResource(R.drawable.snakehead);
				
				//move the fruit to another location
				// Could probably use the controller to call nextLevel() 
				// nextLevel could keep track of current level
				afruit.setPositionX(1);
				afruit.setPositionY(7);
				break;
			}
		
		}
	}
	
	
}