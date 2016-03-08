package com.example.yuanyc.guidedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Android中使用getChildAt方法，不写子布局的情况下开发引导页
 *
 * 进入主页入口一:点击”开始导航“按钮，进入主页
 * 进入主页入口二：滑动到最后一页，再滑动进入主页
 */
public class GuideActivity extends Activity {
    private ViewPager viewPager;
    private ImageView button;
    private ImageView check;
    private ImageView hint;
    private boolean isChecked;
    /**
     * 判断当前是否正在滑动
     */
    private boolean isScrolling;
    /**
     * 底部小点图片数组
     */
    private ImageView[] points;
    private LinearLayout ll;
    /**
     * 记录当前选中位置
     */
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_guide);
        init();
        //初始化底部小点
        initPoint();
        //是否勾选了一键导航按钮
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked) {
                    check.setImageResource(R.mipmap.guide_selected);
                    isChecked = false;
                } else {
                    check.setImageResource(R.mipmap.guide_unselected);
                    isChecked = true;
                }
            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked) {
                    check.setImageResource(R.mipmap.guide_selected);
                    isChecked = false;
                } else {
                    check.setImageResource(R.mipmap.guide_unselected);
                    isChecked = true;
                }
            }
        });
        //进入应用
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChecked) {
                    //TODO一键导航的逻辑

                }
                //TODO页面跳转
                enter();
            }
        });
        //设置缓存页
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewPager.getChildCount();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return container.getChildAt(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 初始化操作
     */
    private void init() {
        viewPager = (ViewPager) findViewById(R.id.guide);
        button = (ImageView) findViewById(R.id.guide_button);
        check = (ImageView) findViewById(R.id.guide_check);
        hint = (ImageView) findViewById(R.id.guide_hint);
        ll = (LinearLayout) findViewById(R.id.guide_point);
    }

    /**
     * 初始化底部小点
     */
    private void initPoint() {
        points = new ImageView[3];

        //循环取得小点图片
        for (int i = 0; i < 3; i++) {
            points[i] = (ImageView) ll.getChildAt(i);
            points[i].setEnabled(true);//都设为灰色
            points[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        points[currentIndex].setEnabled(false);//设置为白色，即选中状态
    }

    /**
     * 当前引导小点的选中
     */
    private void setCurDot(int positon) {
        if (positon > 2) {
            ll.setVisibility(View.GONE);
        } else {
            ll.setVisibility(View.VISIBLE);
        }
        if (positon < 0 || positon > 2 || currentIndex == positon) {
            return;
        }

        points[positon].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = positon;
    }

    /**
     * 进入下一个界面
     */
    private void enter() {
        Intent intent = new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        GuideActivity.this.finish();
    }

    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == viewPager.getChildCount() - 1 && positionOffset == 0 && positionOffsetPixels == 0 && isScrolling) {
                //TODO页面跳转
                if (!isChecked) {
                    //TODO一键导航的逻辑

                }
                //TODO页面跳转
                enter();
            }
        }

        @Override
        public void onPageSelected(int position) {
            //设置底部小点选中状态
            setCurDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 1) {
                isScrolling = true;
            } else {
                isScrolling = false;
            }
        }
    }
}
