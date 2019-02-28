package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.NewsParam;
import com.campus.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TO DO :新闻资讯
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 9:40
 */
@RestController
@RequestMapping(value = "/newsAPI")
public class NewsAPI {
    @Autowired
    NewsService newsService;
    /**
     * @Description:查询咨询详情
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 9:41 2018-08-24
     */
    @RequestMapping(value = "/getNewDetail")
    public CallResult getNewsDetail(@RequestBody NewsParam param){
        return   newsService.getNewsDetail(param);
    }

    /**
     * @Description:获取
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:42 2018-08-30
     */
    @RequestMapping(value = "/getNewsList",method = RequestMethod.POST)
    public  CallResult getNewsList(@RequestBody NewsParam param){
        return   newsService.getNewsListDetail(param);
    }

}
