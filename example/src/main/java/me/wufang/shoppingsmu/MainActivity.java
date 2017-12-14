package me.wufang.shoppingsmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import me.wufang.volvane.activities.ProxyActivity;
import me.wufang.volvane.app.Volvane;
import me.wufang.volvane.delegates.VolvaneDelegate;
import me.wufang.volvane.ec.launcher.LauncherDelegate;
import me.wufang.volvane.ec.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity{

    @Override
    public VolvaneDelegate setRootDelegate() {

        ;//这个Context是ExampleApp中init的this，也就是传入到了配置文件的hashmap里面了
        Toast.makeText(Volvane.getApplication(),"get Context",Toast.LENGTH_LONG).show();
        return new LauncherScrollDelegate();




    }
}
