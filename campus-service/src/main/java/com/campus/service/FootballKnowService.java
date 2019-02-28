package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.FootballKnowParam;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 15:14
 */
public interface FootballKnowService {
    /***查询足球知识列表**/
    CallResult getFootBallKnowList(FootballKnowParam param);
    /**根据id获取足球知识**/
    CallResult getFootballKnow(FootballKnowParam param);
    /**添加足球知识**/
    CallResult addFootballKnow(FootballKnowParam param);
    /**编辑足球知识**/
    CallResult editFootballKnow(FootballKnowParam param);
    /**删除足球知识**/
    CallResult deleteFootballKnow(FootballKnowParam param);
    /**add 足球知识浏览量**/
    CallResult addViews(FootballKnowParam param);
}
