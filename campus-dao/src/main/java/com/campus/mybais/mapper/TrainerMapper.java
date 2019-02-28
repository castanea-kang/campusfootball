package com.campus.mybais.mapper;


import com.campus.dao.pojo.Trainer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
public class TrainerMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public TrainerMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Trainer> findTrainerList(Map<String,Object> params){
        return sqlSessionTemplate.selectList("findTrainerListByParams",params);
    }

    public Trainer findTrainByIdCard(String idCard){
        return sqlSessionTemplate.selectOne("findTrainerByIdCard",idCard);
    }

    public Integer addTrainer(Trainer trainer){
        int insertTrainerSelective = sqlSessionTemplate.insert("insertTrainerSelective", trainer);
        if (insertTrainerSelective > 0) return trainer.getId();
        return 0;
    }

    public Trainer findTrainById(Integer id){
        return sqlSessionTemplate.selectOne("findTrainerById",id);
    }
    public Integer updateTrainer(Trainer trainer){
        return sqlSessionTemplate.update("updateTrainerById",trainer);
    }
    public Integer deleteTrainer(Integer id){
        return sqlSessionTemplate.delete("deleteTrainerById",id);
    }
    public List<Trainer> findTrainerListByClassId(Integer classId){
        return sqlSessionTemplate.selectList("findTrainerListByClassId",classId);
    }
}