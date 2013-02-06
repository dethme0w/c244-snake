package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;


public class Snake {
	private List<SnakeSegment> segments;
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
		segments = new ArrayList<SnakeSegment>();
		segments.add(new SnakeSegment(startX, startY));

		currentDirection = startDir;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void moveSnake(Direction nextDirection) {
		SnakeSegment newHead = segments.get(0);
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

		SnakeSegment tail = segments.get(segments.size() - 1);
		segments.add(0, newHead);
	}

	public boolean collidedSelf() {

		SnakeSegment head = segments.get(0);

		if (segments.size() < 2) {
			return false;
		}

		for (int i = 1; i < segments.size(); i++) {
			if (segments.get(i).equals(head))
				return true;
		}

		return true;
	}

	public SnakeSegment getHead() {
		return segments.get(0);
	}

	private boolean gotFruit(List<Fruit> inFruits) {
		SnakeSegment head = segments.get(0);
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
