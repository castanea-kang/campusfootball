package com.campus.mybais.mapper;


import com.campus.dao.pojo.Grade;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public GradeMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Grade> findGradeList(){
        return this.sqlSessionTemplate.selectList("findAllGrade");
    }
    public Grade findGradeById(Integer id){
        return this.sqlSessionTemplate.selectOne("findGradeById",id);
    }
}