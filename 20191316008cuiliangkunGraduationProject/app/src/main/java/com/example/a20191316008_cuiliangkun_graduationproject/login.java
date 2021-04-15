package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

public class login extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText pwd;
    private Button loginbtn;
    private Button regbtn;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initEvent();
    }

    private void init() {
        username = (EditText)findViewById(R.id.login_username);
        pwd = (EditText)findViewById(R.id.login_pwd);
        loginbtn = (Button)findViewById(R.id.login_loginbtn);
        regbtn = (Button)findViewById(R.id.login_regbtn);
    }

    private void initEvent() {
        loginbtn.setOnClickListener(this);
        regbtn.setOnClickListener(this);
        sp = getSharedPreferences("data",MODE_PRIVATE);
        editor=sp.edit();
        pwd.setText(sp.getString("密码",""));
        username.setText(sp.getString("账号",""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_loginbtn:
                String account = username.getText().toString().trim();
                String password = pwd.getText().toString().trim();
                if(account==null||account.isEmpty()){
                    Toast.makeText(login.this,"账号不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password==null||password.isEmpty()){
                    Toast.makeText(login.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }



                UserDBHelper userDBHelper = new UserDBHelper(this,"userinfo",null,1);
                if(!userDBHelper.findThisUser(account)){
                    Toast.makeText(login.this,"未找到该用户！",Toast.LENGTH_LONG).show();
                }else if(!userDBHelper.UserLogin(account,password)){
                    Toast.makeText(login.this,"密码错误！",Toast.LENGTH_LONG).show();
                }else{
                    editor.putString("账号",account);
                    editor.putString("密码",password);
                    editor.commit();

                    Toast.makeText(login.this,"登录成功！",Toast.LENGTH_LONG).show();
                    Intent toUserpage = new Intent(login.this,MainActivity.class);
                    Bundle bundle = new Bundle();
//                bundle.putInt("hotelid",userid);
                    toUserpage.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                    login.this.startActivity(toUserpage);
                }




                break;
            case R.id.login_regbtn:
                Intent toRegist = new Intent(login.this,regist.class);

                login.this.startActivity(toRegist);
                break;
        }
    }
}
