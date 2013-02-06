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

	// TODO: Method to detect a collision between the snake and the edge

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

	// TODO: Method to detect fruit collision

	public Snake getSnake() {
		return snake;
	}

}
