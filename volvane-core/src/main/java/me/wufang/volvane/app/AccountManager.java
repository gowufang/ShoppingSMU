package me.wufang.volvane.app;

import me.wufang.volvane.util.storage.VolvanePreference;

/**
 * Created by wu on 2017/12/15.
 * Email:gowufang@gmail.com
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登陆状态，登陆后调用
    public static void setSignState(boolean state){
        VolvanePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }


    private static boolean isSignIn(){
        return VolvanePreference.getAppFlag(SignTag.SIGN_TAG.name());//tagname ?

    }
    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            //如果已经登陆了，那么执行登陆的回调，否则执行未登录的回调
            checker.onNotSignIn();
        }else {
            checker.onNotSignIn();
        }
    }





}
