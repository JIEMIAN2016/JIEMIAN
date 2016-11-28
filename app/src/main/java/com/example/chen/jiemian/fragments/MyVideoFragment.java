package com.example.chen.jiemian.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chen.jiemian.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by chen on 2016/11/26.
 */
public class MyVideoFragment extends Fragment {
    public static String TAG = MyVideoFragment.class.getSimpleName();
    private View view;
    private PullToRefreshListView refreshListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_video_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.my_video_listview);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        ListView refreshableView = refreshListView.getRefreshableView();
    }
}
