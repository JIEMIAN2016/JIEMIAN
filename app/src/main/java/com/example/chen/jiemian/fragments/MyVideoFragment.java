package com.example.chen.jiemian.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.adapters.mediaadapter.VideoAdapter;
import com.example.chen.jiemian.constants.Urls;
import com.example.chen.jiemian.models.mediamodels.VideoModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;


import java.util.List;

import okhttp3.Call;

/**
 * Created by chen on 2016/11/26.
 */
public class MyVideoFragment extends Fragment implements MyCallback, AbsListView.OnScrollListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public static String TAG = MyVideoFragment.class.getSimpleName();
    private View view;
    private PullToRefreshListView refreshListView;
    private VideoAdapter videoAdapter;
    private ListView mListView;
    private VideoView playView;
    private MediaController controller;
    private List<VideoModel.ResultBean.RstBean> beanList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_video_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initView();
    }

    private void initView() {
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.my_video_listview);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = refreshListView.getRefreshableView();
        videoAdapter = new VideoAdapter(null,getActivity());
        mListView.setAdapter(videoAdapter);
        mListView.setOnScrollListener(this);
        update();
    }

    private void update() {
        OkHttpUtils.get()
                .url(Urls.VIDEO_LIST_URL)
                .build()
                .execute(new com.zhy.http.okhttp.callback.StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("name", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        VideoModel videoModel = gson.fromJson(response, VideoModel.class);
                        videoAdapter.updateRes(videoModel.getResult().getRst());
                        beanList=videoModel.getResult().getRst();
                        videoAdapter.passValue(MyVideoFragment.this);
                    }
                });
    }

    public void callback(String url) {
        Toast.makeText(getActivity(), ""+url, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void intCallback(int num) {

    }

    //初始化VideoView
    @Override
    public void ViewCallback(View item) {
        int position = (int) item.getTag();
        if (playView==null){
            playView= (VideoView) item.findViewById(R.id.video_item_video);
            ImageView img= (ImageView) item.findViewById(R.id.video_item_cover);
            img.setVisibility(View.GONE);
            playView.setVisibility(View.VISIBLE);
            controller=new MediaController(getActivity());
        }else {
            playView.pause();
        }
        playView.setMediaController(controller);
        playVideo(playView,position,item);
        Log.i("name", "ViewCallback: "+position);
    }

    private void playVideo(VideoView playView, int position, View item) {
        playView.setVideoURI(Uri.parse(beanList.get(position).getUrlmobile()));
        playView.start();
        TextView time= (TextView) item.findViewById(R.id.video_item_time);
        time.setText(playView.getDuration()+"s");
        CheckBox play= (CheckBox) item.findViewById(R.id.video_item_pause);
        play.setChecked(true);
        ImageView full = (ImageView) item.findViewById(R.id.video_item_fullscrenn);
        full.setOnClickListener(this);
        play.setOnCheckedChangeListener(this);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            if (!playView.isPlaying()) {
                playView.start();
            }

        }else {
            if (playView.isPlaying()) {
                playView.pause();
            }
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "nihao ", Toast.LENGTH_SHORT).show();
    }
}
