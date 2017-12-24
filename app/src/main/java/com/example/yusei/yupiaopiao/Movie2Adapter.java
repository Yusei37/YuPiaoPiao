package com.example.yusei.yupiaopiao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by yusei on 2017/12/24
 */
public class Movie2Adapter extends RecyclerView.Adapter<Movie2Adapter.ViewHolder> {

    private Context mContext;

    private List<Movie> mMovieList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_movieimage2;
        TextView tv_movieName2;

        public ViewHolder(View view) {
            super(view);
            iv_movieimage2 = (ImageView) view.findViewById(R.id.iv_movieimage2);
            tv_movieName2 = (TextView) view.findViewById(R.id.tv_movieName2);
        }
    }

    public Movie2Adapter(List<Movie> movieList) {
        mMovieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie2_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
//        holder.movieView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int postion = holder.getAdapterPosition();
//                Movie movie = mMovieList.get(postion);
//                Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
//                intent.putExtra("Movie", movie);
//                v.getContext().startActivity(intent);
//            }
//        });
//        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "购票", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), SeatActivity.class);
//                v.getContext().startActivity(intent);
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        Glide.with(mContext).load(movie.getPoster()).fitCenter().error(R.mipmap.ic_launcher).into(holder.iv_movieimage2);
        holder.tv_movieName2.setText(movie.getMovieName());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
