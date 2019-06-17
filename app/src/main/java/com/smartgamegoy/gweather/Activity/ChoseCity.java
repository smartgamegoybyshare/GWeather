package com.smartgamegoy.gweather.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.smartgamegoy.gweather.R;
import com.smartgamegoy.gweather.SQL.DataBase;

public class ChoseCity extends AppCompatActivity {

    private String TAG = "FirstPage";   //檢視Log日誌時，方便尋找之TAG標籤
    private Vibrator vibrator;  //裝置震動之service
    private DataBase dataBase = new DataBase(this);
    private String url, Name = "", Wx = "", T = "", RH = "", PoP6h = "", lastDate = "";
    //url => api網址
    //Name => 縣市地區
    //Wx => 天氣現象
    //T => 溫度
    //RH => 相對濕度
    //PoP6h => 降雨機率
    //lastDate => 最後更新時間
    private String setname; //選擇的縣市
    private  String[] SiteName = {"基隆市", "新北市", "臺北市", "桃園市", "新竹縣", "宜蘭縣", "苗栗縣",
            "臺中市", "彰化縣", "南投縣", "花蓮縣", "雲林縣", "嘉義縣", "臺南市", "高雄市", "臺東縣",
            "屏東縣", "澎湖縣", "連江縣", "金門縣"};    //此api內所具有的縣市名稱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隱藏標題欄
        supportRequestWindowFeature( Window.FEATURE_NO_TITLE);
        //隱藏狀態欄
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        first();
    }

    private void first(){
        setContentView(R.layout.chosecity);

        Spinner spinner = findViewById(R.id.spinner);
        Button button1 = findViewById( R.id.button1);   //搜尋的按鈕
        Button button2 = findViewById( R.id.button2);   //上次搜尋的按鈕

        if(dataBase.getCount() == 0){   //若沒有上次搜尋的紀錄則將上次搜尋的按鈕設為不可按
            button2.setEnabled(false);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_style,SiteName);
        spinner.setAdapter(adapter);    //將陣列SiteName設入spinner

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                setname = SiteName[position];   //選擇的縣市
            }
            public void onNothingSelected(AdapterView arg0) {   //沒有選擇無動作
            }
        });

        button1.setOnClickListener(view -> {    //查詢現在時間之天氣狀態
            vibrator.vibrate(100);
            Log.d(TAG, "待增加");
        });

        button2.setOnClickListener(view -> {    //調出上次搜尋之紀錄
            vibrator.vibrate(100);
            Log.d(TAG, "待增加");
        });
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
