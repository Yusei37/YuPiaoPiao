package com.example.yusei.yupiaopiao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yusei.yupiaopiao.activity.FilmArrangementActivity;
import com.example.yusei.yupiaopiao.beans.Theatre;
import com.example.yusei.yupiaopiao.R;

import java.util.List;

/**
 * Created by yusei on 2017/12/15
 */
public class TheatreAdapter extends RecyclerView.Adapter<TheatreAdapter.ViewHolder> {

    private Context mContext;

    private List<Theatre> mTheatreList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View theatreView;
        TextView tv_theatreName;
        TextView tv_theatreAddress;

        public ViewHolder(View view) {
            super(view);
            theatreView  = view;
            tv_theatreName = (TextView) view.findViewById(R.id.tv_theatreName);
            tv_theatreAddress = (TextView) view.findViewById(R.id.tv_theatreAddress);
        }

    }

    public TheatreAdapter (List<Theatre> theatreList) {
        mTheatreList = theatreList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.theatre_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.theatreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Theatre theatre = mTheatreList.get(postion);
                Intent intent = new Intent(v.getContext(), FilmArrangementActivity.class);
                intent.putExtra("Theatre", theatre);
            //    intent.putExtra("TheatreName",theatre.getTheatreName());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Theatre theatre = mTheatreList.get(position);
        holder.tv_theatreName.setText(theatre.getTheatreName());
        holder.tv_theatreAddress.setText(theatre.getTheatreAddress());
    }

    @Override
    public int getItemCount() {
        return mTheatreList.size();
    }

}
