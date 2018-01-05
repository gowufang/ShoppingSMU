package me.wufang.volvane.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import me.wufang.volvane.app.ConfigKeys;
import me.wufang.volvane.app.Volvane;

/**
 * Created by wu on 2018/1/5.
 * Email:gowufang@gmail.com
 */

public class VolvaneWechat {
    public static final String APP_ID = Volvane.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Volvane.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;

    private static final class Holder {
        private static final VolvaneWechat INSTANCE = new VolvaneWechat();

    }

    public static VolvaneWechat getInstance() {
        return Holder.INSTANCE;
    }

    private VolvaneWechat() {
        final Activity activity = Volvane.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="random_state";
        WXAPI.sendReq(req);
    }
}
