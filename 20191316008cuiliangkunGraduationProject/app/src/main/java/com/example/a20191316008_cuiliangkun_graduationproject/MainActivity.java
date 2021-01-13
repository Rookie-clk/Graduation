package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房，免费私家车接送迪士尼乐园8分钟，有餐厅可以点餐，有泳池、网红海洋球池",
    "南京路步行街 人民广场 外滩 大床房",
    "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //底部listview
    private String[] prices = {"￥628","￥455","￥575"};


    private LinearLayout dom_search_Lin;
    private LinearLayout spec_search_Lin;       //搜索栏
    private Button button_dom;
    private Button button_spec;
    private dom_search_frag dom_search_frag;
    private spec_search_frag spec_search_frag;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);
        ListView listView1 = findViewById(R.id.home_list1);
        ListView listView2 = findViewById(R.id.home_list2);
        MyBaseAdapter adapter = new MyBaseAdapter();
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);          //底部listView


        initView();                         //搜索栏
        initEvent();
        selectTab(0);
    }
   public  void initView(){
        dom_search_Lin = findViewById(R.id.home_searchdetail_Lin);
        spec_search_Lin=findViewById(R.id.home_spec_searchdetail_Lin);

        button_dom = findViewById(R.id.btn_home_dom);
        button_spec = findViewById(R.id.btn_home_spec);
   }
   public void initEvent(){
        button_spec.setOnClickListener(this);
        button_dom.setOnClickListener(this);
   }

    @Override
    public void onClick(View v) {
//        resetImgs();
        switch (v.getId()){
            case R.id.btn_home_dom:
                selectTab(0);
//                Toast.makeText(MainActivity.this,"123",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_home_spec:
                selectTab(1);
                break;
        }

    }

    private void selectTab(int i) {
        //获取FragmentManager对象

        FragmentManager manager = getSupportFragmentManager();
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

//    private void resetImgs() {
//        button_dom.setBackgroundColor(Color.parseColor("#fff"));
//        button_spec.setBackgroundColor(Color.parseColor("#B3000000"));          //重新设置搜索栏颜色
//    }


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
            View view = View.inflate(MainActivity.this,R.layout.home_listitem,null);

            TextView list_price = view.findViewById(R.id.home_list_price);
            TextView list_detail = view.findViewById(R.id.home_list_detail);
            ImageView list_icon = view.findViewById(R.id.home_list_itemicon);
            list_detail.setText(details[position]);
            list_price.setText(prices[position]);
            list_icon.setImageResource(icons[position]);

            return view;
        }
    }
}
