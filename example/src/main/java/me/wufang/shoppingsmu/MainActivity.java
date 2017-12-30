package me.wufang.shoppingsmu;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import me.wufang.volvane.activities.ProxyActivity;
import me.wufang.volvane.app.Volvane;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.launcher.LauncherDelegate;
import me.wufang.volvane.ec.launcher.LauncherScrollDelegate;
import me.wufang.volvane.ec.sign.ISignListener;
import me.wufang.volvane.ec.sign.SignInDelegate;
import me.wufang.volvane.ec.sign.SignUpDelegate;
import me.wufang.volvane.ui.launcher.ILauncherListener;
import me.wufang.volvane.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();//隐藏掉actionbar
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public VolvaneDelegate setRootDelegate() {

        ;//这个Context是ExampleApp中init的this，也就是传入到了配置文件的hashmap里面了
        Toast.makeText(Volvane.getApplication(), "get Context", Toast.LENGTH_LONG).show();
//        return new LauncherDelegate();
        return new LauncherDelegate();


    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {

        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGINED:
                Toast.makeText(this, "启动结束，用户登陆了", Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没有登陆", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }

    }
}
