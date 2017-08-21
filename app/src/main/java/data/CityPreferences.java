package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by drago on 8/21/2017.
 */

public class CityPreferences {

    SharedPreferences preferences;
    public CityPreferences(Activity activity) {
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity() {
        return preferences.getString("city", "Timisoara,RO");
    }

    public void setCity(String city) {
        preferences.edit().putString("city", city).apply();
    }

}
