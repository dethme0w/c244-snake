package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.camosun.snake.Snake.Direction;
import ca.camosun.snake.gui.GameActivity.ObstacleType;

public class SnakeBoard {

	private Snake snake;
	private List<Fruit> fruits;
	private List<Bomb> bombs;
	private List<Pipe> pipes;
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
		bombs = new ArrayList<Bomb>();
		pipes = new ArrayList<Pipe>();
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
	
	public boolean hitBomb() {
		SnakeSegment head = snake.getHead();
		int headX = head.getPositionX();
		int headY = head.getPositionY();        
		for (Bomb current : bombs) {
			int bombX = current.getPositionX();
			int bombY = current.getPositionY();			
			if (headX == bombX && headY == bombY) {				
				bombs.remove(current);
				return true;
			}
		}
		return false;
	}
	
	public boolean hitPipe() {
		SnakeSegment head = snake.getHead();
		int headX = head.getPositionX();
		int headY = head.getPositionY();        
		for (Pipe current : pipes) {
			int pipeX = current.getPositionX();
			int pipeY = current.getPositionY();			
			if (headX == pipeX && headY == pipeY) {				
				pipes.remove(current);
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
	
	private boolean addRandomBombs(int bombCount) {
		Random rand = new Random();
		int i = 0;
		
		while (i < bombCount) {
			if (hasEmptyCells() == false) {
				return false;
			}
			
			int bombX = rand.nextInt(maxX + 1);
			int bombY = rand.nextInt(maxY + 1);
			
			Bomb theBomb = new Bomb(bombX, bombY);
			
			if (isEmptyCell(theBomb.getPositionX(), theBomb.getPositionY()) == false) {
				return false;
			}
			
			bombs.add(theBomb);
			i++;
			
			
		}
		
		return true;
	}
	
	private boolean addVerticalPipe(int pipeLength) {
		Random rand = new Random();
		int i = 1;
		
		//Place first pipe piece at random position
		int pipeXfirst = rand.nextInt(maxX + 1);
		int pipeYfirst = rand.nextInt(maxY + 1);
		Pipe startPipe = new Pipe(pipeXfirst, pipeYfirst);
		
		if (isEmptyCell(startPipe.getPositionX(), startPipe.getPositionY()) == false) {
			return false;
		}
		
		pipes.add(startPipe);
				
		//Place the next pipe pieces beside the other pipe pieces to create a lane
		while (i < pipeLength) {
	
			if (hasEmptyCells() == false) {
				return false;
			}
			
			int pipeX = startPipe.getPositionX();
			int pipeY = startPipe.getPositionY() + i;
			
			Pipe aPipe = new Pipe(pipeX, pipeY);
			
			
			if (isEmptyCell(aPipe.getPositionX(), aPipe.getPositionY()) == false) {
				return false;
			}
			
			pipes.add(aPipe);
			i++;
			
			
		}
		
		return true;
	}
	
	public boolean addObstacle(ObstacleType obstacle, int obstacleCount) {
		
		
		switch(obstacle) {
		
			case BOMB: 
				addRandomBombs(obstacleCount);
				break;
		
			case PIPE:
				addVerticalPipe(obstacleCount);
				break;
		
			case TRAP_DOOR:
				break;
	
			default:
				break;
				
				
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
	
	public List<Bomb> getBombs() {
		return bombs;
	}
	
	public List<Pipe> getPipes() {
		return pipes;
	}
	
}
