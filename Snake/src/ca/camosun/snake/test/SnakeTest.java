package ca.camosun.snake.test;

import static org.junit.Assert.*;
import static ca.camosun.snake.Snake.Direction.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.camosun.snake.Fruit;
import ca.camosun.snake.HighScores;
import ca.camosun.snake.Score;
import ca.camosun.snake.Snake;
import ca.camosun.snake.SnakeSegment;

public class SnakeTest {
	List<Fruit> noFruit;
	Snake snake;
	SnakeSegment snakeHead;

	@Before
	public void setUp() throws Exception {
		noFruit = Collections.emptyList();
		snake = new Snake(NORTH, 0, 0);
	}

	@Test
	public void changeDirections() {

		snake.moveSnake(EAST);
		assertTrue(snake.getCurrentDirection() == EAST);

		snakeHead = snake.getHead();
		assertTrue(snakeHead.getPositionX() == 1);
		assertTrue(snakeHead.getPositionY() == 0);

		snake.moveSnake(NORTH);
		assertTrue(snake.getCurrentDirection() == NORTH);
		assertTrue(snakeHead.getPositionX() == 1);
		assertTrue(snakeHead.getPositionY() == 1);

		snake.moveSnake(WEST);
		assertTrue(snake.getCurrentDirection() == WEST);
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 1);

		snake.moveSnake(SOUTH);
		assertTrue(snake.getCurrentDirection() == SOUTH);
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 0);
	}

	@Test
	public void changeDirectionNotOpposite() {

		snake.moveSnake(SOUTH);
		assertTrue(snake.getCurrentDirection() == NORTH);

		snake.moveSnake(NORTH);
		assertTrue(snake.getCurrentDirection() == NORTH);

		snakeHead = snake.getHead();
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 1);
	}

	@Test
	public void snakeSegmentEquals() {
		SnakeSegment aSegment = new SnakeSegment(0, 0);
		SnakeSegment bSegment = new SnakeSegment(0, 1);
		SnakeSegment cSegment = new SnakeSegment(1, 0);
		SnakeSegment dSegment = new SnakeSegment(1, 1);
		SnakeSegment eSegment = new SnakeSegment(0, 0);

		// Test unequal segments
		assertTrue(aSegment.equals(bSegment) == false);
		assertTrue(aSegment.equals(cSegment) == false);
		assertTrue(aSegment.equals(dSegment) == false);

		// Test equal but different segments
		assertTrue(aSegment.equals(eSegment) == true);

		// Test same segment
		assertTrue(aSegment.equals(aSegment) == true);

	}

	@Test
	public void collided() {
		assertTrue(snake.collidedSelf() == false); // Should not be in collision
													// when brand new
		// TODO: Add segments to the snake
		// Test for no collision
		// Add segment that equals head
		// Test for collision

	}

	
}
