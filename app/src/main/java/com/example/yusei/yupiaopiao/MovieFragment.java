package com.example.yusei.yupiaopiao;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private List<Movie> movieList = new ArrayList<>();
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
        movieList.clear();
        for (int i = 0; i < 50; i++) {
            Movie movie = new Movie();
            movie.setPoster(R.drawable.nav_icon);
            movie.setMovieName("电影名称:xxx");
            movie.setDirector("导演:xx");
            movie.setActor("主演:xxx");
            movieList.add(movie);
        }
    }
}
