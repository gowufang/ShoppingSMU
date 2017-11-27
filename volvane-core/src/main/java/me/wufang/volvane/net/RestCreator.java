package me.wufang.volvane.net;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import me.wufang.volvane.app.ConfigKeys;
import me.wufang.volvane.app.Volvane;
import okhttp3.Interceptor;
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

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }
//////////////


    //通过get方法得到RestServiceHolder的REST_SERVICE
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //retrofit在全局只需要一个就可以了，因此用单例模式
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Volvane.getConfiguration().get(ConfigKeys.API_HOST.name());
//private static final String BASE_URL = Volvane.getConfiguration(ConfigKeys.API_HOST);

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

        private static OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Volvane.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:INTERCEPTORS){

                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        //吧拦截器传到okhttp里
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


}
