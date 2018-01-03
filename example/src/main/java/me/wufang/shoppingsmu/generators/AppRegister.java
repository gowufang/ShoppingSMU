package me.wufang.shoppingsmu.generators;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;

import me.wufang.volvane.wechat.templates.AppRegisterTemplate;
import me.wufang.volvane.wechat.templates.WXEntryTemplate;

/**
 * Created by wu on 2018/1/3.
 * Email:gowufang@gmail.com
 */
@AppRegisterGenerator(
        packageName = "me.wufang.shoppingsmu",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
