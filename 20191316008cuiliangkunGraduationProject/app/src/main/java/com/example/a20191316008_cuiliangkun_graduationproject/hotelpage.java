package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class hotelpage extends AppCompatActivity implements View.OnClickListener {



    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房，免费私家车接送迪士尼乐园8分钟，有餐厅可以点餐，有泳池、网红海洋球池",
            "南京路步行街 人民广场 外滩 大床房",
            "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //listview
    private String[] prices = {"￥628","￥455","￥575"};
    private ImageButton btn_back ;
    private  ImageButton btn_love;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelpage);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int hotelid = bundle.getInt("hotelid");   //取得主页传输的hotelid


        btn_back = findViewById(R.id.hotelpage_back);
        btn_love = findViewById(R.id.hotelpage_love);

        btn_back.setOnClickListener(this);
        btn_love.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hotelpage_love:

                if(count%2==0) {
                    btn_love.setBackgroundResource(R.mipmap.favorite_filling_red); //收藏
                    count++;
                }else{
                    btn_love.setBackgroundResource(R.mipmap.favorite);  //取消收藏
                    count++;
                }
                break;
            case R.id.hotelpage_back:
                super.onBackPressed();
                break;
        }
    }


    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public Object getItem(int position) {
            return icons[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
