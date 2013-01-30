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

	@Before
	public void setUp() throws Exception {
		noFruit = Collections.emptyList();
		snake = new Snake(NORTH, 0, 0);
	}

	@Test
	public void changeDirectionOnlyAfterMove() {

		snake.changeDirection(EAST);
		assertEquals(NORTH, snake.getCurrentDirection());

		snake.moveSnake(EAST);
		assertEquals(EAST, snake.getCurrentDirection());
		
		snakeHead = snake.getSnake().get(0);
		assertTrue(snakeHead.getPositionX() == 1);
		assertTrue(snakeHead.getPositionY() == 0);
	}

	@Test
	public void changeDirectionNotOpposite() {

		snake.changeDirection(SOUTH);

		snake.moveSnake(NORTH);
		assertEquals(NORTH, snake.getCurrentDirection());
		
		snakeHead = snake.getSnake().get(0);
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 1);
	}

	@Test
	public void changedDirectionTwiddlingOverridesLast() {

		snake.changeDirection(EAST);
		snake.changeDirection(NORTH);

		snake.moveSnake(NORTH);
		assertEquals(NORTH, snake.getCurrentDirection());
		
		snakeHead = snake.getSnake().get(0);
		assertTrue(snakeHead.getPositionX() == 0);
		assertTrue(snakeHead.getPositionY() == 1);
	}

	@Test
	public void changedDirectionOppositeCheckingAppliesToLastMovedDirectionNotNextDirection() {

		snake.changeDirection(EAST);
		snake.changeDirection(WEST);

		snake.moveSnake(WEST);
		assertEquals(WEST, snake.getCurrentDirection());
		
		snakeHead = snake.getSnake().get(0);
		assertTrue(snakeHead.getPositionX() == -1);
		assertTrue(snakeHead.getPositionY() == 0);
		
	}

	@Test
	public void changeDirectionTwiddlingDirectionsDoesntCircumventNotOpposite() {
		/*
		 * consider snake moving north. user presses right then down before
		 * snake moves. on next move, snake shouldn't move down through itself.
		 */

		snake.changeDirection(EAST);
		snake.changeDirection(SOUTH);

		snake.moveSnake(EAST);
		assertEquals(EAST, snake.getCurrentDirection());
		
		snakeHead = snake.getSnake().get(0);
		assertTrue(snakeHead.getPositionX() == 1);
		assertTrue(snakeHead.getPositionY() == 0);
	}

	@Test
	public void snakeSegmentEquals() {
		SnakeSegment aSegment = new SnakeSegment(0, 0);
		SnakeSegment bSegment = new SnakeSegment(0, 1);
		SnakeSegment cSegment = new SnakeSegment(1, 0);
		SnakeSegment dSegment = new SnakeSegment(1, 1);
		SnakeSegment eSegment = new SnakeSegment(0, 0);

		// Test unequal segments
		assertFalse(aSegment.equals(bSegment));
		assertFalse(aSegment.equals(cSegment));
		assertFalse(aSegment.equals(dSegment));

		// Test equal but different segments
		assertTrue(aSegment.equals(eSegment));

		// Test same segment
		assertTrue(aSegment.equals(aSegment));

	}

	@Test
	public void collided() {
		assertFalse(snake.collidedSelf()); // Should not be in collision when brand new
		
		// TODO: Add segments to the snake
		//       Test for no collision
		//       Add segment that equals head
		//       Test for collision
		
	}

}
