package ca.camosun.snake;

public class SnakeSegment {
	private int positionX;
	private int positionY;

	public SnakeSegment(int inX, int inY) {
		positionX = inX;
		positionY = inY;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public boolean equals(Object other) {

		// We want a segment that is *not* this one
		// This simplifies iterating the snake body
		if (this == other)
			return false;

		SnakeSegment that = (SnakeSegment) other;

		if (positionX != that.positionX)
			return false;

		if (positionY != that.positionY)
			return false;

		return true;
	}
}
