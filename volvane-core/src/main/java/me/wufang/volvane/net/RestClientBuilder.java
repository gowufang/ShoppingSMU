package me.wufang.volvane.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import me.wufang.volvane.net.callback.IError;
import me.wufang.volvane.net.callback.IFailure;
import me.wufang.volvane.net.callback.IRequest;
import me.wufang.volvane.net.callback.ISuccess;
import me.wufang.volvane.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wu on 2017/11/19.
 */

//建造者模式，所以属性不能是final
public class RestClientBuilder {
    private String mUrl=null;
    //从creator中获取
    private static final Map<String, Object> PARAMS=RestCreator.getParams();

    private IRequest mIRequest=null;
    private ISuccess mISuccess=null;
    private IFailure mIFailure=null;
    private IError mIError=null;
    private RequestBody mBody=null;
    private Context mContext=null;
    private LoaderStyle mLoaderStyle=null;
    //不允许外部的类直接new该类

    private File mFile=null;
    private String mDownloadDir=null;
    private String mExtension=null;
    private String mName=null;



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

    public final RestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }

    public final RestClientBuilder file(String file){

        this.mFile=new File(file);
        return this;
    }
    public final RestClientBuilder dir(String dir){


        this.mDownloadDir=dir;
        return this;
    }
    public final RestClientBuilder extension(String extension){

        this.mExtension=extension;
        return this;
    }
    public final RestClientBuilder name(String name){


        this.mName=name;
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

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }




    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,
                mIRequest, mDownloadDir, mExtension, mName, mISuccess,
                mIFailure,mIError,mBody,mContext,mLoaderStyle, mFile);
    }
}
