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


public class eighttiles extends ActionBarActivity  {
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
        reset(null);

    }
    public void nextStep(View view)
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
            return;
//        if((board[0][0] == "1") && (board[0][1] == "2") && (board[0][2] == "3") && (board[1][0] == "4") && (board[2][0] == "7") )
//        {
//            if(b1 == 1 && b2 == 1)
//            {
//                swipeLeft(null);
//            }
//            else if(b1 == 2 && b2 == 1)
//            {
//                swipeDown(null);
//            }
//            else if(b1 == 1 && b2 == 2)
//            {
//                swipeUp(null);
//            }
//            else
//            {
//                swipeRight(null);
//            }
//        }
//        else if((board[0][0] == "1") && (board[0][1] == "2") && (board[0][2] == "3") && !((board[1][0] == "4") && (board[2][0] == "7") ))
//        {
//            if(board[1][1] == "" && board[1][2] =="4" && board[2][2] == "7")
//            {
//                swipeLeft(null);
//            }
//            else if(board[1][1] == "4" && board[1][2] =="" && board[2][2] == "7")
//            {
//                swipeUp(null);
//            }
//            else if(board[1][1] == "4" && board[1][2] =="7" && board[2][2] == "")
//            {
//                swipeRight(null);
//            }
//            else if(board[1][1] == "4" && board[1][2] =="7" && board[2][1] == "")
//            {
//                swipeDown(null);
//            }
//            else if(board[1][1] == "" && board[1][2] =="7" && board[2][1] == "4")
//            {
//                swipeRight(null);
//            }
//            else if(board[1][0] == "" && board[1][2] =="7" && board[2][1] == "4")
//            {
//                swipeUp(null);
//            }
//            else if(board[2][0] == "" && board[1][2] =="7" && board[2][1] == "4")
//            {
//                swipeLeft(null);
//            }
//            else if(board[2][0] == "4" && board[1][2] =="7" && board[2][1] == "")
//            {
//                swipeDown(null);
//            }
//            else if(board[2][0] == "4" && board[1][2] =="7" && board[1][1] == "")
//            {
//                swipeLeft(null);
//            }
//            else if(board[2][0] == "4" && board[1][2] =="" && board[1][1] == "7")
//            {
//                swipeUp(null);
//            }
//            else if(board[2][0] == "4" && board[2][2] =="" && board[1][1] == "7")
//            {
//                swipeRight(null);
//            }
//            else if(board[2][0] == "4" && board[2][1] =="" && board[1][1] == "7")
//            {
//                swipeDown(null);
//            }
//            else if(board[2][0] == "4" && board[2][1] =="7" && board[1][1] == "")
//            {
//                swipeRight(null);
//            }
//            else if(board[2][0] == "4" && board[2][1] =="7" && board[1][0] == "")
//            {
//                swipeUp(null);
//            }
//            else if(board[2][0] == "" && board[2][1] =="7" && board[1][0] == "4")
//            {
//                swipeLeft(null);
//            }
//            else if(board[1][0] == "" && board[1][1] == "7" && board[1][2] == "4")
//            {
//                swipeLeft(null);
//            }
//            else if(board[1][0] == "7" && board[1][1] == "" && board[1][2] == "4")
//            {
//                swipeLeft(null);
//            }
//            else if(board[1][0] == "7" && board[1][1] == "4" && board[1][2] == "")
//            {
//                swipeUp(null);
//            }
//            else if(board[1][0] == "7" && board[1][1] == "4" && board[2][0] == "")
//            {
//                swipeDown(null);
//            }
//            else if(board[1][0] == "7" && board[1][1] == "4")
//            {
//                swipeRight(null);
//            }
//            else if(board[1][0] == "" && board[1][1] == "4" && board[2][0] == "7")
//            {
//                swipeLeft(null);
//            }
//            else if(board[1][2] != "4")
//            {
//                if((b1 == 2) && (b2 == 2))
//                {
//                    swipeDown(null);
//                }
//                else if(b1 == 2)
//                {
//                    swipeLeft(null);
//                }
//                else if((b1 == 1) && (b2 == 0))
//                {
//                    swipeUp(null);
//                }
//                else
//                {
//                    swipeRight(null);
//                }
//            }
//            else if((board[1][1]) == "7")
//            {
//                if((b1 == 2) && (b2 == 0))
//                {
//                    swipeDown(null);
//                }
//                else
//                {
//                    swipeRight(null);
//                }
//            }
//            else if((b1 == 2) && (b2 == 2))
//            {
//                swipeRight(null);
//            }
//            else if(board[2][2] == "7" && !(b1 == 1 && b2 == 1))
//            {
//                if(b1 == 2)
//                {
//                    swipeDown(null);
//                }
//                else
//                {
//                    swipeRight(null);
//                }
//            }
//        }
//        else if((board[0][0] =="1") &&(board[0][1] == "2") && (board[1][2] == "3") && (board[1][0] == ""))
//        {
//            swipeDown(null);
//        }
//        else if((board[0][0] =="") &&(board[0][1] == "2") && (board[1][2] == "3") && (board[1][0] == "1"))
//        {
//            swipeLeft(null);
//        }
//        else if((board[0][0] =="2") &&(board[0][1] == "") && (board[1][2] == "3") && (board[1][0] == "1"))
//        {
//            swipeLeft(null);
//        }
//        else if((board[0][0] =="2") &&(board[0][2] == "") && (board[1][2] == "3") && (board[1][0] == "1"))
//        {
//            swipeUp(null);
//        }
//        else if((board[0][0] =="2") &&(board[0][2] == "3") && (board[1][2] == "") && (board[1][0] == "1"))
//        {
//            swipeRight(null);
//        }
//        else if((board[0][0] =="2") &&(board[0][2] == "3") && (board[1][1] == "") && (board[1][0] == "1"))
//        {
//            swipeDown(null);
//        }
//        else if((board[0][0] =="2") &&(board[0][2] == "3") && (board[0][1] == "") && (board[1][0] == "1"))
//        {
//            swipeRight(null);
//        }
//        else if((board[0][0] =="") &&(board[0][2] == "3") && (board[0][1] == "2") && (board[1][0] == "1"))
//        {
//            swipeDown(null);
//        }
//        else
        if(board[0][0] != "1")
        {
            
            board[0][1] = board[2][2];
            int i = 0;
            int j = 1;
            boolean found = false;
            int x;
            int y;
            for( x = 0;x<3;x++)
            {
                for( y = 0; y <3;y++)
                {
                    if(board[x][y] == "1")
                    {

                        i = x;
                        j = y;
                        break;
                    }
                }

            }

            if(true)
            {
                if (board[0][2] == "1")
                    swipeDown(null);
                else if ( j == 2)
                    swipeLeft(null);
                else if ( j ==0)
                    swipeRight(null);
                else
                    swipeUp(null);
            }
            else if((j == 0) && (b2 != 0) )
            {

                if( b1 != 0)
                {
                   swipeDown(findViewById(android.R.id.content));
                }
                else if(( b1 ==0))
                {
                    swipeRight(findViewById(android.R.id.content));
                }

            }
            else if((j == 0) && (b2 == 0))
            {
                if(b1 > i)
                {
                    swipeLeft(findViewById(android.R.id.content));
                }
                else
                {
                    swipeUp(findViewById(android.R.id.content));
                }

            }
            else if ((j == 1) && (b2 == 2))
            {
                swipeRight(findViewById(android.R.id.content));
            }
            else if((j == 1))
            {
                if(b1 == 1 && b2 != 2)
                {
                    swipeUp(findViewById(android.R.id.content));
                }
                else if(b1 ==1)
                {
                    swipeRight(findViewById(android.R.id.content));
                }
                else if(b1 == 0 && b2 == 0)
                {
                    swipeLeft(findViewById(android.R.id.content));
                }
                else
                {
                    swipeDown(findViewById(android.R.id.content));
                }
            }
            else if( j == 2)
            {
                if(b2 == 2 && b1 < i)
                {
                    swipeUp(findViewById(android.R.id.content));
                }
                else if( b2 == 2)
                {
                    swipeRight(findViewById(android.R.id.content));
                }
                else if(b1 == 0)
                {
                    swipeLeft(findViewById(android.R.id.content));
                }
                else
                {
                    swipeDown(findViewById(android.R.id.content));
                }
            }
        }
