package com.example.chen.jiemian.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.models.DrawerLeft;
import com.example.chen.jiemian.myinter.StringCallback;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/24.
 */
public class DrawerRecyclerAdapter extends RecyclerView.Adapter<DrawerRecyclerAdapter.ViewHolder> implements View.OnClickListener {
    private List<DrawerLeft.ResultBean.ChannelBean> channels;
    private LayoutInflater inflater;
    private String TAG = "name";
    private StringCallback listener;

    public DrawerRecyclerAdapter(List<DrawerLeft.ResultBean.ChannelBean> channels, Context context) {

        if (channels == null) {
            this.channels = new ArrayList<>();
        } else {
            this.channels = channels;
        }
        inflater = LayoutInflater.from(context);

    }

    public void updateRes(List<DrawerLeft.ResultBean.ChannelBean> channels) {
        if (channels != null) {
            this.channels.clear();
            this.channels.addAll(channels);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
            case 1:
                return new ViewHolder(inflater.inflate(R.layout.drawer_recycler_item_layout1, parent, false));
            case 3:
                return new ViewHolder(inflater.inflate(R.layout.drawer_recycler_item_layout2, parent, false));
        }
        return null;
    }

    public void getListener(Fragment context) {
        listener = (StringCallback) context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                break;
            case 1:
                holder.line1.setOnClickListener(this);
                holder.line1.setTag(channels.get(position).getRows().get(0).getUrl());
                String icon = channels.get(position).getRows().get(0).getIcon();
                if (icon.endsWith(".png")) {
                    x.image().bind(holder.img1, icon);
                } else {
                    switch (position) {
                        case 0:
                            holder.img1.setImageResource(R.mipmap.ic_launcher);
                            break;
                        case 2:
                            holder.img1.setImageResource(R.mipmap.left_logo_yw);
                            break;
                        case 4:
                            holder.img1.setImageResource(R.mipmap.left_logo_md);
                            break;
                    }
                }

                holder.btn1.setBackgroundColor(Color.parseColor(channels.get(position).getColor()));
                holder.title1.setText(channels.get(position).getRows().get(0).getTitle());
                holder.explain1.setText(channels.get(position).getRows().get(0).getSlogan());
                break;
            case 3:
                holder.line21.setOnClickListener(this);
                holder.line22.setOnClickListener(this);
                holder.line23.setOnClickListener(this);
                holder.line21.setTag(channels.get(position).getRows().get(0).getUrl());
                holder.line22.setTag(channels.get(position).getRows().get(0).getUrl());
                holder.line23.setTag(channels.get(position).getRows().get(0).getUrl());
                holder.btn2.setBackgroundColor(Color.parseColor(channels.get(position).getColor()));
                switch (position) {
                    case 5:
                        holder.img21.setImageResource(R.mipmap.left_logo_fbi);
                        x.image().bind(holder.img22, channels.get(position).getRows().get(1).getIcon());
                        x.image().bind(holder.img23, channels.get(position).getRows().get(2).getIcon());
                        break;
                    case 6:
                        holder.img21.setImageResource(R.mipmap.left_logo_zw);
                        holder.img22.setImageResource(R.mipmap.left_logo_wl);
                        x.image().bind(holder.img23, channels.get(position).getRows().get(2).getIcon());
                        break;
                }
                //x.image().bind(holder.img21,channels.get(position).getRows().get(0).getIcon());

                holder.name21.setText(channels.get(position).getRows().get(0).getTitle());
                holder.name22.setText(channels.get(position).getRows().get(1).getTitle());
                holder.name23.setText(channels.get(position).getRows().get(2).getTitle());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return channels.size() > 0 ? channels.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        int size = channels.get(position).getRows().size();
        if (size > 0) {
            return size;
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        String url = null;
        switch (v.getId()) {
            case R.id.drawer_left_item1:
                url = v.getTag().toString();
                break;
            case R.id.drawer_left_item21:
                url = v.getTag().toString();
                break;
            case R.id.drawer_left_item22:
                url = v.getTag().toString();
                break;
            case R.id.drawer_left_item23:
                url = v.getTag().toString();
                break;
        }
        listener.callback(url);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1, img21, img22, img23;
        Button btn1, btn2;
        LinearLayout line1, line21, line22, line23;
        TextView title1, explain1, name21, name22, name23;

        public ViewHolder(View itemView) {
            super(itemView);
            line1 = (LinearLayout) itemView.findViewById(R.id.drawer_left_item1);
            line21 = (LinearLayout) itemView.findViewById(R.id.drawer_left_item21);
            line22 = (LinearLayout) itemView.findViewById(R.id.drawer_left_item22);
            line23 = (LinearLayout) itemView.findViewById(R.id.drawer_left_item23);
            img1 = (ImageView) itemView.findViewById(R.id.drawer_left_img1);
            img21 = (ImageView) itemView.findViewById(R.id.drawer_left_img21);
            img22 = (ImageView) itemView.findViewById(R.id.drawer_left_img22);
            img23 = (ImageView) itemView.findViewById(R.id.drawer_left_img23);
            btn1 = (Button) itemView.findViewById(R.id.drawer_left_item_btn1);
            btn2 = (Button) itemView.findViewById(R.id.drawer_left_item_btn2);
            name21 = (TextView) itemView.findViewById(R.id.drawer_left_name21);
            name22 = (TextView) itemView.findViewById(R.id.drawer_left_name22);
            name23 = (TextView) itemView.findViewById(R.id.drawer_left_name23);
            title1 = (TextView) itemView.findViewById(R.id.drawer_left_title1);
            explain1 = (TextView) itemView.findViewById(R.id.drawer_left_explain1);
        }
    }
}
