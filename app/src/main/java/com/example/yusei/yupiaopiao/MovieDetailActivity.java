package com.example.yusei.yupiaopiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Cinecism> cinecismList = new ArrayList<>();
    private CinecismAdapter adapter;
    private TextView tv_buy;
    private TextView tv_writeCinecism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initCinecism();
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
                Toast.makeText(MovieDetailActivity.this, "写影评", Toast.LENGTH_SHORT).show();
            }
        }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_writeCinecism.setText(text);
        tv_writeCinecism.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initCinecism() {
        cinecismList.clear();
        for (int i = 0; i < 50; i++) {
            Cinecism cinecism = new Cinecism();
            cinecism.setPhoneNumber(i + " aaaa");
            cinecism.setComment("dsd sad asf as sf d e wqd asd sad asd sf df s fas dasd asd ssd asd sad sad sad asd s dsa ");
            cinecismList.add(cinecism);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                Toast.makeText(this, "买买买!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
