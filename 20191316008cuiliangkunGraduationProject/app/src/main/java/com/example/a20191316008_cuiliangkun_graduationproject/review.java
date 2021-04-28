package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Review;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.User;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.ReviewDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import java.util.List;

public class review extends AppCompatActivity implements View.OnClickListener {

    String intenthotelname;
    List<Hotel> hotelList;
    Hotel tmpHotel;
    Review tmpReview;
    HotelDBHelper hotelDBHelper = new HotelDBHelper(review.this,"hotelinfo",null,1);
    private TextView hotelname;
    private ImageButton weisheng1;
    private ImageButton weisheng2;
    private ImageButton weisheng3;
    private ImageButton weisheng4;
    private ImageButton weisheng5;
    private ImageButton huanjing1;
    private ImageButton huanjing2;
    private ImageButton huanjing3;
    private ImageButton huanjing4;
    private ImageButton huanjing5;
    private ImageButton fuwu1;
    private ImageButton fuwu2;
    private ImageButton fuwu3;
    private ImageButton fuwu4;
    private ImageButton fuwu5;
    private ImageButton sheshi1;
    private ImageButton sheshi2;
    private ImageButton sheshi3;
    private ImageButton sheshi4;
    private ImageButton sheshi5;
    private EditText edit;
    private Button submit;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    String weisheng_score = "0";
    String huanjing_score = "0";
    String fuwu_score = "0";
    String sheshi_score = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        init();
        getData();
    }

    private void init() {

        weisheng1 = findViewById(R.id.weisheng1);
        weisheng2 = findViewById(R.id.weisheng2);
        weisheng3 = findViewById(R.id.weisheng3);
        weisheng4 = findViewById(R.id.weisheng4);
        weisheng5 = findViewById(R.id.weisheng5);

        huanjing1 = findViewById(R.id.huanjing1);
        huanjing2 = findViewById(R.id.huanjing2);
        huanjing3 = findViewById(R.id.huanjing3);
        huanjing4 = findViewById(R.id.huanjing4);
        huanjing5 = findViewById(R.id.huanjing5);

        fuwu1 = findViewById(R.id.fuwu1);
        fuwu2 = findViewById(R.id.fuwu2);
        fuwu3 = findViewById(R.id.fuwu3);
        fuwu4 = findViewById(R.id.fuwu4);
        fuwu5 = findViewById(R.id.fuwu5);

        sheshi1 = findViewById(R.id.sheshi1);
        sheshi2 = findViewById(R.id.sheshi2);
        sheshi3 = findViewById(R.id.sheshi3);
        sheshi4 = findViewById(R.id.sheshi4);
        sheshi5 = findViewById(R.id.sheshi5);

        weisheng1.setOnClickListener(this);
        weisheng2.setOnClickListener(this);
        weisheng3.setOnClickListener(this);
        weisheng4.setOnClickListener(this);
        weisheng5.setOnClickListener(this);

        huanjing1.setOnClickListener(this);
        huanjing2.setOnClickListener(this);
        huanjing3.setOnClickListener(this);
        huanjing4.setOnClickListener(this);
        huanjing5.setOnClickListener(this);

        fuwu1.setOnClickListener(this);
        fuwu2.setOnClickListener(this);
        fuwu3.setOnClickListener(this);
        fuwu4.setOnClickListener(this);
        fuwu5.setOnClickListener(this);

        sheshi1.setOnClickListener(this);
        sheshi2.setOnClickListener(this);
        sheshi3.setOnClickListener(this);
        sheshi4.setOnClickListener(this);
        sheshi5.setOnClickListener(this);

        submit = findViewById(R.id.review_submit);
        submit.setOnClickListener(this);

        edit = findViewById(R.id.review_edit);
        hotelname = findViewById(R.id.review_hotelname);

    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        intenthotelname = bundle.getString("hotelname");   //取得主页传输的hotelname
        hotelList = hotelDBHelper.findHotelByName(intenthotelname);
        tmpHotel = hotelList.get(0);
        hotelname.setText(intenthotelname);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weisheng1:
                weisheng_score = "1";
                weisheng1.setImageResource(R.mipmap.favorite_filling_red);
                weisheng2.setImageResource(R.mipmap.favorite);
                weisheng3.setImageResource(R.mipmap.favorite);
                weisheng4.setImageResource(R.mipmap.favorite);
                weisheng5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.weisheng2:
                weisheng_score = "2";
                weisheng1.setImageResource(R.mipmap.favorite_filling_red);
                weisheng2.setImageResource(R.mipmap.favorite_filling_red);
                weisheng3.setImageResource(R.mipmap.favorite);
                weisheng4.setImageResource(R.mipmap.favorite);
                weisheng5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.weisheng3:
                weisheng_score = "3";
                weisheng1.setImageResource(R.mipmap.favorite_filling_red);
                weisheng2.setImageResource(R.mipmap.favorite_filling_red);
                weisheng3.setImageResource(R.mipmap.favorite_filling_red);
                weisheng4.setImageResource(R.mipmap.favorite);
                weisheng5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.weisheng4:
                weisheng_score = "4";
                weisheng1.setImageResource(R.mipmap.favorite_filling_red);
                weisheng2.setImageResource(R.mipmap.favorite_filling_red);
                weisheng3.setImageResource(R.mipmap.favorite_filling_red);
                weisheng4.setImageResource(R.mipmap.favorite_filling_red);
                weisheng5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.weisheng5:
                weisheng_score = "5";
                weisheng1.setImageResource(R.mipmap.favorite_filling_red);
                weisheng2.setImageResource(R.mipmap.favorite_filling_red);
                weisheng3.setImageResource(R.mipmap.favorite_filling_red);
                weisheng4.setImageResource(R.mipmap.favorite_filling_red);
                weisheng5.setImageResource(R.mipmap.favorite_filling_red);
                break;

            case R.id.huanjing1:
                huanjing_score = "1";
                huanjing1.setImageResource(R.mipmap.favorite_filling_red);
                huanjing2.setImageResource(R.mipmap.favorite);
                huanjing3.setImageResource(R.mipmap.favorite);
                huanjing4.setImageResource(R.mipmap.favorite);
                huanjing5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.huanjing2:
                huanjing_score = "2";
                huanjing1.setImageResource(R.mipmap.favorite_filling_red);
                huanjing2.setImageResource(R.mipmap.favorite_filling_red);
                huanjing3.setImageResource(R.mipmap.favorite);
                huanjing4.setImageResource(R.mipmap.favorite);
                huanjing5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.huanjing3:
                huanjing_score = "3";
                huanjing1.setImageResource(R.mipmap.favorite_filling_red);
                huanjing2.setImageResource(R.mipmap.favorite_filling_red);
                huanjing3.setImageResource(R.mipmap.favorite_filling_red);
                huanjing4.setImageResource(R.mipmap.favorite);
                huanjing5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.huanjing4:
                huanjing_score = "4";
                huanjing1.setImageResource(R.mipmap.favorite_filling_red);
                huanjing2.setImageResource(R.mipmap.favorite_filling_red);
                huanjing3.setImageResource(R.mipmap.favorite_filling_red);
                huanjing4.setImageResource(R.mipmap.favorite_filling_red);
                huanjing5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.huanjing5:
                huanjing_score = "5";
                huanjing1.setImageResource(R.mipmap.favorite_filling_red);
                huanjing2.setImageResource(R.mipmap.favorite_filling_red);
                huanjing3.setImageResource(R.mipmap.favorite_filling_red);
                huanjing4.setImageResource(R.mipmap.favorite_filling_red);
                huanjing5.setImageResource(R.mipmap.favorite_filling_red);
                break;

            case R.id.fuwu1:
                fuwu_score = "1";
                fuwu1.setImageResource(R.mipmap.favorite_filling_red);
                fuwu2.setImageResource(R.mipmap.favorite);
                fuwu3.setImageResource(R.mipmap.favorite);
                fuwu4.setImageResource(R.mipmap.favorite);
                fuwu5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.fuwu2:
                fuwu_score = "2";
                fuwu1.setImageResource(R.mipmap.favorite_filling_red);
                fuwu2.setImageResource(R.mipmap.favorite_filling_red);
                fuwu3.setImageResource(R.mipmap.favorite);
                fuwu4.setImageResource(R.mipmap.favorite);
                fuwu5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.fuwu3:
                fuwu_score = "3";
                fuwu1.setImageResource(R.mipmap.favorite_filling_red);
                fuwu2.setImageResource(R.mipmap.favorite_filling_red);
                fuwu3.setImageResource(R.mipmap.favorite_filling_red);
                fuwu4.setImageResource(R.mipmap.favorite);
                fuwu5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.fuwu4:
                fuwu_score = "4";
                fuwu1.setImageResource(R.mipmap.favorite_filling_red);
                fuwu2.setImageResource(R.mipmap.favorite_filling_red);
                fuwu3.setImageResource(R.mipmap.favorite_filling_red);
                fuwu4.setImageResource(R.mipmap.favorite_filling_red);
                fuwu5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.fuwu5:
                fuwu_score = "5";
                fuwu1.setImageResource(R.mipmap.favorite_filling_red);
                fuwu2.setImageResource(R.mipmap.favorite_filling_red);
                fuwu3.setImageResource(R.mipmap.favorite_filling_red);
                fuwu4.setImageResource(R.mipmap.favorite_filling_red);
                fuwu5.setImageResource(R.mipmap.favorite_filling_red);
                break;

            case R.id.sheshi1:
                sheshi_score = "1";
                sheshi1.setImageResource(R.mipmap.favorite_filling_red);
                sheshi2.setImageResource(R.mipmap.favorite);;
                sheshi3.setImageResource(R.mipmap.favorite);
                sheshi4.setImageResource(R.mipmap.favorite);;
                sheshi5.setImageResource(R.mipmap.favorite);;
                break;
            case R.id.sheshi2:
                sheshi_score = "2";
                sheshi1.setImageResource(R.mipmap.favorite_filling_red);
                sheshi2.setImageResource(R.mipmap.favorite_filling_red);
                sheshi3.setImageResource(R.mipmap.favorite);
                sheshi4.setImageResource(R.mipmap.favorite);
                sheshi5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.sheshi3:
                sheshi_score = "3";
                sheshi1.setImageResource(R.mipmap.favorite_filling_red);
                sheshi2.setImageResource(R.mipmap.favorite_filling_red);
                sheshi3.setImageResource(R.mipmap.favorite_filling_red);
                sheshi4.setImageResource(R.mipmap.favorite);
                sheshi5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.sheshi4:
                sheshi_score = "4";
                sheshi1.setImageResource(R.mipmap.favorite_filling_red);
                sheshi2.setImageResource(R.mipmap.favorite_filling_red);
                sheshi3.setImageResource(R.mipmap.favorite_filling_red);
                sheshi4.setImageResource(R.mipmap.favorite_filling_red);
                sheshi5.setImageResource(R.mipmap.favorite);
                break;
            case R.id.sheshi5:
                sheshi_score = "5";
                sheshi1.setImageResource(R.mipmap.favorite_filling_red);
                sheshi2.setImageResource(R.mipmap.favorite_filling_red);
                sheshi3.setImageResource(R.mipmap.favorite_filling_red);
                sheshi4.setImageResource(R.mipmap.favorite_filling_red);
                sheshi5.setImageResource(R.mipmap.favorite_filling_red);
                break;

            case R.id.review_submit:
                String text = edit.getText().toString().trim();
                if(text == ""|| text.equals("")){
                    Toast.makeText(review.this,"请填写详细的评论！",Toast.LENGTH_LONG).show();
                    return;
                }
                ReviewDBHelper reviewDBHelper = new ReviewDBHelper(review.this,"reviewinfo",null,1);
                sp = review.this.getSharedPreferences("data",MODE_PRIVATE);
                editor=sp.edit();
                UserDBHelper userDBHelper = new UserDBHelper(review.this,"userinfo",null,1);
                int uid = userDBHelper.UserID(sp.getString("账号",""));
                String total = (Integer.parseInt(weisheng_score) + Integer.parseInt(huanjing_score) + Integer.parseInt(fuwu_score) + Integer.parseInt(sheshi_score))/4+"";
                reviewDBHelper.insertReview(tmpHotel.getId(),uid,total,weisheng_score,huanjing_score,fuwu_score,sheshi_score,text);
                Toast.makeText(review.this,"点评成功！",Toast.LENGTH_LONG).show();

                Intent toUserPage = new Intent(review.this,MainActivity.class);
                Bundle tab = new Bundle();
                tab.putString("tab","1");
                toUserPage.putExtras(tab);
                review.this.startActivity(toUserPage);

                break;
        }

    }
}
