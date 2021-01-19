package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class searchresult extends AppCompatActivity implements View.OnClickListener {

    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房",
            "南京路步行街 人民广场 外滩 大床房",
            "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //底部listview
    private String[] prices = {"￥628","￥455","￥575"};

    private ListView listView;
    private Button detailbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        listView = findViewById(R.id.searchresult_listview);
        listView.setAdapter(new SearchBaseAdapter());

    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()){
//            case R.id.searchresult_btn:
//                int hotelid = 0;
//                Intent intent = new Intent(searchresult.this,hotelpage.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("hotelid",hotelid);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                Toast.makeText(searchresult.this,"123",Toast.LENGTH_SHORT).show();
//                break;
//        }
    }


    private class SearchBaseAdapter extends BaseAdapter{

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
            View  view = View.inflate(searchresult.this,R.layout.hotel_item,null);
            ImageView icon = view.findViewById(R.id.searchresult_icon);
           TextView hotelname = view.findViewById(R.id.searchresult_name);
            TextView price = view.findViewById(R.id.searchresult_price);
            Button detailbtn = view.findViewById(R.id.searchresult_btn);
            detailbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hotelid = 0;
                Intent intent = new Intent(searchresult.this,hotelpage.class);
                Bundle bundle = new Bundle();
                bundle.putInt("hotelid",hotelid);               //搜索结果页跳转至详情页
                intent.putExtras(bundle);
                startActivity(intent);1
                }
            });
            hotelname.setText(details[position]);
            price.setText(prices[position]);
            icon.setImageResource(icons[position]);

            return view;
        }


    }
}
