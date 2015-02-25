package com.eecs481.mathinmotion;

import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;

public class Accelerometer implements SensorEventListener {
    private static Accelerometer instance = null;
    private SensorManager mSensorManager;
    private ArrayList<AccelerometerListener> listeners;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private float last_last_x, last_last_y, last_last_z;
    private static final int SHAKE_THRESHOLD = 100;
    boolean moved = false;
    boolean waiting = false;
    private long sleeptime = 0;

    private Accelerometer() {
        listeners = new ArrayList<AccelerometerListener>();
    }

    public static Accelerometer getInstance() {
        if (instance == null)
            instance = new Accelerometer();
        return instance;
    }

    public void addListener(Context context, AccelerometerListener listener) {
        if (listeners.size() == 0) {
            mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        listeners.add(listener);
    }

    public void removeListener(AccelerometerListener listener) {

        listeners.remove(listener);
        if (listeners.size() == 0)
            mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) { //detects change and acts accordingly
        long curTime = System.currentTimeMillis();
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

//        if(curTime - lastUpdate > 2000)
//        {
//            last_x = 0;
//            last_y = 0;
//            last_z = 0;
//            last_last_x = 0;
//            last_last_y = 0;
//            last_last_z = 0;
//        }
//        if(curTime - lastUpdate > 500)
//        {
//            if(Math.abs(x) >= 0.5 || Math.abs(y) >= 0.5 || Math.abs(z) >= 0.5)
//            {
//                if (Math.abs(last_last_x) >= 1.5 || Math.abs(last_last_y) >= 1.5 || Math.abs(last_last_z) >= 1.5)
//                {
//                    if(last_last_x != 0 && Math.abs(last_last_x) > Math.abs(last_last_y)
//                            && Math.abs(last_last_x) > Math.abs(last_last_z))
//                    {
//                        if(last_x * last_last_x <0 && last_x *x < 0 && last_last_x >0)
//                        {
//                            right();
//                            last_x = 0;
//                            last_y = 0;
//                            last_z = 0;
//                            last_last_x = 0;
//                            last_last_y = 0;
//                            last_last_z = 0;
//                        }
//                        else if(last_x * last_last_x <0 && last_x *x < 0 && last_last_x <0)
//                        {
//                            left();
//                            last_x = 0;
//                            last_y = 0;
//                            last_z = 0;
//                            last_last_x = 0;
//                            last_last_y = 0;
//                            last_last_z = 0;
//                        }
//                        long time = System.currentTimeMillis();
//                        while(System.currentTimeMillis() - time >100)
//                        {
//
//                        }
//                    }
//                    else if(last_last_y != 0 && Math.abs(last_last_y) > Math.abs(last_last_x)
//                            && Math.abs(last_last_y) > Math.abs(last_last_z))
//                    {
//                        if(last_y * last_last_y <0 && last_y *y < 0 && last_last_y >0)
//                        {
//                            up();
//                            last_x = 0;
//                            last_y = 0;
//                            last_z = 0;
//                            last_last_x = 0;
//                            last_last_y = 0;
//                            last_last_z = 0;
//                        }
//                        else if(last_y * last_last_y <0 && last_y *y < 0 && last_last_y <0)
//                        {
//                            down();
//                            last_x = 0;
//                            last_y = 0;
//                            last_z = 0;
//                            last_last_x = 0;
//                            last_last_y = 0;
//                            last_last_z = 0;
//                        }
//                        long time = System.currentTimeMillis();
//                        while(System.currentTimeMillis() - time >100)
//                        {
//
//                        }
//                    }
//                    else if(last_last_z != 0 && Math.abs(last_last_z) > Math.abs(last_last_x)
//                            && Math.abs(last_last_z) > Math.abs(last_last_y))
//                    {
//                        if(last_z * last_last_z <0 && last_z *z < 0 )
//                        {
//                            shake();
//                            last_x = 0;
//                            last_y = 0;
//                            last_z = 0;
//                            last_last_x = 0;
//                            last_last_y = 0;
//                            last_last_z = 0;
//                        }
//                        long time = System.currentTimeMillis();
//                        while(System.currentTimeMillis() - time >100)
//                        {
//
//                        }
//                    }
//
//                }
//                last_last_x = last_x;
//                last_last_y = last_y;
//                last_last_z = last_z;
//                last_x = x;
//                last_y = y;
//                last_z = z;
//                lastUpdate = curTime;
//            }
//
//
//        }

        //Original
        if (curTime - lastUpdate > 200) //wait 200 ms to get get opposite direction accel data
        {
            waiting = true;
        }
        if (curTime - lastUpdate > 1000) //wait two seconds to refresh
        {
            last_x = 0;
            last_y = 0;
            last_z = 0;
        }
        if (curTime - sleeptime < 780) {
            return;
        } else {
            sleeptime = 0;
        }

        if (waiting && (Math.abs(x) >= 1.2 || Math.abs(y) >= 1 || Math.abs(z) >= 6)
                && (Math.abs(last_x) >= 3 || Math.abs(last_y) >= 3 || Math.abs(last_z) >= 3)) {
            Log.d("lastx",Float.toString(last_x));
            Log.d("lasty",Float.toString(last_y));
            Log.d("lastz",Float.toString(last_z));
            Log.d("x",Float.toString(x));
            Log.d("y",Float.toString(y));
            Log.d("z",Float.toString(z));
            waiting = false;
            if (last_x != 0 && Math.abs(last_x) > Math.abs(last_y) && (Math.abs(last_x) > Math.abs(last_z)-4)) {
                if ((last_x >x) && last_x > 1) {
                    Log.d("going right","yes");
                    right();
                    last_x = 0;
                    last_y = 0;
                    last_z = 0;
                    sleeptime = System.currentTimeMillis();
                    long time = sleeptime;
//                    while(System.currentTimeMillis() - time <800)
//                    {
//
//                    }

                } else if ((last_x <x) && last_x < -1) {
                    Log.d("going left","yes");
                    left();
                    last_x = 0;
                    last_y = 0;
                    last_z = 0;
                    sleeptime = System.currentTimeMillis();
                    long time = sleeptime;
//                    while(System.currentTimeMillis() - time <800)
//                    {
//
//                    }

                }
            } else if (last_y != 0 && Math.abs(last_y) > Math.abs(last_x) && Math.abs(last_y) > Math.abs(last_z)-3) {

                if ((last_y   >y)) {
                    Log.d("going up","yes");
                    up();
                    last_y = 0;
                    last_x = 0;
                    last_z = 0;
                    sleeptime = System.currentTimeMillis();
                    long time = sleeptime;
//                    while(System.currentTimeMillis() - time <800)
//                    {
//
//                    }
                }
                 else if (last_y < y) {
                    Log.d("going down","yes");
                    down();
                    last_y = 0;
                    last_x = 0;
                    last_z = 0;
                    sleeptime = System.currentTimeMillis();
                    long time = sleeptime;
//                    while(System.currentTimeMillis() - time <800)
//                    {
//
//                    }
                }
            }
        } else if (last_z != 0 && Math.abs(last_z) > Math.abs(last_y)+5 && Math.abs(last_z) > Math.abs(last_x)+5) {
            if ((last_z * z) < 0 ) {
                shake();
                last_y = 0;
                last_x = 0;
                last_z = 0;
                sleeptime = System.currentTimeMillis();
                long time = sleeptime;

//                while(System.currentTimeMillis() - time <800)
//                {
//
//                }
            }
        }
        lastUpdate = curTime;
        last_x = x;
        last_y = y;
        last_z = z;
    }


    public void left()
    {
        for (AccelerometerListener listener : listeners)
            listener.swipeLeft();
    }
    public void right()
    {
        for (AccelerometerListener listener : listeners)
            listener.swipeRight();
    }
    public void down()
    {
        for (AccelerometerListener listener : listeners)
            listener.swipeDown();
    }
    public void up()
    {
        for (AccelerometerListener listener : listeners)
            listener.swipeUp();
    }
    public void shake()
    {
        for (AccelerometerListener listener : listeners)
            listener.nextStep();
    }
}
