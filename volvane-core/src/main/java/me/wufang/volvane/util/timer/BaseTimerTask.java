package me.wufang.volvane.util.timer;

import java.util.TimerTask;

/**
 * Created by wu on 2017/12/12.
 * Email:gowufang@gmail.com
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        //xuyao yige huidiao
        if (mITimerListener!=null){
            mITimerListener.onTimer();//haimeiyou xie ontimer d shixian
        }

    }
}
