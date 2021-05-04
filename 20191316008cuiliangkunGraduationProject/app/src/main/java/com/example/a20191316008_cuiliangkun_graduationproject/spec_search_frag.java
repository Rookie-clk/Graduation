package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.util.List;

public class spec_search_frag extends Fragment{
    private Button search;
    private EditText name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_spec_searchdetail,container,false);
        search = view.findViewById(R.id.search_btn_name);
        name = view.findViewById(R.id.search_edit_name);
        final HotelDBHelper hotelDBHelper = new HotelDBHelper(getActivity(),"hotelinfo",null,1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchname = name.getText().toString().trim();
                if(!hotelDBHelper.ExistHotelByName(searchname)){
                    Toast.makeText(getActivity(),"未找到该民宿！",Toast.LENGTH_LONG).show();
                    return;
                }
                List<Hotel> hotelList = hotelDBHelper.findHotelByName(searchname);
                Hotel searchresult = hotelList.get(0);

                Intent intent = new Intent(getActivity(),hotelpage.class);
                Bundle bundle = new Bundle();
                bundle.putString("hotelname",searchresult.getName());
                intent.putExtras(bundle);                   //传输给hotelpage的id，并跳转
                getActivity().startActivity(intent);
            }
        });
        return view;
    }


}
