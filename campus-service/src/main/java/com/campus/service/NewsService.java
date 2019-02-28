package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.NewsParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface NewsService {

    /**获取新闻列表**/
    CallResult getNewsList(NewsParam newsParam);
    /**新增新闻**/
    CallResult addNews(NewsParam newsParam);
    /**编辑新闻**/
    CallResult editNews(NewsParam newsParam);
    /**删除新闻**/
    CallResult deleteNews(NewsParam newsParam);
    /**根据id获取新闻**/
    CallResult getNews(NewsParam newsParam);
    /**查询新闻咨询详情**/
    CallResult getNewsDetail(NewsParam param);
    /**获取新闻list***/
    CallResult getNewsListDetail(NewsParam param);
}
