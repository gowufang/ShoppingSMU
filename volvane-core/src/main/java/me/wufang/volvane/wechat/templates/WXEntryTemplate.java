package me.wufang.volvane.wechat.templates;

import me.wufang.volvane.activities.ProxyActivity;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.wechat.BaseWXEntryActivity;
import me.wufang.volvane.wechat.VolvaneWechat;

/**
 * Created by wu on 2018/1/3.
 * Email:gowufang@gmail.com
 */

public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {

        VolvaneWechat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
