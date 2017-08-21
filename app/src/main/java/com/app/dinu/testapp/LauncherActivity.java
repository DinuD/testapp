package com.app.dinu.testapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.app.dinu.testapp.WeatherActivity.isNetworkStatusAvailable;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // Defining the list
        final String txtduplicator = getString(R.string.anTextDuplicator);
        final String[] activities = {txtduplicator, "WeatherApp"};

        ListView mListView = (ListView) findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, activities);
        mListView.setAdapter(adapter);

        //this.getActionBar().setTitle(getString(R.string.anLauncher));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (pos == 0) {
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                }
                if (pos == 1) {
                    Intent weather = new Intent(getApplicationContext(), WeatherActivity.class);
                    if(isNetworkStatusAvailable(getApplicationContext())) {
                        startActivity(weather);
                    } else {
                        Toast.makeText(getApplicationContext(), "Internet is not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
