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
	
	public static enum MoveOutcome {
	    CONTINUE, COLLIDED_SELF, COLLIDED_WALL
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
			currentDirection = newDirection;
			break;
		case EAST:
			if (currentDirection == Direction.WEST) {
				break;
			}
			currentDirection = newDirection;
			break;
		case SOUTH:
			if (currentDirection == Direction.NORTH) {
				break;
			}
			currentDirection = newDirection;
			break;
		case WEST:
			if (currentDirection == Direction.EAST) {
				break;
			}
			currentDirection = newDirection;
			break;
		}
	}

	public void moveSnake() {
		SnakeSegment head = snake.get(0);
		int YPosition = head.getPositionY();
		int XPosition = head.getPositionX();
		
		switch (currentDirection) {
		
		case NORTH:
			head.setPositionX(XPosition + distanceToMove);
			break;
		case EAST:
			head.setPositionY(YPosition + distanceToMove);
			break;
		case SOUTH:
			head.setPositionX(XPosition - distanceToMove);
			break;
		case WEST:
			head.setPositionY(YPosition - distanceToMove);
			break;
		}
		
		SnakeSegment tail = snake.get(snake.size()-1);
		if (gotFruit() == false) {
			snake.remove(tail);
		}
			
		snake.add(0, head);
	}
	
	private boolean gotFruit() {
		// TODO Check to see if you got some fruit
		return false;
	}

	public boolean collidedSelf() {
		// TODO: Compare the head segment with all others.
		// If there is a match, the snake has collided with itself and the game
		// is over; return true

		return false;
	}

	public void defaultSnake(int startX, int startY) {
		// TODO: Clear the snake segments, create a new snake
		// with one segment located at startX,startY on the board

	}

	public List<SnakeSegment> getSegments() {
		return snake;
	}

	public void setSegments(List<SnakeSegment> segments) {
		this.snake = segments;
	}

}
