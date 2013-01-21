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

}
