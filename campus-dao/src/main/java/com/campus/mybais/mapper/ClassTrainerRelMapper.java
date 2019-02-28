package com.campus.mybais.mapper;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassTrainerRelMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ClassTrainerRelMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}