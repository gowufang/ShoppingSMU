package me.wufang.volvane.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import me.wufang.volvane.R;
import me.wufang.volvane.util.dimen.DimenUtil;

/**
 * Created by wu on 2017/11/21.
 * Email:gowufang@gmail.com
 */

public class VolvaneLoader {
    private static final int LOADER_SIZE_SCALE=8;
    private static final int LOADER_OFFSET_SCALE=10;

    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();
    private static final String DEFAULT_LOADER=LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);//尽量使用V7中的compat包中的类，可以对兼容性更好

        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth= DimenUtil.getScreenWidth();
        int deviceHeight=DimenUtil.getScreenHeight();
        final Window dialogWindow=dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams lp=dialogWindow.getAttributes();
            lp.width=deviceWidth/LOADER_SIZE_SCALE;
            lp.height=deviceWidth/LOADER_SIZE_SCALE;

            //设置偏移量,ruguo 不合适，可以自己调整
            lp.height=lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }
    public static void stopLoading(){
        for (AppCompatDialog dialog: LOADERS){
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();//dialog.dismiss仅仅只让dialog消失，cancel可以加入一些回掉
                }
            }
        }
    }
}
