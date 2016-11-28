package com.example.chen.jiemian.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.adapters.DrawerListAdapter;
import com.example.chen.jiemian.adapters.DrawerRecyclerAdapter;
import com.example.chen.jiemian.constants.Urls;
import com.example.chen.jiemian.models.DrawerLeft;
import com.example.chen.jiemian.myinter.StringCallback;
import com.example.chen.jiemian.overwrite.MyListview;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by BYC on 2016/11/26.
 */
public class NewsFragment extends Fragment implements View.OnClickListener, StringCallback, AdapterView.OnItemClickListener {
    public static String TAG = NewsFragment.class.getSimpleName();
    private View view;

    private DrawerLayout mDrawer1;
    private ImageView mChannel;
    private ScrollView mLeft;
    private RecyclerView mRec;
    private DrawerRecyclerAdapter leftAdapter;
    private DrawerLeft.ResultBean.CooperationBean beans;
    private DrawerListAdapter listAdapter;
    private MyListview mListView;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_news_fragment, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initView();
    }

    //初始化activity_main的控件
    private void initView() {
        beans = new DrawerLeft.ResultBean.CooperationBean();
        mDrawer1 = (DrawerLayout) view.findViewById(R.id.main_drawer1);
        mChannel = (ImageView) view.findViewById(R.id.main_channel);
        mChannel.setOnClickListener(this);
        initLeft();


    }

    //侧滑菜单的RecyclerView刷新数据
    private void updateDrawer() {
        //android.util.Log.i("name", "update: ");
        RequestParams params = new RequestParams(Urls.DRAWER_LEFT_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //android.util.Log.i("name", "onSuccess: "+(leftAdapter==null));
                Gson gson = new Gson();
                DrawerLeft drawerLeft = gson.fromJson(result, DrawerLeft.class);
                //android.util.Log.i("name", "onSuccess: "+drawerLeft.getResult().getChannel().size());
                leftAdapter.updateRes(drawerLeft.getResult().getChannel());
                listAdapter.update(drawerLeft.getResult().getCooperation().getData());
                beans = drawerLeft.getResult().getCooperation();
                addDrawerFootview();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                android.util.Log.i("name", "onError: " + ex.toString() + isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                android.util.Log.i("name", "onCancelled: ");
            }

            @Override
            public void onFinished() {

                //android.util.Log.i("name", "onFinished: ");
            }
        });
    }

    private void addDrawerFootview() {
        View inflate = LayoutInflater.from(context).inflate(R.layout.drawer_foot_layout, null);
        TextView foot = (TextView) inflate.findViewById(R.id.drawer_foot);
        foot.setText(beans.getNotes());
        foot.setOnClickListener(this);
        mListView.addFooterView(foot);
    }

    //侧边栏初始化
    private void initLeft() {
        mRec = (RecyclerView) view.findViewById(R.id.drawer_left_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRec.setLayoutManager(linearLayoutManager);
        leftAdapter = new DrawerRecyclerAdapter(null, context);
        leftAdapter.getListener(this);
        mRec.setAdapter(leftAdapter);
        updateDrawer();
        mListView = (MyListview) view.findViewById(R.id.drawer_left_listview);
        mListView.setOnItemClickListener(this);
        listAdapter = new DrawerListAdapter(null, context);
        mListView.setAdapter(listAdapter);
    }

    //点击channel自动打开侧边栏
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_channel:
                mDrawer1.openDrawer(Gravity.LEFT);
                break;
            case R.id.drawer_foot:
                callback(beans.getNotesUrl());
                break;
        }

    }

    //处理a适配器传回的数据,全都是url
    @Override
    public void callback(String url) {
        //第一个数据url为空,直接关闭侧边栏
        if (url.isEmpty()) {
            mDrawer1.closeDrawer(Gravity.LEFT);
        } else {
            Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = beans.getData().get(position).getUrl();
        callback(url);
    }

}
