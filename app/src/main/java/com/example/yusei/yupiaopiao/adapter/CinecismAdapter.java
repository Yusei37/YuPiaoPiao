package com.example.yusei.yupiaopiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yusei.yupiaopiao.beans.Cinecism;
import com.example.yusei.yupiaopiao.R;

import java.util.List;

/**
 * Created by yusei on 2017/12/13
 */
public class CinecismAdapter extends RecyclerView.Adapter<CinecismAdapter.ViewHolder> {

    private Context mContext;

    private List<Cinecism> mCinecismList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_phoneNumber;
        private TextView tv_comment;

        public ViewHolder(View view) {
            super(view);
            tv_phoneNumber = (TextView) view.findViewById(R.id.tv_phoneNumber);
            tv_comment = (TextView) view.findViewById(R.id.tv_comment);
        }
    }

    public CinecismAdapter(List<Cinecism> cinecismList) {
        mCinecismList = cinecismList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.cinecism_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cinecism cinecism = mCinecismList.get(position);
        holder.tv_phoneNumber.setText(cinecism.getPhoneNumber());
        holder.tv_comment.setText(cinecism.getComment());
    }

    @Override
    public int getItemCount() {
        return mCinecismList.size();
    }

}
