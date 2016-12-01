package com.example.chen.jiemian;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by chen on 2016/11/29.
 */
public class FullScreenActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private String url;
    private String title;
    private ImageView mBack;
    private ImageView mCancel;
    private ImageView mShare;
    private CheckBox mPlay;
    private TextView mCurrent;
    private SeekBar mProgress;
    private TextView mTotal;
    private TextView mTitle;
    private LinearLayout mController;
    private LinearLayout mTop;
    private VideoView mVideo;
    private MediaController controller;
    private int HIDE_NOW=100;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==HIDE_NOW) {
                mController.setVisibility(View.GONE);
                mTop.setVisibility(View.GONE);
            }else if (msg.what==UPDATE){
                mCurrent.setText(translate(mVideo.getCurrentPosition()/1000));
                mHandler.sendEmptyMessageDelayed(UPDATE,1000);
            }
        }
    };
    private int duration;
    private int UPDATE=1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.video_horizental_fullscreen_layout);
        url = getIntent().getCharSequenceExtra("url").toString();
        title = getIntent().getCharSequenceExtra("title").toString();
        //Toast.makeText(this,"url=="+ url+"    title=="+title,Toast.LENGTH_SHORT).show();
        initView();
    }

    private void initView() {

        //Log.i("name", "initView: ");



        mBack = (ImageView) findViewById(R.id.full_back);
        mCancel = (ImageView) findViewById(R.id.full_cancel);
        mShare = (ImageView) findViewById(R.id.full_share);
        mPlay = (CheckBox) findViewById(R.id.full_play);
        mCurrent = (TextView) findViewById(R.id.full_current);
        mProgress = (SeekBar) findViewById(R.id.full_progress);
        mTotal = (TextView) findViewById(R.id.full_totaltime);
        mTitle = (TextView) findViewById(R.id.full_title);
        mTitle.setText(title);
        mController = (LinearLayout) findViewById(R.id.full_bottom_controller);
        mHandler.sendEmptyMessageDelayed(HIDE_NOW,5000);
        mTop = (LinearLayout) findViewById(R.id.full_top_title);
        mVideo = (VideoView) findViewById(R.id.full_video);

        mHandler.sendEmptyMessageDelayed(HIDE_NOW,5000);
        Uri parse = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nubia);
        mVideo.setVideoURI(parse);


        //Log.i("name", "initView: as");



        mPlay.setChecked(true);
        mProgress.setOnSeekBarChangeListener(this);
        mPlay.setOnCheckedChangeListener(this);
        mCancel.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mVideo.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mVideo.start();
        mVideo.requestFocus();

        MediaController controller = new MediaController(this);
        controller.setVisibility(View.GONE);
        mVideo.setMediaController(controller);
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration = mVideo.getDuration();
                mProgress.setMax(duration);
                mTotal.setText(translate(duration/1000));
            }
        });


        mCurrent.setText("00:00");
        mProgress.setOnSeekBarChangeListener(this);
        mHandler.sendEmptyMessageDelayed(UPDATE,1000);
        mVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.full_cancel:
            case R.id.full_back:
                finish();
                break;
            case R.id.full_share:
                mPlay.setChecked(false);
                Toast.makeText(FullScreenActivity.this, "fenxiang", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(HIDE_NOW,5000);
                break;
            default:
                mController.setVisibility(View.VISIBLE);
                mTop.setVisibility(View.VISIBLE);
                mHandler.sendEmptyMessageDelayed(HIDE_NOW,5000);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (!mVideo.isPlaying()) {
                mVideo.start();
            }
        }else {
            if (mVideo.isPlaying()) {
                mVideo.pause();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mCurrent.setText(translate(progress/1000));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mVideo!=null) {
            mVideo.seekTo(seekBar.getProgress());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mController.setVisibility(View.VISIBLE);
        mTop.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(HIDE_NOW,5000);
        return false;
    }
    private String translate(int progress) {
        int sec=progress%60;
        int min=progress/60;
        if (sec<10){
            return min+":0"+sec;
        }else {
            return min+":"+sec;
        }
    }
}
