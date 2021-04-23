package com.example.a20191316008_cuiliangkun_graduationproject.bean;

import android.content.Intent;

public class Order {
    private Integer oid;
    private Integer uid;
    private Integer hid;
    private String shenfenzheng;
    private String truename;
    private String phonenumber;
    private double price;
    private Integer fangke;
    public void setHid(int string) {
        hid = string;
    }

    public void setUid(int anInt) {
        uid = anInt;
    }

    public void setPrice(double string) {
        price = string;
    }

    public Integer getHid() {
        return hid;
    }

    public Integer getOid() {
        return oid;
    }

    public double getPrice() {
        return price;
    }

    public Integer getUid() {
        return uid;
    }

    public void setShenfenzheng(String anInt) {
        shenfenzheng = anInt;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getShenfenzheng() {
        return shenfenzheng;
    }

    public String getTruename() {
        return truename;
    }

    public void setFangke(int anInt) {
        fangke = anInt;
    }

    public Integer getFangke() {
        return fangke;
    }

    public void setOid(Integer anInt) {
        this.oid = anInt;
    }

}
