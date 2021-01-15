package com.example.a20191316008_cuiliangkun_graduationproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class homepage extends Fragment {
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
        view = inflater.inflate(R.layout.homepage,container,false);

        if (view!=null){
            initView();
        }

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
