package com.example.chen.jiemian.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.adapters.mediaadapter.VoiceAdapter;
import com.example.chen.jiemian.adapters.mediaadapter.VpBetterAdapter;
import com.example.chen.jiemian.constants.Constant;
import com.example.chen.jiemian.constants.Urls;;
import com.example.chen.jiemian.models.mediamodels.VoiceModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by chen on 2016/11/26.
 */
public class MyVoiceFragment extends Fragment implements MyCallback,PullToRefreshListView.OnRefreshListener2, AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, View.OnClickListener {
    public static String TAG = MyVoiceFragment.class.getSimpleName();
    private View view;
    private PullToRefreshListView refreshView;
    private ListView mListview;
    private ViewPager viewPager;
    private TextView headTitle;

    private List<VoiceModel.ResultBean.CarouselBean> beanList;
    private List<ImageButton> buttons;
    private VoiceModel.ResultBean model;

    private VoiceAdapter voiceAdapter;

    private int num=1;
    private int pageNum=0;
    private boolean hasHead=false;
    private int SEITCH_PIC=1111;
    private int position=0;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==SEITCH_PIC) {
                viewPager.setCurrentItem(position+1);
                mHandler.sendEmptyMessageDelayed(SEITCH_PIC,4000);
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_voice_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        refreshView=(PullToRefreshListView) view.findViewById(R.id.voice_listview);
        setRefresh(refreshView);
        mListview = refreshView.getRefreshableView();

        mListview.setOnItemClickListener(this);
        voiceAdapter = new VoiceAdapter(getActivity(),null);
        mListview.setAdapter(voiceAdapter);
        voiceAdapter.initLisetner(this);
        update();
    }

    private void initHead() {
        View head = LayoutInflater.from(getActivity()).inflate(R.layout.my_voice_head, null);
        headTitle = (TextView) head.findViewById(R.id.voice_head_title);
        LinearLayout dots = (LinearLayout) head.findViewById(R.id.voice_head_dots);
        getButtons(dots);

        mListview.addHeaderView(head);
        viewPager = (ViewPager) head.findViewById(R.id.voice_head_vp);
        VpBetterAdapter vpBetterAdapter = new VpBetterAdapter(beanList, getActivity(), this);
        viewPager.setAdapter(vpBetterAdapter);
        viewPager.addOnPageChangeListener(this);
        mHandler.sendEmptyMessageDelayed(SEITCH_PIC,4000);
        viewPager.setCurrentItem(1);
        getMore(head);
    }

    private void getMore(View head) {
        ImageView img1 = (ImageView) head.findViewById(R.id.voice_head_img1);
        TextView title1=(TextView)head.findViewById(R.id.voice_head_title1);
        Picasso.with(getActivity()).load(model.getNavigation().get(0).getImg_url()).into(img1);
        title1.setText(model.getNavigation().get(0).getTitle());
        ImageView img2 = (ImageView) head.findViewById(R.id.voice_head_img2);
        TextView title2=(TextView)head.findViewById(R.id.voice_head_title2);
        Picasso.with(getActivity()).load(model.getNavigation().get(1).getImg_url()).into(img2);
        title2.setText(model.getNavigation().get(1).getTitle());
        ImageView img3 = (ImageView) head.findViewById(R.id.voice_head_img3);
        TextView title3=(TextView)head.findViewById(R.id.voice_head_title3);
        Picasso.with(getActivity()).load(model.getNavigation().get(2).getImg_url()).into(img3);
        title3.setText(model.getNavigation().get(2).getTitle());
        ImageView img4 = (ImageView) head.findViewById(R.id.voice_head_img4);
        TextView title4=(TextView)head.findViewById(R.id.voice_head_title4);
        Picasso.with(getActivity()).load(model.getNavigation().get(3).getImg_url()).into(img4);
        title4.setText(model.getNavigation().get(3).getTitle());
        RelativeLayout more = (RelativeLayout) head.findViewById(R.id.voice_head_more);
        LinearLayout item1 = (LinearLayout) head.findViewById(R.id.voice_head_item1);
        LinearLayout item2 = (LinearLayout) head.findViewById(R.id.voice_head_item2);
        LinearLayout item3 = (LinearLayout) head.findViewById(R.id.voice_head_item3);
        LinearLayout item4 = (LinearLayout) head.findViewById(R.id.voice_head_item4);
        more.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
    }

    public void getButtons(LinearLayout dots){
        buttons=new ArrayList<>();
        for (int i = 0; i < pageNum; i++) {
            ImageButton button = new ImageButton(getActivity());
            button.setLayoutParams(new LinearLayout.LayoutParams(25,25));
            if (i==0){
                button.setImageResource(R.mipmap.dot_selected);
            }else {
                button.setImageResource(R.mipmap.dot_normal);
            }
            buttons.add(button);
            dots.addView(button);
        }
    }

    private void setRefresh(PullToRefreshListView refreshListView) {
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(this);
        refreshListView.setRefreshing();
        refreshListView.setPullLabel("继续下拉刷新!");
        refreshListView.setRefreshingLabel("正在刷新！");
        refreshListView.setReleaseLabel("释放刷新！");
    }
    private void update() {
        OkHttpUtils.get()
                .url(Urls.VOICE_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        //Log.i("name", "onResponse: ");
                        VoiceModel voiceModel = gson.fromJson(response, VoiceModel.class);
                        voiceAdapter.updateRES(voiceModel.getResult());

                        model=voiceModel.getResult();
                        beanList=new ArrayList<>();
                        beanList=voiceModel.getResult().getCarousel();
                        pageNum=beanList.size();
                        if (!hasHead) {
                            initHead();
                            hasHead=true;
                        }

                        if (refreshView.isRefreshing()) {
                            refreshView.onRefreshComplete();
                        }
                    }
                });

    }

    @Override
    public void callback(String url) {
        if (url.length()<8) {
            Toast.makeText(getActivity(), ""+url, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void intCallback(int num) {
        if (num== Constant.REFRESH) {
            refreshRecommend();
        }else {
            Toast.makeText(getActivity(), ""+num, Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshRecommend() {
        Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ViewCallback(View item) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (Constant.VoiceLastUpdate!=null) {
            refreshView.setLastUpdatedLabel("上次刷新时间"+ Constant.VideoLastUpdate);
        }
        Constant.VoiceLastUpdate=getTimeNow();
        update();
    }
    private String getTimeNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd hh:MM:ss");
        String format = simpleDateFormat.format(new java.util.Date());
        Log.i("name", "getTimeNow: ");
        return format;
    }


    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        addNextPage();
    }

    private void addNextPage() {
        String videoListUrl = Urls.VOICE_LIST_URL;
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
                            VoiceModel voiceModel = gson.fromJson(response, VoiceModel.class);
                            voiceAdapter.addRES(voiceModel.getResult());
                            //Log.i("name", "onResponse: "+vpList.size());
                            if (refreshView.isRefreshing()) {
                                refreshView.onRefreshComplete();
                            }

                        }
                    });

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position=position;

        //
        if (beanList.size()>1) {
            if(position<1){
                position=pageNum;
                viewPager.setCurrentItem(pageNum,false);
            }else if (position>pageNum){
                viewPager.setCurrentItem(1,false);
                position=1;
            }
        }


        if (position<=1) {
            headTitle.setText(model.getCarousel().get(0).getTitle());
            changeButton(0);
        }else if (position<=pageNum){
            headTitle.setText(model.getCarousel().get(position-1).getTitle());
            changeButton(position-1);
        }else {
            headTitle.setText(model.getCarousel().get(position-2).getTitle());
            changeButton(position-2);
        }
    }

    private void changeButton(int position) {
        for (int i = 0; i < buttons.size(); i++) {
            if (i==position){
                buttons.get(i).setImageResource(R.mipmap.dot_selected);
            }else {
                buttons.get(i).setImageResource(R.mipmap.dot_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voice_head_item1:
                Toast.makeText(getActivity(), ""+model.getNavigation().get(0).getSid(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.voice_head_item2:
                Toast.makeText(getActivity(), ""+model.getNavigation().get(1).getSid(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.voice_head_item3:
                Toast.makeText(getActivity(), ""+model.getNavigation().get(2).getSid(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.voice_head_item4:
                Toast.makeText(getActivity(), ""+model.getNavigation().get(3).getSid(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.voice_head_more:
                Toast.makeText(getActivity(), "你好", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
