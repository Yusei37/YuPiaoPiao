package com.example.yusei.yupiaopiao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yusei on 2017/12/15
 */
public class TheatreAdapter extends RecyclerView.Adapter<TheatreAdapter.ViewHolder> {

    private Context mContext;

    private List<Theatre> mTheatreList;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_theatreName;
        TextView tv_theatreAddress;

        public ViewHolder(View view) {
            super(view);
            tv_theatreName = (TextView) view.findViewById(R.id.tv_theatreName);
            tv_theatreAddress = (TextView) view.findViewById(R.id.tv_theatreAddress);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), tv_theatreName.getText().toString(), Toast.LENGTH_SHORT).show();
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
        return new ViewHolder(view);
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
