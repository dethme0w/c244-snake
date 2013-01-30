package ca.camosun.snake;

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
	
	/* 
	 private OnClickListener runGame = new OnClickListener() {
			public void onClick(View v) {
				
			//TODO: Code for the Start Game button needs to be linked to the SnakeBoard View
			// instead of starting when you open the app
	                     	
	        }
	    };
	    
	    */
	
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}