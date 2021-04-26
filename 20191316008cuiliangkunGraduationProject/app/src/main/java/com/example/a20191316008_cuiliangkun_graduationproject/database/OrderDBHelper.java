package com.example.a20191316008_cuiliangkun_graduationproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDBHelper extends SQLiteOpenHelper {
    public static final String create_order="create table OrderInfo(" +
            "oid integer primary key," +
            "uid integer not null," +
            "hid integer not null," +
            "shenfenzheng varchar(30) not null," +
            "truename varchar(30) not null," +
            "phonenumber varchar(30) not null," +
            "fangke integer not null," +
            "price double not null)" ;
    public OrderDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_order);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean findOrder(){     //判断是否数据库为空
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("OrderInfo",null,null,null,null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
    public void insertOrder(int uid, int hid,int fangke,String shenfenzheng,String truename,String phonenumber,double price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("uid",uid);
        values.put("hid",hid);
        values.put("shenfenzheng",shenfenzheng);
        values.put("truename",truename);
        values.put("phonenumber",phonenumber);
        values.put("price",price);
        values.put("fangke",fangke);
        db.insert("OrderInfo",null,values);
        db.close();
    }

    //查找所有订单并把信息一次存入数组
    public List<Order> findAllOrder(){
        List<Order> list=new ArrayList<Order>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("OrderInfo",null,null,null,null,null,"oid desc");

        while(cursor.moveToNext()){
            Order or=new Order();
            or.setOid(cursor.getInt(0));
            or.setUid(cursor.getInt(1));
            or.setHid(cursor.getInt(2));
            or.setShenfenzheng(cursor.getString(3));
            or.setTruename(cursor.getString(4));
            or.setPhoneNumber(cursor.getString(5));
            or.setFangke(cursor.getInt(6));
            or.setPrice(cursor.getDouble(7));

            list.add(or);
        }
        cursor.close();
        return list;
    }
    //查找我的订单
    public List<Order> findMyOrder(String fangke){
        List<Order> list=new ArrayList<Order>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("OrderInfo",null,"fangke=?",new String[]{fangke},null,null,"oid desc");
        while(cursor.moveToNext()){
            Order or=new Order();
            or.setOid(cursor.getInt(0));
            or.setUid(cursor.getInt(1));
            or.setHid(cursor.getInt(2));
            or.setShenfenzheng(cursor.getString(3));
            or.setTruename(cursor.getString(4));
            or.setPhoneNumber(cursor.getString(5));
            or.setFangke(cursor.getInt(6));
            or.setPrice(cursor.getDouble(7));
            list.add(or);
        }
        cursor.close();
        return list;
    }
    //根据oid判断订单是否存在
    public boolean ExistOrderById(String oid){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("OrderInfo",null,"oid=?",new String[]{oid},null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
    public boolean deleteOrder(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("OrderInfo","oid=?",new String[]{id})>0;
    }

    public boolean ExistMyOrder(String fangke) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("OrderInfo",null,"fangke=?",new String[]{fangke},null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
}
