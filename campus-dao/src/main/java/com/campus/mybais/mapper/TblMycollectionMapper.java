package com.campus.mybais.mapper;

import com.campus.dao.pojo.TblMycollection;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class TblMycollectionMapper {
    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public TblMycollectionMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Integer addMyCollectionTrue(HashMap<String, Object> map) {
      return   sqlSessionTemplate.insert("addMyCollectionTrue",map);
    }

    public List<TblMycollection> findMyCollectionList(Integer trainerId) {
        return     sqlSessionTemplate.selectList("findMyCollectionList",trainerId);
    }
}