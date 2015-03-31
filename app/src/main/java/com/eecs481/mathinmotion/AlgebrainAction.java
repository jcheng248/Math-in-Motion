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
    String questionFormat = "addition"; // can be "addition", "multiplication", etc
    String answerLine;
    String question;
    private boolean answered = false;
    private boolean correct = false;
    String difficulty ="easy";

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
            int a = 0;
            int b = 0;
            if(difficulty.equals("easy"))
            {
                a = randomGenerator.nextInt(20)+1;
                b =randomGenerator.nextInt(20)+1;
            }
            else if(difficulty.equals("medium"))
            {
                a = randomGenerator.nextInt(500)+1;
                b =randomGenerator.nextInt(500)+1;
            }
            else if(difficulty.equals("hard"))
            {
                a = randomGenerator.nextInt(5000)+1;
                b =randomGenerator.nextInt(5000)+1;
            }
            question = Integer.toString(a) + " + " + Integer.toString(b) + " =";
            answerLine = Integer.toString(a+b);
        }
        else if(questionFormat.equals("multiplication"))
        {

            int a = 0;
            int b = 0;
            if(difficulty.equals("easy"))
            {
                a = randomGenerator.nextInt(10)+1;
                b =randomGenerator.nextInt(10)+1;
            }
            else if(difficulty.equals("medium"))
            {
                a = randomGenerator.nextInt(20)+1;
                b =randomGenerator.nextInt(20)+1;
            }
            else if(difficulty.equals("hard"))
            {
                a = randomGenerator.nextInt(100)+1;
                b =randomGenerator.nextInt(100)+1;
            }
            question = Integer.toString(a) + " x " + Integer.toString(b) + " =";
            answerLine = Integer.toString(a*b);
        }
        else if(questionFormat.equals("equations"))
        {
            int a = 0;
            int b = 0;
            int c = 0;
            int d = randomGenerator.nextInt(100);
            if(difficulty.equals("easy"))
            {
                 a = randomGenerator.nextInt(10)+1;
                 b = randomGenerator.nextInt(5)+1;
                 c = randomGenerator.nextInt(10)+1;
            }
            else if(difficulty.equals("medium"))
            {
                a = randomGenerator.nextInt(20)+1;
                b = randomGenerator.nextInt(20)+1;
                c = randomGenerator.nextInt(40)+1;
            }
            else if(difficulty.equals("hard"))
            {
                a = randomGenerator.nextInt(50)+1;
                b = randomGenerator.nextInt(50)+1;
                c = randomGenerator.nextInt(100)+1;
            }

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
        if(answered)
        {
            return;
        }
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
            if (Integer.parseInt(userAnswer) == Integer.parseInt(answerLine))
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
    public void newProblem(View view)
    {
        generateProblem();
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
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("input_type_aia", "Motion")
                .equals("Motion"))
            Motion.getInstance().addListener(this, this);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_type", "Addition")
                .equals("Addition"))
        {
            boolean change = questionFormat.equals("addition");
            questionFormat = "addition";
            if(!change)
            {
                generateProblem();
            }
        }
        else if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_type", "Addition")
                .equals("Multiplication"))
        {
            boolean change = questionFormat.equals("multiplication");
            questionFormat = "multiplication";
            if(!change)
            {
                generateProblem();
            }
        }
        else
        {
            boolean change = questionFormat.equals("equations");

            questionFormat = "equations";
            if(!change)
            {
                generateProblem();
            }
        }
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_level", "Easy")
                .equals("Easy"))
        {
            boolean change = difficulty.equals("easy");
            difficulty = "easy";
            if(!change)
            {
                generateProblem();
            }
        }
        else if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_level", "Easy")
                .equals("Medium"))
        {
            boolean change = difficulty.equals("medium");
            difficulty = "medium";
            if(!change)
            {
                generateProblem();
            }
        }
        else
        {
            boolean change = difficulty.equals("hard");

            difficulty = "hard";
            if(!change)
            {
                generateProblem();
            }
        }
    }

    protected void onPause()
    {
        super.onPause();
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("input_type_aia", "Motion")
                .equals("Motion"))
            Motion.getInstance().removeListener(this);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_type", "Addition")
                .equals("Addition"))
        {
            boolean change = questionFormat.equals("addition");
            questionFormat = "addition";
            if(!change)
            {
                generateProblem();
            }
        }
        else if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_type", "Addition")
                .equals("Multiplication"))
        {
            boolean change = questionFormat.equals("multiplication");
            questionFormat = "multiplication";
            if(!change)
            {
                generateProblem();
            }
        }
        else
        {
            boolean change = questionFormat.equals("equations");

            questionFormat = "equations";
            if(!change)
            {
                generateProblem();
            }
        }

        if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_level", "Easy")
                .equals("Easy"))
        {
            boolean change = difficulty.equals("easy");
            difficulty = "easy";
            if(!change)
            {
                generateProblem();
            }
        }
        else if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_level", "Easy")
                .equals("Medium"))
        {
            boolean change = difficulty.equals("medium");
            difficulty = "medium";
            if(!change)
            {
                generateProblem();
            }
        }
        else
        {
            boolean change = difficulty.equals("hard");

            difficulty = "hard";
            if(!change)
            {
                generateProblem();
            }
        }
    }
}
