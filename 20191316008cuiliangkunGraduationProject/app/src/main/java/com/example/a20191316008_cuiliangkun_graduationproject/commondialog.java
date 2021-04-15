package com.example.a20191316008_cuiliangkun_graduationproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class commondialog extends AlertDialog{
    private TextView messsageTv;
    private TextView titleTv;
    private Button positiveBn;
    private Button negativeBn;

    protected commondialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);
        initView();
        initEvent();
    }
    public void initView(){
        messsageTv = findViewById(R.id.message);
        titleTv    = findViewById(R.id.title);
        positiveBn = findViewById(R.id.positivebn);
        negativeBn = findViewById(R.id.negativebn);
    }
    public interface OnclickButtonListener{
        void onPositiveClick();
        void onNegativeClick();
    }
    public OnclickButtonListener onclickButtonListener;
    public commondialog setOnClickButtonListener (OnclickButtonListener o){
        this.onclickButtonListener = o;
        return this;
    }

    private void initEvent(){
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclickButtonListener!=null){
                    onclickButtonListener.onPositiveClick();
                }
            }
        });
        negativeBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclickButtonListener!=null){
                    onclickButtonListener.onNegativeClick();
                }
            }
        });
    }


    private String message,title,positive,negative;
    public commondialog setMessage(String message){
        this.message = message;
        return this;

    }
    public commondialog setTitle(String title){
        this.title = title;
        return this;
    }
    public commondialog setPositive(String positive){
        this.positive = positive;
        return this;
    }
    public commondialog setNegative(String negative){
        this.negative = negative;
        return this;
    }
    private void refreshView(){
        if (!TextUtils.isEmpty(title)){
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message)){
            messsageTv.setText(message);
            messsageTv.setVisibility(View.VISIBLE);
        }else {
            messsageTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(positive)){
            positiveBn.setText(positive);
            positiveBn.setVisibility(View.VISIBLE);
        }else {
            positiveBn.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(negative)){
            negativeBn.setText(negative);
            negativeBn.setVisibility(View.VISIBLE);
        }else {
           negativeBn.setVisibility(View.GONE);
        }
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }
}
