package me.wufang.volvane.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */
//final is that no class could extend Volvane
public final class Volvane {
    public static  Configurator init(Context context){
        getConfiguration().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return  Configurator.getInstance();
    }
    private static WeakHashMap<String,Object> getConfiguration(){
        return Configurator.getInstance().getVolvaneConfigs();
    }

}
