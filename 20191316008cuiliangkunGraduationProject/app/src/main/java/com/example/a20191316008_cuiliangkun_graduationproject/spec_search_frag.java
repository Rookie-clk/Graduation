package com.example.a20191316008_cuiliangkun_graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class spec_search_frag extends Fragment implements View.OnClickListener {
    private Button search;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_spec_searchdetail,container,false);
        search = view.findViewById(R.id.search);
        search.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                int id = 0;
                Intent intent = new Intent(getActivity(),searchresult.class);
                Bundle bundle = new Bundle();
                bundle.putInt("hotelid",id);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
                break;
        }
    }
}
