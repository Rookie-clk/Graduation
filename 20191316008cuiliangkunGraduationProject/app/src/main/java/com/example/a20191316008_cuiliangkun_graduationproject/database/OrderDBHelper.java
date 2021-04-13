package com.example.a20191316008_cuiliangkun_graduationproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrderDBHelper extends SQLiteOpenHelper {
    public static final String create_Order="create table Order(" +
            "oid integer primary key," +
            "uid integer not null," +
            "hid integer not null," +
            "status integer not null," +
            "price double not null)" ;
    public OrderDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean findOrder(){     //判断是否数据库为空
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Order",null,null,null,null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
    public void insertOrder(String name, String price,String region, String address,String type, String huxing, String square,String style, byte[] picture,String cequip,String lequip,int uid){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("hname",name);
        values.put("hprice",price);
        values.put("hregion",region);
        values.put("haddress",address);
        values.put("htype",type);
        values.put("hhuxing",huxing);
        values.put("hsquare",square);
        values.put("hstyle",style);
        values.put("himage",picture);
        values.put("hcequip",cequip);
        values.put("hlequip",lequip);
        values.put("uid",uid);
        db.insert("HotelInfo",null,values);
        db.close();
    }
}
