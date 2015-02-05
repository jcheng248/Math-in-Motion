package com.eecs481.mathinmotion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.util.Log;

public class Home extends ActionBarActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) { //detects change and acts accordingly
        //this will only work, if the Home activity is called from start (opened) after each move
        //if we are just returning from search, then the math will need to be changed to deltas
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

                if (speed > SHAKE_THRESHOLD) { //shaking
                    shake();
                }

                Log.d("lastx", Float.toString(last_x));
                Log.d("lasty", Float.toString(last_y));
                Log.d("lastz", Float.toString(last_z));

                if (x <= -5 && Math.abs(y)<= 3 && last_x != 0) right(); //tile goes right //y  should be roughly 0
                if (x >= 5 && Math.abs(y)<= 3 && last_x != 0) left();//tile goes left//y  should be roughly 0
                if (y >= 5 && Math.abs(x)<= 3 && last_y != 0) down(); //tile goes down //x  should be roughly 0
                if (y <= -5 && Math.abs(x)<= 3 &&last_y != 0) up(); //tile goes up //x  should be roughly 0

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void eight(View view){
        Intent intent = new Intent(this, eighttiles.class);
        startActivity(intent);
    }
    public void algebra(View view){
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);

    }
    public void search() {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }
    public void left() {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }
    public void right() {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }
    public void down() {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }
    public void up() {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }
    public void shake() {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }
}
