package com.campus.mybais.mapper;

import com.campus.dao.pojo.Opinion;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OpinionMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public OpinionMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Opinion> findOpinionList(Map<String,Object> params){
        return  sqlSessionTemplate.selectList("findOpinionList",params);
    }

    public Integer deleteOpinion(Integer id){
        return sqlSessionTemplate.delete("deleteOpinionByPrimaryKey",id);
    }

    public Opinion findOpinionById(Integer id){
        return  sqlSessionTemplate.selectOne("selectOpinionByPrimaryKey",id);}
    /**添加反馈消息**/
    public Integer insertOpinion(Map<String, Object> map) {
        return sqlSessionTemplate.insert("insertOpinion",map);
    }
}