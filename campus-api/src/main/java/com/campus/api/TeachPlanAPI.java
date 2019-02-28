package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.TeachPlanParam;
import com.campus.service.TeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.CallSite;

/**
 * TO DO :教案+游戏方案+视频教学
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 10:27
 */
@RestController
@RequestMapping(value = "/teachPlanAPI")
public class TeachPlanAPI {
    @Autowired
    TeachPlanService  teachPlanService;
    /**
     * @Description:教案+游戏方案+视频--->列表查询接口
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 13:43 2018-08-24
     */
    @RequestMapping(value = "/getTeachPlanList",method = RequestMethod.POST)
     public CallResult getTeachPlanList(@RequestBody TeachPlanParam param){
         return  teachPlanService.getTeachPlanList(param);
     }

    /**
     * @Description:关键词搜索（根据文件title模糊搜索文件）
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:00 2018-08-24
     */
    @RequestMapping(value = "/searchTeachPlanList",method = RequestMethod.POST)
     public CallResult searchTeachPlanList(@RequestBody TeachPlanParam param){
        return  teachPlanService.searchTeachPlanList(param);
     }

    /**
     * @Description:添加关注
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 18:02 2018-11-07
     */
    @RequestMapping(value = "/addViews",method = RequestMethod.POST)
     public CallResult addViews(@RequestBody TeachPlanParam param){
         return  teachPlanService.addViews(param);
     }
}
