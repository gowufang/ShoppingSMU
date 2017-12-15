package me.wufang.volvane.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.R;
import me.wufang.volvane.ec.R2;
import me.wufang.volvane.net.RestClient;
import me.wufang.volvane.net.callback.ISuccess;

/**
 * Created by wu on 2017/12/15.
 * Email:gowufang@gmail.com
 */

public class SignUpDelegate extends VolvaneDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName=null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail=null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone=null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword=null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword=null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){//通过验证，向服务器提交信息
//            RestClient.builder()
//                    .url("sign_up")
//                    .params("","")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    })
//                    .build()
//                    .post();
            Toast.makeText(getContext(),"验证成功",Toast.LENGTH_SHORT).show();

        }
    }

    private boolean checkForm(){
        final String name=mName.getText().toString();
        final String email=mEmail.getText().toString();
        final String phone=mPhone.getText().toString();
        final String password=mPassword.getText().toString();
        final String rePassword=mRePassword.getText().toString();

        boolean isPass=true;
        if (name.isEmpty()){
            mName.setError("please set name");
            isPass=false;

        }else {
            mName.setError(null);
        }

        if (email.isEmpty()|| Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("wrong format of your email");
            isPass=false;
        } else{
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
