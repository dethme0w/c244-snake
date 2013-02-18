package ca.camosun.snake;

import java.util.Date;



public class Controller {
	
	// Acts as API between the GUI / player input and the game logic
	// Start game button just needs to make a fresh GameController
	// Player input needs to be translated into a Snkae.Direction (NORTH, SOUTH, EAST, WEST), call setNextDirection to change the snake direction
	
	private SnakeBoard theBoard;
	private Snake.Direction nextDirection;
	private long gameTick = 1000;
	private int fruitPerLevel = 5;
	
	public Controller(int inScreenWidth, int inScreenHeight) {
		theBoard = new SnakeBoard(inScreenWidth, inScreenHeight);
		gameLoop();
	}
	
	public void setNextDirection(Snake.Direction inDirection) {
		nextDirection = inDirection;
	}
	
	private void gameLoop() {
		Snake theSnake = theBoard.getSnake();
		nextLevel();
		
		while (theBoard.isGameOver() == false) {
			theSnake.moveSnake(nextDirection, theBoard.foundFruit());
			if (theBoard.isLevelComplete() == true) {
				nextLevel();
			}
			
			waitUntil(gameTick);
		}
		
	}
	
	private void nextLevel() {
		theBoard.placeFruits(fruitPerLevel);
		gameTick = gameTick - 10;
	}

	private void waitUntil(long time) {
	    long sleepTime = time - new Date().getTime();

	    while (sleepTime >= 0) {
	        try {
	            Thread.sleep(sleepTime);
	        } catch (InterruptedException e) {
	            // Interrupted. sleepTime will be positive, so we'll do it again.
	        }
	        sleepTime = time - new Date().getTime();
	    }
	}

}
