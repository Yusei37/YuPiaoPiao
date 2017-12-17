package com.example.yusei.yupiaopiao;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tabDeal;
    private TextView tabPoi;
    private TextView tabMore;
    private TextView tabUser;

    private FrameLayout ly_content;

    private FirstFragment f2;
    private SecondFragment f1;
    private MovieFragment f3;
    private TheatreFragment f4;

    private FragmentManager fragmentManager;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bindView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_face_black_18dp);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        tabDeal = (TextView)this.findViewById(R.id.txt_deal);
        tabPoi = (TextView)this.findViewById(R.id.txt_poi);
        tabUser = (TextView)this.findViewById(R.id.txt_user);
        tabMore = (TextView)this.findViewById(R.id.txt_more);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tabDeal.setOnClickListener(this);
        tabMore.setOnClickListener(this);
        tabUser.setOnClickListener(this);
        tabPoi.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    public void selected(){
        tabDeal.setSelected(false);
        tabMore.setSelected(false);
        tabPoi.setSelected(false);
        tabUser.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.txt_deal:
                selected();
                tabDeal.setSelected(true);
                if(f1==null){
                //    f1 = new FirstFragment("第一个Fragment");
                    f1 = new SecondFragment();
                    transaction.add(R.id.fragment_container,f1);
                }else{
                    transaction.show(f1);
                }
                break;

            case R.id.txt_more:
                selected();
                tabMore.setSelected(true);
                if(f2==null){
                    f2 = new FirstFragment();
                    transaction.add(R.id.fragment_container,f2);
                }else{
                    transaction.show(f2);
                }
                break;

            case R.id.txt_poi:
                selected();
                tabPoi.setSelected(true);
                if(f3==null){
                    f3 = new MovieFragment();
                    transaction.add(R.id.fragment_container,f3);
                }else{
                    transaction.show(f3);
                }
                break;

            case R.id.txt_user:
                selected();
                tabUser.setSelected(true);
                if(f4==null){
                    f4 = new TheatreFragment();
                    transaction.add(R.id.fragment_container,f4);
                }else{
                    transaction.show(f4);
                }
                break;
        }

        transaction.commit();
    }
}