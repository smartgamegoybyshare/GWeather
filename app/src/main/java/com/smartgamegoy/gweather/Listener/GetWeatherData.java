package com.smartgamegoy.gweather.Listener;

import android.content.Context;
import android.util.Log;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.smartgamegoy.gweather.Support.DateTime;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import static android.content.ContentValues.TAG;

public class GetWeatherData {

    private GetWeatherListener getWeatherListener;
    private DateTime dateTime = new DateTime();

    public void setListener(GetWeatherListener mGetWeatherListener) {    //設置監聽器
        getWeatherListener = mGetWeatherListener;
    }

    public void actListener(String setname, Context context) {  //監聽器的行為動作
        if (getWeatherListener != null) {
            try {
                Date date = new Date();
                String nowDate = dateTime.getDateTime(date);    //為api所用，起始時間
                String newDate = dateTime.getDateTime2();   //最後更新時間
                Calendar c = Calendar.getInstance();
                c.add(Calendar.HOUR, 6); // 目前時間加6小時
                Date date2 = c.getTime();
                String addDate = dateTime.getDateTime(date2);   //為api所用，最後的時間
                String url_SiteName = URLEncoder.encode(setname, "UTF-8");  //將選擇的地區轉碼為utf-8
                String url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-089?locationId=F-D0047-089&locationName=" + url_SiteName +
                        "&elementName=RH,Wx,T,Wind,PoP6h&sort=time&format=json&authorizationkey=CWB-F18174AB-13F5-45CF-BE7C-C1A5BF97AD9E" +
                        "&timeFrom=" + nowDate + "&timeTo=" + addDate;    //api網址

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,    //使用Volley獲取api回傳之json
                        response -> getWeatherListener.getWeather(response, newDate),
                        error -> Log.d(TAG, "error : " + error.toString())
                );
                Volley.newRequestQueue(context).add(jsonObjectRequest);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void setText(String lastTime){
        if(getWeatherListener != null){
            getWeatherListener.setTextView(lastTime);
        }
    }
}
