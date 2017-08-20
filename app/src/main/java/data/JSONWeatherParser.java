package data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by DinuD-PC on 8/9/2017.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data) {
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            // Getting the "coord" JSON Object from the API
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));

            // "sys" obj
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));

            place.setLastupdate(Utils.getInt("dt", jsonObject));
            place.setCity(Utils.getString("name", jsonObject));

            weather.place = place;

            // "weather" obj
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weatherObj = weatherArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", weatherObj));
            weather.currentCondition.setDescription(Utils.getString("description", weatherObj));
            weather.currentCondition.setCondition(Utils.getString("main", weatherObj));
            weather.currentCondition.setIcon(Utils.getString("icon", weatherObj));

            // "wind" obj
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            weather.wind.setDeg(Utils.getFloat("deg", windObj));

            // "clouds" obj
            JSONObject clouds = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", clouds));

            // "main" obj
            JSONObject mainObj = Utils.getObject("main", jsonObject);
            weather.currentCondition.setTemp(Utils.getDouble("temp", mainObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max", mainObj));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min", mainObj));
            weather.currentCondition.setPressure(Utils.getFloat("pressure", mainObj));
            weather.currentCondition.setHumidity(Utils.getFloat("humidity", mainObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
    }

}
