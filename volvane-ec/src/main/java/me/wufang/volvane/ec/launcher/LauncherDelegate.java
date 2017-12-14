package me.wufang.volvane.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;

import butterknife.OnClick;
import me.wufang.volvane.ec.R2;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.R;
import me.wufang.volvane.ui.launcher.ScrollLauncherTag;
import me.wufang.volvane.util.storage.VolvanePreference;
import me.wufang.volvane.util.timer.BaseTimerTask;
import me.wufang.volvane.util.timer.ITimerListener;

/**
 * Created by wu on 2017/12/12.
 * Email:gowufang@gmail.com
 */

public class LauncherDelegate extends VolvaneDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mtvTimer = null;

    private Timer mTimer = null;

    private int mCount=5;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask task =new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        initTimer();//init这个timer，否则不显示
    }

    //如果不是第一次则
    //判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!VolvanePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);///diyici,则使用轮播图
        }else{
            //jianchayonghu shifou denglu
        }
    }
    @Override
    public void onTimer() {

        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mtvTimer!=null){
                    mtvTimer.setText(MessageFormat.format("skip\n{0}s",mCount));
                    mCount--;
                    if (mCount<0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
        //到0的时候，可以做些额外处理了
    }
}
