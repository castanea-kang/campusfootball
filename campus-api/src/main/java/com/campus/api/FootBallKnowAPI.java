package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.FootballKnow;
import com.campus.model.param.FootballKnowParam;
import com.campus.service.FootballKnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TO DO :足球知识
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 15:09
 */
@RestController
@RequestMapping(value = "/footBallKnowAPI")
public class FootBallKnowAPI {
    /**
     * @Description:获取足球知识列表
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:20 2018-11-07
     */
    @Autowired
    FootballKnowService footballKnowService;
    @RequestMapping(value = "/getFootBallKnowList",method = RequestMethod.POST)
    public CallResult getFootBallKnowList(@RequestBody FootballKnowParam param){
      return   footballKnowService.getFootBallKnowList(param);
    }
    @RequestMapping(value = "/addViews",method = RequestMethod.POST)
    public CallResult  addViews(@RequestBody FootballKnowParam param){
        return footballKnowService.addViews(param);
    }
}
