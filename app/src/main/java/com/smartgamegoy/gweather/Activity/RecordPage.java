package com.smartgamegoy.gweather.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.smartgamegoy.gweather.R;
import com.smartgamegoy.gweather.SQL.DataBase;

public class RecordPage extends AppCompatActivity {

    private String TAG = "RecordPage";   //檢視Log日誌時，方便尋找之TAG標籤
    private Vibrator vibrator;  //裝置震動之service
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隱藏標題欄
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //隱藏狀態欄
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        first();
    }

    @SuppressLint("SetTextI18n")
    private void first() {
        setContentView(R.layout.sqldata);

        TextView textView2 = findViewById(R.id.textView2);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView10 = findViewById(R.id.textView10);
        TextView textView12 = findViewById(R.id.textView12);

        Cursor check = dataBase.select();
        check.moveToPosition(0);
        textView2.setText(check.getString(check.getColumnIndex("Name")));
        textView4.setText(check.getString(check.getColumnIndex("Wx")));
        textView6.setText(check.getString(check.getColumnIndex("T")) + (char) (186) + "C");
        textView8.setText(check.getString(check.getColumnIndex("RH")) + "%");
        textView10.setText(check.getString(check.getColumnIndex("PoP6h")) + "%");
        textView12.setText(check.getString(check.getColumnIndex("lasttime")));
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
}
