package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the snake.
 * 
 */

public class Snake {
	private List<SnakeSegment> segments;	
	private SnakeSegment lastTail;
	private Direction currentDirection;
	private Direction nextDirection;

	public static enum Direction {
		NORTH, EAST, SOUTH, WEST
	}
	
	public static enum MoveOutcome {
	    CONTINUE, COLLIDED_SELF, COLLIDED_WALL
	}

	public Snake() {
		segments = new ArrayList<SnakeSegment>();
		lastTail = new SnakeSegment(0, 0);
		currentDirection = Direction.NORTH;
		nextDirection = Direction.NORTH;
	}

		
	public void changeDirection(Direction aDirection) {
		switch (aDirection) {

		case NORTH:
			if (currentDirection == Direction.SOUTH) {
				break;
			}
			nextDirection = aDirection;
			break;
		case EAST:
			if (currentDirection == Direction.WEST) {
				break;
			}
			nextDirection = aDirection;
			break;
		case SOUTH:
			if (currentDirection == Direction.NORTH) {
				break;
			}
			nextDirection = aDirection;
			break;
		case WEST:
			if (currentDirection == Direction.EAST) {
				break;
			}
			nextDirection = aDirection;
			break;

		}

	}

	public void feedFruit() {
		// TODO: The snake ate a fruit. Grow it.

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
		return segments;
	}

	public void setSegments(List<SnakeSegment> segments) {
		this.segments = segments;
	}

}
