package com.example.a20191316008_cuiliangkun_graduationproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;

public class HStatusDBHelper extends SQLiteOpenHelper {
    public static final String create_StatusInfo="create table StatusInfo(" +
            "sid integer primary key," +
            "hid integer not null," +
            "status boolean not null)" ;
    public HStatusDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_StatusInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void SetStatus2Y(String hid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hid",hid);
        values.put("status",true);
        db.insert("StatusInfo",null,values);
        db.close();
    }
    public void SetStatus2N(String hid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hid",hid);
        values.put("status",false);
        db.insert("StatusInfo",null,values);
        db.close();
    }
    public boolean Status(String hid){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("StatusInfo",null,"hid=?",new String[]{hid},null,null,null);
        cursor.moveToFirst();
        if(cursor.getInt(2) == 1){
            return true;
        }
        cursor.close();
        return false;
    }
    public boolean ExistStatus(String hid){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("StatusInfo",null,"hid=?",new String[]{hid},null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }

}
