package com.saxxhw.srn;

import android.app.Application;

import com.saxxhw.srn.di.component.AppComponent;
import com.saxxhw.srn.di.component.DaggerAppComponent;
import com.saxxhw.srn.di.module.AppModule;
import com.socks.library.KLog;

/**
 * Created by Saxxhw on 2017/4/11.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化日志打印工具类
        KLog.init(BuildConfig.DEBUG, "SimplifyReader");
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(instance)).build();
    }
}
