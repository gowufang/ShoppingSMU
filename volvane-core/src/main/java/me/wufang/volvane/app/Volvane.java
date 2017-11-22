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
                .put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }


    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext() {
        /*少了个.name()，报下面的错
           * FATAL EXCEPTION: main
     Process: me.wufang.shoppingsmu, PID: 5406
     java.lang.RuntimeException: Unable to start activity ComponentInfo{me.wufang.shoppingsmu/me.wufang.shoppingsmu.MainActivity}: java.lang.NullPointerException: APPLICATION_CONTEXT IS NULL
         at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2193)
         at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2243)
         at android.app.ActivityThread.access$800(ActivityThread.java:135)
         at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1196)
         at android.os.Handler.dispatchMessage(Handler.java:102)
         at android.os.Looper.loop(Looper.java:136)
         at android.app.ActivityThread.main(ActivityThread.java:5019)
         at java.lang.reflect.Method.invokeNative(Native Method)
         at java.lang.reflect.Method.invoke(Method.java:515)
         at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:779)
         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:595)
         at dalvik.system.NativeStart.main(Native Method)
      Caused by: java.lang.NullPointerException: APPLICATION_CONTEXT IS NULL
         at me.wufang.volvane.app.Configurator.getConfiguration(Configurator.java:85)
         at me.wufang.volvane.app.Volvane.getConfiguration(Volvane.java:34)
         at me.wufang.volvane.app.Volvane.getApplicationContext(Volvane.java:27)*/

        return getConfiguration(ConfigType.APPLICATION_CONTEXT.name());
    }
    public static Context getApplication(){
        return (Context) getConfiguration().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static HashMap<String, Object> getConfiguration() {
        return Configurator.getInstance().getVolvaneConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

}
