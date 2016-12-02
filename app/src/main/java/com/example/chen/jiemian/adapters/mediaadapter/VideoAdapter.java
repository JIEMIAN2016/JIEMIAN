package com.example.chen.jiemian.adapters.mediaadapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.models.mediamodels.VideoModel;
import com.example.chen.jiemian.myinter.MyCallback;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/11/28.
 */
public class VideoAdapter extends BaseAdapter implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SurfaceHolder.Callback, SeekBar.OnSeekBarChangeListener {
    private static final int UPDATE_TIME = 1000;
    private MyCallback listener;
    private List<VideoModel.ResultBean.RstBean> beanList;
    private LayoutInflater inflater;
    private View itemView;
    private Context context;
    private int num = -1;
    private SurfaceView playView;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private static final int BOTTOM_HIDE = 5000;
    private RelativeLayout controlBottom;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BOTTOM_HIDE:
                    if (controlBottom.getVisibility() == View.VISIBLE) {
                        controlBottom.setVisibility(View.GONE);
                    }
                    break;
                case UPDATE_TIME:
                    //Log.i("name", "handleMessage: "+player.getCurrentPosition());
                    current.setText(translate(player.getCurrentPosition() / 1000));
                    mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                    break;

            }
        }
    };
    private SeekBar progress;
    private TextView totalTime;
    private ImageView img;
    private ImageView playIcon;
    private TextView current;
    private boolean IS_PLAY = false;
    private boolean IS_FIRST = true;

    public VideoAdapter(List<VideoModel.ResultBean.RstBean> beanList, Context context) {
        if (beanList != null) {
            this.beanList = beanList;
        } else {
            this.beanList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
        this.context = context;
        //player = MediaPlayer.create(context, R.raw.nubia);
    }

    public void updateRes(List<VideoModel.ResultBean.RstBean> beanList) {
        if (beanList != null) {
            this.beanList.clear();
            this.beanList.addAll(beanList);
            notifyDataSetChanged();
        }
    }

    public void passValue(Fragment fg) {
        listener = (MyCallback) fg;
    }

    @Override
    public int getCount() {
        return beanList.size() > 0 ? beanList.size() : 0;
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
        ViewHolderOne holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.my_video_list_item_layout, parent, false);
            holder = new ViewHolderOne(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderOne) convertView.getTag();
        }
        if (beanList.get(position).getComment().equals("0")) {
            //Log.i("name", "getView: "+beanList.get(position).getComment());
            holder.comments.setVisibility(View.GONE);
        } else {

            holder.comments.setText(beanList.get(position).getComment());
        }

        holder.updateTime.setText(changeTime(Long.parseLong((beanList.get(position).getPublishtime()))));
        holder.title.setText(beanList.get(position).getTitle());
        if (beanList.get(position).getNike_name() != null) {
            Picasso.with(context).load(beanList.get(position).getHead_img()).into(holder.icon);
            holder.author.setText(beanList.get(position).getNike_name());
        }
        holder.linear.setOnClickListener(this);
        holder.linear.setTag(getItem(position).getV_url());
        holder.play.setOnClickListener(this);
        Picasso.with(context).load(beanList.get(position).getO_image()).into(holder.cover);
        holder.cover.setOnClickListener(this);
        itemView = holder.relativeLayout;
        itemView.setTag(position);
        holder.surfaceView.setOnClickListener(this);
        holder.surfaceView.setTag(itemView);
        holder.cover.setTag(itemView);
        holder.play.setTag(itemView);
        return convertView;
    }


    private String changeTime(long i) {
        long now = System.currentTimeMillis() / 1000;
        long past = now - i;
        if (past <= 60 * 60) {
            ;
            int min = (int) (past / (60));
            return min + "分钟前";
        } else {
            int hour;
            hour = (int) (past / (60 * 60));
            return hour + "小时前";
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_item_cover:
            case R.id.video_item_play:
            case R.id.video_item_video:
                if (IS_PLAY&& ((int)( (View) v.getTag()).getTag()==num)) {
                    controlBottom.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessageDelayed(BOTTOM_HIDE, 5000);
                }else {
                    updateView(((View) v.getTag()));
                }

                break;
            case R.id.video_item_fullscrenn:
                IS_PLAY = false;
                player.stop();
                playView.setVisibility(View.GONE);
                playView=null;
                img.setVisibility(View.VISIBLE);
                playIcon.setVisibility(View.VISIBLE);
                mHandler.removeMessages(UPDATE_TIME);
                listener.intCallback(num);
                break;
            default:
                String tag = (String) v.getTag();
                listener.callback(tag);
        }

    }

    private void updateView(View item) {
        int position = (int) item.getTag();

        if (playView == null) {
            //当SurfaceView为空，也就是第一次播放视频时初始化控件
            //num用于判断下次点击的位置与上次点击item是否有所改变
            player=new MediaPlayer();
            try {
                player.setDataSource(context, Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.nubia));
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            num = position;
            playView = (SurfaceView) item.findViewById(R.id.video_item_video);
            img = (ImageView) item.findViewById(R.id.video_item_cover);
            playIcon = (ImageView) item.findViewById(R.id.video_item_play);
            controlBottom = ((RelativeLayout) item.findViewById(R.id.video_item_bottom));
            controlBottom.setVisibility(View.VISIBLE);
            mHandler.sendEmptyMessageDelayed(BOTTOM_HIDE, 5000);
            img.setVisibility(View.GONE);
            playIcon.setVisibility(View.GONE);
            playView.setVisibility(View.VISIBLE);
            Log.i("video", "updateView: null");

        } else {
            //改变播放的视频时隐藏上一个view，并对当前即将播放的视频进行初始化
            if (num != position) {
                num = position;
                img.setVisibility(View.VISIBLE);
                playIcon.setVisibility(View.VISIBLE);
                Log.e("video", "updateView: " );
                //网络查到必须走这三部才能实现player的充实刷新
                if (player.isPlaying()) {
                    player.stop();
                }
                player.reset();
                try {
                    player.setDataSource(context, Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.nubia));
                    player.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playView.setVisibility(View.GONE);
                if (controlBottom.getVisibility() == View.VISIBLE) {
                    controlBottom.setVisibility(View.GONE);
                }
                playView = (SurfaceView) item.findViewById(R.id.video_item_video);
                img = (ImageView) item.findViewById(R.id.video_item_cover);
                playIcon = (ImageView) item.findViewById(R.id.video_item_play);
                ImageView full = (ImageView) item.findViewById(R.id.video_item_fullscrenn);
                full.setOnClickListener(this);
                img.setVisibility(View.GONE);
                playIcon.setVisibility(View.GONE);
                playView.setVisibility(View.VISIBLE);
                controlBottom = ((RelativeLayout) item.findViewById(R.id.video_item_bottom));
                controlBottom.setVisibility(View.VISIBLE);
                mHandler.sendEmptyMessageDelayed(BOTTOM_HIDE, 5000);
                Log.i("video", "updateView: isplay");

            }
        }

        //item下方的可隐藏控制条进行初始化
        current = (TextView) item.findViewById(R.id.video_item_current);
        CheckBox pause = (CheckBox) item.findViewById(R.id.video_item_pause);
        pause.setChecked(true);
        pause.setOnCheckedChangeListener(this);
        ImageView fullScreen = (ImageView) item.findViewById(R.id.video_item_fullscrenn);
        fullScreen.setOnClickListener(this);
        progress = (SeekBar) item.findViewById(R.id.video_item_progress);
        progress.setOnSeekBarChangeListener(this);
        totalTime = (TextView) item.findViewById(R.id.video_item_time);



        holder = playView.getHolder();
        holder.addCallback(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (!IS_PLAY) {
                player.start();
                IS_PLAY = true;
                mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
            }
        } else {
            if (IS_PLAY) {
                player.pause();
                IS_PLAY = false;
                mHandler.removeMessages(UPDATE_TIME);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        Log.i("video", "surfaceCreated: holder=null" + (holder==null));
        //Log.e("video", "surfaceCreated: surface=null->" + (holder.getSurface()==null));
        player.setDisplay(holder);

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("video", "onPrepared: ");
                mp.start();
                IS_FIRST = false;
                int duration = player.getDuration();
                progress.setMax(duration);
                mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                totalTime.setText(translate(duration / 1000));
                IS_PLAY = true;

            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Log.i("video", "onCompletion: ");
                //player.release();
                mHandler.removeMessages(UPDATE_TIME);
            }
        });
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //Log.e("video", "surfaceChanged: " );
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //Log.e("video", "surfaceDestroyed: ");
        IS_PLAY = false;
