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
	private int distanceToMove;
	
	public static enum Direction {
		NORTH, SOUTH, EAST, WEST
	}

	public Snake() {
		snake = new ArrayList<SnakeSegment>();
		currentDirection = Direction.NORTH;
		distanceToMove = 1;
	}
	
	public void changeDirection(Direction newDirection) {
		
		switch (newDirection) {
		case NORTH:
			if (currentDirection == Direction.SOUTH) {
				break;
			}
			currentDirection = Direction.NORTH;
			break;
		
		case EAST:
			if (currentDirection == Direction.WEST) {
				break;
			}
			currentDirection = Direction.EAST;
			break;
		
		case SOUTH:
			if (currentDirection == Direction.NORTH) {
				break;
			}
			currentDirection = Direction.SOUTH;
			break;
		
		case WEST:
			if (currentDirection == Direction.EAST) {
				break;
			}
			currentDirection = Direction.WEST;
			break;
		}
	}

	public void moveSnake() {
		SnakeSegment newHead = snake.get(0);
		int YPosition = newHead.getPositionY();
		int XPosition = newHead.getPositionX();
		
		switch (currentDirection) {
		
		case NORTH:
			newHead.setPositionX(XPosition + distanceToMove);
			break;
		case EAST:
			newHead.setPositionY(YPosition + distanceToMove);
			break;
		case SOUTH:
			newHead.setPositionX(XPosition - distanceToMove);
			break;
		case WEST:
			newHead.setPositionY(YPosition - distanceToMove);
			break;
		}
		
		SnakeSegment tail = snake.get(snake.size()-1);
		snake.remove(tail);
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

	public void grow() {
		// TODO Fuction that grows the snake after it ate some fruit
		// Note: This is only needed if board is controlling snake
		
	}

}
