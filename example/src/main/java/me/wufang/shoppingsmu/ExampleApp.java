package me.wufang.shoppingsmu;

import android.app.Application;

import me.wufang.volvane.app.Volvane;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Volvane.init(this)

                .withApiHost("http://127.0.0.1").configure();
        ;
    }
}
