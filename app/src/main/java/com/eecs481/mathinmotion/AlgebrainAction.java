package com.eecs481.mathinmotion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ImageView;




public class AlgebrainAction extends ActionBarActivity {
    LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_algebrain_action);

        mLinearLayout = new LinearLayout(this);
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.test);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000, 1000);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(layoutParams);
        // Add the ImageView to the layout and set the layout as the content view
        mLinearLayout.addView(i);
        setContentView(mLinearLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_algebrain_action, menu);
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
}
