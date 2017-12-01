package me.wufang.volvane.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import me.wufang.volvane.app.Volvane;
import me.wufang.volvane.net.callback.IRequest;
import me.wufang.volvane.net.callback.ISuccess;
import me.wufang.volvane.util.file.FileUtil;
import okhttp3.ResponseBody;

/**
 * Created by wu on 2017/11/24.
 * Email:gowufang@gmail.com
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir= (String) params[0];
        String extension= (String) params[1];
        final ResponseBody body= (ResponseBody) params[2];//传入请求体
        final String name= (String) params[3];

        final InputStream is =body.byteStream();
        if (downloadDir==null||downloadDir.equals("")){//download目录不存在或者有多余空的字符串
            downloadDir="down_loads";

        }
        if (extension==null||extension.equals("")){
            extension="";//这里暂时不处理，如果之后有什么默认的后缀需求可添加
        }
        if (name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);

        }else {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }

    }

    //执行完异步执行主线程的操作
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();//具体怎么end还没有写
        }
        //做一个单独处理，比如下载后，自动安装apk文件
        autoInstallApk(file);
    }

//默认进行自动安装
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {//获取文件后缀
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Volvane.getApplicationContext().startActivity(install);
        }
    }
}
