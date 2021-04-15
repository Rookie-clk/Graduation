package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import java.io.ByteArrayOutputStream;

public class regist extends AppCompatActivity implements View.OnClickListener {
    private Button registbtn;
    private Button loginbtn;
    private EditText username;
    private EditText pwd;
    private EditText confirm;
    private ImageButton avartor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
        initEvent();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1){
            if (data!=null){
                Uri uri=data.getData();
                avartor.setImageURI(uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initEvent() {
        registbtn.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
        avartor.setOnClickListener(this);
    }

    private void init() {
        registbtn = (Button)findViewById(R.id.regist_registbtn);
        loginbtn = (Button)findViewById(R.id.regist_logbtn);
        username = (EditText)findViewById(R.id.regist_username);
        pwd = (EditText)findViewById(R.id.regist_pwd);
        confirm = (EditText)findViewById(R.id.regist_confirm);
        avartor= (ImageButton)findViewById(R.id.regist_avartor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regist_registbtn:
                String account = username.getText().toString().trim();
                String password = pwd.getText().toString().trim();
                String con = confirm.getText().toString().trim();
                if(account==null||account.isEmpty()){
                    Toast.makeText(regist.this,"账号不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password==null||password.isEmpty()){
                    Toast.makeText(regist.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(con==null||con.isEmpty()){
                    Toast.makeText(regist.this,"请确认你的密码！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(con)){
                    Toast.makeText(regist.this,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDBHelper userDBHelper = new UserDBHelper(this,"userinfo",null,1);
                BitmapDrawable bd=(BitmapDrawable)avartor.getDrawable();   //把图片转成bitmap格式
                Bitmap bitmap=bd.getBitmap();
                ByteArrayOutputStream os=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,os);  //图片进行压缩
                byte[] bytes=os.toByteArray();  //把输出流转成二进制数组
//                cd.insertCommodity(cname,cprice,ctype,cdescribe,cphone,bytes,ud.uid);
                userDBHelper.insertUser(account,password,bytes); //添加用户信息到数据表
                Toast.makeText(regist.this, "注册成功", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(regist.this,login.class));
                finish();

                break;
            case R.id.regist_logbtn:
                break;
            case R.id.regist_avartor:
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                break;
        }

    }
}
