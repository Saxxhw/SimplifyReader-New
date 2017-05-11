package com.saxxhw.srn.model.http.utils;

import com.socks.library.KLog;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Saxxhw on 2016/12/14.
 * 邮箱：Saxxhw@126.com
 * 功能：自定义网络请求拦截器,打印请求内容
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        KLog.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        KLog.json(content);
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }
}
