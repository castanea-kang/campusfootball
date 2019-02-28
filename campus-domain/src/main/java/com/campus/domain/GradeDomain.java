package com.campus.domain;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.Grade;
import com.campus.domain.respository.GradeDomainRepository;
import com.campus.model.param.GradeParam;

import java.util.List;

/**
 * Created by likang on 2018/8/6.
 */
public class GradeDomain {

    private GradeDomainRepository gradeDomainRepository;
    private GradeParam gradeParam;

    public GradeDomain(GradeDomainRepository gradeDomainRepository){
        this.gradeDomainRepository = gradeDomainRepository;
    }
    public GradeDomain(GradeDomainRepository gradeDomainRepository, GradeParam gradeParam){
        this.gradeDomainRepository = gradeDomainRepository;
        this.gradeParam = gradeParam;
    }

    public CallResult findGradeList(){
        List<Grade> gradeList = this.gradeDomainRepository.findGradeList();
        return CallResult.success(gradeList);
    }

    public Grade findGradeByGradeId(){
        return this.gradeDomainRepository.findGradeByGradeId(gradeParam.getId());
    }
}
