package com.example.a20191316008_cuiliangkun_graduationproject.bean;

public class Review {
    private Integer Rid;
    private Integer Hid;
    private Integer Uid;
    private String total;
    private String weisheng;
    private String huanjing;
    private String fuwu;
    private String sheshi;
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

    public void setTotal(String anInt) {
        this.total = anInt;
    }

    public void setWeisheng(String anInt) {
        this.weisheng = anInt;
    }

    public void setHuanjing(String anInt) {
        this.huanjing = anInt;
    }

    public void setFuwu(String anInt) {
        this.fuwu = anInt;
    }

    public void setSheshi(String anInt) {
        this.sheshi = anInt;
    }

    public void setDetail(String string) {
        this.Detail = string;
    }

    public Integer getUid() {
        return Uid;
    }

    public Integer getHid() {
        return Hid;
    }

    public Integer getRid() {
        return Rid;
    }

    public String getDetail() {
        return Detail;
    }

    public String getFuwu() {
        return fuwu;
    }

    public String getHuanjing() {
        return huanjing;
    }

    public String getSheshi() {
        return sheshi;
    }

    public String getTotal() {
        return total;
    }

    public String getWeisheng() {
        return weisheng;
    }
}
