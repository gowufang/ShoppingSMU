package me.wufang.volvane.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */
//final is that no class could extend Volvane
public final class Volvane {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getVolvaneConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
//    public static HashMap<Object, Object> getConfiguration() {
//        return Configurator.getInstance().getVolvaneConfigs();
//    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

}
