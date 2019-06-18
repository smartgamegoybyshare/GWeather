package com.smartgamegoy.gweather.Listener;

import com.smartgamegoy.gweather.SQL.DataBase;

public class GetWeatherData {

    private GetWeatherListener getWeatherListener;

    public void setListener(GetWeatherListener mGetWeatherListener){    //設置監聽器
        getWeatherListener = mGetWeatherListener;
    }

    public void actListener(String setname, DataBase dataBase){  //監聽器的行為動作
        if(getWeatherListener != null){
            getWeatherListener.getWeather();
        }
    }
}
