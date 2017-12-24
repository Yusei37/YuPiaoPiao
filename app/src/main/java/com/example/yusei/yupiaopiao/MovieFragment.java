package com.example.yusei.yupiaopiao;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieFragment extends Fragment {

    public static List<Movie> movieList = new ArrayList<>();
    private MovieAdapter adapter;

    public  MovieFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment,container,false);
        initMovies();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_movie);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(movieList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initMovies() {
        CommonRequest request = new CommonRequest();
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                ArrayList<HashMap<String, String>> list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    Movie movie = new Movie();
                    HashMap<String, String> map = list.get(i);
                    movie.setMovieName(map.get("MovieName"));
                    movie.setDirector(map.get("Director"));
                    movie.setActor(map.get("Actor"));
                    String date = map.get("ReleaseTime");
                    movie.setReleaseTime(new java.util.Date(Long.valueOf(date).longValue()));
                    date = map.get("ReleaseTime");
                    movie.setProjectionTime(new java.util.Date(Long.valueOf(date).longValue()));
                    movie.setPoster(map.get("Poster"));
                    movie.setBrief(map.get("Brief"));
                    movie.setDuration(Integer.valueOf(map.get("Duration")));
                    movieList.add(movie);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute("http://10.0.2.2:8080/ServletTest/MovieServlet");
    }
}
