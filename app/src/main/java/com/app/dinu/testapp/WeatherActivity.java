package com.app.dinu.testapp;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;


public class WeatherActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView update;
    private TextView clouds;

    Weather weather = new Weather();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = (TextView) findViewById(R.id.cityName);
        temp = (TextView) findViewById(R.id.temp);
        iconView = (ImageView) findViewById(R.id.weathericon);
        description = (TextView) findViewById(R.id.description);
        humidity = (TextView) findViewById(R.id.humid);
        pressure = (TextView) findViewById(R.id.pressure);
        wind = (TextView) findViewById(R.id.windspeed);
        sunrise = (TextView) findViewById(R.id.sunrise);
        sunset = (TextView) findViewById(R.id.sunset);
        update = (TextView) findViewById(R.id.lastupdate);
        clouds = (TextView) findViewById(R.id.clouds);

        // Setting up the tabs
        final TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        //CurrentTab
        final TabHost.TabSpec current = tabHost.newTabSpec("Current");
        current.setContent(R.id.realTime);
        current.setIndicator("Current Condition");
        tabHost.addTab(current);

        //ForecastTab
        TabHost.TabSpec forecastTab = tabHost.newTabSpec("Forecast");
        forecastTab.setContent(R.id.forecast);
        forecastTab.setIndicator("forecast");
        tabHost.addTab(forecastTab);

        // TODO : Apply the animations
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

            }
        });

        renderWeatherData("Craiova,RO");

    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city});
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            //java.text.DateFormat df = DateFormat.getTimeFormat(getApplicationContext());
            //String sunriseDate = df.format(new Date(weather.place.getSunrise()));
            //String sunsetDate = df.format(new Date(weather.place.getSunset()));
            //String lastupdate = df.format(new Date(weather.place.getLastupdate()));

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.US);

            Date date = new Date(weather.place.getSunrise());
            String lastupdate = formatter.format(date);

            //Log.v("data: ",  weather.currentCondition.getDescription());
            cityName.setText(weather.place.getCity() + ", " + weather.place.getCountry());
            temp.setText(weather.currentCondition.getTemp() + "°C");
            description.setText(weather.currentCondition.getDescription());
            humidity.setText(getString(R.string.humid) + weather.currentCondition.getHumidity() + getString(R.string.percentage));
            pressure.setText(getString(R.string.pressure) + weather.currentCondition.getPressure() + getString(R.string.hpa));
            wind.setText(getString(R.string.windspeed) + weather.wind.getSpeed() + " " + getString(R.string.speed));
            clouds.setText(getString(R.string.clouds) + weather.clouds.getPrecipitation() + getString(R.string.percentage));
            //sunrise.setText(getString(R.string.sunrise) + sunriseDate);
            //sunset.setText(getString(R.string.sunset) + sunsetDate);
            update.setText(getString(R.string.lastupdate) + lastupdate);

        }

        @Override
        protected Weather doInBackground(String... strings) {
            // Getting the data from our API
            String data = ((new WeatherHttpClient().getWeatherData(strings[0])));
            // And then parsing it and giving it to the weather object
            weather = JSONWeatherParser.getWeather(data);
            //Log.v("data: ",  weather.currentCondition.getDescription());
            return weather;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_weather, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refreshWeather:
                renderWeatherData("Craiova,RO");
                return true;
            case R.id.changeCity:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
