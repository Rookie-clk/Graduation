package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HStatusDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import java.util.List;

public class manage extends AppCompatActivity {
    private Button add;
    private ListView lst_myHotel;
    List<Hotel> myHotel;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private Button back;
    UserDBHelper userDBHelper = new UserDBHelper(manage.this,"userinfo",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        HotelDBHelper hotelDBHelper = new HotelDBHelper(manage.this,"waithotelinfo",null,1);


        init();
        String username = sp.getString("账号","");
        int userid = userDBHelper.UserID(username);
        myHotel = hotelDBHelper.findMyHotel(userid+"");
        lst_myHotel.setAdapter(new otherAdapter());
        setListViewHeightBasedOnChildren(lst_myHotel);


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
    private void init() {
        add = findViewById(R.id.manage_new);
        lst_myHotel = findViewById(R.id.manage_listview);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userid_wbo;
                UserDBHelper userDBHelper = new UserDBHelper(manage.this,"userinfo",null,1);
                userid_wbo = userDBHelper.UserID(sp.getString("账号", ""));

                Intent wbointent = new Intent(manage.this,wbo.class);
                Bundle wbobundle = new Bundle();
                wbobundle.putInt("userid",userid_wbo);
                wbointent.putExtras(wbobundle);
                manage.this.startActivity(wbointent);
            }
        });
        back = findViewById(R.id.manage_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toUserPage = new Intent(manage.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tab","2");
                toUserPage.putExtras(bundle);
                manage.this.startActivity(toUserPage);
            }
        });
        sp = manage.this.getSharedPreferences("data",MODE_PRIVATE);
        editor=sp.edit();

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
    class otherAdapter extends BaseAdapter {

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
            convertView = View.inflate(manage.this,R.layout.manage_item,null);
            ImageView himg = convertView.findViewById(R.id.manage_img);
            final TextView name = convertView.findViewById(R.id.manage_name);
            TextView des = convertView.findViewById(R.id.manage_des);
            Button delete = convertView.findViewById(R.id.manage_delete);


            final  Hotel my = myHotel.get(position);

//            Toast.makeText(hotelpage.this,myHotel.size()+"",Toast.LENGTH_LONG).show();
            name.setText(my.getName());
            String temHuxing[] = my.getHuxing().split(",");
            String temDetail = my.getType()+"·"+temHuxing[0]+"室"+temHuxing[1]+"厅"+"·"+my.getStyle();    //设置简要细节
            des.setText(temDetail);
            HStatusDBHelper hStatusDBHelper = new HStatusDBHelper(manage.this,"statusinfo",null ,1);
             if(!hStatusDBHelper.ExistStatus(my.getId()+"")){
                 TextView status = convertView.findViewById(R.id.manage_status);
                 status.setText("未审核");
                 status.setVisibility(View.VISIBLE);

            }else{
                if(hStatusDBHelper.Status(my.getId()+"")){
                    //通过
                    TextView status;
                    status = convertView.findViewById(R.id.manage_status);
                    status.setText("已通过");
                    status.setVisibility(View.VISIBLE);
                }else{
                    //不通过
                    TextView status;
                    status = convertView.findViewById(R.id.manage_status);
                    status.setText("已驳回");
                    status.setVisibility(View.VISIBLE);
                }
            }


            byte[] ImageByte = my.getPicture();
            Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
            ImageBitmap = zoomBitmap(ImageBitmap,1000,600);
            himg.setImageBitmap(ImageBitmap);

            final  ViewHolder holder = new ViewHolder();
            holder.delete = convertView.findViewById(R.id.manage_delete);

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final commondialog commondialog = new commondialog(manage.this);
                    commondialog.setTitle("提示")
                            .setMessage("您确定要下架此民宿吗？")
                            .setPositive("确定")
                            .setNegative("取消")
                            .setOnClickButtonListener(new commondialog.OnclickButtonListener() {
                                @Override
                                public void onPositiveClick() {
                                    HotelDBHelper whotelDBHelper = new HotelDBHelper(manage.this,"waithotelinfo",null,1);
                                    HotelDBHelper hotelDBHelper = new HotelDBHelper(manage.this,"hotelinfo",null,1);

                                    whotelDBHelper.deleteHotel(my.getId().toString());
                                    if(hotelDBHelper.ExistHotelById(my.getId().toString())){
                                        hotelDBHelper.deleteHotel(my.getId().toString());
                                    }
                                    Toast.makeText(manage.this,"删除成功！",Toast.LENGTH_LONG);


                                        Intent refresh = new Intent(manage.this,manage.class);
                                        Bundle tab = new Bundle();
//                                        tab.putString("tab","1");
                                        refresh.putExtras(tab);
                                        manage.this.startActivity(refresh);


                                    commondialog.dismiss();
                                }

                                @Override
                                public void onNegativeClick() {
                                    commondialog.dismiss();
                                }
                            });
                    commondialog.show();
                }
            });



            return convertView;
        }
        class ViewHolder {
            Button delete;

            Button detail;
        }
    }
}
