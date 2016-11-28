package com.example.chen.jiemian;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chen.jiemian.fragments.newsaddfragment.AllFragment;

public class NewsAddActivity extends HorizontalActivity implements  RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private Fragment allFragment;
    private RadioGroup mRadioGroup;
    private TextView mNewsTips;
    private ImageView mNewsAddBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_add);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_news_add_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mNewsTips = (TextView) findViewById(R.id.main_news_tip);
        mNewsAddBack = (ImageView) findViewById(R.id.main_news_add_back);
        mNewsAddBack.setOnClickListener(this);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        allFragment = new AllFragment();
        transaction.add(R.id.main_news_fg, allFragment, AllFragment.tag);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.main_news_add_all_radiobutton:
                mNewsTips.setText("您可以订阅或者查看频道");
                transaction.hide(allFragment);
                allFragment = manager.findFragmentByTag(AllFragment.tag);
                if (allFragment != null) {
                    transaction.show(allFragment);
                } else {
                    allFragment = new AllFragment();
                    transaction.add(R.id.main_news_fg, allFragment, AllFragment.tag);
                }
                break;
            case R.id.main_news_add_mine_radiobutton:
                mNewsTips.setText("可拖动排序，“－”为取消订阅");
                transaction.hide(allFragment);
                allFragment = manager.findFragmentByTag(com.example.chen.jiemian.fragments.newsaddfragment.MineFragment.tag);
                if (allFragment != null) {
                    transaction.show(allFragment);
                } else {
                    allFragment = new com.example.chen.jiemian.fragments.newsaddfragment.MineFragment();
                    transaction.add(R.id.main_news_fg, allFragment, com.example.chen.jiemian.fragments.newsaddfragment.MineFragment.tag);
                }
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_news_add_back:
                this.finish();
                break;
        }
    }
}
