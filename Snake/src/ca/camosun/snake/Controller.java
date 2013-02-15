package ca.camosun.snake;

import java.util.Date;



public class Controller {
	
	private SnakeBoard theBoard;
	private Snake.Direction nextDirection;
	private long gameTick = 1000;
	
	public Controller(int inScreenWidth, int inScreenHeight) {
		theBoard = new SnakeBoard(inScreenWidth, inScreenHeight);
	}
	
	public void setNextDirection(Snake.Direction inDirection) {
		nextDirection = inDirection;
	}
	
	public void gameLoop() {
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
		// TODO: Place some fruit on the board for each level
		// 		 Might be easier to have a function in SnakeBoard that places x amount of fruit randomly on the board.
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
