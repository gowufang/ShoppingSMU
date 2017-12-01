package me.wufang.volvane.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import me.wufang.volvane.R;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/11/16.
 * Email:gowufang@gmail.com
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract VolvaneDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initContainer(savedInstanceState);//初始化容器
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container=new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
