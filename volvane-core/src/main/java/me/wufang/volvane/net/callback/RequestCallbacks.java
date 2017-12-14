package me.wufang.volvane.net.callback;

import android.os.Handler;

import me.wufang.volvane.ui.loader.LoaderStyle;
import me.wufang.volvane.ui.loader.VolvaneLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wu on 2017/11/20.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;
    private static final Handler HANDLER = new Handler();//handler尽量声明称static类型，防止内存泄漏

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADERSTYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
            stopLoading();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADERSTYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    VolvaneLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
