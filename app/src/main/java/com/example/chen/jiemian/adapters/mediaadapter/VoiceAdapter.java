package com.example.chen.jiemian.adapters.mediaadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.constants.Constant;
import com.example.chen.jiemian.fragments.MyVoiceFragment;
import com.example.chen.jiemian.models.mediamodels.VoiceModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/30.
 */
public class VoiceAdapter extends BaseAdapter implements View.OnClickListener {
    private VoiceModel.ResultBean model;
    private LayoutInflater inflater;
    private Context context;
    int picksSize=0;
    int size=0;
    private MyCallback listener;
    public VoiceAdapter(Context context, VoiceModel.ResultBean model) {
        if (model!=null) {
            this.model = model;
            initSize();
        }else {
            this.model=new VoiceModel().getResult();
            size=0;
        }

        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    private void initSize() {
        if (model!=null) {
            size=0;
            picksSize=model.getEditors_picks().size();
            size=picksSize+model.getNews_rst().size();
            size++;
        }
    }

    public void updateRES(VoiceModel.ResultBean resultBean){
        if (resultBean!=null) {
            model=resultBean;
            initSize();
            notifyDataSetChanged();
        }
    }
    public void addRES(VoiceModel.ResultBean resultBean){
        if (resultBean!=null) {
            model.getNews_rst().addAll(resultBean.getNews_rst());
            initSize();
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
            return size;
    }

    @Override
    public Object getItem(int position) {
        if (position<picksSize){
            return model.getEditors_picks().get(position);
        }else if (position>picksSize){
            return model.getNews_rst().get(position-picksSize);
        }else{
            return model.getRec_list();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==picksSize) {
            return 2;
        }else if (position<picksSize){
            return 1;
        }else {
            return 3;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void initLisetner(MyVoiceFragment fg){
        listener= (MyCallback) fg;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        switch (getItemViewType(position)) {
            case 1:
                if (convertView==null) {
                    convertView=inflater.inflate(R.layout.my_voice_item1_layout,parent,false);
                    holder=new ViewHolder(convertView);
                    convertView.setTag(holder);
                }else {
                    holder= (ViewHolder) convertView.getTag();
                }
                if (position==0) {
                    holder.blank.setVisibility(View.VISIBLE);
                    holder.sort.setVisibility(View.VISIBLE);
                }else {
                    holder.blank.setVisibility(View.GONE);
                    holder.sort.setVisibility(View.GONE);
                }
                Picasso.with(context).load(model.getEditors_picks().get(position).getZ_img()).into(holder.cover);
                holder.cover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.callback(model.getEditors_picks().get(position).getUrl());
                    }
                });
                holder.origin.setText(model.getEditors_picks().get(position).getAlbum_name());
                holder.publish.setText(""+model.getEditors_picks().get(position).getPublishtime());
                holder.title.setText(model.getEditors_picks().get(position).getTitle());
                holder.time.setText(""+model.getEditors_picks().get(position).getPlay_time());
                if (!model.getEditors_picks().get(position).getComment().equals("0")) {
                    holder.comment.setText(model.getEditors_picks().get(position).getComment());
                }
                break;
            case 2:
                View view2=inflater.inflate(R.layout.my_voice_item2_layout,parent,false);
                ImageView img1= (ImageView) view2.findViewById(R.id.voice_item2_img1);
                ImageView img2= (ImageView) view2.findViewById(R.id.voice_item2_img2);
                ImageView img3= (ImageView) view2.findViewById(R.id.voice_item2_img3);
                TextView title1= (TextView) view2.findViewById(R.id.voice_item2_title1);
                TextView title2= (TextView) view2.findViewById(R.id.voice_item2_title2);
                TextView title3= (TextView) view2.findViewById(R.id.voice_item2_title3);
                Picasso.with(context).load(model.getRec_list().get(0).getImg_url()).into(img1);
                Picasso.with(context).load(model.getRec_list().get(1).getImg_url()).into(img2);
                Picasso.with(context).load(model.getRec_list().get(2).getImg_url()).into(img3);
                title1.setText(model.getRec_list().get(0).getTitle());
                title2.setText(model.getRec_list().get(1).getTitle());
                title3.setText(model.getRec_list().get(2).getTitle());
                TextView refresh= (TextView) view2.findViewById(R.id.voice_item2_change);
                TextView sort= (TextView) view2.findViewById(R.id.voice_item2_sort);
                sort.setText(model.getRec_name());
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.intCallback(Constant.REFRESH);
                    }
                });
                img1.setOnClickListener(this);
                img2.setOnClickListener(this);
                img3.setOnClickListener(this);
                title1.setOnClickListener(this);
                title2.setOnClickListener(this);
                title3.setOnClickListener(this);
                return view2;
            case 3:
                final int num=position-picksSize-1;
                if (convertView==null||convertView.getTag()==null) {
                    convertView=inflater.inflate(R.layout.my_voice_item1_layout,parent,false);
                    holder=new ViewHolder(convertView);
                    convertView.setTag(holder);
                }else {
                    holder= (ViewHolder) convertView.getTag();
                }
               // Log.i("name", "getView: "+num);
               // Log.i("name", "getView: "+(holder==null));
                if (num==0) {
                    holder.blank.setVisibility(View.VISIBLE);
                    holder.sort.setVisibility(View.VISIBLE);
                    holder.sort.setText(model.getPicks_name());
                }else {
                    holder.blank.setVisibility(View.GONE);
                    holder.sort.setVisibility(View.GONE);
                }
                Picasso.with(context).load(model.getNews_rst().get(num).getZ_img()).into(holder.cover);
                holder.cover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.callback(model.getNews_rst().get(num).getUrl());
                    }
                });
                holder.origin.setText(model.getNews_rst().get(num).getAlbum_name());
                holder.publish.setText(""+model.getNews_rst().get(num).getPublishtime());
                holder.title.setText(model.getNews_rst().get(num).getTitle());
                holder.time.setText(""+model.getNews_rst().get(num).getPlay_time());
                if (!model.getNews_rst().get(num).getComment().equals("0")) {
                    holder.comment.setText(model.getNews_rst().get(num).getComment());
                }
                break;
            default:
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voice_item2_img1:
            case R.id.voice_item2_title1:
                listener.intCallback(Integer.parseInt(model.getRec_list().get(0).getSid()));
                break;
            case R.id.voice_item2_img2:
            case R.id.voice_item2_title2:
                listener.intCallback(Integer.parseInt(model.getRec_list().get(1).getSid()));
                break;
            case R.id.voice_item2_img3:
            case R.id.voice_item2_title3:
                listener.intCallback(Integer.parseInt(model.getRec_list().get(2).getSid()));
                break;
        }
    }

    public class ViewHolder{
        View blank;
        TextView sort,title,origin,publish,time,comment;
        LinearLayout item;
        ImageView cover;
        public ViewHolder(View itemView){
            blank=itemView.findViewById(R.id.voice_item_blank);
            sort=(TextView) itemView.findViewById(R.id.voice_item_sort);
            title=(TextView) itemView.findViewById(R.id.voice_item_title);
            origin=(TextView) itemView.findViewById(R.id.voice_item_origin);
            publish=(TextView) itemView.findViewById(R.id.voice_item_publish);
            time=(TextView) itemView.findViewById(R.id.voice_item_time);
            comment=(TextView) itemView.findViewById(R.id.voice_item_comment);
            item=(LinearLayout) itemView.findViewById(R.id.voice_item_item);
            cover=(ImageView) itemView.findViewById(R.id.voice_item_cover);
        }
    }
}
