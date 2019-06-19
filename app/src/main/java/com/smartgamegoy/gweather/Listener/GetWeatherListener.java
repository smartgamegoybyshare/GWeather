package com.smartgamegoy.gweather.Listener;

import org.json.JSONObject;

public interface GetWeatherListener {
    void getWeather(JSONObject getJson, String lastTime);
    void setTextView(String lastTime);
}
