package ca.camosun.snake;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.View;

public class SnakeBoard extends View {

	private Snake snake;
	private List<Fruit> fruits;
	private int score;
	
	public SnakeBoard(Context context) {
		super(context);
		setFocusable(true);
        
		snake = new Snake();
		fruits = new ArrayList<Fruit>();
		score = 0;
		
		this.setBackgroundColor(Color.GREEN);

		
		
	}
	
	// TODO: Draw all graphics in here such as the board tiles
	@Override
    public void onDraw(Canvas canvas) {
		
		

    }


	
	// TODO: Method to detect a collision between the snake and the edge
	

}
