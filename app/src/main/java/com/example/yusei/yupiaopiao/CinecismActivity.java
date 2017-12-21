package com.example.yusei.yupiaopiao;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        }).execute("http://10.0.2.2:8080/ServletTest/WriteCinecismServlet");
    }
}
