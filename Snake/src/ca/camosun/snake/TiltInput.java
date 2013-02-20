package ca.camosun.snake;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class TiltInput extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;

	private float x;
	private float y;

	public TiltInput() {
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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

}
