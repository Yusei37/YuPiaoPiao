package com.example.yusei.yupiaopiao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.azoft.carousellayoutmanager.DefaultChildSelectionListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmArrangementActivity extends AppCompatActivity {

    private List<FilmArrangement> filmArrangementList = new ArrayList<>();
    private List<FilmArrangement> filmArrangementListCopy = new ArrayList<>();
    private FilmArrangementAdapter adapter;
    private Theatre theatre;
    private List<Movie> movieList = new ArrayList<>();

    private Movie2Adapter movie2Adapter;

//    private Context mContext;
//    private ViewFlipper vflp_help;
//    private String[] resId = {"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2507566212.jpg","https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.jpg",
//            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2507361925.jpg"};
//
//    private final static int MIN_MOVE = 200;   //最小距离
//    private MyGestureListener mgListener;
//    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmarrangement);
        theatre = getIntent().getParcelableExtra("Theatre");
        initFilmArrangement();
        for (Movie m:MovieFragment.movieList) {
            movieList.add(m);
        }

//        mContext = FilmArrangementActivity.this;
//        mgListener = new MyGestureListener();
//        mDetector = new GestureDetector(this, mgListener);
//        vflp_help = (ViewFlipper) findViewById(R.id.vflp_help);
//        //动态导入添加子View
//        for(int i = 0;i < MovieFragment.movieList.size();i++){
//            vflp_help.addView(getView(MovieFragment.movieList.get(i).getPoster()));
//        }

        final CarouselLayoutManager layoutManager2 = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager2.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        layoutManager2.setMaxVisibleItems(2);
        final RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler_movie2);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        movie2Adapter = new Movie2Adapter(movieList);
        movie2Adapter.notifyDataSetChanged();
        recyclerView2.setAdapter(movie2Adapter);
        recyclerView2.addOnScrollListener(new CenterScrollListener());
//        DefaultChildSelectionListener.initCenterItemListener(new DefaultChildSelectionListener.OnCenterItemClickListener(){
//
//            @Override
//            public void onCenterItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {
//                final int position = recyclerView.getChildLayoutPosition(v);
//                Toast.makeText(MyApplication.getContext(), position, Toast.LENGTH_SHORT).show();
//            }
//        },recyclerView2,layoutManager2);

        layoutManager2.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {
            @Override
            public void onCenterItemChanged(int adapterPosition) {
                filmArrangementListCopy.clear();
                for (FilmArrangement fl : filmArrangementList) {
                    if (fl.getMovieName().equals(movieList.get(adapterPosition).getMovieName())) {
                        filmArrangementListCopy.add(fl);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });


                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_filmarrangement);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FilmArrangementAdapter(filmArrangementListCopy);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


//    //重写onTouchEvent触发MyGestureListener里的方法
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return mDetector.onTouchEvent(event);
//    }
//
//
//    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
//    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
//            if(e1.getX() - e2.getX() > MIN_MOVE){
//                vflp_help.setInAnimation(mContext,R.anim.right_in);
//                vflp_help.setOutAnimation(mContext, R.anim.right_out);
//                vflp_help.showNext();
//            }else if(e2.getX() - e1.getX() > MIN_MOVE){
//                vflp_help.setInAnimation(mContext,R.anim.left_in);
//                vflp_help.setOutAnimation(mContext, R.anim.left_out);
//                vflp_help.showPrevious();
//            }
//            return true;
//        }
//    }
//
//    private View getView(String resId){
//        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewfilpper_item, null);
//        ImageView img = (ImageView) view.findViewById (R.id.iv_movie);
//        Glide.with(mContext).load(resId).fitCenter().error(R.mipmap.ic_launcher).into(img);
//        return view;
//    }

    private void initFilmArrangement() {
        CommonRequest request = new CommonRequest();
        request.addRequestParam("TheatreName", theatre.getTheatreName());
        new HttpPostTask(request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                ArrayList<HashMap<String, String>> list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    HashMap<String, String> map = list.get(i);
                    FilmArrangement filmArrangement = new FilmArrangement();
                    String date = map.get("BeginTime");
                    filmArrangement.setBeginTime(new java.util.Date(Long.valueOf(date).longValue()));
                    date = map.get("EndTime");
                    filmArrangement.setEndTime(new java.util.Date(Long.valueOf(date).longValue()));
                    filmArrangement.setMovieHallName(map.get("MovieHallName"));
                    filmArrangement.setPrice(Double.valueOf(map.get("Price")));
                    filmArrangement.setMovieName(map.get("MovieName"));
                    filmArrangementList.add(filmArrangement);
                    filmArrangementListCopy.add(filmArrangement);
                }
                List<Movie> temp = new ArrayList<>();
                for (FilmArrangement fl : filmArrangementList) {
                    for (Movie m :movieList) {
                        if (m.getMovieName().equals(fl.getMovieName())) {
                            if (!temp.contains(m)) {
                                temp.add(m);
                            }
                        }
                    }
                }
                movieList.clear();
                for (Movie m : temp) {
                    movieList.add(m);
                }
                movie2Adapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyApplication.getContext(), failMsg, Toast.LENGTH_SHORT).show();
            }
        }).execute("http://10.0.2.2:8080/ServletTest/FilmArrangementServlet");
    }

}
