package ca.camosun.snake;

import static ca.camosun.snake.Snake.Direction.NORTH;

import java.util.ArrayList;
import java.util.List;

public class SnakeBoard {

	private Snake snake;
	private List<Fruit> fruits;
	private int score;
	private int maxX;
	private int maxY;

	public SnakeBoard(int inMaxX, int inMaxY) {
		maxX = inMaxX;
		maxY = inMaxY;
		snake = new Snake(NORTH, 0, 0);
		fruits = new ArrayList<Fruit>();
		score = 0;
	}

	public boolean wentOffBoard() {
		SnakeSegment head = snake.getHead();
		int x = head.getPositionX();
		int y = head.getPositionY();

		if (x < 0) {
			return true;
		}
		if (y < 0) {
			return true;
		}
		if (x > maxX) {
			return true;
		}
		if (y > maxY) {
			return true;
		}

		return false;
	}
	
	public boolean isLevelComplete() {
		if (fruits.size() == 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean isGameOver() {
		if (wentOffBoard()) {
			return true;
		}
		if (snake.collidedSelf()) {
			return true;
		}
		return false;
	}

	public boolean foundFruit() {
		SnakeSegment head = snake.getHead();
		int headX = head.getPositionX();
		int headY = head.getPositionY();

		for (Fruit current : fruits) {
			int fruitX = current.getPositionX();
			int fruitY = current.getPositionY();

			if (headX == fruitX && headY == fruitY) {
				fruits.remove(current);
				return true;
			}
		}
		return false;
	}
	
	public void placeFruit(Fruit inFruit) {
		fruits.add(inFruit);
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public int getScore() {
		return score;
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

}
