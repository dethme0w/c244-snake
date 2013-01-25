package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeBoard {
	private int sizeX;
	private int sizeY;
	private Snake snake;
	private List<Fruit> fruits;
	private int score;

	public SnakeBoard(int inSizeX, int inSizeY) {
		sizeX = inSizeX;
		sizeY = inSizeY;
		snake = new Snake();
		fruits = new ArrayList<Fruit>();
		score = 0;
	}

	public void gotFruit() {
		List<SnakeSegment> currentSnake = new ArrayList<SnakeSegment>(snake.getSnake());
		SnakeSegment head = currentSnake.get(0);
		int headX = head.getPositionX();
		int headY = head.getPositionY();
		
		for(Fruit current:fruits) {
			int fruitX = current.getPositionX();
			int fruitY = current.getPositionY();
			
			if (headX == fruitX && headY == fruitY) {
				snake.grow();
			}
		}
	}
	// TODO: Method to initialize the board
	// Method to detect a collision between the snake and the edge
	// Method to detect if the snake has reached a fruit

}
