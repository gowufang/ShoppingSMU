package me.wufang.volvane.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import me.wufang.volvane.app.Volvane;

/**
 * Created by wu on 2017/11/21.
 * Email:gowufang@gmail.com
 */
//建一个util，用来计算他的宽高
public class DimenUtil {

    //得到屏幕的宽
    public static int getScreenWidth(){
        final Resources resources= Volvane.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources= Volvane.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }


}
