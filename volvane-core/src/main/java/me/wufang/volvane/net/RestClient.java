package me.wufang.volvane.net;

import android.content.Context;

import java.util.Map;
import java.util.WeakHashMap;

import me.wufang.volvane.net.callback.IError;
import me.wufang.volvane.net.callback.IFailure;
import me.wufang.volvane.net.callback.IRequest;
import me.wufang.volvane.net.callback.ISuccess;
import me.wufang.volvane.net.callback.RequestCallbacks;
import me.wufang.volvane.ui.LoaderStyle;
import me.wufang.volvane.ui.VolvaneLoader;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by wu on 2017/11/19.
 */
//使用建造者模式
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS=RestCreator.getParams();

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;




    //创建构造者
    public static RestClientBuilder builder(){
        return new RestClientBuilder();//以builder的形式被创建出来
    }

    private void request(HttpMethod method){
        final  RestService service=RestCreator.getRestService();
        Call<String> call=null;
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE!=null){
            VolvaneLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if (call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    public RestClient(String url, Map<String,
            Object> params, IRequest request,
                      ISuccess success, IFailure failure,
                      IError error, RequestBody body,
                      Context context,LoaderStyle loaderStyle) {
        URL = url;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        BODY = body;
        CONTEXT=context;
        LOADER_STYLE=loaderStyle;
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,LOADER_STYLE);
    }
    /*
    具体的使用方法
     */
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
