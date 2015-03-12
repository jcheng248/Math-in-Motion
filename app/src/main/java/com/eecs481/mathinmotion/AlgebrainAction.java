package com.eecs481.mathinmotion;

import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class AlgebrainAction  extends ActionBarActivity implements MotionListener {
    LinearLayout mLinearLayout;
    String answer = "";
    String questionFormat = "equations"; // can be "addition", "multiplication", etc
    String answerLine;
    String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_algebrain_action);

        mLinearLayout = new LinearLayout(this);
        ImageView i = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000, 1000);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(layoutParams);
        // Add the ImageView to the layout and set the layout as the content view
        mLinearLayout.addView(i);
        setContentView(R.layout.activity_algebrain_action);

        generateProblem();
    }
    public void clicker(View view)
    {
        String name = (String)view.getTag();
        if (name.length() == 1)
        {
            append(name);
        }
    }
    public void generateProblem()
    {
        Random randomGenerator = new Random();
        randomGenerator.setSeed(randomGenerator.nextLong());
        if(questionFormat.equals("addition"))
        {
            int a = randomGenerator.nextInt(100);
            int b = randomGenerator.nextInt(100);
            question = Integer.toString(a) + " + " + Integer.toString(b) + "=";
            answerLine = Integer.toString(a+b);
        }
        else if(questionFormat.equals("multiplication"))
        {

            int a = randomGenerator.nextInt(30);
            int b = randomGenerator.nextInt(30);
            question = Integer.toString(a) + " x " + Integer.toString(b) + "=";
            answerLine = Integer.toString(a*b);
        }
        else if(questionFormat.equals("equations"))
        {
            int a = randomGenerator.nextInt(100);
            int b = randomGenerator.nextInt(20);
            int c = randomGenerator.nextInt(100);
            int d  = randomGenerator.nextInt(10);
            if (d%2 ==1 )
            {
                question = "Find x: "+ Integer.toString(b) + "x + "+Integer.toString(c) + " = " + Integer.toString(a*b +c);
            }
            else
            {
                question = "Find x: "+ Integer.toString(b) + "x - "+Integer.toString(c) + " = " + Integer.toString(a*b -c);

            }
            answerLine = Integer.toString(a);
        }
        TextView current = (TextView) findViewById(R.id.problem_display);
        current.setText(question);
        current = (TextView) findViewById(R.id.answer_display);
        current.setText("");
        current.setBackgroundColor(0);
        answer = "";
    }

    public void append(String digit)
    {
        answer = answer + digit;
        TextView current = (TextView) findViewById(R.id.answer_display);
        current.setText(answer);
    }

    public void submit(View view)
    {
        TextView current = (TextView) findViewById(R.id.answer_display);
        String userAnswer = current.getText().toString();
        if(userAnswer.equals(answerLine))
        {
            generateProblem();
        }
        else
        {
            current.setBackgroundColor(-65536);
        }
    }

    public void bksp (View view)
    {
        if(answer.length() >= 1)
        {
            answer = answer.substring(0, answer.length()-1);
        }
        TextView current = (TextView) findViewById(R.id.answer_display);

        current.setText(answer);
    }
    protected void onResume()
    {
        super.onResume();
        if (Motion.getInstance() != null) {
            return;
        }
        Motion.getInstance().addListener(this, this);

    }

    protected void onPause()
    {
        super.onPause();
        if (Motion.getInstance() != null) {
            return;
        }
        Motion.getInstance().addListener(this, this);
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
