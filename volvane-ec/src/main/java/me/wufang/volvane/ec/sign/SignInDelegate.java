package me.wufang.volvane.ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import me.wufang.volvane.app.Volvane;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.R;
import me.wufang.volvane.ec.R2;
import me.wufang.volvane.net.RestClient;
import me.wufang.volvane.net.callback.ISuccess;
import me.wufang.volvane.util.log.LatteLogger;
import me.wufang.volvane.wechat.VolvaneWechat;
import me.wufang.volvane.wechat.callbacks.IWeChatSignInCallback;

/**
 * Created by wu on 2017/12/15.
 * Email:gowufang@gmail.com
 */

public class SignInDelegate extends VolvaneDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail=null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword=null;
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener= (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()) {//通过验证，向服务器提交信息
            RestClient.builder()
                    .url("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")//xuyao自己写
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response,mISignListener);

                        }
                    })
                    .build()
                    .post();
             //Toast.makeText(getContext(), "验证成功", Toast.LENGTH_SHORT).show();

        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWechat(){

        VolvaneWechat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }

    private boolean checkForm(){

        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();
        boolean isPass=true;
        if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("wrong format of your email");
            isPass=false;
        } else{
            mEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
