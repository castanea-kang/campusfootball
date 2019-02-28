package com.campus.domain.respository;

import com.campus.dao.pojo.Grade;
import com.campus.dao.pojo.TeachPlan;
import com.campus.domain.GradeDomain;
import com.campus.domain.TeachPlanDomain;
import com.campus.model.FootballKnowModel;
import com.campus.model.TeachPlanModel;
import com.campus.model.param.GradeParam;
import com.campus.model.param.TeachPlanParam;
import com.campus.mybais.mapper.TeachPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class TeachPlanDomainRepository {

    @Autowired
    private TeachPlanMapper teachPlanMapper;
    @Autowired
    GradeDomainRepository gradeDomainRepository;
    @Autowired
    FootballKnowDomainRepository footballKnowDomainRepository;

    public TeachPlanDomain createDomain(){
        return new TeachPlanDomain(this);
    }

    public TeachPlanDomain createDomain(TeachPlanParam teachPlanParam){
        return new TeachPlanDomain(this,teachPlanParam);
    }

    public List<TeachPlan> findTeachPlanList(Map<String,Object> param){
        return teachPlanMapper.findTeachPlanList(param);
    }
    public Integer addTeachPlan(TeachPlan teachPlan){
        return teachPlanMapper.addTeachPlan(teachPlan);
    }

    public Grade findGradeByGradeId(Integer gradeId){
        GradeParam gradeParam = new GradeParam();
        gradeParam.setId(gradeId);
        GradeDomain gradeDomain = gradeDomainRepository.createDomain(gradeParam);
        return gradeDomain.findGradeByGradeId();
    }

    public Integer deleteTeachPlan(Integer planId){
        return teachPlanMapper.deleteTeachPlan(planId);
    }

    public TeachPlan findTeachPlanById(Integer planId){
        return teachPlanMapper.findTeachPlanById(planId);
    }
    public Integer editTeachPlan(TeachPlan teachPlan){
        return teachPlanMapper.editTeachPlan(teachPlan);
    }
    /**通过关键词检索 足球知识**/
    public List<FootballKnowModel> findFootballKownByKeyword(String keyword) {
        return footballKnowDomainRepository.getFootballKownByKeyword(keyword);
    }
     /**通过ID 获取教案 + 游戏方案**/
    public TeachPlanModel getTeachPlanById(Integer teanPlanId) {
        return createDomain().getTeachPlanById(teanPlanId);
    }
}
