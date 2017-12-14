package me.wufang.volvane.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by wu on 2017/12/13.
 * Email:gowufang@gmail.com
 */

public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView=null;

    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {//每次滑动的时候需要更新的内容

        mImageView.setBackgroundResource(data);//没有这个，图片加载不出来
    }
}
