package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.utils.TimeUtil;
import com.campus.dao.pojo.Opinion;
import com.campus.dao.pojo.Trainer;
import com.campus.domain.respository.OpinionDomainRepository;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.OpinionModel;
import com.campus.model.param.OpinionParam;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
public class OpinionDomain {

    private static final BeanCopier p2m = BeanCopier.create(Opinion.class, OpinionModel.class, false);


    private OpinionDomainRepository opinionDomainRepository;
    private OpinionParam opinionParam;

    public OpinionDomain(OpinionDomainRepository opinionDomainRepository){
        this.opinionDomainRepository = opinionDomainRepository;
    }
    public OpinionDomain(OpinionDomainRepository opinionDomainRepository, OpinionParam opinionParam){
        this.opinionDomainRepository = opinionDomainRepository;
        this.opinionParam = opinionParam;
    }

    public CallResult checkParam(){
        List<Integer> opinionIds = opinionParam.getIds();
        if (ParamCheckUtils.isBlank(opinionIds)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    public CallResult checkIdParam(){
        Integer opinionId = opinionParam.getId();
        if (ParamCheckUtils.isBlank(opinionId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    private OpinionModel copy(Opinion opinion) {
        if (opinion ==  null){
            return null;
        }
        OpinionModel model = new OpinionModel();
        p2m.copy(opinion,model,null);
        return  model;
    }

    public CallResult getOpinionList(){
        List<Opinion> opinionList;
        Map<String,Object> params = new HashMap<String,Object>();
        if(!ParamCheckUtils.isBlank(opinionParam.getKeyword())){
            params.put("keyword",opinionParam.getKeyword());
        }
        if(!ParamCheckUtils.isBlank(opinionParam.getStartTime())){
            params.put("startTime",opinionParam.getStartTime());
        }
        if(!ParamCheckUtils.isBlank(opinionParam.getEndTime())){
            Long endtime = opinionParam.getEndTime();
            endtime = endtime*24*3600*1000;
            params.put("endTime",endtime);
        }
        opinionList = this.opinionDomainRepository.findOpinionList(params);
        List<OpinionModel> opinionModelList = new ArrayList<>();
        for(Opinion op : opinionList){
            OpinionModel opModel = copy(op);
            Trainer trainer = this.opinionDomainRepository.findTrainerByTrainerId(op.getTrainerId());
            String pubdateStr = TimeUtil.getyyyyMMddHHmmss1(op.getPubdate());
            opModel.setPubdateStr(pubdateStr);
            if(trainer != null){
                opModel.setTrainerName(trainer.getName());
                opModel.setPhone(trainer.getPhone());
            }
            opinionModelList.add(opModel);
        }
        return CallResult.success(opinionModelList);
    }

    public CallResult deleteOpinion(){
        for(Integer id:opinionParam.getIds()){
            Integer ret = this.opinionDomainRepository.deleteOpinion(id);
            if(ret < 1){
                return CallResult.fail("删除失败！");
            }
        }
        return CallResult.success("删除成功！");
    }

    public CallResult getOpinion(){
        Opinion opinion = this.opinionDomainRepository.findOpinionById(opinionParam.getId());
        if(opinion == null){
            return CallResult.fail("记录不存在！");
        }
        OpinionModel opModel = copy(opinion);
        Trainer trainer = this.opinionDomainRepository.findTrainerByTrainerId(opinion.getTrainerId());
        String pubdateStr = TimeUtil.getyyyyMMddHHmmss1(opinion.getPubdate());
        opModel.setPubdateStr(pubdateStr);
        if(trainer != null){
            opModel.setTrainerName(trainer.getName());
            opModel.setPhone(trainer.getPhone());
        }
        return CallResult.success(opModel);
    }
    public CallResult sendAdvice(Map<String, Object> map) {
         Integer i =  opinionDomainRepository.insertOpinion(map);
         if (i>0){
             return new CallResult(BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),"反馈消息成功",null);
         }
        return CallResult.fail(BusinessCodeEnum.CHECK_BIZ_NO_RESULT.getCode(),"反馈消息失败，稍后请重试");
    }
}
