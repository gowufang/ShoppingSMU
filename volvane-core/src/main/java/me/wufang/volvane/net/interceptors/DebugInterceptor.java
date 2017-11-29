package me.wufang.volvane.net.interceptors;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by wu on 2017/11/28.
 * Email:gowufang@gmail.com
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debug_url, int debug_raw_id) {
        DEBUG_URL = debug_url;
        DEBUG_RAW_ID = debug_raw_id;
    }
//获取文件
private Response
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
