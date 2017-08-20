package com.app.dinu.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView soontxt = (TextView) findViewById(R.id.maintxt);
        Button rndbtn = (Button) findViewById(R.id.submitbtn);
        final EditText txtfield = (EditText) findViewById(R.id.txtfield);
        final TextView result = (TextView) findViewById(R.id.result);

        rndbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hide the keyboard when button is pressed
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                // update the result
                result.setText(txtfield.getText().toString());
                if (txtfield.getText().toString().equals("secret")) {
                    Intent second = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(second);
                }
            }
        });
    }

    // Add the items from res/menu/menu_main.xml to the activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Specify what happens when each button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_activity:
                Intent reset = getIntent();
                finish();
                startActivity(reset);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

