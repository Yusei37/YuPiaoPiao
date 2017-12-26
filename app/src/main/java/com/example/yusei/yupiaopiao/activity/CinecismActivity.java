package com.example.yusei.yupiaopiao.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.yusei.yupiaopiao.*;
import com.example.yusei.yupiaopiao.beans.CommonRequest;
import com.example.yusei.yupiaopiao.beans.CommonResponse;
import com.example.yusei.yupiaopiao.beans.Movie;
import com.example.yusei.yupiaopiao.constant.IP;
import com.example.yusei.yupiaopiao.constant.MyApplication;
import com.example.yusei.yupiaopiao.http.HttpPostTask;
import com.example.yusei.yupiaopiao.interfaces.ResponseHandler;

public class CinecismActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edt_writecomment;
    private Button btn_publish;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinecism);
        movie = (Movie) getIntent().getSerializableExtra("Movie");

        edt_writecomment = (EditText) findViewById(R.id.edt_writecomment);

        btn_publish = (Button) findViewById(R.id.btn_publish);
        btn_publish.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_publish:
                writeComement();
                break;
        }
    }

    private void writeComement(){
        CommonRequest request = new CommonRequest();
        request.addRequestParam("MovieName", movie.getMovieName());
        request.addRequestParam("PhoneNumber", MainActivity.currentLoginCustomer.getPhoneNumber());
        request.addRequestParam("Comment", edt_writecomment.getText().toString());
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(MyApplication.getContext(), "发布成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute(IP.IP+"/ServletTest/WriteCinecismServlet");
    }
}
