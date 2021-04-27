package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HStatusDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class manageAll extends AppCompatActivity {
    private ListView lst_allHotel;
    private ImageButton back;
    List<Hotel> waithotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_all);
        HotelDBHelper waithotelDBHelper = new HotelDBHelper(manageAll.this,"waithotelinfo",null,1);
        HotelDBHelper hotelDBHelper = new HotelDBHelper(manageAll.this,"hotelinfo",null ,1);
        waithotel = waithotelDBHelper.findAllHotel();
        lst_allHotel = findViewById(R.id.manage_all_listview);
        lst_allHotel.setAdapter(new AllHotelAdapter());
        setListViewHeightBasedOnChildren(lst_allHotel);
        back = findViewById(R.id.manage_all_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toUserPage = new Intent(manageAll.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tab","2");
                toUserPage.putExtras(bundle);
                manageAll.this.startActivity(toUserPage);
            }
        });
        set();
    }

    private void set() {
        HotelDBHelper hotelDBHelper = new HotelDBHelper(manageAll.this,"hotelinfo",null ,1);


    }

    class AllHotelAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return waithotel.size();
        }

        @Override
        public Object getItem(int position) {
            return waithotel.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(manageAll.this,R.layout.manage_all_item,null);
            final Hotel wait = waithotel.get(position);
            final ImageView himg = convertView.findViewById(R.id.manage_all_img);
            TextView name = convertView.findViewById(R.id.manage_all_name);
            TextView des = convertView.findViewById(R.id.manage_all_des);
            final Button retain = convertView.findViewById(R.id.manage_all_retain);
            final Button pass = convertView.findViewById(R.id.manage_all_pass);

            HStatusDBHelper statusDBHelper = new HStatusDBHelper(manageAll.this,"statusinfo",null ,1);
            if(statusDBHelper.ExistStatus(wait.getId()+"")){
                retain.setVisibility(View.INVISIBLE);
                pass.setVisibility(View.INVISIBLE);
                if(statusDBHelper.Status(wait.getId()+"")){
                    //通过
                    TextView passornot;
                    passornot = convertView.findViewById(R.id.manage_all_passornot);
                    passornot.setText("已通过");
                    passornot.setVisibility(View.VISIBLE);
                }else{
                    //不通过
                    TextView passornot;
                    passornot = convertView.findViewById(R.id.manage_all_passornot);
                    passornot.setText("已驳回");
                    passornot.setVisibility(View.VISIBLE);
                }
            }




            name.setText(wait.getName());
            String temHuxing[] = wait.getHuxing().split(",");
            String temDetail = wait.getType()+"·"+temHuxing[0]+"室"+temHuxing[1]+"厅"+"·"+wait.getStyle();    //设置简要细节
            des.setText(temDetail);

            byte[] ImageByte = wait.getPicture();
            Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
            ImageBitmap = zoomBitmap(ImageBitmap,1000,600);
            himg.setImageBitmap(ImageBitmap);

            pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sname = wait.getName().trim();
                    String sprice = wait.getPrice().trim();
                    String sregion = wait.getRegion();
                    String sadress = wait.getAddress().trim();
                    String stype = wait.getType().trim();
                    String shuxing = wait.getHuxing().trim();
                    String ssquare = wait.getSquare()+"".trim();
                    String sstyle = wait.getStyle().trim();
                    byte[] spicture = wait.getPicture();
                    String cequip = wait.getCequip().trim();
                    String lequip = wait.getLequip().trim();
                    int uid = wait.getUid();
                    HotelDBHelper waitdb = new HotelDBHelper(manageAll.this,"waithotelinfo",null,1);
                    HotelDBHelper hoteldb = new HotelDBHelper(manageAll.this,"hotelinfo",null ,1);
                    HStatusDBHelper statusdb = new HStatusDBHelper(manageAll.this,"statusinfo",null ,1);
                    statusdb.SetStatus2Y(wait.getId()+"");

                    hoteldb.insertHotel(sname,sprice,sregion,sadress,stype,shuxing,ssquare,sstyle,spicture,cequip,lequip,uid);
//                    waitdb.deleteHotel(wait.getId()+"");
                    Intent refresh = new Intent(manageAll.this,manageAll.class);
                    manageAll.this.startActivity(refresh);
                    Toast.makeText(manageAll.this,"审核通过！",Toast.LENGTH_LONG).show();

                }
            });
            retain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotelDBHelper waithotelDBHelper = new HotelDBHelper(manageAll.this,"waithotelinfo",null,1);
                    HotelDBHelper hotelDBHelper = new HotelDBHelper(manageAll.this,"hotelinfo",null ,1);

                }
            });

            return convertView;
        }

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
//        Toast.makeText(manage.this,totalHeight+"",Toast.LENGTH_LONG).show();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 最后再加上分割线的高度和padding高度，否则显示不完整。
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))+listView.getPaddingTop()+listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }
}
