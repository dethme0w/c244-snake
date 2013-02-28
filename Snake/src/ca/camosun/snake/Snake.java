package ca.camosun.snake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Snake {
	private List<SnakeSegment> segments;
	private Direction currentDirection;

	public static enum Direction {
		NORTH(-1), SOUTH(1), EAST(1), WEST(-1);

		private final int distance;
		
		public int getDistance() {
			return distance;
		}

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
		
		
		segments.add(new SnakeSegment(startX, startY));	
		
		currentDirection = startDir;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake(Direction nextDirection, boolean ateFruit) {
		SnakeSegment head = getHead();
		SnakeSegment newHead = new SnakeSegment(head.getPositionX(), head.getPositionY());
		int YPosition = newHead.getPositionY();
		int XPosition = newHead.getPositionX();

		if (nextDirection.isOpposite(currentDirection)) {
			return;
		}

		switch (nextDirection) {

		case NORTH:
		case SOUTH:
			newHead.setPositionY(YPosition + nextDirection.getDistance());
			break;
		case EAST:
		case WEST:
			newHead.setPositionX(XPosition + nextDirection.getDistance());
			break;
		}

		currentDirection = nextDirection;
		SnakeSegment tail = segments.get(segments.size()-1);
		
		if (ateFruit == true) {
			segments.add(newHead);
			return;
		}
		
		segments.add(newHead);
		segments.remove(tail);
	}

	public boolean collidedSelf() {

		if (segments.size() == 1) {
			return false;
		}

		Iterator<SnakeSegment> segmentIterator = segments.iterator();
		SnakeSegment head = segmentIterator.next();
		
		while(segmentIterator.hasNext()) {
			SnakeSegment currentSegment = segmentIterator.next();
			if (currentSegment.equals(head)){
				return true;
			}
		}

		return false;
	}
	
	public Iterator<SnakeSegment> iterator() {
		return segments.iterator();
	}

	public SnakeSegment getHead() {
		return segments.get(0);
	}
	
	public SnakeSegment getTail() {
		return segments.get(segments.size()-1);
	}
	
	public SnakeSegment get(int index) {
		return segments.get(index);
	}
		
	public int size() {
		return segments.size();
	}
	
}
