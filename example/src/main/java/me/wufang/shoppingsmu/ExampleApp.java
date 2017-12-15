package me.wufang.shoppingsmu;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import me.wufang.volvane.app.Volvane;
import me.wufang.volvane.ec.database.DatabaseManager;
import me.wufang.volvane.ec.icon.FontEcModule;
import me.wufang.volvane.net.interceptors.DebugInterceptor;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Volvane.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withApiHost("http://127.0.0.1")
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
