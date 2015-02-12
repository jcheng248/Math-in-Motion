package com.eecs481.mathinmotion;

import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.ArrayList;

public class Accelerometer implements SensorEventListener
{
    private static Accelerometer instance = null;
    private SensorManager mSensorManager;
    private ArrayList<AccelerometerListener> listeners;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private String move = "none";
    private static final int SHAKE_THRESHOLD = 100;

    private Accelerometer(Context context)
    {
        listeners = new ArrayList<AccelerometerListener>();
    }

    public static Accelerometer getInstance(Context context)
    {
        if (instance == null)
            instance = new Accelerometer(context);
        return instance;
    }

    public void addListener(Context context, AccelerometerListener listener)
    {
        if (listeners.size() == 0)
        {
            mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
            Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        listeners.add(listener);
    }

    public void removeListener(AccelerometerListener listener)
    {
        listeners.remove(listener);
        if (listeners.size() == 0)
            mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) { //detects change and acts accordingly

        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 3500) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                /*Log.d("xTag", Float.toString(x));
                Log.d("yTag", Float.toString(y));
                Log.d("zTag", Float.toString(z));


                Log.d("lastx", Float.toString(last_x));
                Log.d("lasty", Float.toString(last_y));
                Log.d("lastz", Float.toString(last_z)); */

                if(move == "none"){
                    if(x - last_x >= 3) right();
                    if(x - last_x <= 3) left();
                }

                last_x = x;
                last_y = y;
                last_z = z;

                lastUpdate = System.currentTimeMillis();
            }
        }
    }

    public void left() {
        Log.d("direction", "left");
        //Intent intent = new Intent(this, AlgebrainAction.class);
        //startActivity(intent);
    }
    public void right() {Log.d("direction", "right");
        //Intent intent = new Intent(this, AlgebrainAction.class);
        // startActivity(intent);
    }
    public void down() {Log.d("direction", "down");
        //Intent intent = new Intent(this, AlgebrainAction.class);
        //startActivity(intent);
    }
    public void up() {Log.d("direction", "up");
        //Intent intent = new Intent(this, AlgebrainAction.class);
        // startActivity(intent);
    }
    public void shake() {
        Log.d("shake", "shake");
        //Intent intent = new Intent(this, AlgebrainAction.class);
        //startActivity(intent);
    }
}
