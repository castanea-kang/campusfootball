package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.BannerParam;
import com.campus.model.param.NewsParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface BannerService {

    /**获取banner新闻列表**/
    CallResult getBannerList(BannerParam bannerParam);
    /**添加banner新闻**/
    CallResult addBanner(BannerParam bannerParam);
    /**编辑banner新闻**/
    CallResult editBanner(BannerParam bannerParam);
    /**获取banner新闻**/
    CallResult getBanner(BannerParam bannerParam);
    /**删除banner新闻**/
    CallResult deleteBanner(BannerParam bannerParam);
}
