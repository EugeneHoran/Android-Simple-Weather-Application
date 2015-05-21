package com.eugene.weatherapplication.Utilities;


import android.content.Context;

import com.eugene.weatherapplication.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteFetch {

    private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?";

    public static JSONObject getTodayForecast(Context context, String lat, String longitude) {
        try {
            URL url = new URL(OPEN_WEATHER_MAP_API + "lat=" + lat + "&lon=" + longitude + "&units=imperial");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder(1024);
            String tmp;
            while ((tmp = reader.readLine()) != null) json.append(tmp).append("\n");
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                return null;
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    private static final String OPEN_WEATHER_MAP_API_SEVEN_DAY = "http://api.openweathermap.org/data/2.5/forecast/daily?";

    public static JSONObject getFiveDayForecast(Context context, String lat, String longitude) {
        try {
            URL url = new URL(OPEN_WEATHER_MAP_API_SEVEN_DAY + "lat=" + lat + "&lon=" + longitude + "&cnt=6&mode=json" + "&units=imperial");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder(1024);
            String tmp;
            while ((tmp = reader.readLine()) != null) json.append(tmp).append("\n");
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                return null;
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }
}
