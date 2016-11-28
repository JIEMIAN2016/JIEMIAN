package com.example.chen.jiemian.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.models.DrawerLeft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/25.
 */
public class DrawerListAdapter extends BaseAdapter {
    private List<DrawerLeft.ResultBean.CooperationBean.DataBean> beanList;
    private LayoutInflater inflater;

    public DrawerListAdapter(List<DrawerLeft.ResultBean.CooperationBean.DataBean> beanList, Context context) {
        if (beanList != null) {
            this.beanList = beanList;
        } else {
            this.beanList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    public void update(List<DrawerLeft.ResultBean.CooperationBean.DataBean> beans) {
        if (beans != null) {
            this.beanList.clear();
            this.beanList.addAll(beans);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return beanList.size() > 0 ? beanList.size() : 0;
    }

    @Override
    public DrawerLeft.ResultBean.CooperationBean.DataBean getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView bigTv, smallTv;
        convertView = inflater.inflate(R.layout.drawer_listview_item_layout, parent, false);
        bigTv = (TextView) convertView.findViewById(R.id.drawer_left_big);
        smallTv = (TextView) convertView.findViewById(R.id.drawer_left_smll);
        bigTv.setText(getItem(position).getTitle());
        smallTv.setText(getItem(position).getSlogan());
        return convertView;
    }
}
