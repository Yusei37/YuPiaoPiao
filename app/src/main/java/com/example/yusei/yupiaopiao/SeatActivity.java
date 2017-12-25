package com.example.yusei.yupiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeatActivity extends AppCompatActivity implements SeatTable.SeatChecker, View.OnClickListener{

    public SeatTable seatTableView;
    private TextView tv_buy2;

    private FilmArrangement filmArrangement;
    private int[][] seat = null;
    private Booking booking = new Booking();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        filmArrangement = (FilmArrangement) getIntent().getSerializableExtra("FilmArrangement");
        booking.setPhoneNumber(MainActivity.currentLoginCustomer.getPhoneNumber());
        booking.setMovieName(filmArrangement.getMovieName());
        booking.setTheatreName(filmArrangement.getTheatreName());
        booking.setMovieHallName(filmArrangement.getMovieHallName());
        booking.setTime(filmArrangement.getBeginTime());
        booking.setPrice(filmArrangement.getPrice());


        initSeat();

        tv_buy2 = (TextView) findViewById(R.id.tv_buy2);
        tv_buy2.setOnClickListener(this);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName(filmArrangement.getMovieHallName());//设置屏幕名称
        seatTableView.setMaxSelected(1);//设置最多选中
        seatTableView.setSeatChecker(this);
    }

    private void initSeat() {
        CommonRequest request = new CommonRequest();
        request.addRequestParam("FilmArrangementID", filmArrangement.getFilmArrangementID()+"");
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                ArrayList<HashMap<String, String>> list = response.getDataList();
                HashMap<String, String> map = list.get(0);
                seat = new int[Integer.valueOf(map.get("MaxRow"))][Integer.valueOf(map.get("MaxCol"))];
                seatTableView.setData(Integer.valueOf(map.get("MaxRow")),Integer.valueOf(map.get("MaxCol")));
                for (int i = 1; i < list.size(); i++) {
                    HashMap<String, String> map2 = list.get(i);
                    seat[Integer.valueOf(map2.get("Row"))][Integer.valueOf(map2.get("Col"))] = 1;
                }
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute("http://10.0.2.2:8080/ServletTest/SeatServlet");
    }

    @Override
    public boolean isValidSeat(int row, int column) {
//                if(column==2) {
//                    return false;
//                }
        return true;
    }

    @Override
    public boolean isSold(int row, int column) {
//                if(row==6&&column==6){
//                    return true;
//                }
        if (seat[row][column] == 1){
            return true;
        }
        return false;
    }

    @Override
    public void checked(int row, int column) {

    }

    @Override
    public void unCheck(int row, int column) {

    }

    @Override
    public String[] checkedSeatTxt(int row, int column) {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy2:
                Intent intent = new Intent(this, BookingActivity.class);
                List<String> seatList = seatTableView.getSelectedSeat();
                String[] seat = seatList.get(0).split(",");
                int row = Integer.valueOf(seat[0]) + 1;
                int col = Integer.valueOf(seat[1]) + 1;
                booking.setRow(row);
                booking.setCol(col);
                intent.putExtra("Booking", booking);
                startActivity(intent);
                break;
        }
    }
}
