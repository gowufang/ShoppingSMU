package me.wufang.volvane.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */
//configure some tools
public class Configurator {

    private static final HashMap<Object, Object> VOLVANE_CONFIGS = new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //拦截器是一种强大的机制,可以监视、重写和重试调用.下面是一个简单例子,拦截发出的请求和传入的响应的日志.
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        VOLVANE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);

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

        initIcons();
        VOLVANE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        VOLVANE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            //从1 开始，因为0已经被加入过了
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        VOLVANE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        VOLVANE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final Configurator withWeChatAppId(String appId) {
        VOLVANE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }
    public final Configurator withWeChatAppSecret(String secret) {
        VOLVANE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, secret);
        return this;
    }
    public final Configurator withActivity(Activity activity) {
        VOLVANE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) VOLVANE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //    @SuppressWarnings("unchecked")
//    final <T> T getConfiguration(Enum<ConfigKeys> key) {
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
