package com.campus.mybais.mapper;


import com.campus.dao.pojo.FootballKnow;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FootballKnowMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public FootballKnowMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<FootballKnow> findFootballKnowList(Map<String,Object> params){
        return this.sqlSessionTemplate.selectList("findFootballKnowList",params);
    }
    public FootballKnow findFootballKnowById(Integer id){
        return sqlSessionTemplate.selectOne("findFootballKnowById",id);
    }
    public Integer addFootballKnow(FootballKnow footballKnow){
        return sqlSessionTemplate.insert("insertFootballKnowSelective",footballKnow);
    }
    public Integer editFootballKnow(FootballKnow footballKnow){
        return sqlSessionTemplate.update("updateFootballKnowByIdSelective",footballKnow);
    }
    public Integer deleteFootballKnow(Integer id){
        return sqlSessionTemplate.delete("deleteFootBallKnowById",id);
    }
}