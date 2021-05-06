package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.util.List;

public class searchresult extends AppCompatActivity {



    private ListView listView;
    HotelDBHelper hotelDBHelper = new HotelDBHelper(searchresult.this,"hotelinfo",null,1);
    List<Hotel> hotelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String region = bundle.getString("region");
        hotelList = hotelDBHelper.findHotelByRegion(region);

        listView = findViewById(R.id.searchresult_listview);
        listView.setAdapter(new MyBaseAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {          //listview点击事件

                Intent intent = new Intent(searchresult.this,hotelpage.class);
                Bundle bundle = new Bundle();
                Hotel intentHotel = new Hotel();
                Adapter adapter=parent.getAdapter();
                intentHotel = (Hotel)adapter.getItem(position);
                bundle.putString("hotelname",intentHotel.getName());
                intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                searchresult.this.startActivity(intent);

            }
        });
        setListViewHeightBasedOnChildren(listView);

    }


    public static Bitmap getPicFromBytes(byte[] bytes) {
        if (bytes != null)
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {       //动态设置listview高度
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);  // 获取item高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 最后再加上分割线的高度和padding高度，否则显示不完整。
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))+listView.getPaddingTop()+listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }

    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return hotelList.size();
        }

        @Override
        public Object getItem(int position) {
            return hotelList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(searchresult.this,R.layout.home_listitem,null);
            TextView list_price = convertView.findViewById(R.id.home_list_price);
            TextView list_detail = convertView.findViewById(R.id.home_list_detail);
            ImageView list_icon = convertView.findViewById(R.id.home_list_itemicon);
            TextView list_name = convertView.findViewById(R.id.home_list_name);
            Hotel temHotel = hotelList.get(position);//获取当前位置
//            list_detail.setText(details[position]);
            list_name.setText(temHotel.getName());

            String temHuxing[] = temHotel.getHuxing().split(",");
            String temDetail = temHotel.getType()+"·"+temHuxing[0]+"室"+temHuxing[1]+"厅"+"·"+temHotel.getStyle();    //设置简要细节
            list_detail.setText(temDetail);

            list_price.setText(temHotel.getPrice());

            byte[] ImageByte = temHotel.getPicture();
            Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
            ImageBitmap = zoomBitmap(ImageBitmap,1000,600);
            list_icon.setImageBitmap(ImageBitmap);
            return convertView;
        }
    }


    }

