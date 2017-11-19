package me.wufang.volvane.app;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */
//configure some tools
public class Configurator {//static final class have to use Upper leter
    private static final WeakHashMap<String, Object> VOLVANE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        VOLVANE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);

    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getVolvaneConfigs() {
        return VOLVANE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        VOLVANE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        VOLVANE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) VOLVANE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("configuration is not ready,call config ");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) VOLVANE_CONFIGS.get(key.name());

    }
}
