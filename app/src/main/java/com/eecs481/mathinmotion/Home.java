package com.eecs481.mathinmotion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

public class Home extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void eight_puzzle_launch(View view)
    {
        Intent intent = new Intent(this, EightPuzzle.class);
        startActivity(intent);
    }

    public void algebra_launch(View view)
    {
        Intent intent = new Intent(this, AlgebrainAction.class);
        startActivity(intent);
    }

    public void instructions_launch(View view)
    {
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
    }
    public void highscores_launch(View view)
    {
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
    }
}
