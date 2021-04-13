package com.example.a20191316008_cuiliangkun_graduationproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDBHelper extends SQLiteOpenHelper {

    public static final String create_HotelInfo="create table HotelInfo(" +
            "hid integer primary key," +
            "hname varchar(30) not null," +
            "hprice varchar(20) not null," +
            "hregion varchar(30) not null," +
            "haddress varchar(30) not null," +
            "htype varchar(30) not null," +
            "hhuxing varchar(30) not null," +
            "hsquare interger not null," +
            "hstyle varchar(30) not null," +
            "himage BLOB not null," +
            "hcequip varchar(30) not null," +
            "hlequip varchar(30) not null," +
            "uid integer not null)" ;

    public HotelDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean findHotel(){     //判断是否数据库为空
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("HotelInfo",null,null,null,null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }

    public void insertHotel(String name, String price,String region, String address,String type, String huxing, String square,String style, byte[] picture,String cequip,String lequip,int uid){
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

    //查找所有民宿并把信息一次存入数组
    public List<Hotel> findAllHotel(){
        List<Hotel> list=new ArrayList<Hotel>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("HotelInfo",null,null,null,null,null,"hid desc");
        for (int i = 0; i<5; i++){      //取前五条记录
            while(cursor.moveToNext()){
                Hotel ho=new Hotel();
                ho.setId(cursor.getInt(0));
                ho.setName(cursor.getString(1));
                ho.setPrice(cursor.getString(2));
                ho.setRegion(cursor.getString(3));
                ho.setAddress(cursor.getString(4));
                ho.setType(cursor.getString(5));
                ho.setHuxing(cursor.getString(6));
                ho.setSquare(cursor.getInt(7));
                ho.setStyle(cursor.getString(8));
                ho.setPicture(cursor.getBlob(9));
                ho.setCequip(cursor.getString(10));
                ho.setLequip(cursor.getString(11));
                ho.setUid(cursor.getInt(12));
                list.add(ho);
            }
        }
        cursor.close();
        return list;
    }
    //查找对应地区的民宿
    public List<Hotel> findHotelByRegion(String region){
        List<Hotel> list=new ArrayList<Hotel>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("HotelInfo",null,"hregion=?",new String[]{region},null,null,null);
        while(cursor.moveToNext()){
            Hotel ho=new Hotel();
            ho.setId(cursor.getInt(0));
            ho.setName(cursor.getString(1));
            ho.setPrice(cursor.getString(2));
            ho.setRegion(cursor.getString(3));
            ho.setAddress(cursor.getString(4));
            ho.setType(cursor.getString(5));
            ho.setHuxing(cursor.getString(6));
            ho.setSquare(cursor.getInt(7));
            ho.setStyle(cursor.getString(8));
            ho.setPicture(cursor.getBlob(9));
            ho.setCequip(cursor.getString(10));
            ho.setLequip(cursor.getString(11));
            ho.setUid(cursor.getInt(12));
            list.add(ho);
        }
        cursor.close();
        return list;
    }
    //根据名称查找民宿
    public List<Hotel> findHotelByName(String name){
        List<Hotel> list=new ArrayList<Hotel>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("HotelInfo",null,"hname=?",new String[]{name},null,null,null);
        while(cursor.moveToNext()){
            Hotel ho=new Hotel();
            ho.setId(cursor.getInt(0));
            ho.setName(cursor.getString(1));
            ho.setPrice(cursor.getString(2));
            ho.setRegion(cursor.getString(3));
            ho.setAddress(cursor.getString(4));
            ho.setType(cursor.getString(5));
            ho.setHuxing(cursor.getString(6));
            ho.setSquare(cursor.getInt(7));
            ho.setStyle(cursor.getString(8));
            ho.setPicture(cursor.getBlob(9));
            ho.setCequip(cursor.getString(10));
            ho.setLequip(cursor.getString(11));
            ho.setUid(cursor.getInt(12));
            list.add(ho);
        }
        cursor.close();
        return list;
    }
    //查找我发布的民宿
    public List<Hotel> findMyHotel(String uid){
        List<Hotel> list=new ArrayList<Hotel>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("HotelInfo",null,"uid=?",new String[]{uid},null,null,"hid desc");
        while(cursor.moveToNext()){
            Hotel ho=new Hotel();
            ho.setId(cursor.getInt(0));
            ho.setName(cursor.getString(1));
            ho.setPrice(cursor.getString(2));
            ho.setRegion(cursor.getString(3));
            ho.setAddress(cursor.getString(4));
            ho.setType(cursor.getString(5));
            ho.setHuxing(cursor.getString(6));
            ho.setSquare(cursor.getInt(7));
            ho.setStyle(cursor.getString(8));
            ho.setPicture(cursor.getBlob(9));
            ho.setCequip(cursor.getString(10));
            ho.setLequip(cursor.getString(11));
            ho.setUid(cursor.getInt(12));
            list.add(ho);
        }
        cursor.close();
        return list;
    }

    public boolean deleteHotel(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("HotelInfo","hid=?",new String[]{id})>0;
    }

}