//        else if(board[0][1] != "2")
//        {
//            current.setText("2");
//            int i = 0;
//            int j = 0;
//            found:
//                for(i = 0;i<3;i++)
//                {
//                    for(j = 0; j <3;j++)
//                    {
//                        if(board[i][j] == "2")
//                        {
//                            break found;
//                        }
//                    }
//                }
//            if(j == 0)
//            {
//                if(b2 == 0)
//                {
//                    if (b1 > i)
//                    {
//                        swipeLeft(null);
//                    }
//                    else
//                    {
//                        swipeUp(null);
//                    }
//                }
//                else
//                {
//                    if (b1 >1)
//                    {
//                        swipeDown(null);
//                    }
//                    else if( b1 < 1)
//                    {
//                        swipeUp(null);
//                    }
//                    else
//                    {
//                        swipeRight(null);
//                    }
//                }
//            }
//            else if(j == 1)
//            {
//                if((b1 < j) && (b2 == 1))
//                {
//                    swipeUp(null);
//                }
//                if(b2 == 2)
//                {
//                    if(b1 == 0)
//                    {
//                        swipeRight(null);
//                    }
//                    else
//                    {
//                        swipeDown(null);
//                    }
//                }
//                else if(i == 2)
//                {
//                    if((b1 == 2) && (b2 == 0))
//                    {
//                        swipeDown(null);
//                    }
//                    else  if((b1 == 1) && (b2 == 0))
//                    {
//                        swipeLeft(null);
//                    }
//                }
//                else if(i == 1)
//                {
//                    if ((b1 ==1) && (b2 == 0))
//                    {
//                        swipeUp(null);
//                    }
//                    else
//                    {
//                        swipeLeft(null);
//                    }
//                }
//            }
//            //j = 2
//            else
//            {
//                if((b2 == 2) && (b1 < i))
//                {
//                    swipeUp(null);
//                }
//                else if((b2 == 2) )
//                {
//                    swipeRight(null);
//                }
//                else if(b2 == 0)
//                {
//                    swipeLeft(null);
//                }
//                else if (b1 == 0)
//                {
//                    swipeLeft(null);
//                }
//                else
//                {
//                    swipeDown(null);
//                }
//            }
//        }
//        else if((board[0][2] != "3") && (board[1][2] != "3"))
//        {
//            if((b1 == 0)&&(b2 == 2))
//            {
//                swipeUp(null);
//            }
//            if((b1 == 2) && (b2 == 2))
//            {
//                swipeDown(null);
//            }
//            else if(b1 == 2)
//            {
//                swipeLeft(null);
//            }
//            else if((b1 == 1) && (b2 == 0))
//            {
//                swipeUp(null);
//            }
//            else
//            {
//                swipeRight(null);
//            }
//        }
//        else if(board[1][2] == "3" && !((b1 == 1) && (b2 == 0)))
//        {
//            if((b1 == 0)&&(b2 == 2))
//            {
//                swipeUp(null);
//            }
//            else if((b1 == 1) && (b2 ==1))
//            {
//                swipeRight(null);
//            }
//            else if ((b1 == 2) && (b2 == 0))
//            {
//                swipeDown(null);
//            }
//            else
//            {
//                swipeRight(null);
//            }
//        }


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
                if(board[i][j] != Integer.toString(i*3+j+1) && (board[i][j] != ""))
//                current.setText(Integer.toString(i));
                    finish = false;
                if (board[i][j] == "")
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
        renderBoard();
    }
    public void swipeUp(View view)
    {
        if (spacerow != 2)
            goUp();
        renderBoard();
    }
    public void swipeDown(View view)
    {
        if (spacerow != 0)
            goDown();
        renderBoard();
    }
    public void swipeLeft(View view)
    {
        if (spacecolumn != 2)
            goLeft();
        renderBoard();
    }
    public void swipeRight(View view)
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
