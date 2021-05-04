package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a20191316008_cuiliangkun_graduationproject.bean.Hotel;
import com.example.a20191316008_cuiliangkun_graduationproject.database.HotelDBHelper;

import java.util.List;

public class dom_search_frag extends Fragment {
    private Button search;
    private EditText ed_region;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_searchdetail,container,false);
        search = view.findViewById(R.id.search_btn_region);
        ed_region = view.findViewById(R.id.search_edit_region);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String region = ed_region.getText().toString().trim();
                HotelDBHelper hotelDBHelper = new HotelDBHelper(getActivity(),"hotelinfo",null,1);
                List<Hotel> regionHotel = hotelDBHelper.findHotelByRegion(region);
                Intent toResult = new Intent(getActivity(),searchresult.class);
                Bundle bundle = new Bundle();
                
            }
        });
        return view;
    }
}
