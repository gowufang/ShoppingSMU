package me.wufang.volvane.app;

/**
 * Created by Administrator on 2017/11/15.
 * Email:gowufang@gmail.com
 */
//enum 是 单例，只能被初始化一次，进行惰性单例初始化
public enum ConfigType {
    API_HOST,//请求域名
    APPLICATION_CONTEXT,//全局初始化
    CONFIG_READY,//chushihua shifou wancheng
    ICON
}
