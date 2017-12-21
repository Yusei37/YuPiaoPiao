package com.example.yusei.yupiaopiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmArrangementActivity extends AppCompatActivity {

    private List<FilmArrangement> filmArrangementList = new ArrayList<>();
    private FilmArrangementAdapter adapter;
    private FilmArrangement filmArrangement;
    private Theatre theatre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmarrangement);
        theatre = getIntent().getParcelableExtra("Theatre");
        initFilmArrangement();

        Spinner sp_movie = (Spinner) findViewById(R.id.sp_movie);
        final String[] movie = {"不限区域", "滨江区", "江干区", "富阳市", "拱墅区", "上城区","下城区", "西湖区", "余杭区","下沙"};
        ArrayAdapter<String> movieAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movie);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_movie.setAdapter(movieAdapter);
        sp_movie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Toast.makeText(MyApplication.getContext(), "你点击的是:"+movie[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        Spinner sp_time = (Spinner) findViewById(R.id.sp_time);
        final String[] time = {"不限区域", "滨江区", "江干区", "富阳市", "拱墅区", "上城区","下城区", "西湖区", "余杭区","下沙"};
        ArrayAdapter<String> timeAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movie);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_time.setAdapter(timeAdapter);
        sp_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Toast.makeText(MyApplication.getContext(), "你点击的是:"+time[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_filmarrangement);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FilmArrangementAdapter(filmArrangementList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    public void initFilmArrangement() {
        CommonRequest request = new CommonRequest();
        request.addRequestParam("TheatreName", "哈迪斯影院");
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                ArrayList<HashMap<String, String>> list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    FilmArrangement filmArrangement = new FilmArrangement();
                    HashMap<String, String> map = list.get(i);
                    String date = map.get("BeginTime");
                    filmArrangement.setBeginTime(new java.util.Date(Long.valueOf(date).longValue()));
                    date = map.get("EndTime");
                    filmArrangement.setEndTime(new java.util.Date(Long.valueOf(date).longValue()));
                    filmArrangement.setMovieHallName(map.get("MovieHallName"));
                    filmArrangement.setPrice(Double.valueOf(map.get("Price")));
                    filmArrangementList.add(filmArrangement);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute("http://10.0.2.2:8080/ServletTest/FilmArrangementServlet");
    }
}
