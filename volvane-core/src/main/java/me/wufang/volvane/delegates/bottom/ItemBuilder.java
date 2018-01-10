package me.wufang.volvane.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by wu on 2018/1/10.
 * Email:gowufang@gmail.com
 */

public final class ItemBuilder {
    //map建立对应的映射关系
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();  //youxude map

    static ItemBuilder builder(){
        return new ItemBuilder();

    }
    public final ItemBuilder addItem(BottomTabBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;

    }
    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build(){
        return ITEMS;
    }
}
