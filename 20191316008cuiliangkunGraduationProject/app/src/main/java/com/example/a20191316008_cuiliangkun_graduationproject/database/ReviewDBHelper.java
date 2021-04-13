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
    public static final String create_Review="create table Review(" +
            "rid integer primary key," +
            "hid integer not null," +
            "uid integer not null," +
            "des integer not null," +
            "ser integer not null," +
            "loc integer not null," +
            "cle integer not null," +
            "pri integer not null," +
            "con integer not null," +
            "detail varchar(200) not null)" ;
    public ReviewDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean findReview(){     //判断是否数据库为空
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Review",null,null,null,null,null,null);
        if (cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
    public void insertReview(Integer rid, Integer hid,Integer uid, int des,int ser, int loc, int cle,int pri, int con,String detail){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("rid",rid);
        values.put("hid",hid);
        values.put("uid",uid);
        values.put("des",des);
        values.put("ser",ser);
        values.put("loc",loc);
        values.put("cle",cle);
        values.put("pri",pri);
        values.put("con",con);
        values.put("detail",detail);
        db.insert("Review",null,values);
        db.close();
    }
    //查找所有评论并把信息一次存入数组
    public List<Review> findAllReview(){
        List<Review> list=new ArrayList<Review>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Review",null,null,null,null,null,"rid desc");
        for (int i = 0; i<5; i++){      //取前五条记录
            while(cursor.moveToNext()){
                Review re=new Review();
                re.setRid(cursor.getInt(0));
                re.setHid(cursor.getInt(1));
                re.setUid(cursor.getInt(2));
                re.setDes(cursor.getInt(3));
                re.setSer(cursor.getInt(4));
                re.setLoc(cursor.getInt(5));
                re.setCle(cursor.getInt(6));
                re.setPri(cursor.getInt(7));
                re.setCon(cursor.getInt(8));
                re.setDetail(cursor.getString(9));
                list.add(re);
            }
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
            re.setDes(cursor.getInt(3));
            re.setSer(cursor.getInt(4));
            re.setLoc(cursor.getInt(5));
            re.setCle(cursor.getInt(6));
            re.setPri(cursor.getInt(7));
            re.setCon(cursor.getInt(8));
            re.setDetail(cursor.getString(9));
            list.add(re);
        }
        cursor.close();
        return list;
    }

    public boolean deleteReview(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Review","rid=?",new String[]{id})>0;
    }
}
