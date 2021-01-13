package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

//    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
//    private String[] details = {"原木双1.8米大床浴缸房，免费私家车接送迪士尼乐园8分钟，有餐厅可以点餐，有泳池、网红海洋球池",
//    "南京路步行街 人民广场 外滩 大床房",
//    "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //底部listview
//    private String[] prices = {"￥628","￥455","￥575"};
//
//
//    private LinearLayout dom_search_Lin;
//    private LinearLayout spec_search_Lin;       //搜索栏
//    private Button button_dom;
//    private Button button_spec;
//    private dom_search_frag dom_search_frag;
//    private spec_search_frag spec_search_frag;







//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setTheme(R.style.AppTheme);
//        ListView listView1 = findViewById(R.id.home_list1);
//        ListView listView2 = findViewById(R.id.home_list2);
//        MyBaseAdapter adapter = new MyBaseAdapter();
//        listView1.setAdapter(adapter);
//        listView2.setAdapter(adapter);          //底部listView
//
//
//        initView();                         //搜索栏
//        initEvent();
//        selectTab(0);
//    }
//   public  void initView(){
//        dom_search_Lin = findViewById(R.id.home_searchdetail_Lin);
//        spec_search_Lin=findViewById(R.id.home_spec_searchdetail_Lin);
//
//        button_dom = findViewById(R.id.btn_home_dom);
//        button_spec = findViewById(R.id.btn_home_spec);
//   }
//   public void initEvent(){
//        button_spec.setOnClickListener(this);
//        button_dom.setOnClickListener(this);
//   }
//
//    @Override
//    public void onClick(View v) {
//        resetImgs();
//        switch (v.getId()){
//            case R.id.btn_home_dom:
//                selectTab(0);
////                Toast.makeText(MainActivity.this,"123",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btn_home_spec:
//                selectTab(1);
//                break;
//        }
//
//    }
//
//    private void selectTab(int i) {
//        //获取FragmentManager对象
//
//        FragmentManager manager = getSupportFragmentManager();
//        //获取FragmentTransaction对象
//        FragmentTransaction transaction = manager.beginTransaction();
//        //先隐藏所有的Fragment
//        hideFragments(transaction);
//        switch (i){
//            case 0:
//                //选中后设置为白色
////                button_dom.setBackgroundColor(Color.parseColor("#fff"));
////                如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来（实例化自己写的dom__search_frag）
//                if (dom_search_frag == null) {
//                    dom_search_frag = new dom_search_frag();
//                    transaction.add(R.id.home_searchdetail, dom_search_frag); //这里连接自己写的fragment和activity
//                } else {
//                    //如果第一页对应的Fragment已经实例化，则直接显示出来
//                    transaction.show(dom_search_frag);
//                }
//                break;
//            case 1:
//
////                button_spec.setBackgroundColor(Color.parseColor("#fff"));
//                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
//                if (spec_search_frag == null) {
//                    spec_search_frag = new spec_search_frag();
//                    transaction.add(R.id.home_spec_searchdetail, spec_search_frag);
//                } else {
//                    //如果第一页对应的Fragment已经实例化，则直接显示出来
//                    transaction.show(spec_search_frag);
//                }
//                break;
//        }
//        transaction.commit();
//    }
//
//    private void hideFragments(FragmentTransaction transaction) {
//        if (dom_search_frag != null) {
//            transaction.hide(dom_search_frag);
//        }
//        if (spec_search_frag != null) {
//            transaction.hide(spec_search_frag);
//        }
//    }
//
//    private void resetImgs() {
////        button_spec.getBackground().setColorFilter(0xbbbbbb, PorterDuff.Mode.MULTIPLY);
////        button_dom.setBackgroundColor(Color.parseColor("#fff"));
////        button_spec.setBackgroundColor(Color.parseColor("#B3000000"));          //重新设置搜索栏颜色
//    }
//
//
//    class MyBaseAdapter extends BaseAdapter{
//
//        @Override
//        public int getCount() {
//            return details.length;
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
//            View view = View.inflate(MainActivity.this,R.layout.home_listitem,null);
//
//            TextView list_price = view.findViewById(R.id.home_list_price);
//            TextView list_detail = view.findViewById(R.id.home_list_detail);
//            ImageView list_icon = view.findViewById(R.id.home_list_itemicon);
//            list_detail.setText(details[position]);
//            list_price.setText(prices[position]);
//            list_icon.setImageResource(icons[position]);
//
//            return view;
//        }











    //声明四个Tab的布局文件
    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;
    private LinearLayout mTab4;

    //声明四个Tab的ImageButton
    private ImageButton mImg1;
    private ImageButton mImg2;
    private ImageButton mImg3;
    private ImageButton mImg4;

    //声明四个Tab分别对应的Fragment
    private Fragment mFrag1;
    private Fragment mFrag2;
    private Fragment mFrag3;
    private Fragment mFrag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();//初始化控件
        initEvents();//初始化事件
        selectTab(0);//默认选中第一个Tab
    }

    private void initEvents() {
        //初始化四个Tab的点击事件
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
    }

    private void initViews() {
        //初始化四个Tab的布局文件
        mTab1 = (LinearLayout) findViewById(R.id.id_tab1);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab2);
        mTab3 = (LinearLayout) findViewById(R.id.id_tab3);
        mTab4 = (LinearLayout) findViewById(R.id.id_tab4);

        //初始化四个ImageButton
        mImg1 = (ImageButton) findViewById(R.id.id_tab_img1);
        mImg2 = (ImageButton) findViewById(R.id.id_tab_img2);
        mImg3 = (ImageButton) findViewById(R.id.id_tab_img3);
        mImg4 = (ImageButton) findViewById(R.id.id_tab_img4);
    }

    //处理Tab的点击事件
    @Override
    public void onClick(View v) {
        resetImgs(); //先将四个ImageButton置为灰色
        switch (v.getId()) {
            case R.id.id_tab1:
                selectTab(0);
                break;
            case R.id.id_tab2:
                selectTab(1);
                break;
            case R.id.id_tab3:
                selectTab(2);
                break;
            case R.id.id_tab4:
                selectTab(3);
                break;
        }

    }

    //进行选中Tab的处理
    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            //当选中点击的是第一页的Tab时
            case 0:
                //设置第一页的ImageButton为绿色
                mImg1.setImageResource(R.mipmap.home_filling);
                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFrag1 == null) {
                    mFrag1 = new homepage();
                    transaction.add(R.id.id_content, mFrag1);
                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFrag1);
                }
                break;
            case 1:
                mImg2.setImageResource(R.mipmap.favorite_filling);
                if (mFrag2 == null) {
                    mFrag2 = new homepage();
                    transaction.add(R.id.id_content, mFrag2);
                } else {
                    transaction.show(mFrag2);
                }
                break;
            case 2:
                mImg3.setImageResource(R.mipmap.comment_filling);
                if (mFrag3 == null) {
                    mFrag3 = new homepage();
                    transaction.add(R.id.id_content, mFrag3);
                } else {
                    transaction.show(mFrag3);
                }
                break;
            case 3:
                mImg4.setImageResource(R.mipmap.user_filling);
                if (mFrag4 == null) {
                    mFrag4 = new Fragment();
                    transaction.add(R.id.id_content, mFrag4);
                } else {
                    transaction.show(mFrag4);
                }
                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }
        if (mFrag3 != null) {
            transaction.hide(mFrag3);
        }
        if (mFrag4 != null) {
            transaction.hide(mFrag4);
        }
    }

    //将四个ImageButton置为灰色
    private void resetImgs() {
        mImg1.setImageResource(R.mipmap.home);
        mImg2.setImageResource(R.mipmap.favorite);
        mImg3.setImageResource(R.mipmap.comment);
        mImg4.setImageResource(R.mipmap.user);
    }
}


