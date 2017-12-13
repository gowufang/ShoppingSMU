package me.wufang.volvane.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;

import me.wufang.volvane.ec.R2;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.R;
import me.wufang.volvane.util.timer.ITimerListener;

/**
 * Created by wu on 2017/12/12.
 * Email:gowufang@gmail.com
 */

public class LauncherDelegate extends VolvaneDelegate implements ITimerListener{


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mtvTimer=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onTimer() {

    }
}
