package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.camosun.snake.Snake.Direction;

public class SnakeBoard {

	private Snake snake;
	private List<Fruit> fruits;
	private int score;
	private int maxX;
	private int maxY;

	public SnakeBoard(int inWidth, int inHeight) {
		this(inWidth, inHeight, new Snake(Direction.NORTH, inWidth/2, inHeight/2));
	}
	
	public SnakeBoard(int inWidth, int inHeight, Snake inSnake) {
		maxX = inWidth  - 1;
		maxY = inHeight - 1;
		snake = inSnake; 
		fruits = new ArrayList<Fruit>();
		score = 0;
	}
		
	public boolean wentOffBoard() {
		SnakeSegment head = snake.getHead();
		int x = head.getPositionX();
		int y = head.getPositionY();

		
		if (x < 0) {
			return true;
		}
		if (y < 0) {
			return true;
		}
		if (x > maxX) {
			return true;
		}
		if (y > maxY) {
			return true;
		}

		return false;
	}

	public boolean foundFruit() {
		SnakeSegment head = snake.getHead();
		int headX = head.getPositionX();
		int headY = head.getPositionY();        
		for (Fruit current : fruits) {
			int fruitX = current.getPositionX();
			int fruitY = current.getPositionY();			
			if (headX == fruitX && headY == fruitY) {				
				fruits.remove(current);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return false if not enough space to add fruitsToAdd count fruits, else true
	 */
	public boolean addRandomFruits(int fruitsToAdd) {
		Random rand = new Random();
		int i = 0;
		
		while (i < fruitsToAdd) {
			if (hasEmptyCells() == false) {
				return false;
			}
			
			int fruitX = rand.nextInt(maxX + 1);
			int fruitY = rand.nextInt(maxY + 1);
			
			Fruit theFruit = new Fruit(fruitX, fruitY);
			if (placeFruit(theFruit)) {
				i++;
			}
		}
		return true;
	}
	
	/**
	 * @return false if position already occupied
	 */
	public boolean placeFruit(Fruit addFruit) {
		if (isEmptyCell(addFruit.getPositionX(), addFruit.getPositionY()) == false) {
			return false;
		}
		
		fruits.add(addFruit);
		return true;
	}
	
	private boolean hasEmptyCells() {
		return (snake.size() + fruits.size()) < ((maxX+1) * (maxY+1));
	}
	
	public boolean isEmptyCell(int inX, int inY) {
		return get(inX, inY) == null;
	}
	
	public Entity get(int inX, int inY) {
		SnakeSegment fakeSegment = new SnakeSegment(inX, inY);
		for (SnakeSegment seg : snake) {
			if (seg.equals(fakeSegment)) {
				return seg;
			}
		}
		
		Fruit fakeFruit = new Fruit(inX, inY);
		int foundFruitIndex = fruits.indexOf(fakeFruit);
		if (foundFruitIndex != -1) {
			return fruits.get(foundFruitIndex);
		}
		
		return null;
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public int getScore() {
		return score;
	}

	public List<Fruit> getFruits() {
		return fruits;
	}
	
}
