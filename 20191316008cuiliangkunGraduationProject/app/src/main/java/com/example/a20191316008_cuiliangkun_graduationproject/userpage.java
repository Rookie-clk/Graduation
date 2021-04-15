package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a20191316008_cuiliangkun_graduationproject.database.UserDBHelper;

import static android.content.Context.MODE_PRIVATE;

public class userpage extends Fragment implements View.OnClickListener {
    private ImageView avatar;
    public View view;
    private Button loginbtn;
    private Button favorite;
    private Button info;
    private Button owner;
    private TextView txt1;
    private TextView txt2;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_userpage,container,false);

        initView();
        initEvent();
        return view;
    }

    private void initEvent() {
        loginbtn.setOnClickListener(this);
        favorite.setOnClickListener(this);
        info.setOnClickListener(this);
        owner.setOnClickListener(this);
    }

    private void initView() {
        loginbtn = view.findViewById(R.id.loginbtn);
        avatar= view.findViewById(R.id.userpage_avatar);
        favorite = view.findViewById(R.id.userpage_favorite);
        info = view.findViewById(R.id.userpage_info);
        owner = view.findViewById(R.id.userpage_wantbeowner);
        txt1 = view.findViewById(R.id.userpage_logintxt);
        txt2 = view.findViewById(R.id.userpage_logintxt2);

        sp = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        editor=sp.edit();
        if(sp.getString("账号","")!=null) {
            txt1.setText(sp.getString("账号", ""));
            txt2.setText("");
            UserDBHelper userDBHelper = new UserDBHelper(getActivity(),"userinfo",null,1);
            Toast.makeText(getActivity(),sp.getString("账号", ""),Toast.LENGTH_LONG).show();
//            byte[] avartarByte = userDBHelper.UserAvartor(sp.getString("账号", ""));
//            Bitmap avartarBitmap = getPicFromBytes(avartarByte);
//            avartarBitmap = zoomBitmap(avartarBitmap,100,100);
//            avatar.setImageBitmap(avartarBitmap);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
                Intent toLogin = new Intent(getActivity(),login.class);

                getActivity().startActivity(toLogin);
                break;
            case R.id.userpage_favorite:
                int userid = 1;
                Intent intent = new Intent(getActivity(),favorite.class);
                Bundle bundle = new Bundle();
                bundle.putInt("hotelid",userid);
                intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                getActivity().startActivity(intent);

                break;
            case R.id.userpage_info:

                break;
            case R.id.userpage_wantbeowner:

                Intent wbointent = new Intent(getActivity(),wbo.class);
                Bundle wbobundle = new Bundle();
                wbobundle.putInt("hotelid",1);
                wbointent.putExtras(wbobundle);
                getActivity().startActivity(wbointent);
                break;
        }
    }
}
