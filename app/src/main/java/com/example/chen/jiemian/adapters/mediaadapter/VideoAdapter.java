package com.example.chen.jiemian.adapters.mediaadapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.models.mediamodels.VideoModel;
import com.example.chen.jiemian.myinter.MyCallback;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/28.
 */
public class VideoAdapter extends BaseAdapter implements View.OnClickListener {
    private MyCallback listener;
    private List<VideoModel.ResultBean.RstBean> beanList;
    private LayoutInflater inflater;
    private View itemView;
    public VideoAdapter(List<VideoModel.ResultBean.RstBean> beanList, Context context) {
        if (beanList!=null) {
            this.beanList = beanList;
        }else {
            this.beanList=new ArrayList<>();
        }
        inflater=LayoutInflater.from(context);
    }

    public void updateRes(List<VideoModel.ResultBean.RstBean> beanList){
        if (beanList!=null) {
            this.beanList.clear();
            this.beanList.addAll(beanList);
            notifyDataSetChanged();
        }
    }
    public void passValue(Fragment fg){
        listener= (MyCallback) fg;
    }
    @Override
    public int getCount() {
        return beanList.size()>0?beanList.size():0;
    }

    @Override
    public VideoModel.ResultBean.RstBean getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.my_video_list_item_layout,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (beanList.get(position).getComment().equals("0")) {
            //Log.i("name", "getView: "+beanList.get(position).getComment());
            holder.comments.setVisibility(View.GONE);
        }else {
            holder.comments.setText(beanList.get(position).getComment());
        }
        holder.updateTime.setText(changeTime(Long.parseLong((beanList.get(position).getPublishtime()))));
        holder.title.setText(beanList.get(position).getTitle());
        if (beanList.get(position).getNike_name()!=null) {
            x.image().bind(holder.icon,beanList.get(position).getHead_img());
            holder.author.setText(beanList.get(position).getNike_name());
        }
        holder.linear.setOnClickListener(this);
        holder.linear.setTag(getItem(position).getV_url());
        holder.play.setOnClickListener(this);
        x.image().bind(holder.cover,beanList.get(position).getO_image());
        holder.cover.setOnClickListener(this);
        itemView=convertView;
        //itemView.setTag(position);
        holder.cover.setTag(itemView);
        holder.play.setTag(itemView);
        return convertView;
    }

    private String changeTime(long i) {
        long now = System.currentTimeMillis()/1000;
//        Log.i("name", "changeTime: now"+now);
//        Log.i("name", "changeTime: i"+i);
        long past=now-i;
        if (past<=60*60) {;
            int min= (int) (past/(60));
            return min+"分钟前";
        } else {
            int hour;
            hour= (int) (past/(60*60));
            return hour+"小时前";
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_item_cover:
            case R.id.video_item_play:

                View view= (View) v.getTag();
                listener.ViewCallback(view);
                break;
            default:
                String tag = (String) v.getTag();
                listener.callback(tag);
        }

    }

    public class ViewHolder{
        TextView comments,title,author,updateTime;
        ImageView icon,play,cover;
        LinearLayout linear;
        public ViewHolder(View itemView){
            comments= (TextView) itemView.findViewById(R.id.video_item_comments);
            title= (TextView) itemView.findViewById(R.id.video_item_title);
            author= (TextView) itemView.findViewById(R.id.video_item_author);
            updateTime= (TextView) itemView.findViewById(R.id.video_item_updatetime);
            icon= (ImageView) itemView.findViewById(R.id.video_item_icon);
            cover= (ImageView) itemView.findViewById(R.id.video_item_cover);
            play= (ImageView) itemView.findViewById(R.id.video_item_play);
            linear= (LinearLayout) itemView.findViewById(R.id.video_item_linear);
        }
    }
}
