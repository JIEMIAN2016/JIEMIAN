package com.example.chen.jiemian.adapters.mediaadapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chen.jiemian.models.mediamodels.VideoModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/30.
 */
public class VpAdapter extends PagerAdapter implements View.OnClickListener {
    private List<VideoModel.ResultBean.CarouselBean> vpList;
    List<ImageView> imgList;
    private Context context;
    private MyCallback listener;
    public VpAdapter(List<VideoModel.ResultBean.CarouselBean> vpList, Context context) {
        if (vpList!=null) {
            this.vpList = vpList;
        }else {
            this.vpList=new ArrayList<>();
        }
        this.context=context;
        initList();
    }

    public void initList(){
        List<VideoModel.ResultBean.CarouselBean> newList=new ArrayList<>();
        if (vpList.size()>1) {
            newList.add(vpList.get(vpList.size()-1));
            newList.addAll(vpList);
            newList.add(vpList.get(0));
        }
        vpList.clear();
        vpList.addAll(newList);
    }
    public void initListener(Fragment fg){
        listener= (MyCallback) fg;
    }
    @Override
    public int getCount() {
        imgList=new ArrayList<>();
        int size = vpList.size();
        //Log.i("name", "getCount: "+size);
        size=size>0?size:0;
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(context);
            imgList.add(imageView);
        }
        return size>0?size:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public ImageView instantiateItem(ViewGroup container, int position) {
        //tv.setText(vpList.get(position).getTitle());
        //Log.i("name", "instantiateItem: "+vpList.get(position).getO_image());
        ImageView img=imgList.get(position);
        img.setOnClickListener(this);
        img.setTag(position);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        //img.setMaxHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        Picasso.with(context).load(vpList.get(position).getO_image()).into(img);
        container.addView(imgList.get(position));
        return imgList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(imgList.get(position));
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        listener.callback(vpList.get(tag).getV_url());
    }
}
