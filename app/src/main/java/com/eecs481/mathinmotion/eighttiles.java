package com.eecs481.mathinmotion;

import android.hardware.SensorEventListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;

import java.io.Console;
import java.util.Random;


public class eighttiles extends ActionBarActivity implements AccelerometerListener {
    static String[][] board = new String [3][3];
    boolean done = false;
    int spacerow = 2;
    int spacecolumn = 2;
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

        setContentView(R.layout.activity_eighttiles);
        reset();

    }

    protected void onResume()
    {
        super.onResume();
        Accelerometer.getInstance().addListener(this, this);
    }

    protected void onPause()
    {
        super.onPause();
        Accelerometer.getInstance().removeListener(this);
    }

    public void nextStep()
    {
//        TextView current = (TextView) findViewById(R.id.win);
//        current.setText("you!!");
//        Log.e("werwer","werwerwer");
//
//        int moveThree = 0;
//        int move47p1 = 0;
//        int move47p2 =0;
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
        boolean finish = true;
        for (int i = 0; i <3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                TextView current = (TextView) findViewById(R.id.board).findViewWithTag(Integer.toString(j+3*i +1 ));
                current.setText(board[i][j]);
                if(!(board[i][j].equals( Integer.toString(i*3+j+1)) || (board[i][j].equals(""))))
//                current.setText(Integer.toString(i));
                    finish = false;
                if (board[i][j].equals(""))
                    current.setBackgroundColor( -16776961);
                else
                    current.setBackgroundColor( -65536 );
            }

        }
        return finish;
    }

    public void renderBoard()
    {

        TextView current = (TextView) findViewById(R.id.win);
        if (checkComplete())
        {
            done = true;
            current.setText("You win!!!");
            for (int i = 1; i <10; i++) {
                current = (TextView) findViewById(R.id.board).findViewWithTag(Integer.toString(i));
                current.setBackgroundColor(-256);
            }
        }
        else
        {
            current.setText("Play 8-squares");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eighttiles, menu);
        return true;
    }
    public void reset()
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
        renderBoard();
    }
    public void swipeUp()
    {
        if (spacerow != 2)
            goUp();
        renderBoard();
    }
    public void swipeDown()
    {
        if (spacerow != 0)
            goDown();
        renderBoard();
    }
    public void swipeLeft()
    {
        if (spacecolumn != 2)
            goLeft();
        renderBoard();
    }
    public void swipeRight()
    {
        if (spacecolumn != 0)
            goRight();
        renderBoard();
    }
    public void goUp()
    {

        board[spacerow][spacecolumn] = board[spacerow +1][spacecolumn];
        board[spacerow +1][spacecolumn] ="";
        spacerow++;
        //renderBoard();
    }
    public void goDown()
    {
        board[spacerow][spacecolumn] = board[spacerow-1][spacecolumn];
        board[spacerow -1][spacecolumn] = "";
        spacerow--;
        //renderBoard();
    }
    public void goLeft()
    {

        board[spacerow][spacecolumn] = board[spacerow][spacecolumn+1];
        board[spacerow][spacecolumn+1] = "";
        spacecolumn++;
        //renderBoard();
    }
    public void goRight()
    {
        board[spacerow][spacecolumn] = board[spacerow][spacecolumn - 1];
        board[spacerow][spacecolumn-1] = "";
        spacecolumn--;
        //renderBoard();
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
