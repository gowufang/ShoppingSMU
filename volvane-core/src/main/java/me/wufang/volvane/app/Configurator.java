package me.wufang.volvane.app;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */
//configure some tools
public class Configurator {//static final class have to use Upper leter
    private static final HashMap<Object, Object> VOLVANE_CONFIGS = new HashMap<>();

    private Configurator() {
        VOLVANE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);

    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

//    final WeakHashMap<String, Object> getVolvaneConfigs() {
//        return VOLVANE_CONFIGS;
//    }

    final HashMap<Object, Object> getVolvaneConfigs() {
        return VOLVANE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        VOLVANE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        VOLVANE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) VOLVANE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("configuration is not ready,call config ");
        }
    }

//    @SuppressWarnings("unchecked")
//    final <T> T getConfiguration(Enum<ConfigType> key) {
//        checkConfiguration();
//        return (T) VOLVANE_CONFIGS.get(key.name());
//
//    }
@SuppressWarnings("unchecked")
final <T> T getConfiguration(Object key) {
    checkConfiguration();
    final Object value = VOLVANE_CONFIGS.get(key);
    if (value == null) {
        throw new NullPointerException(key.toString() + " IS NULL");
    }
    return (T) VOLVANE_CONFIGS.get(key);
}
}
