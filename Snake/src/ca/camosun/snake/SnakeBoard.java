package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeBoard {

	private Snake snake;
	private List<Fruit> fruits;
	private int score;
	
	public SnakeBoard() {
		
		snake = new Snake();
		fruits = new ArrayList<Fruit>();
		score = 0;
		
		
	}
			
	// TODO: Method to detect a collision between the snake and the edge
	

}
