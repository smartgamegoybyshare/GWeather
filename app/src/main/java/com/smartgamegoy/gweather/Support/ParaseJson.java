package com.smartgamegoy.gweather.Support;

import com.smartgamegoy.gweather.Listener.GetWeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParaseJson {

    private String Wx = "", T = "", RH = "", PoP6h = "";
    //Wx => 天氣現象
    //T => 溫度
    //RH => 相對濕度
    //PoP6h => 降雨機率

    public ParaseJson() {
        super();
    }

    public void makeParase(JSONObject getJson, String lastTime, GetWeatherData getWeatherData) { //分解json，獲取資料
        try {
            JSONObject records = new JSONObject(getJson.get("records").toString());
            JSONArray locations = new JSONArray(records.get("locations").toString());
            JSONObject object = new JSONObject(locations.get(0).toString());
            JSONArray location = new JSONArray(object.get("location").toString());
            JSONObject object2 = new JSONObject(location.get(0).toString());
            JSONArray weatherElement = new JSONArray(object2.get("weatherElement").toString());
            for (int i = 0; i < weatherElement.length(); i++) {
                JSONObject jsonObject = new JSONObject(weatherElement.get(i).toString());
                JSONArray time = new JSONArray(jsonObject.get("time").toString());
                JSONObject jsonObject2 = new JSONObject(time.get(0).toString());
                JSONArray elementValue = new JSONArray(jsonObject2.get("elementValue").toString());
                JSONObject jsonObject3 = new JSONObject(elementValue.get(0).toString());
                String value = jsonObject3.get("value").toString();
                setValue(value, i);
            }
            getWeatherData.setText(lastTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setValue(String value, int i) {
        switch (i) {
            case 0:
                Wx = value;
                break;
            case 1:
                T = value;
                break;
            case 2:
                RH = value;
                break;
            case 3:
                PoP6h = value;
                break;
            default:
                break;
        }
    }

    public String getWx(){
        return Wx;
    }

    public String getT(){
        return T;
    }

    public String getRH(){
        return RH;
    }

    public String getPoP6h(){
        return  PoP6h;
    }
}
