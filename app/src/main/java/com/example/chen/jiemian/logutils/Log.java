package com.example.chen.jiemian.logutils;

import com.example.chen.jiemian.constants.Constant;

/**
 * Created by chen on 2016/11/24.
 */
public class Log {
    public void i(String flag,String msg){

        android.util.Log.i(flag, " : "+msg);
    }
    public void e(String flag,String msg){
        android.util.Log.e(flag, " : "+msg);
    }
}
