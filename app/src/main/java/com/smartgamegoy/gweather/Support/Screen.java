package com.smartgamegoy.gweather.Support;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Screen {   //檢視目前裝置螢幕長寬之class

    private String TAG = "Screen";  //檢視Log日誌時，方便尋找之TAG標籤
    private Context context;

    public Screen(Context context){ //導入context
        this.context = context;
    }

    public DisplayMetrics size(){   //獲取螢幕長寬之係數dm，dm.heightPixels(高)、dm.widthPixels(寬)
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}
