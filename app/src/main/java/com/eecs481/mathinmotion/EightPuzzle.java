package com.eecs481.mathinmotion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.Stack;

public class EightPuzzle extends ActionBarActivity implements AccelerometerListener {
    boolean magicSquare = false;
    static String[][] board = new String [3][3];
    boolean done = false;
    int spacerow = 2;
    int spacecolumn = 2;
    Stack<String> last_move = new Stack<String>();
    long lastTime = 0;
    long timeElapsed = 0;
    boolean winnerWinnerChickenDinner = false;
    GestureDetectorCompat detector;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable()
    {
        public void run()
        {
            updateTime();
            timerHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int counter = 1;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                board[i][j] = Integer.toString(counter++);
            }
        }


        board[2][2]= "";

        setContentView(R.layout.activity_eightpuzzle);
        setupToolbars();
        reset(null);

        PreferenceManager.setDefaultValues(this, R.xml.eight_puzzle_settings, false);
    }

    protected void onResume()
    {
        super.onResume();
        lastTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 500);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("input_type", "Motion")
                .equals("Motion"))
            Accelerometer.getInstance().addListener(this, this);
        else
            detector = new GestureDetectorCompat(this, new Gesture(this));
    }

    protected void onPause()
    {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("input_type", "Motion")
                .equals("Motion"))
            Accelerometer.getInstance().removeListener(this);
        else
            detector = null;
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
        title.setText(R.string.title_activity_eightpuzzle);
        actionbar.setCustomView(custom);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if (detector != null)
            detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void nextStep()
    {
        int b1 = spacerow;
        int b2 = spacecolumn;
        if(checkComplete())
        {
            renderBoard();
            return;
        }

        if((board[0][0].equals("1")) && (board[0][1].equals("2")) && (board[0][2].equals("3")) && (board[1][0].equals("4")) && (board[2][0].equals("7")) )
        {
            if(b1 == 1 && b2 == 1)
            {
                swipeLeft();
            }
            else if(b1 == 2 && b2 == 1)
            {
                swipeDown();
            }
            else if(b1 == 1 && b2 == 2)
            {
                swipeUp();
            }
            else
            {
                swipeRight();
            }
        }

        else if((board[0][0].equals("1")) && (board[0][1].equals("2")) && (board[0][2].equals("3")) && !((board[1][0].equals("4")) && (board[2][0].equals("7")) ))
        {
            if(board[1][1].equals("") && board[1][2].equals("4") && board[2][2].equals("7"))
            {
                swipeLeft();
            }
            else if(board[1][1].equals("4") && board[1][2].equals("") && board[2][2].equals("7"))
            {
                swipeUp();
            }
            else if(board[1][1].equals("4") && board[1][2].equals("7") && board[2][2].equals(""))
            {
                swipeRight();
            }
            else if(board[1][1].equals("4") && board[1][2].equals("7") && board[2][1].equals(""))
            {
                swipeDown();
            }
            else if(board[1][1].equals("") && board[1][2].equals("7") && board[2][1].equals("4"))
            {
                swipeRight();
            }
            else if(board[1][0].equals("") && board[1][2].equals("7") && board[2][1].equals("4"))
            {
                swipeUp();
            }
            else if(board[2][0].equals("") && board[1][2].equals("7") && board[2][1].equals("4"))
            {
                swipeLeft();
            }
            else if(board[2][0].equals("4") && board[1][2].equals("7") && board[2][1].equals(""))
            {
                swipeDown();
            }
            else if(board[2][0].equals("4") && board[1][2].equals("7") && board[1][1].equals(""))
            {
                swipeLeft();
            }
            else if(board[2][0].equals("4") && board[1][2].equals("") && board[1][1].equals("7"))
            {
                swipeUp();
            }
            else if(board[2][0].equals("4") && board[2][2].equals("") && board[1][1].equals("7"))
            {
                swipeRight();
            }
            else if(board[2][0].equals("4") && board[2][1].equals("") && board[1][1].equals("7"))
            {
                swipeDown();
            }
            else if(board[2][0].equals("4") && board[2][1].equals("7") && board[1][1].equals(""))
            {
                swipeRight();
            }
            else if(board[2][0].equals("4") && board[2][1].equals("7") && board[1][0].equals(""))
            {
                swipeUp();
            }
            else if(board[2][0].equals("") && board[2][1].equals("7") && board[1][0].equals("4"))
            {
                swipeLeft();
            }
            else if(board[1][0].equals("") && board[1][1].equals("7") && board[1][2].equals("4"))
            {
                swipeLeft();
            }
            else if(board[1][0].equals("7") && board[1][1].equals("") && board[1][2].equals("4"))
            {
                swipeLeft();
            }
            else if(board[1][0].equals("7") && board[1][1].equals("4") && board[1][2].equals(""))
            {
                swipeUp();
            }
            else if(board[1][0].equals("7") && board[1][1].equals("4") && board[2][2].equals(""))
            {
                swipeRight();
            }
            else if(board[1][0].equals("7") && board[1][1].equals("4") && board[2][1].equals(""))
            {
                swipeRight();
            }
            else if(board[1][0].equals("7") && board[1][1].equals("4") && board[2][0].equals(""))
            {
                swipeDown();
            }
            else if(board[1][0].equals("") && board[1][1].equals("4") && board[2][0].equals("7"))
            {
                swipeLeft();
            }
            else if(!board[1][2].equals("4"))
            {
                if((b1 == 2) && (b2 == 2))
                {
                    swipeDown();
                }
                else if(b1 == 2)
                {
                    swipeLeft();
                }
                else if((b1 == 1) && (b2 == 0))
                {
                    swipeUp();
                }
                else
                {
                    swipeRight();
                }
            }
            else if((board[1][1]).equals("7"))
            {
                if((b1 == 2) && (b2 == 0))
                {
                    swipeDown();
                }
                else
                {
                    swipeRight();
                }
            }
            else if((b1 == 2) && (b2 == 2))
            {
                swipeRight();
            }
            else if(board[2][2].equals("7") && !(b1 == 1 && b2 == 1))
            {
                if(b1 == 2)
                {
                    swipeDown();
                }
                else
                {
                    swipeRight();
                }
            }
            else
            {
                if(b1 == 1 && b2 == 1)
                {
                    swipeUp();
                }
                else if(b1 == 1)
                {
                    swipeLeft();
                }
                else if(b2 == 1)
                {
                    swipeRight();
                }
                else
                {
                    swipeDown();
                }
            }
        }
        if((board[0][0].equals("1") &&(board[0][1].equals("2") && (board[1][2].equals("3")) && (board[1][0].equals("")))))
        {
            swipeDown();
        }
        else if((board[0][0].equals("") &&(board[0][1].equals("2") && (board[1][2].equals("3") && (board[1][0].equals("1"))))))
        {
            swipeLeft();
        }
        else if((board[0][0].equals("2") &&(board[0][1].equals("") && (board[1][2].equals("3") && (board[1][0].equals("1"))))))
        {
            swipeLeft();
        }
        else if((board[0][0].equals("2") &&(board[0][2].equals("") && (board[1][2].equals("3") && (board[1][0].equals("1"))))))
        {
            swipeUp();
        }
        else if((board[0][0].equals("2") &&(board[0][2].equals("3") && (board[1][2].equals("") && (board[1][0].equals("1"))))))
        {
            swipeRight();
        }
        else if((board[0][0].equals("2") &&(board[0][2].equals("3") && (board[1][1].equals("") && (board[1][0].equals("1"))))))
        {
            swipeDown();
        }
        else if((board[0][0].equals("2") &&(board[0][2].equals("3") && (board[0][1].equals("") && (board[1][0].equals("1"))))))
        {
            swipeRight();
        }
        else if((board[0][0].equals("") &&(board[0][2].equals("3") && (board[0][1].equals("2") && (board[1][0].equals("1"))))))
        {
            swipeUp();
        }

        else if(!board[0][0].equals("1"))
        {

            //board[0][1] = board[2][2];
            int i = 0;
            int j = 1;
            boolean found = false;
            int x;
            int y;
            for( x = 0;x<3;x++)
            {
                for( y = 0; y <3;y++)
                {
                    if(board[x][y].equals("1"))
                    {

                        i = x;
                        j = y;
                        break;
                    }
                }

            }

            if((j == 0) && (b2 != 0) )
            {

                if( b1 != 0)
                {
                   swipeDown();
                }
                else if(( b1 ==0))
                {
                    swipeRight();
                }

            }
            else if((j == 0) && (b2 == 0))
            {
                if(b1 > i)
                {
                    swipeLeft();
                }
                else
                {
                    swipeUp();
                }

            }
            else if ((j == 1) && (b2 == 2) && b1 != 0)
            {
                swipeRight();
            }
            else if (((j == 1) && (b2 == 2)))
            {
                swipeUp();
            }
            else if((j == 1))
            {
                if(b2 == 1 && b1 != 2)
                {
                    swipeUp();
                }
                else if(b2 ==1)
                {
                    swipeRight();
                }
                else if(b1 == 0 && b2 == 0)
                {
                    swipeLeft();
                }
                else
                {
                    swipeDown();
                }
            }
            else if( j == 2)
            {
                if(b2 == 2 && b1 < i)
                {
                    swipeUp();
                }
                else if( b2 == 2)
                {
                    swipeRight();
                }
                else if(b1 == 0)
                {
                    swipeLeft();
                }
                else
                {
                    swipeDown();
                }
            }
        }
        else if(!board[0][1].equals("2"))
        {
            int i = 0;
            int j = 0;
            found:
                for(i = 0;i<3;i++)
                {
                    for(j = 0; j <3;j++)
                    {
                        if(board[i][j].equals("2"))
                        {
                            break found;
                        }
                    }
                }
            if(j == 0)
            {
                if(b2 == 0)
                {
                    if (b1 > i)
                    {
                        swipeLeft();
                    }
                    else
                    {
                        swipeUp();
                    }
                }
                else
                {
                    if (b1 == 1)
                    {
                        swipeRight();
                    }
                    else if( b1 < 1)
                    {
                        swipeUp();
                    }
                    else
                    {
                        swipeDown();
                    }
                }
            }
            else if( j == 1)
            {
                if((b2 == 1) && (b1 < i))
                {
                    swipeUp();
                }
                else if(b2 == 1)
                {
                    swipeLeft();
                }
                else if(b2 == 0)
                {
                    if(b1!= i)
                    {
                        swipeLeft();
                    }
                    else if(b1 == 1)
                    {
                        swipeUp();
                    }
                    else if(b1 == 2)
                    {
                        swipeDown();
                    }
                }
                else
                {
                    if(b1 == 0)
                    {
                        swipeRight();
                    }
                    else
                    {
                        swipeDown();
                    }
                }

            }
            else if(j == 2)
            {
                if(b2 == 0)
                {
                    swipeLeft();
                }
                else if( b2 == 1 && b1 == 0)
                {
                    swipeLeft();
                }
                else if(b2 == 1)
                {
                    swipeDown();
                }
                else if (b2 == 2 && b1 <i)
                {
                    swipeUp();
                }
                else
                {
                    swipeRight();
                }
            }
        }
        else if((!board[0][2].equals("3")) && (!board[1][2].equals("3")))
        {
            if((b1 == 0)&&(b2 == 2))
            {
                swipeUp();
            }
            if((b1 == 2) && (b2 == 0))
            {
                swipeDown();
            }
            else if(b1 == 2)
            {
                swipeRight();
            }
            else if((b1 == 1) && (b2 == 2))
            {
                swipeUp();
            }
            else
            {
                swipeLeft();
            }
        }
        else if(!((b1 == 1)&&(b2 == 0)) && (board[1][2].equals("3")))
        {
            if((b1 == 0)&&(b2 == 2))
            {
                swipeUp();
            }
            else if(b2 != 0)
            {
                swipeRight();
            }
            else
            {
                swipeDown();
            }
        }

    }
    public boolean checkComplete()
    {
        TextView moveCounter = (TextView) findViewById(R.id.eight_puzzle_moves_value);
        moveCounter.setText(Integer.toString(last_move.size()));
        boolean finish = true;
        if(!magicSquare)
        {
            for (int i = 0; i <3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    TextView current = (TextView) findViewById(R.id.board).findViewWithTag(Integer.toString(j+3*i +1 ));
                    current.setText(board[i][j]);
                    if(!(board[i][j].equals( Integer.toString(i*3+j+1)) || (board[i][j].equals(""))))
                        finish = false;
                    if (board[i][j].equals(""))
                        current.setBackgroundResource(Color.TRANSPARENT);
                    else
                        current.setBackgroundResource(R.drawable.tile);
                }

            }
        }
        else
        {
            int sum = Integer.parseInt(board[0][0]) + Integer.parseInt(board[0][1])+Integer.parseInt(board[0][2]);
            if(sum != Integer.parseInt(board[1][0]) + Integer.parseInt(board[1][1])+Integer.parseInt(board[1][2])){
                return false;
            }
            if(sum != Integer.parseInt(board[2][0]) + Integer.parseInt(board[2][1])+Integer.parseInt(board[2][2])) {
                return false;
            }
            if(sum != Integer.parseInt(board[0][0]) + Integer.parseInt(board[1][0])+Integer.parseInt(board[2][0])){
                return false;
            }
            if(sum != Integer.parseInt(board[0][1]) + Integer.parseInt(board[1][1])+Integer.parseInt(board[2][1])){
                return false;
            }
            if(sum != Integer.parseInt(board[0][2]) + Integer.parseInt(board[1][2])+Integer.parseInt(board[2][2])){
                return false;
            }
            if(sum != Integer.parseInt(board[0][0]) + Integer.parseInt(board[1][1])+Integer.parseInt(board[2][2])){
                return false;
            }
            if(sum != Integer.parseInt(board[2][0]) + Integer.parseInt(board[1][1])+Integer.parseInt(board[0][2])){
                return false;
            }
            finish = true;


        }

        return finish;
    }

    public void renderBoard()
    {

        TextView current = (TextView) findViewById(R.id.eight_puzzle_win);
        if (checkComplete())
        {
            done = true;
            timeElapsed += System.currentTimeMillis() - lastTime;
            long seconds = timeElapsed / 1000;
            long minutes = seconds / 60;
            seconds %= 60;
            current.setText("Finished in " + String.format("%d:%02d", minutes, seconds) + " and "
                + last_move.size() + " moves!!!");
        }


    }

    public void reset(View view)
    {
        Random randomGenerator = new Random();
        for(int i = 0; i < 400; i++)
        {
            if(randomGenerator.nextInt(4) == 3)
            {
                if (spacerow != 2)
                    goUp();
            }
            else if(randomGenerator.nextInt(4) == 2)
            {
                if (spacerow != 0)
                    goDown();
            }
            else if(randomGenerator.nextInt(4) == 1)
            {
                if (spacecolumn != 2)
                    goLeft();
            }
            else
            {
                if (spacecolumn != 0)
                    goRight();
            }
        }
        while(!last_move.empty())
        {
            last_move.pop();
        }
        done = false;
        TextView current = (TextView) findViewById(R.id.eight_puzzle_win);
        current.setText("");
        renderBoard();
        lastTime = System.currentTimeMillis();
        timeElapsed = 0;
        updateTime();
    }
    public void swipeUp()
    {
        if (done) return;
        if (spacerow != 2){
            String direction = "up";
            last_move.push(direction);
            goUp();
        }
        renderBoard();
    }
    public void swipeDown()
    {
        if (done) return;
        if (spacerow != 0){
            String direction = "down";
            last_move.push(direction);
            goDown();
        }
        renderBoard();
    }
    public void swipeLeft()
    {
        if (done) return;
        if (spacecolumn != 2){
            String direction = "left";
            last_move.push(direction);
            goLeft();
        }
        renderBoard();
    }
    public void swipeRight()
    {
        if (done) return;
        if (spacecolumn != 0){
            String direction = "right";
            last_move.push(direction);
            goRight();
        }
        renderBoard();
    }
    public void goUp()
    {
        board[spacerow][spacecolumn] = board[spacerow +1][spacecolumn];
        board[spacerow +1][spacecolumn] = "";
        spacerow++;
    }
    public void goDown()
    {
        board[spacerow][spacecolumn] = board[spacerow-1][spacecolumn];
        board[spacerow -1][spacecolumn] = "";
        spacerow--;
    }
    public void goLeft()
    {

        board[spacerow][spacecolumn] = board[spacerow][spacecolumn+1];
        board[spacerow][spacecolumn+1] = "";
        spacecolumn++;
    }
    public void goRight()
    {
        board[spacerow][spacecolumn] = board[spacerow][spacecolumn - 1];
        board[spacerow][spacecolumn-1] = "";
        spacecolumn--;
    }

    public void home_launch(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void settings_launch(View view)
    {
        Intent intent = new Intent(this, EightPuzzleSettingsActivity.class);
        startActivity(intent);
    }

    public void undo(View view){
        if(last_move.empty()) return;
        String last = last_move.peek();
        last_move.pop();
        if(last.equals("left")) swipeRight();
        else if(last.equals("right")) swipeLeft();
        else if(last.equals("up")) swipeDown();
        else swipeUp();
        last_move.pop();
        renderBoard();
    }

    public void updateTime()
    {
        if(done)
            return;
        long currentTime = System.currentTimeMillis();
        timeElapsed += currentTime - lastTime;
        lastTime = currentTime;
        long seconds = timeElapsed / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        TextView timeIndicator = (TextView) findViewById(R.id.eight_puzzle_time_value);
        timeIndicator.setText(String.format("%d:%02d", minutes, seconds));
    }
}
