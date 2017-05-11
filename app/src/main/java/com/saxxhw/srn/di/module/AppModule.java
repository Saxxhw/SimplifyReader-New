package com.saxxhw.srn.di.module;

import com.saxxhw.srn.App;
import com.saxxhw.srn.di.ContextLife;
import com.saxxhw.srn.model.db.DBHelper;
import com.saxxhw.srn.model.http.HttpHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    App provideAppContext() {
        return mApp;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper() {
        return new HttpHelper();
    }

    @Provides
    @Singleton
    DBHelper provideDbHelper() {
        return new DBHelper();
    }
}
