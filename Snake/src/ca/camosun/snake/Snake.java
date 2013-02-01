package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the snake.
 * 
 */

public class Snake {
	private List<SnakeSegment> snake;
	private Direction currentDirection;

	public static enum Direction {
		NORTH(1), 
		SOUTH(-1), 
		EAST(1), 
		WEST(-1);

		private final int distance;

		Direction(int moveDistance) {
			distance = moveDistance;
		}

		public Direction opposite() {
			switch (this) {
			case NORTH:
				return SOUTH;
			case EAST:
				return WEST;
			case SOUTH:
				return NORTH;
			case WEST:
				return EAST;
			default:
				throw new IllegalStateException("Direction not implemented");
			}
		}

		public boolean isOpposite(Direction other) {
			return opposite() == other;
		}
	}

	public Snake(Direction startDir, int startX, int startY) {
		snake = new ArrayList<SnakeSegment>();
		snake.add(new SnakeSegment(startX, startY));

		currentDirection = startDir;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake(Direction nextDirection) {
		SnakeSegment newHead = snake.get(0);
		int YPosition = newHead.getPositionY();
		int XPosition = newHead.getPositionX();
		
		if (nextDirection.isOpposite(currentDirection)) {
			return;
		}

		switch (nextDirection) {

		case NORTH:
		case SOUTH:
			newHead.setPositionY(YPosition + nextDirection.distance);
			break;
		case EAST:
		case WEST:
			newHead.setPositionX(XPosition + nextDirection.distance);
			break;
		}

		currentDirection = nextDirection;

		SnakeSegment tail = snake.get(snake.size() - 1);
		/*
		 * if (gotFruit(inFruits) == false) { snake.remove(tail); }
		 */
		snake.add(0, newHead);
	}

	public boolean collidedSelf() {

		SnakeSegment head = snake.get(0);

		if (snake.size() < 2)
			return false;

		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(i).equals(head))
				return true;
		}

		return true;
	}

	public List<SnakeSegment> getSnake() {
		return snake;
	}

	private boolean gotFruit(List<Fruit> inFruits) {
		SnakeSegment head = snake.get(0);
		int headX = head.getPositionX();
		int headY = head.getPositionY();

		for (Fruit current : inFruits) {
			int fruitX = current.getPositionX();
			int fruitY = current.getPositionY();

			if (headX == fruitX && headY == fruitY) {
				return true;
			}
		}
		return false;
	}

}