//        player.stop();
//        player.reset();
//        playView = null;
        img.setVisibility(View.VISIBLE);
        playIcon.setVisibility(View.VISIBLE);
        mHandler.removeMessages(UPDATE_TIME);
    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        current.setText(translate(progress / 1000));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        current.setText(translate(seekBar.getProgress() / 1000));
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (player != null) {
            player.seekTo(seekBar.getProgress());
        }
    }

    private String translate(int progress) {
        int sec = progress % 60;
        int min = progress / 60;
        if (sec < 10) {
            return min + ":0" + sec;
        } else {
            return min + ":" + sec;
        }
    }

    public void resume() {
        //Log.i("name", "resume: ");
        notifyDataSetChanged();
    }

    public void addRes(List<VideoModel.ResultBean.RstBean> rst) {
        beanList.addAll(rst);
        notifyDataSetChanged();
    }


    public class ViewHolderOne {
        TextView comments, title, author, updateTime;
        ImageView icon, play, cover;
        LinearLayout linear;
        SurfaceView surfaceView;
        RelativeLayout relativeLayout;

        public ViewHolderOne(View itemView) {
            comments = (TextView) itemView.findViewById(R.id.video_item_comments);
            title = (TextView) itemView.findViewById(R.id.video_item_title);
            author = (TextView) itemView.findViewById(R.id.video_item_author);
            updateTime = (TextView) itemView.findViewById(R.id.video_item_updatetime);
            icon = (ImageView) itemView.findViewById(R.id.video_item_icon);
            cover = (ImageView) itemView.findViewById(R.id.video_item_cover);
            play = (ImageView) itemView.findViewById(R.id.video_item_play);
            linear = (LinearLayout) itemView.findViewById(R.id.video_item_linear);
            surfaceView = (SurfaceView) itemView.findViewById(R.id.video_item_video);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.video_item_playview);
        }
    }
}
