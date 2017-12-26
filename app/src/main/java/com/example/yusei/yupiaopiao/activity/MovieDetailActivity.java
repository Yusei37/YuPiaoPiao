package com.example.yusei.yupiaopiao.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yusei.yupiaopiao.*;
import com.example.yusei.yupiaopiao.adapter.CinecismAdapter;
import com.example.yusei.yupiaopiao.beans.Cinecism;
import com.example.yusei.yupiaopiao.beans.CommonRequest;
import com.example.yusei.yupiaopiao.beans.CommonResponse;
import com.example.yusei.yupiaopiao.beans.Movie;
import com.example.yusei.yupiaopiao.constant.IP;
import com.example.yusei.yupiaopiao.constant.MyApplication;
import com.example.yusei.yupiaopiao.http.HttpPostTask;
import com.example.yusei.yupiaopiao.interfaces.ResponseHandler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Cinecism> cinecismList = new ArrayList<>();
    private CinecismAdapter adapter;
    private TextView tv_buy;
    private TextView tv_writeCinecism;
    private TextView tv_brief;
    private TextView tv_actor;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        movie = (Movie) getIntent().getSerializableExtra("Movie");
        initCinecism();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView iv_movieImage = (ImageView) findViewById(R.id.iv_movieImage);
        Glide.with(this).load(movie.getPoster()).fitCenter().error(R.mipmap.ic_launcher).into(iv_movieImage);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(movie.getMovieName());
        tv_brief = (TextView) findViewById(R.id.tv_brief);
        tv_brief.setText(movie.getBrief());
        tv_actor = (TextView) findViewById(R.id.tv_actor);
        tv_actor.setText(movie.getActor());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_cinecism);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CinecismAdapter(cinecismList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        tv_buy.setOnClickListener(this);
        tv_writeCinecism = (TextView) findViewById(R.id.tv_writeCinecism);
        SpannableString text = new SpannableString(new String("写影评"));
        text.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), CinecismActivity.class);
                intent.putExtra("Movie", movie);
                startActivity(intent);
            }
        }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_writeCinecism.setText(text);
        tv_writeCinecism.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initCinecism() {
        CommonRequest request = new CommonRequest();
        request.addRequestParam("MovieName", movie.getMovieName());
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                ArrayList<HashMap<String, String>> list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    Cinecism cinecism = new Cinecism();
                    HashMap<String, String> map = list.get(i);
                    cinecism.setPhoneNumber(map.get("PhoneNumber"));
                    cinecism.setComment(map.get("Comment"));
                    cinecismList.add(cinecism);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute(IP.IP+"/ServletTest/CinecismServlet");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                Toast.makeText(this, "买买买!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
