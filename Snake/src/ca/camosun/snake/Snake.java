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
		NORTH, SOUTH, EAST, WEST;
		
		public boolean isOpposite(Direction other) {
			switch (this) {
			case NORTH:
				if (other == Direction.SOUTH) {
					return true;
				}
				break;
			
			case EAST:
				if (other == Direction.WEST) {
					return true;
				}
				break;
			
			case SOUTH:
				if (other == Direction.NORTH) {
					return true;
				}
				break;
			
			case WEST:
				if (other == Direction.EAST) {
					return true;
				}
				break;
			}
			
			return false;
		}
	}

	public Snake() {
		snake = new ArrayList<SnakeSegment>();
		currentDirection = Direction.NORTH;
	}
	
	public void changeDirection(Direction newDirection) {
		if (newDirection.isOpposite(currentDirection)) {
			return;
		}
		
		currentDirection = newDirection;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake(List<Fruit> inFruits) {
		SnakeSegment newHead = snake.get(0);
		int YPosition = newHead.getPositionY();
		int XPosition = newHead.getPositionX();
		
		switch (currentDirection) {
		
		case NORTH:
			newHead.setPositionX(XPosition + 1);
			break;
		case EAST:
			newHead.setPositionY(YPosition + 1);
			break;
		case SOUTH:
			newHead.setPositionX(XPosition - 1);
			break;
		case WEST:
			newHead.setPositionY(YPosition - 1);
			break;
		}
		
		SnakeSegment tail = snake.get(snake.size()-1);
		if (gotFruit(inFruits) == false) {
			snake.remove(tail);
		}
		snake.add(0, newHead);
	}
	
	public boolean collidedSelf() {
		// TODO: Compare the head segment with all others.
		// If there is a match, the snake has collided with itself and the game
		// is over; return true

		return false;
	}

	public List<SnakeSegment> getSnake() {
		return snake;
	}

	private boolean gotFruit(List<Fruit> inFruits) {
		SnakeSegment head = snake.get(0);
		int headX = head.getPositionX();
		int headY = head.getPositionY();
		
		for(Fruit current:inFruits) {
			int fruitX = current.getPositionX();
			int fruitY = current.getPositionY();
			
			if (headX == fruitX && headY == fruitY) {
				return true;
			}
		}
		return false;
	}

}
