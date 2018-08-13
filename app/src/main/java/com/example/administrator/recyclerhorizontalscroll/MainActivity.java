package com.example.administrator.recyclerhorizontalscroll;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.recyclerhorizontalscroll.R;

import com.example.administrator.recyclerhorizontalscroll.TodayAccountAdapter;
import com.example.administrator.recyclerhorizontalscroll.entity;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private int leftPos;//用于记录CustomHScrollView的初始位置
    private int topPos;
    private RecyclerView recyclerView;
    TodayAccountAdapter accountAdapter;
    private List<entity> data;
    private LinearLayout head;

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entity item = new entity("测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9", "测试10", "测试11", "测试12");
            data.add(item);
        }
        head = findViewById(R.id.head);
        head.setFocusable(true);
        head.setClickable(true);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        accountAdapter = new TodayAccountAdapter(this, data, head);
        recyclerView.setAdapter(accountAdapter);

        head.setOnTouchListener(new MyTouchLinstener());
        recyclerView.setOnTouchListener(new MyTouchLinstener());
    }

    /**
     * 记录CustomHScrollView的初始位置
     *
     * @param l
     * @param t
     */
    public void setPosData(int l, int t) {
        this.leftPos = l;
        this.topPos = t;
    }

    class MyTouchLinstener implements View.OnTouchListener {
        float lastX = 0;
        float lastY = 0;
        private boolean isClick = false;
        private long downTime = 0;

        @Override
        public boolean onTouch(View arg0, MotionEvent ev) {
            //判断是否是点击
            float tempX = ev.getX();
            float tempY = ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = tempX;
                    lastY = tempY;
                    downTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    isClick = false;
                    break;
                case MotionEvent.ACTION_UP:
                    if (Math.abs(lastX - tempX) > 10 || Math.abs(lastY - tempY) > 10) {
                        isClick = false;
                    } else {
                        isClick = true;
                    }
                    long timeDef = System.currentTimeMillis() - downTime;
                    if (timeDef <= 40 && isClick) {
                        isClick = false;
                    }
                    break;
            }
            if (isClick) {
                int position = accountAdapter.getTouchPosition();
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            } else {
                //当在表头和listView控件上touch时，将事件分发给 ScrollView
                HorizontalScrollView headSrcrollView = (HorizontalScrollView) head.findViewById(R.id.h_scrollView);
                headSrcrollView.onTouchEvent(ev);
            }
            return false;
        }

    }
}
