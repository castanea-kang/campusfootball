package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.TrainClassParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface TrainClassService {

    /**获取培训班级**/
    CallResult getTrainClass(TrainClassParam param);
    /**获取班级列表**/
    CallResult getTrainClassList(TrainClassParam param);
    /**添加培训班级**/
    CallResult addTrainClass(TrainClassParam param);
    /**编辑培训班级**/
    CallResult editTrainClass(TrainClassParam param);
    /**删除培训班级**/
    CallResult deleteTrainClass(TrainClassParam param);
    /**删除班级学员**/
    CallResult deleteClassTrainer(TrainClassParam param);
}
