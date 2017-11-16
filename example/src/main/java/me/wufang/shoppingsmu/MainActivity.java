package me.wufang.shoppingsmu;

import me.wufang.volvane.activities.ProxyActivity;
import me.wufang.volvane.delegates.VolvaneDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public VolvaneDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
