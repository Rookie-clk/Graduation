package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

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

    private Button submit;
    private TextView tes;
    private ImageButton upload;
    private Button leixing;
    private EditText price;

    private EditText mianji;
    private EditText address;
    private Button fengge;
    private EditText shi;
    private EditText ting;
    private EditText name;
    int userid;
    String basic = "";
    String tiexinsheshi ="";
    String region = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbo);
        init();
        initEvent();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         userid = bundle.getInt("userid");   //取得主页传输的hotelid


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1){
            if (data!=null){
                Uri uri=data.getData();
                upload.setImageURI(uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initEvent() {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        leixing.setOnClickListener(new View.OnClickListener() {
            CharSequence[] items = {"住宅公寓","酒店式公寓","Loft复式","客栈"};
            int selected=0;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bulider = new AlertDialog.Builder(wbo.this);

                bulider.setIcon(R.mipmap.ic_launcher)
                        .setTitle("请选择你的房屋类型！")
                        .setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                                selected = which;
                            }
                        })
//                       .setMultiChoiceItems(items, check, new DialogInterface.OnMultiChoiceClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                               check[which]=isChecked;
//                           }
//                       })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                                leixing.setText(items[selected]);
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = bulider.create();
                dialog.show();
            }
        });


        fengge.setOnClickListener(new View.OnClickListener() {
            CharSequence[] items = {"北欧风","中式风","日式风","异域风情"};
            int selected=0;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bulider = new AlertDialog.Builder(wbo.this);

                bulider.setIcon(R.mipmap.ic_launcher)
                        .setTitle("请选择你的房屋风格！")
                        .setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                                selected = which;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                                fengge.setText(items[selected]);
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = bulider.create();
                dialog.show();
            }
        });

        windowgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.windowy){
                    basic += "窗户,";

                }else if (i == R.id.windown){
//                    if(basic.contains("窗户,")){
                        basic.replace("窗户,","");
//                    }
                }
            }
        });
        bathgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bathy){
                    basic += "热水澡,";
                }else if (checkedId == R.id.bathn){
//                    if(basic.contains("热水澡,")){
                        basic.replace("热水澡,","");
//                    }
                }
            }
        });
       tvgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tvy){
                    basic += "电视,";
                }else if (checkedId == R.id.tvn){
//                    if(basic.contains("电视,")){
                        basic.replace("电视,","");
//                    }

                }
            }
        });
        conditionergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.conditionery){
                    basic += "空调,";
                }else if (checkedId == R.id.conditionern){
//                    if(basic.contains("空调,")){
                        basic.replace("空调,","");
//                    }

                }
            }
        });
        lockgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.locky){
                    basic += "电子门锁,";
                }else if (checkedId == R.id.lockn){
//                    if(basic.contains("电子门锁,")){
                        basic.replace("电子门锁,","");
//                    }

                }
            }
        });
        wifigroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.wifiy){
                    basic += "无线网络,";
                }else if (checkedId == R.id.wifin){
//                    if(basic.contains("无线网络,")){
                        basic.replace("无线网络,","");
//                    }

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
                String sname = name.getText().toString().trim();
                String saddress = address.getText().toString().trim();
                String sprice = price.getText().toString().trim();
                String sleixing = leixing.getText().toString().trim();
                String smianji = mianji.getText().toString().trim();
                String sfengge = fengge.getText().toString().trim();
                String sshi = shi.getText().toString().trim();
                String sting = ting.getText().toString().trim();
                if(sname==""||sname.isEmpty()||saddress==""||saddress.isEmpty()||sprice==""||sprice.isEmpty()){
                    Toast.makeText(wbo.this,"请输入基本民宿信息！",Toast.LENGTH_LONG).show();
                    return;
                }

                if(basic!=""){
                    basic = basic.substring(0,basic.length()-1);
                }
                if(tiexinsheshi!=""){
                    tiexinsheshi = tiexinsheshi.substring(0,tiexinsheshi.length()-1);
                }

                BitmapDrawable bd=(BitmapDrawable)upload.getDrawable();   //把图片转成bitmap格式
                Bitmap bitmap=bd.getBitmap();
                ByteArrayOutputStream os=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,os);  //图片进行压缩
                byte[] bytes=os.toByteArray();  //把输出流转成二进制数组
                HotelDBHelper hotelDBHelper = new HotelDBHelper(wbo.this,"hotelinfo",null,1);
                hotelDBHelper.insertHotel(sname,sprice,region,saddress,sleixing,sshi+","+sting,smianji,sfengge,bytes,basic,tiexinsheshi,userid);

                Toast.makeText(wbo.this,"发布成功！",Toast.LENGTH_LONG).show();
                Toast.makeText(wbo.this,tiexinsheshi,Toast.LENGTH_LONG).show();
                Toast.makeText(wbo.this,basic,Toast.LENGTH_LONG).show();
//                Intent submit = new Intent(wbo.this,userpage.class);
//                Bundle submitbundle = new Bundle();
//                submitbundle.putInt("hotelid",1);
//                submit.putExtras(submitbundle);
//                wbo.this.startActivity(submit);

            }
        });
        List<String> list = new ArrayList<String>();
        list.add("上海");
        list.add("北京");
        list.add("广州");
        list.add("深圳");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        Spinner sp = (Spinner) findViewById(R.id.spinner1);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvResult = (TextView) findViewById(R.id.tvResult);
//获取Spinner控件的适配器
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                tvResult.setText(adapter.getItem(position));
                region = adapter.getItem(position);
            }
            //没有选中时的处理
            public void onNothingSelected(AdapterView<?> parent) {
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

        leixing = (Button)findViewById(R.id.home_leixing);
        fengge = (Button)findViewById(R.id.home_fengge);
        mianji = (EditText) findViewById(R.id.mianji);
        address = (EditText)findViewById(R.id.home_address);
        shi = (EditText)findViewById(R.id.shi);
        ting = (EditText)findViewById(R.id.ting);
        name = (EditText)findViewById(R.id.home_name);
        price = (EditText)findViewById(R.id.home_price);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        String text = buttonView.getText().toString();
        if (isChecked) {

                if(!tiexinsheshi.contains(text)) {
                    tiexinsheshi += text+",";
//                    Toast.makeText(wbo.this,tiexinsheshi,Toast.LENGTH_LONG).show();

                }
        }else {
            if(tiexinsheshi.contains(text)) {
                tiexinsheshi = tiexinsheshi.replace(text+",", "");
//                Toast.makeText(wbo.this,tiexinsheshi,Toast.LENGTH_LONG).show();

            }
        }
    }
}
