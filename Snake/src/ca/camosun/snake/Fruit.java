package ca.camosun.snake;

public class Fruit {

	private int positionX;
	private int positionY;
	private int points;
	private int flavour;

	public Fruit(int inX, int inY, int inPoints, int inFlavour) {
		positionX = inX;
		positionY = inY;
		points = inPoints;
		flavour = inFlavour;
	}
	
	public Fruit(int inX, int inY) {
		positionX = inX;
		positionY = inY;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getFlavour() {
		return flavour;
	}

	public void setFlavour(int flavour) {
		this.flavour = flavour;
	}

}
