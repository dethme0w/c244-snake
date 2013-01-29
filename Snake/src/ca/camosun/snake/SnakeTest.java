package ca.camosun.snake;

import static org.junit.Assert.*;
import static ca.camosun.snake.Snake.Direction.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SnakeTest {
	List<Fruit> noFruit;

	@Before
	public void setUp() throws Exception {
		noFruit = Collections.emptyList();
	}

	@Test
	public void changeDirectionOnlyAfterMove() {
		Snake snake = new Snake(NORTH);

		snake.changeDirection(EAST);
		assertEquals(NORTH, snake.getCurrentDirection());

		snake.moveSnake(noFruit);
		assertEquals(EAST, snake.getCurrentDirection());
	}

	@Test
	public void changeDirectionNotOpposite() {
		Snake snake = new Snake(NORTH);

		snake.changeDirection(SOUTH);

		snake.moveSnake(noFruit);
		assertEquals(NORTH, snake.getCurrentDirection());
	}

	@Test
	public void changedDirectionTwiddlingOverridesLast() {
		Snake snake = new Snake(NORTH);

		snake.changeDirection(EAST);
		snake.changeDirection(NORTH);

		snake.moveSnake(noFruit);
		assertEquals(NORTH, snake.getCurrentDirection());
	}

	@Test
	public void changedDirectionOppositeCheckingAppliesToLastMovedDirectionNotNextDirection() {
		Snake snake = new Snake(NORTH);

		snake.changeDirection(EAST);
		snake.changeDirection(WEST);

		snake.moveSnake(noFruit);
		assertEquals(WEST, snake.getCurrentDirection());
		
	}

	@Test
	public void changeDirectionTwiddlingDirectionsDoesntCircumventNotOpposite() {
		/*
		 * consider snake moving north. user presses right then down before
		 * snake moves. on next move, snake shouldn't move down through itself.
		 */

		Snake snake = new Snake(NORTH);

		snake.changeDirection(EAST);
		snake.changeDirection(SOUTH);

		snake.moveSnake(noFruit);
		assertEquals(EAST, snake.getCurrentDirection());
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
		assertFalse(aSegment.equals(aSegment));

	}

	@Test
	public void collided() {
		Snake snake = new Snake(NORTH);
		assertFalse(snake.collidedSelf()); // Should not be in collision when brand new
		
		// TODO: Add segments to the snake
		//       Test for no collision
		//       Add segment that equals head
		//       Test for collision
		
	}

}
