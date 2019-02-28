package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.TeachPlanParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface TeachPlanService {

    /**获取banner新闻列表**/
    CallResult getTeachPlanList(TeachPlanParam teachPlanParam);
    /**搜索文件**/
    CallResult searchTeachPlanList(TeachPlanParam param);
    /**添加教案**/
    CallResult addTeachPlan(TeachPlanParam teachPlanParam);
    /**删除教案**/
    CallResult deleteTeachPlan(TeachPlanParam teachPlanParam);
    /**编辑教案**/
    CallResult editTeachPlan(TeachPlanParam teachPlanParam);
    /*添加关注**/
    CallResult addViews(TeachPlanParam param);
}
