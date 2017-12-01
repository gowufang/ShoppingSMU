package me.wufang.volvane.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wu on 2017/11/28.
 * Email:gowufang@gmail.com
 */

public abstract class BaseInterceptor implements Interceptor{
    @Override  //模拟服务器要获取传入的参数
    public  Response intercept(Chain chain) throws IOException {
        return null;
    }

    protected LinkedHashMap<String ,String > getUrlParameters(Chain chain){
        final HttpUrl url=chain.request().url();
        int size=url.querySize();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));

        }
        return params;  //获取一个有序的url参数列
    }
    protected String getUrlParameters(Chain chain,String key){
        final Request request=chain.request();
        return request.url().queryParameter(key);
    }
    protected LinkedHashMap<String ,String > getBodyParameters(Chain chain){
        final FormBody formBody= (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        int size=formBody.size();
        for(int i=0;i<size;i++){
            params.put(formBody.name(i),formBody.value(i));

        }
        return params;
    }
    protected String getBodyParameters(Chain chain,String key ){
        return getBodyParameters(chain).get(key);
    }

}
