package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.TrainerParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface TrainerService {

    /**查询培训师列表**/
    CallResult getTrainerList(TrainerParam trainerParam);

    /**添加培训师信息**/
    CallResult addTrainer(TrainerParam trainerParam);
     /**登录*/
    CallResult login(TrainerParam param);
    /**注册*/
    CallResult register(TrainerParam param);
    /**修改密码*/
    CallResult editPassword(TrainerParam param);
    /**编辑培训师*/
    CallResult editTrainer(TrainerParam param);
    /**删除培训师*/
    CallResult deleteTrainer(TrainerParam param);
    /**获取培训师**/
    CallResult getTrainer(TrainerParam param);
    /**获取班级成员**/
    CallResult getClassTrainerList(TrainerParam param);
    /**添加班级成员**/
    CallResult addClassTrainer(TrainerParam param);
     /**查看我的收藏**/
    CallResult getMyCollection(TrainerParam param);
    /****更换手机号**/
    CallResult replacePhone(TrainerParam param);
    /**更换登录密码**/
    CallResult replacePassword(TrainerParam param);
    /**注册时发送手机验证码**/
    CallResult sendRegisterCode(TrainerParam param );
     /*忘记密码**/
    CallResult forgetPasswordSendCode(TrainerParam param);
    /**我的培训**/
    CallResult getMyCourse(TrainerParam param);
    /**更换手机号发送验证码**/
    CallResult replacePhoneSendCode(TrainerParam param);
    /**添加我的收藏**/
    CallResult addMyCollection(TrainerParam param);
    /*意见反馈***/
    CallResult sendAdvice(TrainerParam param);
}
