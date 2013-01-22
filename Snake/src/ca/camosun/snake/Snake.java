package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the snake. 
 * 
 * Fundamentally, the snake is a list of grid coordinates. As the snake
 * moves, the tail moves to the head, and the new head's grid location is updated.
 * We maintain head and tail list indices for this; the first and last items on the list
 * are not necessarily the head and tail of the snake.
 * When the snake eats a fruit, a new segment is added to the head at the location of the fruit.
 * This mechanism is open for discussion.
 * 
 */

public class Snake {
	private List<SnakeSegment> segments;
	private int head;
	private int tail;
	private Direction currentDirection;
	private Direction nextDirection;

	public static enum Direction {
		NORTH, EAST, SOUTH, WEST
	}

	public Snake() {
		segments = new ArrayList<SnakeSegment>();
		head = 0;
		tail = 0;
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

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}

}
