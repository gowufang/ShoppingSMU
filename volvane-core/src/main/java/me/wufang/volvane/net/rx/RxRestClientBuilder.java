package me.wufang.volvane.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import me.wufang.volvane.net.RestCreator;
import me.wufang.volvane.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wu on 2017/11/19.
 */

//建造者模式，所以属性不能是final
public class RxRestClientBuilder {
    private String mUrl=null;
    //从creator中获取
    private static final Map<String, Object> PARAMS=RestCreator.getParams();


    private RequestBody mBody=null;
    private Context mContext=null;
    private LoaderStyle mLoaderStyle=null;
    //不允许外部的类直接new该类

    private File mFile=null;




    RxRestClientBuilder() {
    }




    public final RxRestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }


    public final RxRestClientBuilder params(String key, Object value){

        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }

    public final RxRestClientBuilder file(String file){

        this.mFile=new File(file);
        return this;
    }


    public final RxRestClientBuilder raw(String raw){
        //一般传入的是json数据
        this.mBody=RequestBody.create(MediaType.
                parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    //使用全局获取PARAMS，则不需要检查
//    private Map<String,Object> checkParams(){
//        if (mParams==null){
//            return new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }




    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS,
              mBody,mContext,mLoaderStyle, mFile);
    }
}
