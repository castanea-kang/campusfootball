package com.campus.mybais.mapper;


import com.campus.dao.pojo.TeachPlan;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TeachPlanMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public TeachPlanMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<TeachPlan> findTeachPlanList(Map<String,Object> param){
        return sqlSessionTemplate.selectList("findTeachPlanList",param);
    }
    public Integer addTeachPlan(TeachPlan teachPlan){
        return  sqlSessionTemplate.insert("insertTeachPlanSelective",teachPlan);
    }
    public Integer deleteTeachPlan(Integer id){
        return  sqlSessionTemplate.delete("deleteTeachPlanById",id);
    }
    public TeachPlan findTeachPlanById(Integer id){
        return  sqlSessionTemplate.selectOne("findTeachPlanById",id);
    }
    public Integer editTeachPlan(TeachPlan teachPlan){
        return sqlSessionTemplate.update("updateTeachPlanByIdSelective",teachPlan);
    }
}