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
    private ImageView arrow;
    private Button logoutbtn;
    private Button manage;
    private Button manageAll;
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
        manageAll.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
//        favorite.setOnClickListener(this);
//        info.setOnClickListener(this);
        owner.setOnClickListener(this);
        logoutbtn.setOnClickListener(this);
        manage.setOnClickListener(this);
    }

    private void initView() {
        manageAll = view.findViewById(R.id.userpage_manageAllHotel);
        loginbtn = view.findViewById(R.id.loginbtn);
        avatar= view.findViewById(R.id.userpage_avatar);
//        favorite = view.findViewById(R.id.userpage_favorite);
//        info = view.findViewById(R.id.userpage_info);
        owner = view.findViewById(R.id.userpage_wantbeowner);
        txt1 = view.findViewById(R.id.userpage_logintxt);
        txt2 = view.findViewById(R.id.userpage_logintxt2);
        arrow = view.findViewById(R.id.userpage_arrow);
        logoutbtn = view.findViewById(R.id.userpage_logout);
        manage = view.findViewById(R.id.userpage_manageMyHotel);

        sp = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        editor=sp.edit();
        if(sp.getString("账号","") !="") {//登陆成功
            UserDBHelper userDBHelper = new UserDBHelper(getActivity(),"userinfo",null,1);

            txt1.setText(sp.getString("账号", ""));
            txt2.setText("");
            arrow.setVisibility(view.INVISIBLE);
            loginbtn.setEnabled(false);                 //设置用户信息以及将登陆按钮设为不可点击
            logoutbtn.setVisibility(View.VISIBLE);


            byte[] avartarByte = userDBHelper.UserAvartor(sp.getString("账号", ""));          //设置用户界面的头像
            Bitmap avartarBitmap = getPicFromBytes(avartarByte);
            avartarBitmap = zoomBitmap(avartarBitmap,100,100);
            avatar.setImageBitmap(avartarBitmap);
            if(userDBHelper.IsAdmin(sp.getString("账号",""))){
                owner.setVisibility(View.INVISIBLE);
                manage.setVisibility(View.INVISIBLE);
                manageAll.setVisibility(View.VISIBLE);
            }
            if(userDBHelper.IsOwner(sp.getString("账号",""))){
                owner.setVisibility(View.INVISIBLE);
                manage.setVisibility(View.VISIBLE);
                manageAll.setVisibility(View.INVISIBLE);
            }


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
            case R.id.userpage_manageAllHotel:
                Intent manageAllintent = new Intent(getActivity(),manageAll.class);
//                Bundle manageAllbundle = new Bundle();
//                managebundle.putInt("userid",userid_wbo);
//                manageintent.putExtras(managebundle);
                getActivity().startActivity(manageAllintent);
                break;
            case R.id.loginbtn:
                Intent toLogin = new Intent(getActivity(),login.class);

                getActivity().startActivity(toLogin);
                break;
//            case R.id.userpage_favorite:
//                int userid = 1;
//                Intent intent = new Intent(getActivity(),favorite.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("hotelid",userid);
//                intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
//                getActivity().startActivity(intent);
//
//                break;
//            case R.id.userpage_info:
//
//                break;
            case R.id.userpage_wantbeowner:
                if(sp.getString("账号", "")==""){
                    Toast.makeText(getActivity(),"请先登录！",Toast.LENGTH_LONG).show();
                    return;
                }
                int userid_wbo;
                UserDBHelper userDBHelper = new UserDBHelper(getActivity(),"userinfo",null,1);
                userid_wbo = userDBHelper.UserID(sp.getString("账号", ""));

                Intent wbointent = new Intent(getActivity(),wbo.class);
                Bundle wbobundle = new Bundle();
                wbobundle.putInt("userid",userid_wbo);
                wbointent.putExtras(wbobundle);
                getActivity().startActivity(wbointent);
                break;
            case R.id.userpage_logout:
                final commondialog commondialog = new commondialog(getActivity());
                commondialog.setTitle("提示")
                        .setMessage("您确定要登出吗？")
                        .setPositive("确定")
                        .setNegative("取消")
                        .setOnClickButtonListener(new commondialog.OnclickButtonListener() {
                            @Override
                            public void onPositiveClick() {
                                editor.putString("账号","");
                                editor.putString("密码","");
                                editor.commit();
                                txt1.setText("登录/注册");
                                txt2.setText("登录享更多特权");
                                avatar.setImageResource(R.drawable.head);
                                arrow.setVisibility(view.VISIBLE);
                                loginbtn.setEnabled(true);
                                logoutbtn.setVisibility(View.INVISIBLE);
                                manage.setVisibility(View.INVISIBLE);
                                owner.setVisibility(View.VISIBLE);
                                manageAll.setVisibility(View.INVISIBLE);
                                Intent toUserpage = new Intent(getActivity(),MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("tab","2");
                                toUserpage.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                                getActivity().startActivity(toUserpage);
                                commondialog.dismiss();
                            }

                            @Override
                            public void onNegativeClick() {
                                commondialog.dismiss();
                            }
                        });
                commondialog.show();
                break;
            case R.id.userpage_manageMyHotel:
                Intent manageintent = new Intent(getActivity(),manage.class);
                Bundle managebundle = new Bundle();
//                managebundle.putInt("userid",userid_wbo);
//                manageintent.putExtras(managebundle);
                getActivity().startActivity(manageintent);
                break;
        }
    }
}
