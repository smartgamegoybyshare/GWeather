package com.smartgamegoy.gweather.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.smartgamegoy.gweather.R;

public class FirstPage extends AppCompatActivity {

    private String TAG = "FirstPage";   //檢視Log日誌時，方便尋找之TAG標籤
    private Vibrator vibrator;  //裝置震動之service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隱藏標題欄
        supportRequestWindowFeature( Window.FEATURE_NO_TITLE);
        //隱藏狀態欄
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.firstpage);

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        ImageView imageView = findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.weather); //設定起始畫面之背景圖

        Handler handler = new Handler();    //宣告一個新handler
        handler.postDelayed(() -> { //3秒後執行此handler
            Intent intent = new Intent(this, ChoseCity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    public boolean onKeyDown(int key, KeyEvent event) {
        switch (key) {
            case KeyEvent.KEYCODE_SEARCH:
                break;
            case KeyEvent.KEYCODE_BACK: //按下上一頁的行為
                vibrator.vibrate(100);
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
}
