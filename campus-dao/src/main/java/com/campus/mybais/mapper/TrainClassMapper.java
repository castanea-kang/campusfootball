package com.campus.mybais.mapper;


import com.campus.dao.pojo.TrainClass;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TrainClassMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public TrainClassMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public TrainClass findTrainClassById(Integer id){
        return sqlSessionTemplate.selectOne("findTrainClassById",id);
    }
    public List<TrainClass> findTrainClassList(Map<String,Object> param){
        return sqlSessionTemplate.selectList("findTrainClassByParam",param);
    }
    public TrainClass findTrainClassByNameAndMaster(String name,String masterName){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("masterName", masterName);
        return sqlSessionTemplate.selectOne("findTrainClassByNameAndMaster",paramMap);
    }
    public Integer addTrainClass(TrainClass trainClass){
        return sqlSessionTemplate.insert("insertTrainClassSelective",trainClass);
    }

    public Integer editTrainClass(TrainClass trainClass){
        return sqlSessionTemplate.update("updateTrainClassByIdSelective",trainClass);
    }
    public Integer deleteTrainClass(Integer id){
        return sqlSessionTemplate.delete("deleteTrainClassById",id);
    }

    public TrainClass findTrainClassByTrainerId(Integer trainerId) {
        return  sqlSessionTemplate.selectOne("findTrainClassByTrainerId",trainerId);
    }
}