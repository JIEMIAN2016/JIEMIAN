package com.example.chen.jiemian.adapters.mediaadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chen.jiemian.models.mediamodels.VoiceModel;

/**
 * Created by chen on 2016/11/30.
 */
public class VoiceAdapter extends BaseAdapter {
    private VoiceModel model;
    @Override
    public int getCount() {
        if (model==null) {
            return 0;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
