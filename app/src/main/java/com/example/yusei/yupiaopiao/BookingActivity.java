package com.example.yusei.yupiaopiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_booking_moviename;
    private TextView tv_booking_movietime;
    private TextView tv_booking_theatre;
    private TextView tv_booking_seat;
    private TextView tv_booking_price;
    private TextView tv_pay;


    private Booking booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        booking = (Booking) getIntent().getSerializableExtra("Booking");

        tv_booking_moviename = (TextView) findViewById(R.id.tv_booking_moviename);
        tv_booking_movietime = (TextView) findViewById(R.id.tv_booking_movietime);
        tv_booking_theatre = (TextView) findViewById(R.id.tv_booking_theatre);
        tv_booking_seat = (TextView) findViewById(R.id.tv_booking_seat);
        tv_booking_price = (TextView) findViewById(R.id.tv_booking_price);
        tv_pay = (TextView) findViewById(R.id.tv_pay);
        tv_pay.setOnClickListener(this);

        tv_booking_moviename.setText(booking.getMovieName());
        DateFormat df = new SimpleDateFormat("MM-dd HH:mm");
        tv_booking_movietime.setText(df.format(booking.getTime()));
        tv_booking_theatre.setText(booking.getTheatreName());
        tv_booking_seat.setText(booking.getMovieHallName() + "  " + booking.getRow() + "排" + booking.getCol() + "座");
        tv_booking_price.setText(booking.getPrice() + "元");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pay:
                CommonRequest request = new CommonRequest();
                request.addRequestParam("PhoneNumber", booking.getPhoneNumber());
                request.addRequestParam("MovieName", booking.getMovieName());
                request.addRequestParam("TheatreName", booking.getTheatreName());
                request.addRequestParam("MovieHallName", booking.getMovieHallName());
                request.addRequestParam("Row", String.valueOf(booking.getRow()));
                request.addRequestParam("Col", String.valueOf(booking.getCol()));
                request.addRequestParam("Time", String.valueOf(booking.getTime().getTime()));
                request.addRequestParam("Price", String.valueOf(booking.getPrice()));
                book(request);
                break;
        }
    }

    private void book(CommonRequest request) {
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(MyApplication.getContext(), "订票成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute("http://10.0.2.2:8080/ServletTest/BookingServlet");
    }
}
