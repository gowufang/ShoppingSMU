package me.wufang.volvane.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by wu on 2017/11/21.
 * Email:gowufang@gmail.com
 */

//官方通过反射取loader的名字来加载loader

public class LoaderCreator {
    private static final WeakHashMap<String,Indicator> LOADING_MAP=new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type,Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type)==null){

            final Indicator indicator=getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    //以缓存的方式创建loader，而不需要每次都要创建
    private static  Indicator getIndicator(String name){
        if (name==null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName=new StringBuilder();
        if (!name.contains(".")){
            final String defaultPackageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }//具体的名字拼接到他的包名下
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass=Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
