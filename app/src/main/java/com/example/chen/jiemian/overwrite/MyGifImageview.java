package com.example.chen.jiemian.overwrite;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.chen.jiemian.R;
import com.example.chen.jiemian.constants.Constant;

import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by chen on 2016/11/26.
 */
public class MyGifImageview extends ImageView {
    private Movie mMovie;
    private boolean isPlaying=false;
    private int oldwidth,oldheight;
    private Bitmap bitmapStart;
    private long startTime;

    public MyGifImageview(Context context) {
        this(context,null);
    }

    public MyGifImageview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyGifImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImgae(context, attrs, defStyleAttr);
    }

    private void setImgae(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppCompatImageView);
        int resId=getresorceId(typedArray,context,attrs);
        if (resId!=0) {
            InputStream inputStream = getResources().openRawResource(resId);
            mMovie = Movie.decodeStream(inputStream);
            if (mMovie!=null) {
                //boolean isPlayAuto = typedArray.getBoolean(R.styleable.MyGifImageview_autoPlay, false);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                oldwidth = bitmap.getWidth();
                oldheight = bitmap.getHeight();
                bitmap.recycle();
                if (!Constant.IS_PLAY_VIDEO) {
                    bitmapStart = BitmapFactory.decodeResource(getResources(), R.drawable.audio_gif);
                }
                //Constant.staticBitmap=bitmapStart;
            }
        }
    }

    public void isInit(boolean isPlay){
        if (isPlay){
            Log.i("name", "isInit: ");
            isPlaying=isPlay;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMovie==null) {
            super.onDraw(canvas);
        }else{
            if (Constant.IS_PLAY_VIDEO){
                playMovie(canvas);
                invalidate();
            }else {
                if (isPlaying) {
                    if (playMovie(canvas)) {
                        isPlaying=false;
                    }
                    invalidate();
                }else {
                    mMovie.setTime(0);
                    mMovie.draw(canvas,0,0);
                    int offWidth = (oldwidth - bitmapStart.getWidth()) / 2;
                    int offHeight = (oldheight - bitmapStart.getHeight()) / 2;
                    canvas.drawBitmap(bitmapStart,offWidth,offHeight,null);
                }
            }
        }
    }

    public void stop(){
        bitmapStart=bitmapStart = BitmapFactory.decodeResource(getResources(), R.drawable.audio_gif);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mMovie != null) {
            // 如果是GIF图片则重写设定PowerImageView的大小
            setMeasuredDimension(oldwidth, oldheight);
        }
    }

    private boolean playMovie(Canvas canvas) {
        long millis = SystemClock.uptimeMillis();
        if (startTime==0) {
            startTime=millis;
        }
        int duration = mMovie.duration();
        if (duration==0) {
            duration=1000;
        }
        int time = (int) ((millis - startTime) % duration);
        mMovie.setTime(time);
        mMovie.draw(canvas,0,0);
        if ((millis-startTime)>=duration) {
            startTime=0;
            return true;
        }
        return false;

    }

    private int getresorceId(TypedArray typedArray, Context context, AttributeSet attrs) {
        try {
            Field fields = TypedArray.class.getDeclaredField("mValue");
            fields.setAccessible(true);
            TypedValue typedValue = (TypedValue) fields.get(typedArray);
            return typedValue.resourceId;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            if (typedArray!=null){
                typedArray.recycle();
            }
        }
        return 0;
    }


}
