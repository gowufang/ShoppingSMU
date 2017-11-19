package me.wufang.shoppingsmu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.net.RestClient;
import me.wufang.volvane.net.callback.IError;
import me.wufang.volvane.net.callback.IFailure;
import me.wufang.volvane.net.callback.ISuccess;

/**
 * Created by wu on 2017/11/19.
 */

public class ExampleDelegate extends VolvaneDelegate{
    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
    private void testRestClient(){
        RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build();//这样就构建了一个restClient
    }
}
