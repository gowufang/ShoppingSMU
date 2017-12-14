package me.wufang.volvane.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import me.wufang.volvane.net.HttpMethod;
import me.wufang.volvane.net.RestCreator;
import me.wufang.volvane.ui.loader.LoaderStyle;
import me.wufang.volvane.ui.loader.VolvaneLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by wu on 2017/11/19.
 */
//使用建造者模式
public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS=RestCreator.getParams();



    private final RequestBody BODY;//Okhttp3
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    private final File FILE;

/*用拦截器模拟rest请求，当请求url的时候通过拦截器返回一个json数据，达到response目的
* */



    private Observable<String> request(HttpMethod method){
        final  RxRestService service=RestCreator.getRxRestService();
//        Call<String> call=null;
        Observable<String> observable=null;

        if (LOADER_STYLE!=null){
            VolvaneLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                observable=service.get(URL,PARAMS);
                break;
            case POST:
                observable=service.post(URL,PARAMS);
                break;
            case POST_RAW:
                observable=service.postRaw(URL,BODY);
                break;
            case PUT:
                observable=service.put(URL,PARAMS);
                break;

            case PUT_RAW:
                observable=service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body=MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
//                observable=RestCreator.getRestService().upload(URL,body);
                observable=service.upload(URL,body);
                break;
            default:
                break;
        }
        return observable;

    }
    public RxRestClient(String url, Map<String,
            Object> params,
                RequestBody body,
                        Context context, LoaderStyle loaderStyle, File file) {
        URL = url;

        FILE = file;
        PARAMS.putAll(params);


        BODY = body;
        CONTEXT=context;
        LOADER_STYLE=loaderStyle;
    }

    /*
    具体的使用方法
     */
    public final Observable<String> get(){
       return request(HttpMethod.GET);
    }
    public final Observable<String> post(){
        if (BODY==null){
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");

            }
            return request(HttpMethod.POST_RAW);
        }
    }
    public final Observable<String> put(){
        if (BODY==null){
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");

            }
            return request(HttpMethod.PUT_RAW);
        }
    }
    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }
    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public Observable<ResponseBody> download(){

        return RestCreator.getRxRestService().download(URL,PARAMS);


    }
}
