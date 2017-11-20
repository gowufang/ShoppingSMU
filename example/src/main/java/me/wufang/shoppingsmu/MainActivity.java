package me.wufang.shoppingsmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.wufang.volvane.activities.ProxyActivity;
import me.wufang.volvane.delegates.VolvaneDelegate;

public class MainActivity extends ProxyActivity{

    @Override
    public VolvaneDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
