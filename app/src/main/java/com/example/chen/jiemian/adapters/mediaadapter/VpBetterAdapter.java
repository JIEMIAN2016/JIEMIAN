package com.example.chen.jiemian.adapters.mediaadapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chen.jiemian.models.mediamodels.VoiceModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/12/2.
 */
public class VpBetterAdapter extends PagerAdapter implements View.OnClickListener {
    private List<VoiceModel.ResultBean.CarouselBean> beanList;
    private List<ImageView> imgList;
    private Context context;
    private MyCallback callback;

    public VpBetterAdapter(List<VoiceModel.ResultBean.CarouselBean> beanList, Context context, Fragment fg) {
        if (beanList!=null) {
            this.beanList = beanList;
        }else {
            this.beanList=new ArrayList<>();
        }
        callback= (MyCallback) fg;
        this.context = context;
        changeList();
    }

    //无限循环的初始化操作，前后各添加一个
    private void changeList() {
        List<VoiceModel.ResultBean.CarouselBean> newList=new ArrayList<>();
        if (beanList.size()>0) {
            newList.add(beanList.get(beanList.size()-1));
            for (int i = 0; i < beanList.size(); i++) {
                newList.add(beanList.get(i));
            }
            newList.add(beanList.get(0));
            beanList.clear();
            beanList.addAll(newList);
            imgList=new ArrayList<>();
            for (int i = 0; i < beanList.size(); i++) {
                imgList.add(new ImageView(context));
            }
        }

    }

    @Override
    public int getCount() {
        return beanList.size()>0?beanList.size():0;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imgList.get(position);
        if (imageView.getTag()==null) {
            //Log.i("name", "instantiateItem: "+beanList.get(position).getO_img());
            Picasso.with(context).load(beanList.get(position).getO_img()).into(imageView);
        }
        imageView.setOnClickListener(this);
        imageView.setTag(beanList.get(position).getId());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imgList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(imgList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        callback.callback(tag);
    }
}
