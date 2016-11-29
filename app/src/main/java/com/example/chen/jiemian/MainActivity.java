package com.example.chen.jiemian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.chen.jiemian.fragments.CommunityFragment;
import com.example.chen.jiemian.fragments.MineFragment;
import com.example.chen.jiemian.fragments.NewsFragment;
import com.example.chen.jiemian.fragments.VideoFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private Fragment showFragment;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mRadioGroup = (RadioGroup) findViewById(R.id.main_rg);
        mRadioGroup.setOnCheckedChangeListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showFragment = new NewsFragment();
        transaction.add(R.id.main_fg, showFragment, NewsFragment.TAG);
        transaction.commit();

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.main_news:
                transaction.hide(showFragment);
                showFragment = manager.findFragmentByTag(NewsFragment.TAG);
                if (showFragment != null) {
                    transaction.show(showFragment);
                } else {
                    showFragment = new NewsFragment();
                    transaction.add(R.id.main_fg, showFragment, NewsFragment.TAG);
                }
                break;
            case R.id.main_video:
                transaction.hide(showFragment);
                showFragment = manager.findFragmentByTag(VideoFragment.TAG);
                if (showFragment != null) {
                    transaction.show(showFragment);
                } else {
                    showFragment = new VideoFragment();
                    transaction.add(R.id.main_fg, showFragment, VideoFragment.TAG);
                }
                break;
            case R.id.main_community:
                transaction.hide(showFragment);
                showFragment = manager.findFragmentByTag(CommunityFragment.TAG);
                if (showFragment != null) {
                    transaction.show(showFragment);
                } else {
                    showFragment = new CommunityFragment();
                    transaction.add(R.id.main_fg, showFragment, CommunityFragment.TAG);
                }
                break;
            case R.id.main_mine:
                transaction.hide(showFragment);
                showFragment = manager.findFragmentByTag(MineFragment.TAG);
                if (showFragment != null) {
                    transaction.show(showFragment);
                } else {
                    showFragment = new MineFragment();
                    transaction.add(R.id.main_fg, showFragment, MineFragment.TAG);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
