package com.example.chen.jiemian.adapters.mediaadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.chen.jiemian.R;

/**
 * Created by chen on 2016/11/28.
 */
public class VideoAdapter extends BaseAdapter {
    @Override
    public int getCount() {
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
    public class ViewHolder{
        VideoView video;
        TextView comments,title,author;
        ImageView icon;
        public void ViewHolder(View itemView){
            video= (VideoView) itemView.findViewById(R.id.video_item_video);


        }
    }
}
