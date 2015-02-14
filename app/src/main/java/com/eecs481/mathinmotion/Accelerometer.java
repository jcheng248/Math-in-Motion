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
    private float velocity_x = 0;
    private float avg_vel_x = 0;
    private float velocity_y= 0;
    private float avg_vel_y = 0;
    private float velocity_z = 0;
    private float avg_vel_z = 0;
    private float timer = 0;
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
            Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
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
    boolean waiting = false;
    public void onSensorChanged(SensorEvent event) { //detects change and acts accordingly
         long curTime = System.currentTimeMillis();
         float x = event.values[0];
         float y = event.values[1];
         float z = event.values[2];
         if(curTime - lastUpdate > 200)
         {
             waiting = true;
         }
        if(curTime - lastUpdate > 2000)
        {
            last_x = 0;
            last_y = 0;
            last_z = 0;
        }
         if(waiting && (Math.abs(x) >= 2.5 || Math.abs(y) >= 2.5 || Math.abs(z) >= 2.5)) {
            waiting = false;
            if(last_x != 0 && Math.abs(last_x) > Math.abs(last_y) &&Math.abs(last_x)>Math.abs(last_z))
            {
                if((last_x*x )< 0 && last_x >0 )
                {
                    right();
                    last_x = 0;
                    last_y = 0;
                    last_z = 0;
                }
                else if((last_x*x )< 0 && last_x <0 )
                {
                    left();
                    last_x = 0;
                    last_y = 0;
                    last_z = 0;
                }

            }
             if(last_y != 0 && Math.abs(last_y) > Math.abs(last_x) && Math.abs(last_y)>Math.abs(last_z)) {
                if((last_y * y) <0 && last_y >0) {
                    up();
                    last_y = 0;
                    last_x = 0;
                    last_z = 0;
                }
                else if((last_y*y) < 0 && last_y <0) {
                    down();
                    last_y = 0;
                    last_x = 0;
                    last_z = 0;
                }
            }
             if(last_z != 0 && Math.abs(last_z) > Math.abs(last_y) && Math.abs(last_z)>Math.abs(last_x)) {
                 if((last_z * z) <0) {

                     shake();
                     last_y = 0;
                     last_x = 0;
                     last_z = 0;
                 }

             }
            lastUpdate = curTime;
            last_x = x;
            last_y = y;
            last_z = z;
         }


    }
    public float max3(float a, float b, float c)
    {
        return Math.max(Math.max(a,b),c);
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
