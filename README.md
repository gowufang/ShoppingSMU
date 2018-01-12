## 一些记录
annotations和compiler中这两个model是用来接入微信的。绕过微信的限制

compiler中的代码是仿照Butterknife的，直接cp过来的。





## javapoet简介

当我们在处理注解或元数据文件的时候，往往有自动生成源代码的需要。比如：[java基础之注解](https://www.jianshu.com/p/ca7f22b4b751)，里面讲到了一个例子，在运行时通过反射处理注解，为我们实例化控件并添加点击事件，然而这种方法很大的一个缺点就是用了反射，导致app性能下降。其实我们完全可以通过接下来要讲的内容来在编译期间自动生成一个viewHolder.java，viewHolder里面的内容是实例化view控件并为其添加上点击事件。 这样就不需要再用反射，那么也就解决了性能问题。然而这个viewHolder.java是在编译期间生成的，也就和我们手动写的.java源文件一样。

通过编译期间自动生成的代码，你无需编写样板，无需再一次又一次地写findViewById和addOnClickListener。把用在这部分上的时间用在解决其他问题上，是不是也就提高了我们的效率呢？其实自动生成源代码的事情也就是javapoet要帮我们做的。

```
compile 'com.squareup:javapoet:1.9.0'
```
很多跟代码生成的都用到它。

作者：尸情化异
链接：https://www.jianshu.com/p/95f12f72f69a
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

compiler modle中还用到
```
 compile 'com.google.auto.service:auto-service:1.0-rc3'
 compile 'com.google.auto:auto-common:0.8'
```

RestClient是自定义的一个网络框架

VolvaneWechat中完成调用微信接口的方法

##透明activity
   ```
 <style name="Theme.AppCompat.Translucent">
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:colorBackgroundCacheHint">@null</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowAnimationStyle">@android:style/Animation</item>

    </style>
```

```
  WXAPI.sendReq(req);//微信api接口发送登陆请求
```

## 底部状态栏
首先需要bean 来存储每个tab的信息，然后需要基类来实现每个tab的功能

_mActivity是Fragmentation里的，是宿主activity
https://github.com/YoKeyword/Fragmentation
```
compile 'me.yokeyword:fragmentation:1.1.8'
compile 'me.yokeyword:fragmentation-swipeback:1.1.8'

```

# 推荐工具
手机的显示屏在电脑上同步显示：https://www.airdroid.com/zh-cn/

[HandShaker - Manage Your Android Phones at Ease](https://itunes.apple.com/us/app/handshaker-manage-your-android-phones-at-ease/id1012930195?mt=12)