package cn.zdh.designdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Toolbar sheet_detail_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        new AppBarLayout(this).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            }
        });
        horizontalAdapter = new HorizontalAdapter_new(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(horizontalAdapter);
        initData();
        CoordinatorLayout coordinatorLayout= (CoordinatorLayout) findViewById(R.id.main_content);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.sheet_detail_toolbar_layout);
//        collapsingToolbar.setTitle("标题"); //设置标题
//        collapsingToolbar.setCollapsedTitleGravity(Gravity.CENTER );//设置收缩后标题的位置
//        collapsingToolbar.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置


        sheet_detail_toolbar = (Toolbar) findViewById(R.id.sheet_detail_toolbar);


        sheet_detail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "返回键", Toast.LENGTH_SHORT).show();
            }
        });



        //监听折叠
        AppBarLayout sheet_detail_app_bar = (AppBarLayout) findViewById(R.id.sheet_detail_app_bar);

        sheet_detail_app_bar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
//                  sheet_detail_toolbar.setNavigationIcon(null);
                    sheet_detail_toolbar.setNavigationIcon(R.mipmap.back);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    sheet_detail_toolbar.setNavigationIcon(R.mipmap.back);


                } else {

                    //中间状态

                }
            }
        });


        translucentStatusBar();
    }

    private HorizontalAdapter_new horizontalAdapter;
    private int positions = -1;//设置初始化选中的item
    private List<String> mList = new ArrayList<>();
    private RecyclerView recyclerView;

    //数据
    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add("第一" + i + "中学");
        }
        horizontalAdapter.setList(mList, positions);

    }


    //折叠监听封装
    public abstract static class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

        public enum State {
            EXPANDED,
            COLLAPSED,
            IDLE
        }

        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    }


    /**
     * 状态栏着色
     */
    private void translucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


}
