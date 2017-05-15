package com.saxxhw.srn.model.http.api;

import com.saxxhw.srn.model.bean.ImageListEntity;
import com.saxxhw.srn.model.bean.VideoListEntity;

import java.util.List;

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
    Observable<ImageListEntity> getImageList(@Query("col") String category,
                                             @Query("tag") String tag,
                                             @Query("pn") int total,
                                             @Query("rn") int pageNum,
                                             @Query("from") int from);

    /**
     * 获取视频列表
     *
     * @param keyword    视频类别关键字
     * @param page       页码数
     * @param count      每页条数
     * @param publicType 类型
     * @param paid       paid
     * @param period     视频日期
     * @param orderby    排序
     * @param clientId   id
     * @return 视频列表json
     */
    @GET("by_keyword.json")
    Observable<VideoListEntity> getVideoList(@Query("keyword") String keyword,
                                  @Query("page") int page,
                                  @Query("count") int count,
                                  @Query("public_type") String publicType,
                                  @Query("paid") int paid,
                                  @Query("period") String period,
                                  @Query("orderby") String orderby,
                                  @Query("client_id") String clientId);
}
