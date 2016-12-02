package com.example.chen.jiemian.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.jiemian.FullScreenActivity;
import com.example.chen.jiemian.R;
import com.example.chen.jiemian.adapters.mediaadapter.VideoAdapter;
import com.example.chen.jiemian.adapters.mediaadapter.VpAdapter;
import com.example.chen.jiemian.constants.Constant;
import com.example.chen.jiemian.constants.Urls;
import com.example.chen.jiemian.models.mediamodels.VideoModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by chen on 2016/11/26.
 */
public class MyVideoFragment extends Fragment implements MyCallback, ViewPager.OnPageChangeListener,PullToRefreshListView.OnRefreshListener2 {
    private static final int REQUEST_BACK = 12;
    public static String TAG = MyVideoFragment.class.getSimpleName();
    private View view;
    public static boolean hasHead=false;
    private PullToRefreshListView refreshListView;
    private VideoAdapter videoAdapter;
    private ListView mListView;
    private static int pageNum = 0;
    private int position = 0;
    private static int num=1;
    private List<VideoModel.ResultBean.RstBean> beanList;
    private List<VideoModel.ResultBean.CarouselBean> vpList;
    private List<Button> buttons;
    private ViewPager vp;
    private static final int SWITCH_VP = 3000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCH_VP:
                    if (position<vpList.size()) {
                        vp.setCurrentItem(position + 1);
                    } else {
                        vp.setCurrentItem(0);
                    }
                    mHandler.sendEmptyMessageDelayed(SWITCH_VP, 3000);
            }
        }
    };
    private TextView title;
    private Bitmap bitmapNor;
    private Bitmap bitmapSel;
    private VpAdapter vpAdapter;


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
        setRefresh(refreshListView);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = refreshListView.getRefreshableView();
        videoAdapter = new VideoAdapter(null, getActivity());
        mListView.setAdapter(videoAdapter);
        update();
    }

    private void setRefresh(PullToRefreshListView refreshListView) {
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(this);
        refreshListView.setRefreshing();
        refreshListView.setPullLabel("继续下拉刷新!");
        refreshListView.setRefreshingLabel("正在刷新！");
        refreshListView.setReleaseLabel("释放刷新！");
    }

    private String getTimeNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd hh:MM:ss");
        String format = simpleDateFormat.format(new java.util.Date());
        return format;
    }


    private void initHead() {
        View head = LayoutInflater.from(getActivity()).inflate(R.layout.my_video_head, null);
        mListView.addHeaderView(head);
        LinearLayout dots = (LinearLayout) head.findViewById(R.id.video_head_dots);
        vp = (ViewPager) head.findViewById(R.id.video_head_vp);
        title = (TextView) head.findViewById(R.id.video_head_title);
        vpAdapter = new VpAdapter(vpList, getActivity());
        vpAdapter.initListener(this);
        vp.setAdapter(vpAdapter);
        buttons = new ArrayList<>();
        for (int i = 0; i < pageNum; i++) {
            Button imageButton = new Button(getActivity());
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(25,25));
            if (i == 0) {
                imageButton.setBackgroundResource(R.mipmap.dot_selected);
            } else {
                imageButton.setBackgroundResource(R.mipmap.dot_normal);
            }
            buttons.add(imageButton);
            dots.addView(imageButton);
        }
        vp.addOnPageChangeListener(this);
        mHandler.sendEmptyMessageDelayed(SWITCH_VP, 3000);
    }

    private void update() {
        OkHttpUtils.get()
                .url(Urls.VIDEO_LIST_URL)
                .build()
                .execute(new com.zhy.http.okhttp.callback.StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("name", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        VideoModel videoModel = gson.fromJson(response, VideoModel.class);
                        videoAdapter.updateRes(videoModel.getResult().getRst());
                        beanList = videoModel.getResult().getRst();
                        vpList = videoModel.getResult().getCarousel();
                        //Log.i("name", "onResponse: "+vpList.size());
                        pageNum = vpList.size();
                        videoAdapter.passValue(MyVideoFragment.this);
                        if (!hasHead) {
                            initHead();
                            hasHead=true;
                        }
                        if (refreshListView.isRefreshing()) {
                            refreshListView.onRefreshComplete();
                        }
                    }
                });
    }

    public void callback(String url) {
        Toast.makeText(getActivity(), "url=" + url, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void intCallback(int num) {
        Toast.makeText(getActivity(), "title:" + beanList.get(num).getTitle(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "url:" + beanList.get(num).getUrlmobile(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), FullScreenActivity.class);
        intent.putExtra("url", beanList.get(num).getUrlmobile());
        intent.putExtra("title", beanList.get(num).getTitle());
        startActivityForResult(intent,REQUEST_BACK);
    }

    @Override
    public void ViewCallback(View item) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        if ( vpList.size() > 1) { //多于1，才会循环跳转
            if ( position < 1) { //首位之前，跳转到末尾（N）
                position = pageNum;
                //Log.i("name", "onPageSelected: position"+position);
                //Log.i("name", "onPageSelected: size"+vpList.size());
                vp.setCurrentItem(position, false);
            } else if ( position > pageNum) { //末位之后，跳转到首位（1）
                vp.setCurrentItem(1, false); //false:不显示跳转过程的动画
                position = 1;
            }
        }
        switch (position) {
            case 5:
            case 1:
                changeButton(0);
                title.setText(vpList.get(0).getTitle());
                break;
            case 2:
                changeButton(1);
                title.setText(vpList.get(1).getTitle());
                break;
            case 3:
                changeButton(2);
                title.setText(vpList.get(2).getTitle());
                break;
            case 4:
            case 0:
                title.setText(vpList.get(3).getTitle());
                changeButton(3);
                break;
        }

    }

    private void changeButton(int position) {
        for (int i = 0; i < pageNum; i++) {
            if (i == position) {
                //Log.i(TAG, "onPageSelected: " + position);
                buttons.get(i).setBackgroundResource(R.mipmap.dot_selected);
            } else {
                buttons.get(i).setBackgroundResource(R.mipmap.dot_normal);
            }

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_BACK) {
            //
            refreshListView.setRefreshing();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (Constant.VideoLastUpdate!=null) {
            refreshListView.setLastUpdatedLabel("上次刷新时间"+ Constant.VideoLastUpdate);
        }
        Constant.VideoLastUpdate=getTimeNow();
        update();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        addNextPage();
    }

    private void addNextPage() {
        String videoListUrl = Urls.VIDEO_LIST_URL;
        int i = videoListUrl.lastIndexOf("/");
        String substring = videoListUrl.substring(0, i + 1);
        String pageUrl = substring + (++num) + ".json";
        if (num<=5) {
            OkHttpUtils.get()
                    .url(pageUrl)
                    .build()
                    .execute(new com.zhy.http.okhttp.callback.StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i("name", "onError: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Gson gson = new Gson();
                            VideoModel videoModel = gson.fromJson(response, VideoModel.class);
                            videoAdapter.addRes(videoModel.getResult().getRst());
                            //Log.i("name", "onResponse: "+vpList.size());
                            if (refreshListView.isRefreshing()) {
                                refreshListView.onRefreshComplete();
                            }

                        }
                    });

        }

    }
}
