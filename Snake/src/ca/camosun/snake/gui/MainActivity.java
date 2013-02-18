package ca.camosun.snake.gui;

import ca.camosun.snake.ImageAdapter;
import ca.camosun.snake.R;
import ca.camosun.snake.R.layout;
import ca.camosun.snake.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

	private View snakeBoard;
	private Button startGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
	    gridview.setVerticalScrollBarEnabled(false);
	    gridview.setEnabled(false);

	}

	/*
	 * private OnClickListener runGame = new OnClickListener() { public void
	 * onClick(View v) { //TODO: Code for the Start Game button needs to be
	 * linked to the SnakeBoard View // instead of starting when you open the
	 * app
	 * 
	 * } };
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onClick(View v) {
        System.out.println("Grid clicked");
	}

}