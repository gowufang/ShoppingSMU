package me.wufang.volvane.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import me.wufang.volvane.app.AccountManager;
import me.wufang.volvane.ec.database.DatabaseManager;
import me.wufang.volvane.ec.database.UserProfile;

/**
 * Created by wu on 2017/12/15.
 * Email:gowufang@gmail.com
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
    public static void onSignUp(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
