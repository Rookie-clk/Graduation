package com.example.a20191316008_cuiliangkun_graduationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class order extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order,container,false);
//        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
//        int hotelid = bundle.getInt("hotelid");   //取得主页传输的hotelid
//        if (hotelid==0) {
//            Toast.makeText(getActivity(),"123",Toast.LENGTH_SHORT).show();
//        }








        return view;
    }
}
