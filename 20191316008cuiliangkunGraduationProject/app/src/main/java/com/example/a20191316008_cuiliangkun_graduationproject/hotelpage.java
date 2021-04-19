package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.util.List;

public class hotelpage extends AppCompatActivity implements View.OnClickListener {

    private int[] icons = {R.drawable.beach,R.drawable.cyber,R.drawable.pool};
    private String[] details = {"原木双1.8米大床浴缸房，免费私家车接送迪士尼乐园8分钟，有餐厅可以点餐，有泳池、网红海洋球池",
            "南京路步行街 人民广场 外滩 大床房",
            "迪缘小舍/迪士尼乐园接送/日式白色屋顶"};                 //listview
    private String[] prices = {"￥628","￥455","￥575"};
    private ImageButton btn_back ;
    private  ImageButton btn_love;
    int count = 0;

    private ImageView img;
    private TextView name;
    private TextView leixing;
    private TextView huxing;
    private TextView fengge;
    private TextView score1;
    private TextView jichusheshi;
    private TextView aixinsheshi;
    private ListView otherlist;
    private TextView score2;
    private TextView miaoshu;
    private TextView zhoudao;
    private TextView bianli;
    private TextView weisheng;
    private TextView xingjiabi;
    private TextView bianjie;
    private ImageView avatar;
    private TextView username;
    private TextView date;
    private TextView detail;
    private Button btn_quanbupinglun;
    private List<Hotel> hotelList;
    private Hotel tmp;
    private List<Hotel> myHotel;
    private TextView address;
    String intenthotelname;
    HotelDBHelper hotelDBHelper = new HotelDBHelper(hotelpage.this,"hotelinfo",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelpage);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        intenthotelname = bundle.getString("hotelname");   //取得主页传输的hotelname
        hotelList = hotelDBHelper.findHotelByName(intenthotelname);
        tmp = hotelList.get(0);
        myHotel = hotelDBHelper.findMyHotel(String.valueOf(tmp.getUid()));

        init();
        initEvent();
        show();

    }

    private void show() {

        byte[] ImageByte = tmp.getPicture();
        Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
        ImageBitmap = zoomBitmap(ImageBitmap,1000,600);
        img.setImageBitmap(ImageBitmap);
        name.setText(tmp.getName());
        leixing.setText(tmp.getType());
        String temHuxing[] = tmp.getHuxing().split(",");
        String temDetail = temHuxing[0]+"室"+temHuxing[1]+"厅";
        huxing.setText(temDetail);
        fengge.setText(tmp.getStyle());
        address.setText(tmp.getAddress());
        jichusheshi.setText(tmp.getCequip());
        aixinsheshi.setText(tmp.getLequip());
        otherlist.setAdapter(new otherAdapter());
        otherlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(hotelpage.this,hotelpage.class);
                Bundle bundle = new Bundle();
                Hotel intentHotel = new Hotel();
                Adapter adapter=parent.getAdapter();
                intentHotel = (Hotel)adapter.getItem(position);
                bundle.putString("hotelname",intentHotel.getName());
                intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                hotelpage.this.startActivity(intent);
            }
        });
        setListViewHeightBasedOnChildren(otherlist);
    }
    class otherAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return myHotel.size();
        }

        @Override
        public Object getItem(int position) {

            return myHotel.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(hotelpage.this,R.layout.hotelpage_otheritem,null);
            ImageView oimg = convertView.findViewById(R.id.other_img);
            TextView other_name = convertView.findViewById(R.id.other_name);
            TextView btn = convertView.findViewById(R.id.other_btn);

            Hotel my = myHotel.get(position);

//            Toast.makeText(hotelpage.this,myHotel.size()+"",Toast.LENGTH_LONG).show();
            other_name.setText(my.getName());

            byte[] ImageByte = my.getPicture();
            Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
            ImageBitmap = zoomBitmap(ImageBitmap,1000,600);
            oimg.setImageBitmap(ImageBitmap);
            return convertView;
        }
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
    private void init() {
        img = findViewById(R.id.hotelpagr_img);
        name = findViewById(R.id.hotelpage_name);
        leixing = findViewById(R.id.hotelpage_leixing);
        huxing = findViewById(R.id.hotelpage_huxing);
        fengge = findViewById(R.id.hotelpage_fengge);
        score2 = findViewById(R.id.hotelpage_score2);
        jichusheshi = findViewById(R.id.hotelpage_jichusheshi);
        aixinsheshi = findViewById(R.id.hotelpage_aixinsheshi);
        otherlist = findViewById(R.id.hotelpage_other_list);
        miaoshu = findViewById(R.id.rushimiaoshu);
        zhoudao = findViewById(R.id.fuwuzhoudao);
        bianli = findViewById(R.id.weizhibianli);
        weisheng = findViewById(R.id.ganjingweisheng);
        xingjiabi = findViewById(R.id.gaoxingjiabi);
        bianjie = findViewById(R.id.ruzhubianjie);
        avatar = findViewById(R.id.hotelpage_avatar);
        username = findViewById(R.id.hotelpage_username);
        date = findViewById(R.id.hotelpage_ruzhuriqi);
        detail = findViewById(R.id.hotelpage_pinglun);
        btn_quanbupinglun = findViewById(R.id.hotelpage_btn_quanbupinglun);
        address = findViewById(R.id.hotelpage_address);

        btn_back = findViewById(R.id.hotelpage_back);
        btn_love = findViewById(R.id.hotelpage_love);
    }

    private void initEvent() {
        btn_back.setOnClickListener(this);
        btn_love.setOnClickListener(this);
        btn_quanbupinglun.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hotelpage_love:

                if(count%2==0) {
                    btn_love.setBackgroundResource(R.mipmap.favorite_filling_red); //收藏
                    count++;
                    Toast.makeText(hotelpage.this,"已收藏",Toast.LENGTH_SHORT).show();
                }else{
                    btn_love.setBackgroundResource(R.mipmap.favorite);  //取消收藏
                    count++;
                    Toast.makeText(hotelpage.this,"已取消收藏",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hotelpage_back:
                super.onBackPressed();
                break;

            case R.id.hotelpage_btn_quanbupinglun:
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
