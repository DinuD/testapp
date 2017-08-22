package com.app.dinu.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import game.GameView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
