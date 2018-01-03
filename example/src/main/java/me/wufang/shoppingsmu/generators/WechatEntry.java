package me.wufang.shoppingsmu.generators;

import com.example.annotations.EntryGenerator;

import me.wufang.volvane.wechat.templates.AppRegisterTemplate;
import me.wufang.volvane.wechat.templates.WXEntryTemplate;

/**
 * Created by wu on 2018/1/3.
 * Email:gowufang@gmail.com
 */
@EntryGenerator(
        packageName = "me.wufang.shoppingsmu",
        entryTemplate = WXEntryTemplate.class
)
public interface WechatEntry {
}
