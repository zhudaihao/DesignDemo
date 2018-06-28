package cn.zdh.designdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Activity1 extends AppCompatActivity {
    private HorizontalAdapter_new horizontalAdapter;
    private int positions = -1;//设置初始化选中的item
    private List<String> mList = new ArrayList<>();
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        horizontalAdapter = new HorizontalAdapter_new(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(horizontalAdapter);

        setListener();
        initData();



    }


    //监听
    private void setListener() {
        horizontalAdapter.setOnItemClickListener(new HorizontalAdapter_new.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                horizontalAdapter.setList(mList, position);

            }
        });
    }

    //数据
    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add("第一" + i + "中学");
        }
        horizontalAdapter.setList(mList, positions);

    }
}
