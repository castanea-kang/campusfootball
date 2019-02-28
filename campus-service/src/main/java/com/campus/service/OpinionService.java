package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.OpinionParam;

/**
 * Created by likang on 2018/9/4.
 */
public interface OpinionService {

    CallResult getOpinionList(OpinionParam opinionParam);
    /**删除反馈意见**/
    CallResult deleteOpinion(OpinionParam opinionParam);
    /**获取反馈信息**/
    CallResult getOpinion(OpinionParam opinionParam);
}
