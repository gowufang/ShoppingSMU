package me.wufang.volvane.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import me.wufang.volvane.R;
import me.wufang.volvane.app.Volvane;
import me.wufang.volvane.delegates.VolvaneDelegate;

/**
 * Created by wu on 2018/1/10.
 * Email:gowufang@gmail.com
 */

public abstract class BottomItemDelegate extends VolvaneDelegate implements View.OnKeyListener{
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Volvane.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
