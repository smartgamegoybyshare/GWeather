package com.smartgamegoy.gweather.Support;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    public DateTime(){
        super();
    }

    public String getDateTime(Date date){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdFormat2 = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdFormat.format(date);
        String strDate2 = sdFormat2.format(date);
        return strDate+"T"+strDate2;
    }

    public String getDateTime2(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        return sdFormat.format(date);
    }
}
