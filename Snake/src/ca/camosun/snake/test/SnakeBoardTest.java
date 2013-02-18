package ca.camosun.snake.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.camosun.snake.Fruit;
import ca.camosun.snake.Snake;
import ca.camosun.snake.SnakeBoard;
import ca.camosun.snake.SnakeSegment;

public class SnakeBoardTest {

	Snake snake;
	SnakeSegment snakeHead;
	SnakeBoard board;

	@Before
	public void setUp() throws Exception {
		board = new SnakeBoard(49, 49);
		snake = board.getSnake();		
	}

	@Test
	public void snakeWentOffBoard() {
		
		board = new SnakeBoard(49, 49);
		snake = board.getSnake();
		// Try to go south (will run off board)
		snake.moveSnake(Snake.Direction.EAST, board.foundFruit());
		snake.moveSnake(Snake.Direction.SOUTH, board.foundFruit());
		assertTrue(board.wentOffBoard() == true);
		
		board = new SnakeBoard(49, 49);
		snake = board.getSnake();
		// Try to go north (will not run off board)
		snake.moveSnake(Snake.Direction.NORTH, board.foundFruit());
		assertTrue(board.wentOffBoard() == false);
		
		board = new SnakeBoard(49, 49);
		snake = board.getSnake();
		// Try to go west (will run off board)
		snake.moveSnake(Snake.Direction.WEST, board.foundFruit());
		assertTrue(board.wentOffBoard() == true);
		
		board = new SnakeBoard(49, 49);
		snake = board.getSnake();
		// Try to go east (will not run off board)
		snake.moveSnake(Snake.Direction.EAST, board.foundFruit());
		assertTrue(board.wentOffBoard() == false);

	}
	
	@Test
	public void snakeAteFruit() {		
		Fruit a = new Fruit(0, 1);
		board.placeFruit(a);
		
		snake.moveSnake(Snake.Direction.NORTH, board.foundFruit());
		assertTrue(board.foundFruit() == true);
		
		snake.moveSnake(Snake.Direction.NORTH, board.foundFruit());
		assertTrue(board.foundFruit() == false);
		
	}
	
	@Test
	public void placeFruitWorks() {
		Fruit a = new Fruit(0, 1);
		board.placeFruit(a);
		Fruit b = new Fruit(1, 2);
		board.placeFruit(b);
		Fruit c = new Fruit(1, 3);
		board.placeFruit(c);
		assertTrue(board.getFruits().size() == 3);
	}
	
	@Test
	public void eatFruit() {
		Fruit a = new Fruit(0, 1);
		board.placeFruit(a);
		Fruit b = new Fruit(1, 2);
		board.placeFruit(b);
		Fruit c = new Fruit(1, 3);
		board.placeFruit(c);
		assertTrue(board.getFruits().size() == 3);
		
		snake.moveSnake(Snake.Direction.NORTH, board.foundFruit());
		snake.moveSnake(Snake.Direction.NORTH, board.foundFruit());
		
		assertTrue(board.getFruits().size() == 2);
	}
	
	@Test
	public void checkFruitWorks() {
		Fruit a = new Fruit(0,0);
		Fruit b = new Fruit(1,1);
		
		assertTrue(board.checkFruit(a.getPositionX(), a.getPositionY()) == false);
		assertTrue(board.checkFruit(b.getPositionX(), b.getPositionY()) == true);
	}
	
	@Test
	public void placeFruitsWorks() {
		snake.moveSnake(Snake.Direction.EAST, false);
		snake.moveSnake(Snake.Direction.SOUTH, false);
		
		List<Fruit> fruits;
		board.placeFruits(3);
		fruits = board.getFruits();
		assertTrue(fruits.size() == 3);
		
	}
}
