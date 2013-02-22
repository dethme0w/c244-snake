package ca.camosun.snake;

import java.util.Timer;
import java.util.TimerTask;
import ca.camosun.snake.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class TiltTestActivity extends Activity implements SensorEventListener {

	private static final int REFRESH_TIME = 250; // ms	
	private Timer mTimer;
    private TextView mTextView;
    private SensorManager mSensorManager;
	private Sensor mSensor;
	private float x;
	private float y;
    
	private void updateDisplay() {
		String tiltDirection = getTilt();			
		mTextView.setText(tiltDirection);
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tilt_test);
		mTextView = (TextView) findViewById(R.id.tvRaw);
						
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
        
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
	}
	
	 @Override
	    protected void onResume() {
	      super.onResume();
	      mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
	
	public String getTilt() {
		
		if (Math.abs(x) > Math.abs(y)) {
			// tilt is horizontal
			if (x > 0) {
				return ("L");
			}
			return ("R");
		}

		// tilt is vertical
		if (y > 0) {
			return ("U");
		}
		return ("D");

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		x = event.values[0];
		y = event.values[1];		
	}

}
