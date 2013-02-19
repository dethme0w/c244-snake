package ca.camosun.snake.gui;

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

public class MainActivity extends Activity {

	private View snakeBoard;
	private Button startGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	
	/** Called when the user clicks the Send button */
	public void startGame(View view) {
	    System.out.println("Start button pressed");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}