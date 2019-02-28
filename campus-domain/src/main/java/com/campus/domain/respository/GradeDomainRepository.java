package com.campus.domain.respository;

import com.campus.dao.pojo.Grade;
import com.campus.domain.GradeDomain;
import com.campus.model.param.GradeParam;
import com.campus.mybais.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class GradeDomainRepository {

    @Autowired
    private GradeMapper gradeMapper;

    public GradeDomain createDomain(){
        return new GradeDomain(this);
    }

    public GradeDomain createDomain(GradeParam gradeParam){
        return new GradeDomain(this,gradeParam);
    }

    public List<Grade> findGradeList(){
        return gradeMapper.findGradeList();
    }

    public Grade findGradeByGradeId(Integer gradeId){
        return gradeMapper.findGradeById(gradeId);
    }
}
