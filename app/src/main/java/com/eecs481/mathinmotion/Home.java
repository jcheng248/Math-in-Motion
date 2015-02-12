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

public class Home extends ActionBarActivity implements AccelerometerListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    protected void onResume() {
        super.onResume();
        Accelerometer.getInstance(this).addListener(this, this);
    }

    protected void onPause() {
        super.onPause();
        Accelerometer.getInstance(this).removeListener(this);
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
}
