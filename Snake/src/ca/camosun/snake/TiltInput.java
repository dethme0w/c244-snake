package ca.camosun.snake;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class TiltInput extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float x;
	private float y;

	public TiltInput() {
      x = 0.0F;
      y = 0.0F;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("TiltInput.onCreate called");
		super.onCreate(savedInstanceState);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		System.out.println("TiltInput.onCreate survived");
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
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

	public String getTilt() {
		String d = "";
		d += x + " / " + y + " ";
		if (Math.abs(x) > Math.abs(y)) {
			// tilt is horizontal
			if (x > 0) {
				return (d + "L");
			}
			return (d + "R");
		}

		// tilt is vertical
		if (y > 0) {
			return (d + "U");
		}
		return (d + "D");

	}

}
