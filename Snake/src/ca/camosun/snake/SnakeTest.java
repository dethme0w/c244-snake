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
	public void testChangeDirectionOnlyAfterMove() {
		Snake snake = new Snake(NORTH);
		
		snake.changeDirection(EAST);
		assertEquals(NORTH, snake.getCurrentDirection());
		
		snake.moveSnake(noFruit);
		assertEquals(EAST, snake.getCurrentDirection());
	}
	
	@Test
	public void testChangeDirectionNotOpposite() {
		Snake snake = new Snake(NORTH);
		
		snake.changeDirection(SOUTH);
		
		snake.moveSnake(noFruit);
		assertEquals(NORTH, snake.getCurrentDirection());
	}
	
	@Test
	public void testChangedDirectionTwiddlingOverridesLast() {
		Snake snake = new Snake(NORTH);
		
		snake.changeDirection(EAST);
		snake.changeDirection(NORTH);
		
		snake.moveSnake(noFruit);
		assertEquals(NORTH, snake.getCurrentDirection());
	}
	
	@Test
	public void testChangedDirectionOppositeCheckingAppliesToLastMovedDirectionNotNextDirection() {
		Snake snake = new Snake(NORTH);
		
		snake.changeDirection(EAST);
		snake.changeDirection(WEST);
		
		snake.moveSnake(noFruit);
		assertEquals(WEST, snake.getCurrentDirection());
	}
	
	@Test
	public void testChangeDirectionTwiddlingDirectionsDoesntCircumventNotOpposite() {
		/* consider snake moving north.  
		 * user presses right then down before snake moves.  
		 * on next move, snake shouldn't move down through itself. */
		
		Snake snake = new Snake(NORTH);
		
		snake.changeDirection(EAST);
		snake.changeDirection(SOUTH);
		
		snake.moveSnake(noFruit);
		assertEquals(EAST, snake.getCurrentDirection());
	}
}
