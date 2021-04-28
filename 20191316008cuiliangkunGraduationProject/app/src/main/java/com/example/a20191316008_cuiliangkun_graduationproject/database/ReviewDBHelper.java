package com.example.a20191316008_cuiliangkun_graduationproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a20191316008_cuiliangkun_graduationproject.R;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDBHelper extends SQLiteOpenHelper {
    public static final String create_Review="create table ReviewInfo(" +
            "rid integer primary key," +
            "hid integer not null," +
            "uid integer not null," +
            "total varchar(10) not null," +
            "weisheng varchar(10) not null," +
            "huanjing varchar(10) not null," +
            "fuwu varchar(10) not null," +
            "sheshi varchar(10) not null," +
            "detail varchar(200) not null)" ;
    public ReviewDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_Review);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean findReview(String hid){     //判断是否民宿评论为空
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("ReviewInfo",null,"hid=?",new String[]{hid},null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
    public void insertReview(Integer hid,Integer uid,String total, String weisheng,String huanjing,String fuwu,String sheshi,String detail){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("hid",hid);
        values.put("uid",uid);
        values.put("total",total);
        values.put("weisheng",weisheng);
        values.put("huanjing",huanjing);
        values.put("fuwu",fuwu);
        values.put("sheshi",sheshi);
        values.put("detail",detail);
        db.insert("ReviewInfo",null,values);
        db.close();
    }
    //查找所有民宿评论并把信息一次存入数组
    public List<Review> findAllReview(String hid){
        List<Review> list=new ArrayList<Review>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("ReviewInfo",null,"hid=?",new String[]{hid},null,null,"rid desc");
            while(cursor.moveToNext()){
                Review re=new Review();
                re.setRid(cursor.getInt(0));
                re.setHid(cursor.getInt(1));
                re.setUid(cursor.getInt(2));
                re.setTotal(cursor.getString(3));
                re.setWeisheng(cursor.getString(4));
                re.setHuanjing(cursor.getString(5));
                re.setFuwu(cursor.getString(6));
                re.setSheshi(cursor.getString(7));
                re.setDetail(cursor.getString(8));
                list.add(re);
            }
        cursor.close();
        return list;
    }


    //查找我发布的评论
    public List<Review> findMyReview(String uid){
        List<Review> list=new ArrayList<Review>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Review",null,"uid=?",new String[]{uid},null,null,"rid desc");
        while(cursor.moveToNext()){
            Review re=new Review();
            re.setRid(cursor.getInt(0));
            re.setHid(cursor.getInt(1));
            re.setUid(cursor.getInt(2));
            re.setTotal(cursor.getString(3));
            re.setWeisheng(cursor.getString(4));
            re.setHuanjing(cursor.getString(5));
            re.setFuwu(cursor.getString(6));
            re.setSheshi(cursor.getString(7));
            re.setDetail(cursor.getString(8));
            list.add(re);
        }
        cursor.close();
        return list;
    }

    public boolean deleteReview(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Review","rid=?",new String[]{id})>0;
    }

    public boolean isReviewed(String userid, Integer id) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("ReviewInfo",null,"uid=?",new String[]{userid},null,null,null);
        while(cursor.moveToNext()){
           if(cursor.getInt(1) == id){
               return true;
           }
        }
        cursor.close();
        db.close();
        return false;
    }
}
