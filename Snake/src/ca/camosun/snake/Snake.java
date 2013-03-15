package ca.camosun.snake;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Snake implements Iterable<SnakeSegment> {
	private Deque<SnakeSegment> segments;
	private Direction currentDirection;
	
	/* num of segments snake is waiting to grow; grows one each move by not 
	 * deleting its tail */
	private int queuedGrowth;

	public static enum Direction {
		NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0);

		public final int x;
		public final int y;

		Direction(int inX, int inY) {
			this.x = inX;
			this.y = inY;
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
		queuedGrowth = 0;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake(Direction nextDirection) {
		SnakeSegment oldHead = getHead();
				
		// snake can't reverse unless tiny
		if (segments.size() > 1 && nextDirection.isOpposite(currentDirection)) {
				nextDirection = currentDirection; // cancel next direction
		}

		// add new head
		{
			SnakeSegment newHead = new SnakeSegment(oldHead.getPositionX() + nextDirection.x,
													oldHead.getPositionY() + nextDirection.y);
			segments.addFirst(newHead);
		}
		
		currentDirection = nextDirection;	
				
		
		// remove tail
		if (queuedGrowth == 0) {
			segments.removeLast();
		} else { // grow by not removing tail segment		
			queuedGrowth--;
		}
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
	
	public void grow() {
		queuedGrowth++;
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
		
	public int size() {
		return segments.size();
	}
	
	public void dump() {
		int i = 0;
		for (SnakeSegment s : this) {
			System.out.print(i+": "+s.getPositionX()+","+s.getPositionY()+"  ");
			i++;
		}
		System.out.println();
		
	}
	
}
