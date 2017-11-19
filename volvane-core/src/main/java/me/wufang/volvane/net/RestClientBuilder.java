package me.wufang.volvane.net;

import java.util.Map;
import java.util.WeakHashMap;

import me.wufang.volvane.net.callback.IError;
import me.wufang.volvane.net.callback.IFailure;
import me.wufang.volvane.net.callback.IRequest;
import me.wufang.volvane.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wu on 2017/11/19.
 */

//建造者模式，所以属性不能是final
public class RestClientBuilder {
    private String mUrl;
    //从creator中获取
    private static final Map<String, Object> PARAMS=RestCreator.getParams();

    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    //不允许外部的类直接new该类



    RestClientBuilder() {
    }
    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }


    public final RestClientBuilder params(String key,Object value){

        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        //一般传入的是json数据
        this.mBody=RequestBody.create(MediaType.
                parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }
    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest=iRequest;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }
    public final RestClientBuilder error(IError iError){
        this.mIError=iError;
        return this;
    }
    //使用全局获取PARAMS，则不需要检查
//    private Map<String,Object> checkParams(){
//        if (mParams==null){
//            return new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,
                mIRequest,mISuccess,
                mIFailure,mIError,mBody);
    }
}
