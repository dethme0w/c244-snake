package ca.camosun.snake.test;

import static org.junit.Assert.*;
import static ca.camosun.snake.Snake.Direction.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.camosun.snake.Fruit;

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
		snake = new Snake(NORTH, 10, 10);
	}

	@Test
	public void changeDirections() {

		snake.moveSnake(EAST);
		assertTrue(snake.getCurrentDirection() == EAST);

		snakeHead = snake.getHead();
		assertTrue(snakeHead.getPositionX() == 11);
		assertTrue(snakeHead.getPositionY() == 10);

		snake.moveSnake(NORTH);
		snakeHead = snake.getHead();
		assertTrue(snake.getCurrentDirection() == NORTH);
		assertTrue(snakeHead.getPositionX() == 11);
		assertTrue(snakeHead.getPositionY() == 9);

		snake.moveSnake(WEST);
		snakeHead = snake.getHead();
		assertTrue(snake.getCurrentDirection() == WEST);
		assertTrue(snakeHead.getPositionX() == 10);
		assertTrue(snakeHead.getPositionY() == 9);

		snake.moveSnake(SOUTH);
		snakeHead = snake.getHead();
		assertTrue(snake.getCurrentDirection() == SOUTH);
		assertTrue(snakeHead.getPositionX() == 10);
		assertTrue(snakeHead.getPositionY() == 10);
	}

	@Test
	public void changeDirectionNotOpposite() {

		snake.moveSnake(SOUTH);
		assertTrue(snake.getCurrentDirection() == NORTH);

		snake.moveSnake(NORTH);
		assertTrue(snake.getCurrentDirection() == NORTH);

		snakeHead = snake.getHead();
		assertTrue(snakeHead.getPositionX() == 10);
		assertTrue(snakeHead.getPositionY() == 9);
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
		SnakeSegment tail = snake.getTail();	
		snake.dump();
		// initial
		assertTrue(snake.collidedSelf() == false);
		
		// give the snake some segments
		snake.moveSnake(NORTH);
		snake.grow(tail);
		snake.dump();
		snake.moveSnake(NORTH);
		snake.grow(tail);
		snake.dump();
		snake.moveSnake(NORTH);
		snake.grow(tail);
		snake.dump();
		snake.moveSnake(NORTH);
		snake.grow(tail);
		snake.dump();
		// Snake should now have enough segments to collide in this test
		assertTrue(snake.size()==5);		
		
		// Now move the snake in a tight circle
		snake.moveSnake(NORTH);
		assertTrue(snake.collidedSelf() == false);
		snake.moveSnake(EAST);
		assertTrue(snake.collidedSelf() == false);
		snake.moveSnake(SOUTH);
		assertTrue(snake.collidedSelf() == false);		
		snake.moveSnake(WEST);
		assertTrue(snake.collidedSelf() == true);
		
	}
	
	@Test
	public void snakeGrows() {
		
		SnakeSegment tail = snake.getTail();		
		assertTrue(snake.size() == 1);
		
		snake.moveSnake(NORTH);
		snake.grow(tail);
		assertTrue(snake.size() == 2);
		
		snake.moveSnake(EAST);
		snake.grow(tail);
		assertTrue(snake.size() == 3);
	}

	
}
