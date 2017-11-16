package me.wufang.shoppingsmu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.wufang.volvane.delegates.VolvaneDelegate;

/**
 * Created by Administrator on 2017/11/16.
 * Email:gowufang@gmail.com
 */

public class ExampleDelegate extends VolvaneDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
