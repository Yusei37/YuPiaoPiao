package com.example.yusei.yupiaopiao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yusei on 2017/12/18
 */
public class FilmArrangementAdapter extends RecyclerView.Adapter<FilmArrangementAdapter.ViewHolder> {

    private Context mContext;

    private List<FilmArrangement> mFilmArrangementList;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_begintime;
        TextView tv_endtime;
        TextView tv_moviehall;
        TextView tv_price;
        Button btn_buy2;

        public ViewHolder(View view) {
            super(view);
            tv_begintime = (TextView) view.findViewById(R.id.tv_begintime);
            tv_endtime = (TextView) view.findViewById(R.id.tv_endtime);
            tv_moviehall = (TextView) view.findViewById(R.id.tv_moviehall);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            btn_buy2 = (Button) view.findViewById(R.id.btn_buy2);
            btn_buy2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), SeatActivity.class);
            v.getContext().startActivity(intent);
        }

    }

    public FilmArrangementAdapter (List<FilmArrangement> filmArrangementList) {
        mFilmArrangementList = filmArrangementList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.filmarrangement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FilmArrangement filmArrangement = mFilmArrangementList.get(position);
        holder.tv_begintime.setText(String.format("%tR", filmArrangement.getBeginTime()));
        holder.tv_endtime.setText(String.format("%tR", filmArrangement.getEndTime()) + " 散场");
        holder.tv_moviehall.setText(filmArrangement.getMovieHallName());
        holder.tv_price.setText("票价: " + filmArrangement.getPrice());
    }

    @Override
    public int getItemCount() {
        return mFilmArrangementList.size();
    }

}
