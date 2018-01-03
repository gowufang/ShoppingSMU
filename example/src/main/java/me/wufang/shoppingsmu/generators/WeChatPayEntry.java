package me.wufang.shoppingsmu.generators;

import com.example.annotations.PayEntryGenerator;

import me.wufang.volvane.wechat.templates.WXPayEntryTemplate;

/**
 * Created by wu on 2018/1/3.
 * Email:gowufang@gmail.com
 */
@PayEntryGenerator(
        packageName = "me.wufang.shoppingsmu",
        payEntryTemplate = WXPayEntryTemplate.class
)
public class WeChatPayEntry {
}
