package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class homepage extends Fragment implements View.OnClickListener {
    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房，免费私家车接送迪士尼乐园8分钟，有餐厅可以点餐，有泳池、网红海洋球池",
            "南京路步行街 人民广场 外滩 大床房",
            "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //底部listview
    private String[] prices = {"￥628","￥455","￥575"};


    public View view;
    private ListView mlistView1;
    private ListView mlistView2;
    private LinearLayout dom_search_Lin;
    private LinearLayout spec_search_Lin;       //搜索栏
    private Button button_dom;
    private Button button_spec;
    private dom_search_frag dom_search_frag;
    private spec_search_frag spec_search_frag;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_homepage,container,false);

        if (view!=null){
            initView();
        }


        initsView();                         //搜索栏
        initEvent();
        selectTab(0);

        mlistView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {          //listview点击事件

                        Intent intent = new Intent(getActivity(),hotelpage.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("hotelid",position);
                        intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                        getActivity().startActivity(intent);

            }
        });

        return view;
    }


    private void initView() {
        mlistView1 = (ListView) view.findViewById(R.id.home_list1);
        mlistView2 = (ListView) view.findViewById(R.id.home_list2);
        if (icons != null) {                                      //页面加载时，就要放置adapter来显示菜单
            mlistView1.setAdapter(new MyBaseAdapter());
            mlistView2.setAdapter(new MyBaseAdapter());
        }
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
            return details.length;
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

            convertView = View.inflate(getActivity(),R.layout.home_listitem,null);

            TextView list_price = convertView.findViewById(R.id.home_list_price);
            TextView list_detail = convertView.findViewById(R.id.home_list_detail);
            ImageView list_icon = convertView.findViewById(R.id.home_list_itemicon);
            list_detail.setText(details[position]);
            list_price.setText(prices[position]);
            list_icon.setImageResource(icons[position]);

            return convertView;
        }
    }





}


