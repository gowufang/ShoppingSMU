package me.wufang.volvane.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.R;
import me.wufang.volvane.ui.launcher.LauncherHolderCreator;

/**
 * Created by wu on 2017/12/13.
 * Email:gowufang@gmail.com
 */
//滚动的启动效果，轮播图
public class LauncherScrollDelegate extends VolvaneDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner=null;
    private static final ArrayList<Integer> INTEGERS=new ArrayList<>();
    private void initBanner(){

        INTEGERS.add(R.mipmap.launcher_1);
        INTEGERS.add(R.mipmap.launcher_2);
        INTEGERS.add(R.mipmap.launcher_3);
        INTEGERS.add(R.mipmap.launcher_4);


        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }
    @Override
    public Object setLayout() {
        mConvenientBanner=new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        initBanner();
    }

    @Override
    public void onItemClick(int position) {

    }
}
