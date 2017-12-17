package com.example.yusei.yupiaopiao;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TheatreFragment extends Fragment {

    private List<Theatre> theatreList = new ArrayList<>();
    private TheatreAdapter adapter;

    public  TheatreFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theatre_fragment,container,false);
        initTheatre();

        Spinner sp_area = (Spinner) view.findViewById(R.id.sp_area);
        final String[] area = {"不限区域", "滨江区", "江干区", "富阳市", "拱墅区", "上城区","下城区", "西湖区", "余杭区","下沙"};
        ArrayAdapter<String> areaAdapter= new ArrayAdapter<String>(MyApplication.getContext(), android.R.layout.simple_spinner_item, area);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_area.setAdapter(areaAdapter);
        sp_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Toast.makeText(MyApplication.getContext(), "你点击的是:"+area[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        final Spinner sp_special = (Spinner) view.findViewById(R.id.sp_special);
        final String[] special = {"不限特色", "IMAX厅", "巨幕厅", "普通厅", "4k厅", "4D厅","激光厅"};
        ArrayAdapter<String> specialAdapter= new ArrayAdapter<String>(MyApplication.getContext(), android.R.layout.simple_spinner_item, special);
        specialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_special.setAdapter(specialAdapter);
        sp_special.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Toast.makeText(MyApplication.getContext(), "你点击的是:"+special[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_theatre);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TheatreAdapter(theatreList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void initTheatre() {
        theatreList.clear();
        for (int i = 0; i < 20; i++) {
            Theatre theatre = new Theatre();
            theatre.setTheatreName(i+"影院名称");
            theatre.setTheatreAddress("大萨达所大所是的阿萨德撒");
            theatreList.add(theatre);
        }
    }
}
