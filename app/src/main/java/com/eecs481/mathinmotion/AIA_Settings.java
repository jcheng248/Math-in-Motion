package com.eecs481.mathinmotion;

import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


public class AIA_Settings extends ActionBarActivity {
    public final static String DIFFICULTY = "com.eecs481.mathinmotion.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aia__settings);
    }

    public void sendMessageE(View view) {
        Intent intent = new Intent(this, AlgebrainAction.class);
        intent.putExtra(DIFFICULTY, "easy");
        startActivity(intent);
    }
    public void sendMessageM(View view) {
        Intent intent = new Intent(this, AlgebrainAction.class);
        intent.putExtra(DIFFICULTY, "medium");
        startActivity(intent);
    }
    public void sendMessageH(View view) {
        Intent intent = new Intent(this, AlgebrainAction.class);
        intent.putExtra(DIFFICULTY, "hard");
        startActivity(intent);
    }
}

