package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class wbo extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private RadioGroup windowgroup;
    private RadioButton windowy;
    private RadioButton windown;
    private RadioGroup bathgroup;
    private RadioButton bathy;
    private RadioButton bathn;
    private RadioGroup conditionergroup;
    private RadioButton conditionery;
    private RadioButton conditionern;
    private RadioGroup tvgroup;
    private RadioButton tvy;
    private RadioButton tvn;
    private RadioGroup lockgroup;
    private RadioButton locky;
    private RadioButton lockn;
    private RadioGroup wifigroup;
    private RadioButton wifiy;
    private RadioButton wifin;
    private CheckBox nuanqi;
    private CheckBox jiashiqi;
    private CheckBox chaji;
    private CheckBox shafa;
    private CheckBox garden;
    private CheckBox pool;
    private CheckBox pingpang;
    private CheckBox spa;
    private String tiexinsheshi = new String();
    private Button submit;
    private TextView tes;
    private ImageButton upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbo);
        init();
        initEvent();
    }

    private void initEvent() {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(wbo.this,"ImageButton被点击了",Toast.LENGTH_SHORT).show();
               upload.setImageDrawable(getResources().getDrawable(R.drawable.pool));
            }
        });
        windowgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.windowy){
                    Toast.makeText(wbo.this,"yes",Toast.LENGTH_LONG).show();
                }else if (i == R.id.windown){
                    Toast.makeText(wbo.this,"no",Toast.LENGTH_LONG).show();
                }
            }
        });
        bathgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bathy){
                    Toast.makeText(wbo.this,"yes",Toast.LENGTH_LONG).show();
                }else if (checkedId == R.id.bathn){
                    Toast.makeText(wbo.this,"no",Toast.LENGTH_LONG).show();
                }
            }
        });
       tvgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tvy){
                    Toast.makeText(wbo.this,"yes",Toast.LENGTH_LONG).show();
                }else if (checkedId == R.id.tvn){
                    Toast.makeText(wbo.this,"no",Toast.LENGTH_LONG).show();
                }
            }
        });
        conditionergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.conditionery){
                    Toast.makeText(wbo.this,"yes",Toast.LENGTH_LONG).show();
                }else if (checkedId == R.id.conditionern){
                    Toast.makeText(wbo.this,"no",Toast.LENGTH_LONG).show();
                }
            }
        });
        lockgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.locky){
                    Toast.makeText(wbo.this,"yes",Toast.LENGTH_LONG).show();
                }else if (checkedId == R.id.lockn){
                    Toast.makeText(wbo.this,"no",Toast.LENGTH_LONG).show();
                }
            }
        });
        wifigroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.wifiy){
                    Toast.makeText(wbo.this,"yes",Toast.LENGTH_LONG).show();
                }else if (checkedId == R.id.wifin){
                    Toast.makeText(wbo.this,"no",Toast.LENGTH_LONG).show();
                }
            }
        });

        nuanqi.setOnCheckedChangeListener(this);
        jiashiqi.setOnCheckedChangeListener(this);
        chaji.setOnCheckedChangeListener(this);
        shafa.setOnCheckedChangeListener(this);
        garden.setOnCheckedChangeListener(this);
        pingpang.setOnCheckedChangeListener(this);
        pool.setOnCheckedChangeListener(this);
        spa.setOnCheckedChangeListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submit = new Intent(wbo.this,userpage.class);
                Bundle submitbundle = new Bundle();
                submitbundle.putInt("hotelid",1);
                submit.putExtras(submitbundle);                   //传输给hotelpage的id，并跳转
                wbo.this.startActivity(submit);

            }
        });
    }

    private void init() {
        windowgroup = (RadioGroup)findViewById(R.id.radioGroupwindow);
        windowy = (RadioButton)findViewById(R.id.windowy);
        windown = (RadioButton)findViewById(R.id.windown);

        bathgroup = (RadioGroup)findViewById(R.id.radioGroupbath);
        bathy = (RadioButton)findViewById(R.id.bathy);
        bathn = (RadioButton)findViewById(R.id.bathn);

        conditionergroup = (RadioGroup)findViewById(R.id.radioGroupconditioner);
        conditionery = (RadioButton)findViewById(R.id.conditionery);
        conditionern = (RadioButton)findViewById(R.id.conditionern);

        tvgroup = (RadioGroup)findViewById(R.id.radioGrouptv);
        tvy = (RadioButton)findViewById(R.id.tvy);
        tvn = (RadioButton)findViewById(R.id.tvn);

        lockgroup = (RadioGroup)findViewById(R.id.radioGrouplock);
        locky = (RadioButton)findViewById(R.id.locky);
        lockn = (RadioButton)findViewById(R.id.lockn);

        wifigroup = (RadioGroup)findViewById(R.id.radioGroupwifi);
        wifiy = (RadioButton)findViewById(R.id.wifiy);
        wifin = (RadioButton)findViewById(R.id.wifin);

        nuanqi=(CheckBox)findViewById(R.id.nuanqi);
        jiashiqi=(CheckBox)findViewById(R.id.jiashiqi);
        chaji=(CheckBox)findViewById(R.id.chaji);
        shafa=(CheckBox)findViewById(R.id.shafa);
        garden=(CheckBox)findViewById(R.id.garden);
        pool=(CheckBox)findViewById(R.id.pool);
        pingpang=(CheckBox)findViewById(R.id.pingpang);
        spa=(CheckBox)findViewById(R.id.spa);

        submit = (Button)findViewById(R.id.homeinfo_submit);

        upload = (ImageButton)findViewById(R.id.image_upload);
        tes = findViewById(R.id.tes);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        String text = buttonView.getText().toString();
        if (isChecked) {

                if(!tiexinsheshi.contains(text)) {
                    tiexinsheshi = tiexinsheshi + text;
                    tes.setText(tiexinsheshi);
                }
        }else {
            if(tiexinsheshi.contains(text)) {
                tiexinsheshi = tiexinsheshi.replace(text, "");
                tes.setText(tiexinsheshi);
            }
        }
    }
}
