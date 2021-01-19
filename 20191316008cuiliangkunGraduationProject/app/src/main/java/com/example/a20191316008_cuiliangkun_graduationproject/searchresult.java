package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class searchresult extends AppCompatActivity {

    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房",
            "南京路步行街 人民广场 外滩 大床房",
            "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //底部listview
    private String[] prices = {"￥628","￥455","￥575"};

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
//        listView = findViewById(R.id.searchresult_listview);
//        listView.setAdapter(new SearchBaseAdapter());
    }


//    private class SearchBaseAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return icons.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return icons[position];
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View  view = View.inflate(searchresult.this,R.layout.hotel_item,null);
//
//            ImageView icon = findViewById(R.id.searchresult_icon);
//           TextView hotelname = findViewById(R.id.searchresult_name);
//            TextView price = findViewById(R.id.searchresult_price);
//
//            hotelname.setText(details[position]);
//            price.setText(prices[position]);
//            icon.setImageResource(icons[position]);
//
//            return view;
//        }
//    }
}
