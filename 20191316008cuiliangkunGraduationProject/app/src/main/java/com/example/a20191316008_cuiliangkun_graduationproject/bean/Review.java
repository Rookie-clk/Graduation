package com.example.a20191316008_cuiliangkun_graduationproject.bean;

public class Review {
    private Integer Rid;
    private Integer Hid;
    private Integer Uid;
    private int Description;
    private int Service;
    private int Location;
    private int Clean;
    private int Price;
    private int Convenient;
    private String Detail;

    public void setRid(int anInt) {
        this.Rid = anInt;
    }

    public void setHid(int hid) {
        this.Hid = hid;
    }

    public void setUid(int anInt) {
        this.Uid = anInt;
    }

    public void setDes(int anInt) {
        this.Description = anInt;
    }

    public void setSer(int anInt) {
        this.Service = anInt;
    }

    public void setLoc(int anInt) {
        this.Location = anInt;
    }

    public void setCle(int anInt) {
        this.Clean = anInt;
    }

    public void setPri(int anInt) {
        this.Price = anInt;
    }

    public void setCon(int anInt) {
        this.Convenient = anInt;
    }

    public void setDetail(String string) {
        this.Detail = string;
    }
}
