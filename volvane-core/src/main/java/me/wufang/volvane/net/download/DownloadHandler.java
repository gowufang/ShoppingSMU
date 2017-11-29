package me.wufang.volvane.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import me.wufang.volvane.net.RestCreator;
import me.wufang.volvane.net.callback.IError;
import me.wufang.volvane.net.callback.IFailure;
import me.wufang.volvane.net.callback.IRequest;
import me.wufang.volvane.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wu on 2017/11/24.
 * Email:gowufang@gmail.com
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS= RestCreator.getParams();

    private final IRequest REQUEST;//回掉

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url, IRequest request, String download_dir, String extension, String name, ISuccess success, IFailure failure, IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    //chuli download
    public final void handleDownload(){

         if (REQUEST!=null){
             REQUEST.onRequestStart();//不为空，则开始下载
         }
         RestCreator.getRestService().download(URL,PARAMS)
                 .enqueue(new Callback<ResponseBody>() {//enqueue，
                     @Override
                     public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            ResponseBody responseBody=response.body();
                            final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                         /*
                         * 这个函数让任务是以单线程队列方式或线程池队列方式运行
                         * //这里不用 execute方法，而以县城池的方式来处理
                         * */
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR,EXTENSION,
                                    responseBody,NAME);
                            //一定要判断，否则文件可能下载不全
                            if (task.isCancelled()){//task取消后执行下面的才行
                                if (REQUEST!=null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                     }

                     @Override
                     public void onFailure(Call<ResponseBody> call, Throwable t) {

                         if (FAILURE!=null){
                             FAILURE.onFailure();
                         }
                     }
                 });
    }
}
