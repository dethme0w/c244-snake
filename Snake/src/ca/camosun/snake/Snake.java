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
	private Direction nextDirection; // This will become currentDirection only when we actually move

	public static enum Direction {
		NORTH, SOUTH, EAST, WEST;

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
		nextDirection = startDir;
	}

	public void changeDirection(Direction newDirection) {
		if (newDirection.isOpposite(currentDirection)) {
			return;
		}
		
        // OK, direction change is allowed - queue it up
		nextDirection = newDirection;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake() {
		SnakeSegment newHead = snake.get(0);
		int YPosition = newHead.getPositionY();
		int XPosition = newHead.getPositionX();

		switch (nextDirection) {

		case NORTH:
			newHead.setPositionY(YPosition + 1);
			break;
		case EAST:
			newHead.setPositionX(XPosition + 1);
			break;
		case SOUTH:
			newHead.setPositionY(YPosition - 1);
			break;
		case WEST:
			newHead.setPositionX(XPosition - 1);
			break;
		}
        
		currentDirection = nextDirection;
		
		SnakeSegment tail = snake.get(snake.size() - 1);
		/*if (gotFruit(inFruits) == false) {
			snake.remove(tail);
		}*/
		snake.add(0, newHead);
	}

	public boolean collidedSelf() {

		SnakeSegment head = snake.get(0);

		if (snake.size()<2) 
			return false;
		
		for(int i=1; i<snake.size();i++){
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
