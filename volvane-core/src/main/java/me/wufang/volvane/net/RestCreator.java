package me.wufang.volvane.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import me.wufang.volvane.app.ConfigType;
import me.wufang.volvane.app.Volvane;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wu on 2017/11/19.
 */

public class RestCreator {

    // PARAMS作为全局变量
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
//////////////




    //通过get方法得到RestServiceHolder的REST_SERVICE
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //retrofit在全局只需要一个就可以了，因此用单例模式
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Volvane.getConfiguration().get(ConfigType.API_HOST.name());

        //Create Retrofit Client
        private static final Retrofit RETROFIT_CLENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //调用上面的Holder方法的RETROFIT_CLENT实例，创建Restful服务(RestService)
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLENT.create(RestService.class);//也可以=new Retrofit.Builder().XXX()

    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


}
