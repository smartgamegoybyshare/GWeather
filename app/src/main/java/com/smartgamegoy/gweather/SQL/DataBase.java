package com.smartgamegoy.gweather.SQL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private String table_name = "mySQL"; //資料表名
    private final static String db_name = "mySQL.db";    //資料庫名
    private static final int VERSION = 2;

    public DataBase(Context context) {
        super(context, db_name, null, VERSION);
    }

    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(table_name, null, null, null, null,
                null, null, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //ok
        String DATABASE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + table_name + "(" +
                "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
                "Name" + " TEXT, " +    //縣市
                "Wx" + " TEXT, " +  //天氣現象
                "T" + " TEXT, " +   //溫度
                "RH" + " TEXT, " +  //相對濕度
                "PoP6h" + " TEXT, " +   //降雨機率
                "lasttime" + " TEXT" + ")"; //最後更新時間
        db.execSQL(DATABASE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
        db.execSQL("DROP TABLE IF EXISTS " + table_name); //刪除舊有的資料表
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO 每次成功打開數據庫後首先被執行
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public int getCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name, null);
        return cursor.getCount();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_name); //刪除舊有的資料表
        onCreate(db);
    }

    public void insert(String Name, String Wx, String T, String RH, String PoP6h, String lasttime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name ", Name);
        cv.put("Wx", Wx);
        cv.put("T", T);
        cv.put("RH", RH);
        cv.put("PoP6h", PoP6h);
        cv.put("lasttime", lasttime);
        db.insert(table_name, Name, cv);
    }
}
