package com.example.yusei.yupiaopiao.activity;


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
import com.example.yusei.yupiaopiao.*;
import com.example.yusei.yupiaopiao.adapter.TheatreAdapter;
import com.example.yusei.yupiaopiao.beans.CommonRequest;
import com.example.yusei.yupiaopiao.beans.CommonResponse;
import com.example.yusei.yupiaopiao.beans.Theatre;
import com.example.yusei.yupiaopiao.constant.IP;
import com.example.yusei.yupiaopiao.constant.MyApplication;
import com.example.yusei.yupiaopiao.http.HttpPostTask;
import com.example.yusei.yupiaopiao.interfaces.ResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TheatreFragment extends Fragment {

    private List<Theatre> theatreList = new ArrayList<>();
    private List<Theatre> theatreListCopy = new ArrayList<>();
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
        final ArrayAdapter<String> areaAdapter= new ArrayAdapter<String>(MyApplication.getContext(), android.R.layout.simple_spinner_item, area);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_area.setAdapter(areaAdapter);
        sp_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
             //   Toast.makeText(MyApplication.getContext(), "你点击的是:"+area[pos], Toast.LENGTH_SHORT).show();
                theatreListCopy.clear();
                if (!area[pos].equals("不限区域")) {
                    for (int i = 0; i < theatreList.size(); i++) {
                        if (theatreList.get(i).getTheatreAddress().contains(area[pos])) {
                            theatreListCopy.add(theatreList.get(i));
                        }
                    }
                }
                else {
                    for (int i = 0; i < theatreList.size(); i++) {
                        theatreListCopy.add(theatreList.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        final Spinner sp_special = (Spinner) view.findViewById(R.id.sp_special);
        final String[] special = {"不限特色", "IMAX厅", "巨幕厅", "普通厅", "4DX厅", "4D厅","激光厅"};
        ArrayAdapter<String> specialAdapter= new ArrayAdapter<String>(MyApplication.getContext(), android.R.layout.simple_spinner_item, special);
        specialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_special.setAdapter(specialAdapter);
        sp_special.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
            //    Toast.makeText(MyApplication.getContext(), "你点击的是:"+special[pos], Toast.LENGTH_SHORT).show();
                theatreListCopy.clear();
                if (!special[pos].equals("不限特色")) {
                    for (int i = 0; i < theatreList.size(); i++) {
                        if (theatreList.get(i).getTheatreDescription().contains(special[pos])) {
                            theatreListCopy.add(theatreList.get(i));
                        }
                    }
                }
                else {
                    for (int i = 0; i < theatreList.size(); i++) {
                        theatreListCopy.add(theatreList.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_theatre);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TheatreAdapter(theatreListCopy);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void initTheatre() {
        CommonRequest request = new CommonRequest();
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                ArrayList<HashMap<String, String>> list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    Theatre theatre = new Theatre();
                    HashMap<String, String> map = list.get(i);
                    theatre.setTheatreName(map.get("TheatreName"));
                    theatre.setTheatreAddress(map.get("TheatreAddress"));
                    theatre.setPhoneNumber(map.get("PhoneNumber"));
                    theatre.setTheatreDescription(map.get("TheatreDescription"));
                    theatreList.add(theatre);
                    theatreListCopy.add(theatre);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute(IP.IP+"/ServletTest/TheatreServlet");
    }
}
