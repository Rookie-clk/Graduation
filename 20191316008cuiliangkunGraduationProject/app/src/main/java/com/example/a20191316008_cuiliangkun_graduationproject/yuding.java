package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.Order;
import com.example.a20191316008_cuiliangkun_graduationproject.bean.User;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.OrderDBHelper;
import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import java.util.List;

public class yuding extends AppCompatActivity {
    private ImageView im_img;
    private TextView te_name;
    private TextView te_detail;
    private EditText ed_truename;
    private EditText ed_shenfenzheng;
    private EditText ed_phonenumber;
    private TextView te_price;
    private Button bt_submit;
    List<Hotel> hotelList;
    Hotel tmphotel;
    Order tmporder;
    String intenthotelname;
    HotelDBHelper hotelDBHelper = new HotelDBHelper(yuding.this,"hotelinfo",null,1);
    UserDBHelper userDBHelper = new UserDBHelper(yuding.this,"userinfo",null,1);
    OrderDBHelper orderDBHelper = new OrderDBHelper(yuding.this,"orderinfo",null,1);
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuding);

        getData();
        init();
        show();


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
    private void show() {
        byte[] ImageByte = tmphotel.getPicture();
        Bitmap ImageBitmap = getPicFromBytes(ImageByte);            //设置图片
        ImageBitmap = zoomBitmap(ImageBitmap,1000,600);
        im_img.setImageBitmap(ImageBitmap);
        te_name.setText(tmphotel.getName());
        String[] temp = tmphotel.getHuxing().split(",");
        te_detail.setText(tmphotel.getType()+"·"+temp[0]+"室"+temp[1]+"厅"+"·"+tmphotel.getStyle());
        te_price.setText("￥"+tmphotel.getPrice());
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        intenthotelname = bundle.getString("hotelname");  //取得主页传输的hotelname
        hotelList = hotelDBHelper.findHotelByName(intenthotelname);
        tmphotel = hotelList.get(0);

    }


    private void init() {
        im_img = findViewById(R.id.yuding_img);
        te_name = findViewById(R.id.yuding_name);
        te_detail = findViewById(R.id.yuding_detail);
        ed_truename = findViewById(R.id.yuding_truename);
        ed_phonenumber = findViewById(R.id.yuding_phonenumber);
        ed_shenfenzheng = findViewById(R.id.yuding_shenfenzheng);
        te_price = findViewById(R.id.yuding_price);
        bt_submit = findViewById(R.id.yuding_yuding);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String truename = ed_truename.getText().toString().trim();
                final String shenfenzheng = ed_shenfenzheng.getText().toString().trim();
                final String phonenumber = ed_phonenumber.getText().toString().trim();

                if(truename==null||truename.isEmpty()){
                    Toast.makeText(yuding.this,"请填写您的真实姓名！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(shenfenzheng==null||shenfenzheng.isEmpty()){
                    Toast.makeText(yuding.this,"请填写您的身份证！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phonenumber==null||phonenumber.isEmpty()){
                    Toast.makeText(yuding.this,"请填写您的手机号码！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(shenfenzheng.length()!=18){
                    Toast.makeText(yuding.this,"请正确填写您的身份证！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phonenumber.length()!=11){
                    Toast.makeText(yuding.this,"请正确填写您的手机号码！",Toast.LENGTH_SHORT).show();
                    return;
                }

                final commondialog commondialog = new commondialog(yuding.this);
                commondialog.setTitle("提示")
                        .setMessage("您确定要提交吗？")
                        .setPositive("确定")
                        .setNegative("取消")
                        .setOnClickButtonListener(new commondialog.OnclickButtonListener() {
                            @Override
                            public void onPositiveClick() {
                                sp = yuding.this.getSharedPreferences("data",MODE_PRIVATE);
                                editor=sp.edit();
                                orderDBHelper.insertOrder(tmphotel.getUid(),tmphotel.getId(),userDBHelper.UserID(sp.getString("账号","")),shenfenzheng,truename,phonenumber,Double.parseDouble(tmphotel.getPrice()));

                                Toast.makeText(yuding.this,"提交订单成功！",Toast.LENGTH_LONG).show();
                                Intent toUserPage = new Intent(yuding.this,MainActivity.class);
                                yuding.this.startActivity(toUserPage);
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
    }


}
