package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
  private List<SnakeSegment> segments;
  private int head;
  private int tail;
  
public Snake() {
  segments = new ArrayList<SnakeSegment>();
  head = 0;
  tail = 0;
}
  
public void feedFruit() {
    // TODO: The snake ate a fruit.  Grow it.	
  	
}

public boolean collidedSelf() {
    // TODO: Compare the head segment with all others.  
    //       If there is a match, the snake has collided with itself and the game is over; return true

  
	
  return false;	
}

public void defaultSnake(int startX, int startY) {
    // TODO: Clear the snake segments, create a new snake 
	// with one segment located at startX,startY on the board
	
	
}

public List<SnakeSegment> getSegments() {
	return segments;
}

public void setSegments(List<SnakeSegment> segments) {
	this.segments = segments;
}

public int getHead() {
	return head;
}

public void setHead(int head) {
	this.head = head;
}

public int getTail() {
	return tail;
}

public void setTail(int tail) {
	this.tail = tail;
}
  
}
