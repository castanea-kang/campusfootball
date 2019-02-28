package com.campus.domain.respository;


import com.campus.dao.pojo.Opinion;
import com.campus.dao.pojo.Trainer;
import com.campus.domain.OpinionDomain;
import com.campus.domain.TrainerDomain;
import com.campus.model.param.OpinionParam;
import com.campus.model.param.TrainerParam;
import cn.com.hisee.common.model.CallResult;
import com.campus.mybais.mapper.OpinionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-09-06 11:14
 */
@Component
public class OpinionDomainRepository {

    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private TrainerDomainRepository trainerDomainRepository;


    public OpinionDomain createDomain() {
        return new OpinionDomain(this);
    }

    public OpinionDomain createDomain(OpinionParam opinionParam) {
        return new OpinionDomain(this, opinionParam);
    }

    public List<Opinion> findOpinionList(Map<String, Object> params) {
        return opinionMapper.findOpinionList(params);
    }

    public Trainer findTrainerByTrainerId(Integer trainerId) {
        TrainerParam trainerParam = new TrainerParam();
        trainerParam.setTrainerId(trainerId);
        TrainerDomain trainerDomain = trainerDomainRepository.createDomain(trainerParam);
        return trainerDomain.findTrainerByTrainerId();
    }

    public Integer deleteOpinion(Integer id) {
        return opinionMapper.deleteOpinion(id);
    }

    public Opinion findOpinionById(Integer id) {
        return opinionMapper.findOpinionById(id);
    }

    /**消息反馈**/
    public CallResult sendAdvice(Map<String, Object> map) {
        return createDomain(new OpinionParam()).sendAdvice(map);
    }
    /*添加反馈消息***/
    public Integer insertOpinion(Map<String, Object> map) {
        return opinionMapper.insertOpinion(map);
    }
}
