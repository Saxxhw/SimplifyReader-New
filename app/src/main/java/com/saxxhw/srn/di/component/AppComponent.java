package com.saxxhw.srn.di.component;

import com.saxxhw.srn.App;
import com.saxxhw.srn.di.ContextLife;
import com.saxxhw.srn.di.module.AppModule;
import com.saxxhw.srn.model.db.DBHelper;
import com.saxxhw.srn.model.http.HttpHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * @return 提供App的Context
     */
    @ContextLife("Application")
    App getContext();

    /**
     * http请求帮助类
     *
     * @return
     */
    HttpHelper httpHelper();

    /**
     * 数据库帮助类
     *
     * @return
     */
    DBHelper dbHelper();
}
