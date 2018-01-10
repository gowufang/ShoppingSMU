package me.wufang.volvane.delegates.bottom;

/**
 * Created by wu on 2018/1/10.
 * Email:gowufang@gmail.com
 */

public class BottomTabBean  {
    private final CharSequence ICON;
    private final CharSequence TITLE;


    public BottomTabBean(CharSequence icon, CharSequence title) {
        ICON = icon;
        TITLE = title;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
