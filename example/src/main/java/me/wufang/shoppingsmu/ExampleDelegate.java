package me.wufang.shoppingsmu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Log.d("onBindView", "onbindview");
        testRestClient();//测试
    }



    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com/")
               // .params("","")
                .success(new ISuccess() {
                    public static final String TAG ="testRestClient" ;

                    @Override
                    public void onSuccess(String response) {

                        Log.d(TAG, "onSuccess: "+response);
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
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
                .build()//这样就构建了一个restClient
        .get();//这样就实现了get方法
    }
}
