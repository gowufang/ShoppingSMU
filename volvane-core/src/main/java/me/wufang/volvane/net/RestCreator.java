package me.wufang.volvane.net;

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

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    //retrofit在全局只需要一个就可以了，因此用单例模式
    private static final  class  RetrofitHolder{
        private static final String BASE_URL= (String) Volvane.getConfiguration().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                 .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    private static final class OkHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE=RetrofitHolder
                .RETROFIT_CLENT.create(RestService.class);
    }
}
