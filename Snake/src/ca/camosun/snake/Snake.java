package ca.camosun.snake;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Snake implements Iterable<SnakeSegment> {
	private Deque<SnakeSegment> segments;
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
			default:
				return EAST;
			}
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

	public SnakeSegment moveSnake(Direction nextDirection) {
		SnakeSegment head = getHead();
		SnakeSegment newHead = new SnakeSegment(head.getPositionX(), head.getPositionY());
		SnakeSegment tail = getTail();
		
		int YPosition = newHead.getPositionY();
		int XPosition = newHead.getPositionX();

		if (nextDirection.isOpposite(currentDirection)) {
			nextDirection = currentDirection;
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
				
		segments.addFirst(newHead);
		return(segments.removeLast());
		
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
	
	public void grow(SnakeSegment newTail) {
		segments.addLast(newTail);
	}
	
	public Iterator<SnakeSegment> iterator() {
		return segments.iterator();
	}

	public SnakeSegment getHead() {
		return segments.peek();
	}
	
	public SnakeSegment getTail() {
		return segments.peekLast();
	}
	
	public SnakeSegment get(int index) {
		Object[] y = segments.toArray();		
		return (SnakeSegment)y[index];
	}
		
	public int size() {
		return segments.size();
	}
	
	public void dump() {
		for (int i=0; i<segments.size(); i++) {
			SnakeSegment s = get(i);
			System.out.print(i+": "+s.getPositionX()+","+s.getPositionY()+"  ");
		}
		System.out.println();
		
	}
	
}
