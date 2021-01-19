package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class userpage extends Fragment implements View.OnClickListener {
    private ImageView avatar;
    public View view;
    private Button loginbtn;
    private Button favorite;
    private Button info;
    private Button owner;


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
//                Toast.makeText(getActivity(),"123",Toast.LENGTH_SHORT).show();
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
                break;
        }
    }
}
