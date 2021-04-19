package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class homepage extends Fragment implements View.OnClickListener  {
    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房",
            "南京路步行街 人民广场 外滩 大床房",
            "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //底部listview
    private String[] prices = {"628","455","575"};

    public View view;
    private ListView mlistView1;
    private ListView mlistView2;
    private GridView mGridView;
    private LinearLayout dom_search_Lin;
    private LinearLayout spec_search_Lin;       //搜索栏
    private Button button_dom;
    private Button button_spec;
    private dom_search_frag dom_search_frag;
    private spec_search_frag spec_search_frag;
    private List<Hotel> hotelList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_homepage,container,false);
//        hotelDBHelper.deleteHotel("1");
        HotelDBHelper hotelDBHelper = new HotelDBHelper(getActivity(),"hotelinfo",null,1);
        hotelList = hotelDBHelper.findAllHotel();

        if (view!=null){
            initView();
        }


        initsView();                         //搜索栏
        initEvent();
        selectTab(0);



        return view;
    }




    private void initView() {

        mlistView1 = (ListView) view.findViewById(R.id.home_list1);

                                         //页面加载时，就要放置adapter来显示菜单
            mlistView1.setAdapter(new MyBaseAdapter());


        mlistView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {          //listview点击事件

                Intent intent = new Intent(getActivity(),hotelpage.class);
                Bundle bundle = new Bundle();
                Hotel intentHotel = new Hotel();
                Adapter adapter=parent.getAdapter();
                intentHotel = (Hotel)adapter.getItem(position);
                bundle.putString("hotelname",intentHotel.getName());
                intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                getActivity().startActivity(intent);

            }
        });
        setListViewHeightBasedOnChildren(mlistView1);
    }



    public  void initsView(){
        dom_search_Lin = view.findViewById(R.id.home_searchdetail_Lin);
        spec_search_Lin=view.findViewById(R.id.home_spec_searchdetail_Lin); //搜索栏

        button_dom = view.findViewById(R.id.btn_home_dom);
        button_spec = view.findViewById(R.id.btn_home_spec);
   }
   public void initEvent(){
        button_dom.setOnClickListener(this);   //搜索栏点击事件初始化
        button_spec.setOnClickListener(this);

   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_dom:
                selectTab(0);
//                Toast.makeText(MainActivity.this,"123",Toast.LENGTH_SHORT).show();   //搜索栏点击事件
                break;
            case R.id.btn_home_spec:
                selectTab(1);
                break;
        }

    }
        private void selectTab(int i) {
           // 获取FragmentManager对象

        FragmentManager manager = getActivity().getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i){
            case 0:
                //选中后设置为白色
//                button_dom.setBackgroundColor(Color.parseColor("#fff"));
//                如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来（实例化自己写的dom__search_frag）
                if (dom_search_frag == null) {
                    dom_search_frag = new dom_search_frag();
                    transaction.add(R.id.home_searchdetail, dom_search_frag); //这里连接自己写的fragment和activity
                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(dom_search_frag);
                }
                break;
            case 1:

//                button_spec.setBackgroundColor(Color.parseColor("#fff"));
                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (spec_search_frag == null) {
                    spec_search_frag = new spec_search_frag();
                    transaction.add(R.id.home_spec_searchdetail, spec_search_frag);
                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(spec_search_frag);
                }
                break;
        }
        transaction.commit();
        }



    private void hideFragments(FragmentTransaction transaction) {
        if (dom_search_frag != null) {
            transaction.hide(dom_search_frag);
        }
        if (spec_search_frag != null) {
            transaction.hide(spec_search_frag);
        }
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
            convertView = View.inflate(getActivity(),R.layout.home_listitem,null);
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

//            list_icon.setImageResource(icons[position]);


            return convertView;
        }
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

    public static Bitmap getPicFromBytes(byte[] bytes) {
        if (bytes != null)
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
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




}


