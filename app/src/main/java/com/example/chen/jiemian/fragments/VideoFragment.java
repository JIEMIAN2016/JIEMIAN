package com.example.chen.jiemian.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.constants.Constant;
import com.example.chen.jiemian.overwrite.MyGifImageview;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by BYC on 2016/11/26.
 */
public class VideoFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    public static String TAG = VideoFragment.class.getSimpleName();
    private View view;
    private RadioButton mVideo;
    private RadioButton mVoice;
    private RadioGroup mSwitch;
    private Fragment showFragment;
    private MyGifImageview gif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_video_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mSwitch = (RadioGroup) view.findViewById(R.id.video_switch_fragment);
        mSwitch.setOnCheckedChangeListener(this);
        mVideo = (RadioButton) view.findViewById(R.id.video_video);
        mVoice = (RadioButton) view.findViewById(R.id.video_voice);
        gif = (MyGifImageview) view.findViewById(R.id.media_gif);
        mVideo.setChecked(true);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showFragment = new MyVideoFragment();
        transaction.add(R.id.media_frame,showFragment,MyVideoFragment.TAG);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.video_video:
                gif.stop();
                Constant.IS_PLAY_VIDEO=false;
                changeFragment(MyVideoFragment.TAG,MyVideoFragment.class);
                break;
            case R.id.video_voice:
                Constant.IS_PLAY_VIDEO=true;
                gif.isInit(true);
                changeFragment(MyVoiceFragment.TAG,MyVoiceFragment.class);
                break;
        }
    }

    private void changeFragment(String TAG, Class myFragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        if (fragmentManager.findFragmentByTag(TAG)!=showFragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(showFragment);
            showFragment=fragmentManager.findFragmentByTag(TAG);
            if (showFragment!=null) {
                transaction.show(showFragment);
            }else {
                try {
                    showFragment = (Fragment) myFragment.getConstructor().newInstance();
                    transaction.add(R.id.media_frame,showFragment,TAG);
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
            transaction.commit();
        }
    }
}
