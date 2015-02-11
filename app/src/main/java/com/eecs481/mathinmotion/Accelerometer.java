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

            if ((curTime - lastUpdate) > 1000) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                Log.d("xTag", Float.toString(x));
                Log.d("yTag", Float.toString(y));
                Log.d("zTag", Float.toString(z));

                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) { //shaking on the horiz axis by moving bottom and top right and left repeatedly
                    shake();//They need to shake quite forcefully....need to lower threshold WITHOUT
                    //calling function when user does not want this action to happen.
                }

                Log.d("lastx", Float.toString(last_x));
                Log.d("lasty", Float.toString(last_y));
                Log.d("lastz", Float.toString(last_z));

                //detects change in a certain axis while making sure the other axises remain near 0 or +/- 9.8
                if (Math.abs(last_x) <= 1 && x-last_x <= -3 && Math.abs(y)<= 2) right();
                if (Math.abs(last_x) <= 1 && x-last_x >= 3 && Math.abs(y)<= 2) left();//tile goes left//y  should be roughly 0
                if (Math.abs(last_y) <= 1 && y-last_y >= 3 && Math.abs(x)<= 2) down(); //tile goes down //x  should be roughly 0
                if (Math.abs(last_y) <= 1 && y-last_y <= -3 && Math.abs(x)<= 2) up(); //tile goes up //x  should be roughly 0
                //*NOTE: USER MUST RETURN PHONE BACK TO HORIZ HOLDING POSITION for this to work
                //user waits for about 2.5 seconds at rest position

                last_x = x;
                last_y = y;
                last_z = z;

                /*
                 //this will only work, if the Home activity is called from start (opened) after each move
                 //if we are just returning from search, then the math will need to be changed to deltas
                if (x <= -5 && Math.abs(y)<= 1 && last_x != 0) right(); //tile goes right //y  should be roughly 0
                if (x >= 5 && Math.abs(y)<= 1 && last_x != 0) left();//tile goes left//y  should be roughly 0
                if (y >= 5 && Math.abs(x)<= 1 && last_y != 0) down(); //tile goes down //x  should be roughly 0
                if (y <= -5 && Math.abs(x)<= 1 &&last_y != 0) up(); //tile goes up //x  should be roughly 0

                last_x = x;
                last_y = y;
                last_z = z;*/



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
