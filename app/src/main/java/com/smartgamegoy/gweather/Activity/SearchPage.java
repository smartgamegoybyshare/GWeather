package com.smartgamegoy.gweather.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.smartgamegoy.gweather.Listener.GetWeatherData;
import com.smartgamegoy.gweather.Listener.GetWeatherListener;
import com.smartgamegoy.gweather.R;
import com.smartgamegoy.gweather.SQL.DataBase;

public class SearchPage extends AppCompatActivity implements GetWeatherListener {

    private String TAG = "SearchPage";   //檢視Log日誌時，方便尋找之TAG標籤
    private Vibrator vibrator;  //裝置震動之service
    private DataBase dataBase = new DataBase(this);
    private GetWeatherData getWeatherData = new GetWeatherData();
    private String url, Name = "", Wx = "", T = "", RH = "", PoP6h = "", lastDate = "";
    //url => api網址
    //Name => 縣市地區
    //Wx => 天氣現象
    //T => 溫度
    //RH => 相對濕度
    //PoP6h => 降雨機率
    //lastDate => 最後更新時間
    private String setname;
    private TextView textView2, textView4, textView6, textView8, textView10, textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隱藏標題欄
        supportRequestWindowFeature( Window.FEATURE_NO_TITLE);
        //隱藏狀態欄
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        get_intent();
    }

    private void get_intent(){
        getWeatherData.setListener(this);   //設置監聽器
        Intent intent = getIntent();    //獲取上一個生命週期傳遞之資料
        setname = intent.getStringExtra("setname");

        viewData();
    }

    private void viewData(){
        setContentView(R.layout.showdata);

        textView2 = findViewById(R.id.textView2);   //地點
        textView4 = findViewById(R.id.textView4);   //天氣現象
        textView6 = findViewById(R.id.textView6);   //溫度
        textView8 = findViewById(R.id.textView8);   //相對濕度
        textView10 = findViewById(R.id.textView10); //降雨機率
        textView12 = findViewById(R.id.textView12); //最後更新時間

        getWeatherData.actListener(setname, dataBase);  //啟動監聽器
    }

    public boolean isWebConnect() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    private void backup(){  //返回地區選擇頁面
        Intent intent = new Intent(this, ChoseCity.class);
        startActivity(intent);
        finish();
    }

    public boolean onKeyDown(int key, KeyEvent event) {
        switch (key) {
            case KeyEvent.KEYCODE_SEARCH:
                break;
            case KeyEvent.KEYCODE_BACK: //按下上一頁的行為
                vibrator.vibrate(100);
                backup();
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                break;
            default:
                return false;
        }
        return false;
    }

    @Override
    public void onDestroy() {   //結束此生命週期
        super.onDestroy();
        dataBase.close();   //關閉資料庫
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onStop() {   //停止此生命週期狀態
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onResume() { //恢復此生命週期狀態
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {  //暫停此生命週期狀態
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {   //裝置平放或直立之行為
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { //平放
            // land do nothing is ok
            Log.d(TAG, "onConfigurationChanged(LANDSCAPE) do nothing");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {   //直立
            // port do nothing is ok
            Log.d(TAG, "onConfigurationChanged(PORTRAIT) do nothing");
        }
    }

    @Override
    public void getWeather() {
        Log.d(TAG, "待增加");
    }
}
