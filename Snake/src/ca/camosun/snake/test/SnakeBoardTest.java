package ca.camosun.snake.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.camosun.snake.Fruit;
import ca.camosun.snake.Snake;
import ca.camosun.snake.Snake.Direction;
import ca.camosun.snake.SnakeBoard;

public class SnakeBoardTest {

	Snake snake;
	SnakeBoard board;

	@Before
	public void setUp() throws Exception {
		reloadBoard(Direction.NORTH);
	}
	
	private void reloadBoard(Direction startDir) {
		snake = new Snake(startDir, 2, 2);
		board = new SnakeBoard(5, 5, snake);		
	}

	@Test
	public void snakeWentOffBoard() {
		reloadBoard(Direction.NORTH);
		snake.moveSnake(Direction.NORTH);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.NORTH);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.NORTH);
		assertTrue(board.wentOffBoard() == true);
		
		reloadBoard(Direction.EAST);
		snake.moveSnake(Direction.EAST);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.EAST);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.EAST);
		assertTrue(board.wentOffBoard() == true);
		
		reloadBoard(Direction.SOUTH);
		snake.moveSnake(Direction.SOUTH);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.SOUTH);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.SOUTH);
		assertTrue(board.wentOffBoard() == true);
		
		reloadBoard(Direction.WEST);
		snake.moveSnake(Direction.WEST);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.WEST);
		assertTrue(board.wentOffBoard() == false);
		snake.moveSnake(Direction.WEST);
		assertTrue(board.wentOffBoard() == true);
	}
		
	@Test
	public void isCellEmpty() {
		board.placeFruit(new Fruit(4,4));
		
		assertTrue(board.isEmptyCell(4, 4) == false);
	}
	
	@Test
	public void placeFruitsWorks() {
		board.addRandomFruits(3);
		
		assertTrue(board.getFruits().size() == 3);
	}
	
	@Test
	public void placeFruitsFailsAddingTooMany() {
		// fill every empty space with fruit
		assertTrue(board.addRandomFruits(5*5-1));
		
		// no more spaces!
		assertTrue(board.addRandomFruits(1) == false);
	}
	
	@Test
	public void placeFruitTestsCollision() {
		// place on fruit fails
		assertTrue(board.placeFruit(new Fruit(4,4)));
		assertTrue(board.placeFruit(new Fruit(4,4)) == false);

		// place on snake fails
		assertTrue(board.placeFruit(new Fruit(2,2)) == false);
	}
}
