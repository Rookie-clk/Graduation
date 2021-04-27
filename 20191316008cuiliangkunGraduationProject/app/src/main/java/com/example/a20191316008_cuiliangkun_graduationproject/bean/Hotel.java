package com.example.a20191316008_cuiliangkun_graduationproject.bean;

public class Hotel {
    private Integer id;
    private String name;
    private String price;
    private String address;
    private String info;
    private byte[] picture;
    private String cequip;
    private String lequip;
    private String region;
    private String type;
    private String huxing;
    private Integer square;
    private String style;
    private int uid;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String  getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String  getInfo() {
        return info;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public byte[] getPicture() {
        return picture;
    }
    public void setPicture(byte[] picture){
        this.picture = picture;
    }
    public String getCequip() {
        return cequip;
    }
    public void setCequip(String cequip){
        this.cequip = cequip;
    }
    public String getLequip() {
        return lequip;
    }
    public void setLequip(String lequip){
        this.lequip = lequip;
    }

    public String getRegion(){
        return region;
    }
    public void setRegion(String string) {
        this.region = string;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String string) {
        this.huxing = string;
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String string) {
        this.style = string;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int anInt) {
        this.uid = anInt;
    }

}
