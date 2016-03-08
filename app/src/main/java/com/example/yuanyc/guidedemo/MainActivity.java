package com.example.yuanyc.guidedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Android中使用getChildAt方法，不写子布局的情况下开发引导页
 *
 * 进入主页入口一:点击”开始导航“按钮，进入主页
 * 进入主页入口二：滑动到最后一页，再滑动进入主页
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
