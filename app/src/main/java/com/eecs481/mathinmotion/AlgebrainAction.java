package com.eecs481.mathinmotion;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class AlgebrainAction  extends ActionBarActivity implements MotionListener {
    LinearLayout mLinearLayout;
    String answer = "";
    String questionFormat = "equations"; // can be "addition", "multiplication", etc
    String answerLine;
    String question;
    private boolean answered = false;
    private boolean correct = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebrain_action);
        generateProblem();
        setupToolbars();

        PreferenceManager.setDefaultValues(this, R.xml.aia_settings, false);
    }
    public void home_launch(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }
    public void settings_launch(View view)
    {
        Intent intent = new Intent(this, AIA_Settings.class);
        startActivity(intent);
    }

    private void setupToolbars()
    {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.setDisplayUseLogoEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);

        View custom = LayoutInflater.from(this).inflate(R.layout.actionbar, null);
        TextView title = (TextView)custom.findViewById(R.id.actionbar_title);
        title.setText(R.string.title_activity_algebrain_action);
        actionbar.setCustomView(custom);
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
            question = Integer.toString(a) + " + " + Integer.toString(b) + " =";
            answerLine = Integer.toString(a+b);
        }
        else if(questionFormat.equals("multiplication"))
        {

            int a = randomGenerator.nextInt(30);
            int b = randomGenerator.nextInt(30);
            question = Integer.toString(a) + " x " + Integer.toString(b) + " =";
            answerLine = Integer.toString(a*b);
        }
        else if(questionFormat.equals("equations"))
        {
            int a = randomGenerator.nextInt(100);
            int b = randomGenerator.nextInt(20);
            int c = randomGenerator.nextInt(100);
            int d  = randomGenerator.nextInt(10);
            if (d % 2 == 1 )
            {
                question = "Find x: "+ Integer.toString(b) + "x + " + Integer.toString(c) + " = " + Integer.toString(a*b +c);
            }
            else
            {
                question = "Find x: "+ Integer.toString(b) + "x - " + Integer.toString(c) + " = " + Integer.toString(a*b -c);

            }
            answerLine = Integer.toString(a);
        }
        TextView current = (TextView) findViewById(R.id.generated_question);
        current.setText(question);
        current = (TextView) findViewById(R.id.aia_answer);
        current.setText("");
        answer = "";
    }

    public void append(String digit)
    {
        answer = answer + digit;
        TextView current = (TextView) findViewById(R.id.aia_answer);
        current.setText(answer);
    }

    public void submit(View view)
    {
        if (!answered)
        {
            TextView current = (TextView) findViewById(R.id.aia_answer);
            String userAnswer = current.getText().toString();

            TextView submit_view = (TextView) findViewById(R.id.aia_submit);
            RelativeLayout submit_area = (RelativeLayout)findViewById(R.id.aia_submit_area);
            if (userAnswer.equals(answerLine))
            {
                submit_view.setText(R.string.correct_submission);
                submit_area.setBackgroundResource(R.color.green);
                correct = true;
            }
            else
            {
                submit_view.setText(R.string.incorrect_submission);
                submit_area.setBackgroundResource(R.color.red);
                correct = false;
            }
            answered = true;
        }
        else
        {
            TextView submit_view = (TextView) findViewById(R.id.aia_submit);
            submit_view.setText(R.string.submit_answer);
            RelativeLayout submit_area = (RelativeLayout)findViewById(R.id.aia_submit_area);
            submit_area.setBackgroundResource(R.color.orange);

            if (correct)
            {
                generateProblem();
            }
            else
            {
                TextView answer_view = (TextView) findViewById(R.id.aia_answer);
                answer_view.setText("");
                answer = "";
            }

            correct = false;
            answered = false;
        }
    }

    public void bksp (View view)
    {
        if(answer.length() >= 1)
        {
            answer = answer.substring(0, answer.length() - 1);
        }
        TextView current = (TextView) findViewById(R.id.aia_answer);
        current.setText(answer);
    }
    protected void onResume()
    {
        super.onResume();
        Motion.getInstance().addListener(this, this);
    }

    protected void onPause()
    {
        super.onPause();
        Motion.getInstance().removeListener(this);
    }
}
