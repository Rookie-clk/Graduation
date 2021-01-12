package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房，免费私家车接送迪士尼乐园8分钟，有餐厅可以点餐，有泳池、网红海洋球池",
    "南京路步行街 人民广场 外滩 大床房",
    "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};
    private String[] prices = {"￥628","￥455","￥575"};
    private ListView listView1 = findViewById(R.id.home_list1);
    private ListView listView2 = findViewById(R.id.home_list2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);
        MyBaseAdapter adapter = new MyBaseAdapter();
        listView1.setAdapter(adapter);
//        listView2.setAdapter(adapter);
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
            View view = View.inflate(MainActivity.this,R.layout.home_listitem,null);
            TextView list_price = view.findViewById(R.id.home_list_price);
            TextView list_detail = view.findViewById(R.id.home_list_detail);
            ImageView list_icon = view.findViewById(R.id.home_list_itemicon);
            list_detail.setText(details[position]);
            list_price.setText(prices[position]);
            list_icon.setBackgroundResource(icons[position]);

            return convertView;
        }
    }
}
