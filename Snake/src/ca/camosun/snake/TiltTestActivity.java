package ca.camosun.snake;

import java.util.Timer;
import java.util.TimerTask;
import ca.camosun.snake.R;
import ca.camosun.snake.TiltInput;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TiltTestActivity extends Activity {

	private static final int REFRESH_TIME = 250; // ms
	private TiltInput sensor;
	private Timer mTimer;
    private TextView mTextView;
    private int count;
    
    
	private void updateDisplay() {
		String tiltDirection = sensor.getTilt();
		count++;
		mTextView.setText(tiltDirection+" "+count);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tilt_test);
		mTextView = (TextView) findViewById(R.id.tvRaw);
		sensor = new TiltInput();
		
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
				     public void run() {				
				    	 updateDisplay();
				    }
				});
				
			}
		}, 0, REFRESH_TIME);
        mTextView.setText("X");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tilt_test, menu);
		return true;
	}

	public void doneClicked(View view) {
		this.finish();
	}

}
