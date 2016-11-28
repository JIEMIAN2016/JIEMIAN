package com.example.chen.jiemian;

import android.app.Application;

import org.xutils.x;

/**
 * Created by chen on 2016/11/24.
 */
public class JieMian extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
