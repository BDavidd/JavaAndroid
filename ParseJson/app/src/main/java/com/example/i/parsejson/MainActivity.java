package com.example.i.parsejson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView weatherConditionTextView;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherConditionTextView = findViewById(R.id.tv_weather_condition);

        jsonString = readJSONFile();
        if (jsonString != null) {
            String weatherCondition = readWeatherCondition(jsonString);
            if (weatherCondition != null) {
                weatherConditionTextView.setText(weatherCondition);
            }
        }
    }

    private String readJSONFile() {
        String jsonString;
        try {
            InputStream stream = getAssets().open("data.json");
            byte[] buffer = new byte[stream.available()];

            stream.read(buffer);
            stream.close();

            jsonString = new String(buffer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private String readWeatherCondition(String jsonString) {
        String weatherCondition;
        try {
            JSONObject weather = new JSONObject(jsonString).getJSONObject("weather");
            weatherCondition = weather.getString("condition");
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
        return weatherCondition;
    }
}
