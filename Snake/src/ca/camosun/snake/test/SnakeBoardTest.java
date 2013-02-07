package ca.camosun.snake.test;

import static org.junit.Assert.*;

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

		// Try to go south (will run off board)
		snake.moveSnake(Snake.Direction.SOUTH);
		assertTrue(board.wentOffBoard() == true);

		// Try to go north (will not run off board)
		snake.moveSnake(Snake.Direction.NORTH);
		assertTrue(board.wentOffBoard() == false);

		// Try to go west (will run off board)
		snake.moveSnake(Snake.Direction.WEST);
		assertTrue(board.wentOffBoard() == true);

		// Try to go east (will not run off board)
		snake.moveSnake(Snake.Direction.EAST);
		assertTrue(board.wentOffBoard() == false);

	}
	
	@Test
	public void snakeAteFruit() {
		
		Fruit a = new Fruit(0, 1);
		board.addFruit(a);
		
		snake.moveSnake(Snake.Direction.NORTH);
		assertTrue(board.ateFruit() == true);
		
		snake.moveSnake(Snake.Direction.NORTH);
		assertTrue(board.ateFruit() == false);
		
	}
}
