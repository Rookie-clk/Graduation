package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Review;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.User;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.ReviewDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import java.util.List;

public class hotelpage extends AppCompatActivity implements View.OnClickListener {


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
    private TextView Text_weisheng;
    private TextView Text_huanjing;
    private TextView Text_fuwu;
    private TextView Text_sheshi;
    private ImageView avatar;
    private TextView username;
    private ListView reviewlist;
    private TextView detail;
    private List<Hotel> hotelList;
    private Hotel tmp;
    private List<Hotel> myHotel;
    private TextView price;
    private TextView address;
    private Button btn_yuding;
    String intenthotelname;
    UserDBHelper userDBHelper = new UserDBHelper(hotelpage.this,"userinfo",null,1);
    HotelDBHelper hotelDBHelper = new HotelDBHelper(hotelpage.this,"hotelinfo",null,1);
    ReviewDBHelper reviewDBHelper = new ReviewDBHelper(hotelpage.this,"reviewinfo",null,1);
    List<Review> allreview ;

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
        allreview = reviewDBHelper.findAllReview(tmp.getId()+"");
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
        price.setText(tmp.getPrice());
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
        reviewlist.setAdapter(new reviewAdapter());
        setListViewHeightBasedOnChildren(reviewlist);
        if(allreview.size() == 0){

        }
        double zongfen = 0;
        double weisheng = 0;
        double huanjing = 0;
        double fuwu = 0;
        double sheshi = 0;
        for(int i = 0;i< allreview.size();i++){
            zongfen += Integer.valueOf(allreview.get(i).getTotal());
            zongfen = zongfen / allreview.size();

            weisheng += Integer.valueOf(allreview.get(i).getWeisheng());
            weisheng = weisheng / allreview.size();

            huanjing += Integer.valueOf(allreview.get(i).getHuanjing());
            huanjing = huanjing / allreview.size();

            fuwu += Integer.valueOf(allreview.get(i).getFuwu());
            fuwu = fuwu / allreview.size();

            sheshi += Integer.valueOf(allreview.get(i).getSheshi());
            sheshi = sheshi / allreview.size();
        }
        score2.setText(zongfen+"");
        Text_fuwu.setText(fuwu+"");
        Text_sheshi.setText(sheshi+"");
        Text_weisheng.setText(weisheng+"");
        Text_huanjing.setText(huanjing+"");


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
//        weisheng = findViewById(R.id.weisheng);
//        huanjing = findViewById(R.id.huanjing);
//        fuwu = findViewById(R.id.fuwu);
//        weisheng = findViewById(R.id.weisheng);
        reviewlist = findViewById(R.id.hotelpage_reviewlist);

        avatar = findViewById(R.id.hotelpage_avatar);
        username = findViewById(R.id.hotelpage_username);

        detail = findViewById(R.id.hotelpage_pinglun);

        address = findViewById(R.id.hotelpage_address);
        price = findViewById(R.id.hotelpage_price);

        btn_back = findViewById(R.id.hotelpage_back);
        btn_love = findViewById(R.id.hotelpage_love);
        btn_yuding = findViewById(R.id.hotelpage_yuding);

        Text_weisheng = findViewById(R.id.weisheng);
        Text_fuwu = findViewById(R.id.fuwu);
        Text_sheshi = findViewById(R.id.sheshi);
        Text_huanjing = findViewById(R.id.huanjing);

    }

    private void initEvent() {
        btn_back.setOnClickListener(this);
        btn_love.setOnClickListener(this);

        btn_yuding.setOnClickListener(this);
//        reviewlist.setAdapter(new reviewAdapter());
//        setListViewHeightBasedOnChildren(reviewlist);
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


            case R.id.hotelpage_yuding:
                Intent intent = new Intent(hotelpage.this,yuding.class);
                Bundle bundle = new Bundle();
                bundle.putString("hotelname",intenthotelname);
                intent.putExtras(bundle);                   //传输给hotelpage的name，并跳转
                hotelpage.this.startActivity(intent);
                break;
        }
    }


    class reviewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return allreview.size();
        }

        @Override
        public Object getItem(int position) {
            return allreview.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SharedPreferences sp;
            SharedPreferences.Editor editor;
            sp = hotelpage.this.getSharedPreferences("data",MODE_PRIVATE);
            editor=sp.edit();
            convertView = View.inflate(hotelpage.this,R.layout.review_item,null);
            ImageView avatar = convertView.findViewById(R.id.hotelpage_avatar);
            TextView uname = convertView.findViewById(R.id.hotelpage_username);
            TextView pinglun = convertView.findViewById(R.id.hotelpage_pinglun);
            TextView pweisheng = convertView.findViewById(R.id.hotelpage_pweisheng);
            TextView phuanjing = convertView.findViewById(R.id.hotelpage_phuanjing);
            TextView pfuwu = convertView.findViewById(R.id.hotelpage_pfuwu);
            TextView psheshi = convertView.findViewById(R.id.hotelpage_psheshi);

            Review tmpreview = allreview.get(position);
            int userid = tmpreview.getUid();

            byte[] ImageByte = userDBHelper.UserAvartorByid(userid+"");
            Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
            ImageBitmap = zoomBitmap(ImageBitmap,600,600);
            avatar.setImageBitmap(ImageBitmap);


            uname.setText(userDBHelper.UserName(userid+""));
            pinglun.setText(tmpreview.getDetail());
            pweisheng.setText(tmpreview.getWeisheng()+".0");
            phuanjing.setText(tmpreview.getHuanjing()+".0");
            pfuwu.setText(tmpreview.getFuwu()+".0");
            psheshi.setText(tmpreview.getSheshi()+".0");
            return convertView;
        }
    }
}
