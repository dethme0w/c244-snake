package ca.camosun.snake;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


public class Snake {
	private Deque<SnakeSegment> segments;
	private Direction currentDirection;

	public static enum Direction {
		NORTH(1), SOUTH(-1), EAST(1), WEST(-1);

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
			}
			return EAST;
		}

		public boolean isOpposite(Direction other) {
			return opposite() == other;
		}
	}

	public Snake(Direction startDir, int startX, int startY) {
		segments = new ArrayDeque<SnakeSegment>();
		segments.add(new SnakeSegment(startX, startY));

		currentDirection = startDir;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake(Direction nextDirection, boolean ateFruit) {
		SnakeSegment newHead = getHead();
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
		SnakeSegment tail = segments.peekLast();
		
		if (ateFruit == true) {
			segments.addFirst(newHead);
			return;
		}
		
		segments.addFirst(newHead);
		segments.remove(tail);
	}

	public boolean collidedSelf() {

		SnakeSegment head = segments.peek();

		if (segments.size() == 1) {
			return false;
		}

		Iterator<SnakeSegment> segmentIterator = segments.iterator();
		segmentIterator.next();
		
		while(segmentIterator.hasNext()) {
			SnakeSegment currentSegment = segmentIterator.next();
			if (currentSegment == head){
				return true;
			}
		}

		return false;
	}

	public SnakeSegment getHead() {
		return segments.peek();
	}
	
	
	public int size() {
		return segments.size();
	}
	
}
