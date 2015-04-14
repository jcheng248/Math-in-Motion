package com.eecs481.mathinmotion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
    String answer = "";
    String questionFormat = "addition"; // can be "addition", "multiplication", etc
    String answerLine;
    String question;
    private boolean answered = false;
    private boolean correct = false;
    String difficulty ="easy";
    SharedPreferences high_score_preference;
    int consecutiveCorrect=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebrain_action);
        //loads problem
        high_score_preference = getSharedPreferences("high_score", Context.MODE_PRIVATE);
        if(high_score_preference.contains("aia" + questionFormat + difficulty + "current"))
        {
            consecutiveCorrect = high_score_preference.getInt("aia"+questionFormat + difficulty + "current",0);
        }
        else
        {
            consecutiveCorrect = 0;
        }
        generateProblem();
        //loads toolbars
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
        //handles when people click buttons to enter numbers
        String name = (String)view.getTag();

        if (name.length() == 1)//add only if it's a digit
        {
            //adds digit to answer
            append(name);
        }
    }
    public void generateProblem()
    {
        Random randomGenerator = new Random();
        randomGenerator.setSeed(randomGenerator.nextLong());

        if(questionFormat.equals("addition"))//question type
        {
            int a = 0;
            int b = 0;
            if(difficulty.equals("easy"))//size of the numbers used in calculation and answer
            {
                a = randomGenerator.nextInt(20)+1;
                b =randomGenerator.nextInt(20)+1;
            }
            else if(difficulty.equals("medium"))
            {
                a = randomGenerator.nextInt(400)+100;
                b =randomGenerator.nextInt(400)+100;
            }
            else if(difficulty.equals("hard"))
            {
                a = randomGenerator.nextInt(4500)+500;
                b =randomGenerator.nextInt(4500)+500;
            }
            //saves string to be displayed
            question = Integer.toString(a) + " + " + Integer.toString(b) + " =";
            //saves answer to check against
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
                a = randomGenerator.nextInt(15)+11;
                b =randomGenerator.nextInt(15)+11;
            }
            else if(difficulty.equals("hard"))
            {
                a = randomGenerator.nextInt(80)+21;
                b =randomGenerator.nextInt(80)+21;
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
                a = randomGenerator.nextInt(10)+11;
                b = randomGenerator.nextInt(10)+11;
                c = randomGenerator.nextInt(30)+11;
            }
            else if(difficulty.equals("hard"))
            {
                a = randomGenerator.nextInt(40)+20;
                b = randomGenerator.nextInt(40)+20;
                c = randomGenerator.nextInt(100)+1;
            }

            if (d % 2 == 1 )//2 different form of equations: one with addition, one with subtraction
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
        //displays question
        current.setText(question);
        current = (TextView) findViewById(R.id.aia_answer);
        //clears answer box
        current.setText("");
        //clears internal answer
        answer = "";
        TextView highScore = (TextView) findViewById(R.id.consecutive);
        highScore.setText("Consecutive Right Answers: "+consecutiveCorrect);

    }

    //adds stuff to answer
    public void append(String digit)
    {
        if(answered)//lock keypad in case of submission
        {
            return;
        }
        if(answer.length() > 7)
        {
            return;
        }
        if(answer.length() == 0 && digit.equals("0"))
        {
            return;
        }
        answer = answer + digit;//adds new digit to answer already
        TextView current = (TextView) findViewById(R.id.aia_answer);
        current.setText(answer);
    }
    public void editCurrentConsec()
    {
        if(high_score_preference.contains("aia" + questionFormat + difficulty + "current"))
        {
            consecutiveCorrect = high_score_preference.getInt("aia"+questionFormat + difficulty + "current",0);
        }
        else
        {
            consecutiveCorrect = 0;
        }
    }
    //submits the answer
    public void submit(View view)
    {
        Log.d("con: ", Integer.toString(consecutiveCorrect));
        if (!answered)//if not submitted
        {
            TextView current = (TextView) findViewById(R.id.aia_answer);
            String userAnswer = current.getText().toString();
            TextView submit_view = (TextView) findViewById(R.id.aia_submit);
            RelativeLayout submit_area = (RelativeLayout)findViewById(R.id.aia_submit_area);
            if (!userAnswer.equals("")&&(Integer.parseInt(userAnswer) == Integer.parseInt(answerLine)))//checks if correct
            {
                submit_view.setText(R.string.correct_submission);
                submit_area.setBackgroundResource(R.color.green);
                correct = true;
                consecutiveCorrect++;
            }
            else
            {
                submit_view.setText(R.string.incorrect_submission);
                submit_area.setBackgroundResource(R.color.red);
                correct = false;
                TextView highScore = (TextView) findViewById(R.id.consecutive);
                if(!high_score_preference.contains("aia"+questionFormat + difficulty + "record"))
                {
                    SharedPreferences.Editor edit_high = high_score_preference.edit();
                    edit_high.putInt("aia"+questionFormat + difficulty + "record", consecutiveCorrect);
                    highScore.setText(consecutiveCorrect +
                            " right in a row! \nNew record for "+difficulty+" " +questionFormat+"!");
                    edit_high.commit();
                }
                else if(high_score_preference.getInt("aia"+questionFormat + difficulty + "record",0) < consecutiveCorrect)
                {
                    SharedPreferences.Editor edit_high = high_score_preference.edit();
                    edit_high.putInt("aia"+questionFormat + difficulty + "record", consecutiveCorrect);
                    highScore.setText(consecutiveCorrect +
                            " right in a row! \nNew record for "+difficulty+" " +questionFormat+"!");
                    edit_high.commit();
                }
                else if(high_score_preference.getInt("aia"+questionFormat + difficulty + "record",0) == consecutiveCorrect)
                {
                    highScore.setText(consecutiveCorrect +
                            " right in a row! \nMatched record for "+difficulty+" " +questionFormat+"!");                }
                else
                {
                    highScore.setText(consecutiveCorrect + " right in a row! Record: "
                            + Integer.toString(high_score_preference.getInt("aia"+questionFormat + difficulty + "record",0)));
                }
            }
            answered = true;
        }
        else
        {
            TextView submit_view = (TextView) findViewById(R.id.aia_submit);
            submit_view.setText(R.string.submit_answer);
            RelativeLayout submit_area = (RelativeLayout)findViewById(R.id.aia_submit_area);
            submit_area.setBackgroundResource(R.color.orange);
            //if already submitted
            if (correct)//new problem if correct
            {

                generateProblem();
            }
            else
            {
                //resets answer line for another try
                consecutiveCorrect = 0;
                TextView answer_view = (TextView) findViewById(R.id.aia_answer);
                answer_view.setText("");
                answer = "";
                TextView highScore = (TextView) findViewById(R.id.consecutive);
                highScore.setText("Consecutive Right Answers: "+consecutiveCorrect);
            }
            correct = false;
            answered = false;
        }
    }

    //handles "reset" button; generates a new problem
    public void newProblem(View view)
    {
        if(answered)//locks if answer is submitted already
        {
            return;
        }
        generateProblem();
    }

    //backspace
    public void bksp (View view)
    {
        if(answered)//locks if answer is submitted already
        {
            return;
        }
        if(answer.length() >= 1)//make sure there's something to delete
        {
            //replaces answer with everything except for last char
            answer = answer.substring(0, answer.length() - 1);
        }
        //redisplays
        TextView current = (TextView) findViewById(R.id.aia_answer);
        current.setText(answer);
    }


    protected void onResume()
    {
        super.onResume();
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("input_type_aia", "Motion")
                .equals("Motion"))
            Motion.getInstance().addListener(this, this);

        //handles settings loading for question type
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_type", "Addition")
                .equals("Addition"))
        {
            boolean change = questionFormat.equals("addition");
            questionFormat = "addition";
            if(!change)//if there's a change, then reload the questions
            {
                editCurrentConsec();
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
                editCurrentConsec();

                generateProblem();
            }
        }
        else
        {
            boolean change = questionFormat.equals("equations");

            questionFormat = "equations";
            if(!change)
            {
                editCurrentConsec();

                generateProblem();
            }
        }

        //handles Difficulty preference
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("diff_level", "Easy")
                .equals("Easy"))
        {
            boolean change = difficulty.equals("easy");
            difficulty = "easy";
            if(!change)//if there's a change, then reload the questions
            {
                editCurrentConsec();

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
                editCurrentConsec();

                generateProblem();
            }
        }
        else
        {
            boolean change = difficulty.equals("hard");

            difficulty = "hard";
            if(!change)
            {
                editCurrentConsec();

                generateProblem();
            }
        }

    }

    protected void onPause()
    {
        //same as onResume
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

        SharedPreferences.Editor editor = high_score_preference.edit();
        editor.putInt("aia"+questionFormat + difficulty + "current", consecutiveCorrect);
        editor.commit();
    }
}
