package ca.camosun.snake.test;

import static org.junit.Assert.*;
import static ca.camosun.snake.Snake.Direction.*;

import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.camosun.snake.Fruit;
import ca.camosun.snake.HighScores;

import ca.camosun.snake.Snake;
import ca.camosun.snake.SnakeSegment;

public class SnakeTest {
	List<Fruit> noFruit;
	Snake snake;
	SnakeSegment snakeHead;
	boolean ateFruit = false;

	@Before
	public void setUp() throws Exception {
		noFruit = Collections.emptyList();
		snake = new Snake(NORTH, 0, 0);
	}

	@Test
	public void changeDirections() {

		snake.moveSnake(EAST, ateFruit);
		assertTrue(snake.getCurrentDirection() == EAST);

		snakeHead = snake.getHead();
		assertTrue(snakeHead.getPositionX() == 1);
		assertTrue(snakeHead.getPositionY() == 0);

		snake.moveSnake(NORTH, ateFruit);
		snakeHead = snake.getHead();
		assertTrue(snake.getCurrentDirection() == NORTH);
		assertTrue(snakeHead.getPositionX() == 1);
		assertTrue(snakeHead.getPositionY() == 1);

		snake.moveSnake(WEST, ateFruit);
		snakeHead = snake.getHead();
		assertTrue(snake.getCurrentDirection() == WEST);
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 1);

		snake.moveSnake(SOUTH, ateFruit);
		snakeHead = snake.getHead();
		assertTrue(snake.getCurrentDirection() == SOUTH);
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 0);
	}

	@Test
	public void changeDirectionNotOpposite() {

		snake.moveSnake(SOUTH, ateFruit);
		assertTrue(snake.getCurrentDirection() == NORTH);

		snake.moveSnake(NORTH, ateFruit);
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
		assertTrue(snake.collidedSelf() == false);
		
		ateFruit = true;
		snake.moveSnake(NORTH, ateFruit);
		assertTrue(snake.collidedSelf() == false);
		snake.moveSnake(EAST, ateFruit);
		assertTrue(snake.collidedSelf() == false);
		snake.moveSnake(SOUTH, ateFruit);
		assertTrue(snake.collidedSelf() == false);
		
		snake.moveSnake(WEST, ateFruit);
		assertTrue(snake.collidedSelf() == true);
		
	}
	
	@Test
	public void snakeGrows() {
		assertTrue(snake.size() == 1);
		
		snake.moveSnake(NORTH, false);
		assertTrue(snake.size() == 1);
		
		snake.moveSnake(NORTH, true);
		assertTrue(snake.size() == 2);
	}

	
}
