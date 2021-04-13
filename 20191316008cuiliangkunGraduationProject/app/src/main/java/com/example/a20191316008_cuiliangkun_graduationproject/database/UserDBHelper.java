package com.example.a20191316008_cuiliangkun_graduationproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;

public class UserDBHelper extends SQLiteOpenHelper {

    HotelDBHelper dbHelper;
    public static int uid;
    private static final String create_userinfo="create table UserInfo(" +
            "uid integer primary key," +
            "uname varchar(12) not null," +
            "upwd varchar(20) not null," +
            "uavartor BLOB not null," +
            "isowner boolean not null)" ;

    public UserDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //注册成功进行数据的插入
    public void insertUser(String username,String pwd,String avartor){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("uname",username);
        values.put("upwd",pwd);
        values.put("uavartor",avartor);
        values.put("isowner",false);
        db.insert("UserInfo",null,values);
        db.close();
    }
    //判断该用户名是否以及被使用
    public boolean findThisUser(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("UserInfo",null,"uname=?",new String[]{username},null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }

}