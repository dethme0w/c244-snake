package ca.camosun.snake.gui;

import ca.camosun.snake.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		
	}
	
	/** Called when the user clicks the Send button */
	public void startGame(View view) {	    
	    Intent intent = new Intent(this, GameActivity.class);	    
	    startActivity(intent);
	}
	
	public void quit(View view) {
		MainActivity.this.finish();
	}
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}