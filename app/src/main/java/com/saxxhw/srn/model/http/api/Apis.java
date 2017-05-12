package com.saxxhw.srn.model.http.api;

import com.saxxhw.srn.model.bean.ImageListEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Saxxhw on 2017/5/11.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public interface Apis {

    /**
     * 获取图片列表
     *
     * @param category 图片类别
     * @param tag      固定值-"全部"
     * @param total    每页条数*页码数
     * @param pageNum  当前页码数
     * @param from     固定值-"1"
     * @return 图片列表json
     */
    @GET("imgs/")
    Observable<ImageListEntity> getImageList(@Query("col") String category, @Query("tag") String tag, @Query("pn") int total, @Query("rn") int pageNum, @Query("from") int from);
}
