package com.example.yusei.yupiaopiao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yusei.yupiaopiao.activity.MovieDetailActivity;
import com.example.yusei.yupiaopiao.activity.SeatActivity;
import com.example.yusei.yupiaopiao.activity.TheatreFragment;
import com.example.yusei.yupiaopiao.beans.Movie;
import com.example.yusei.yupiaopiao.R;

import java.util.List;

/**
 * Created by yusei on 2017/12/12
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private Context mContext;

    private List<Movie> mMovieList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View movieView;
        ImageView movieimage;
        TextView tv_moviename;
        TextView tv_director;
        TextView tv_actor;
        Button btn_buy;

        public ViewHolder(View view) {
            super(view);
            movieView = view;
            movieimage = (ImageView) view.findViewById(R.id.movie_image);
            tv_moviename = (TextView) view.findViewById(R.id.tv_movieName);
            tv_director = (TextView) view.findViewById(R.id.tv_director);
            tv_actor = (TextView) view.findViewById(R.id.tv_actor);
            btn_buy = (Button) view.findViewById(R.id.btn_buy);
        }
    }

    public MovieAdapter(List<Movie> movieList) {
        mMovieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.movieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Movie movie = mMovieList.get(postion);
                Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
                intent.putExtra("Movie", movie);
                v.getContext().startActivity(intent);
            }
        });
        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "购票", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), TheatreFragment.class);
//                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        Glide.with(mContext).load(movie.getPoster()).fitCenter().error(R.mipmap.ic_launcher).into(holder.movieimage);
        holder.tv_moviename.setText(movie.getMovieName());
        holder.tv_director.setText(movie.getDirector());
        holder.tv_actor.setText(movie.getActor());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

}
