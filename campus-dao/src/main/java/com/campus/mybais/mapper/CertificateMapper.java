package com.campus.mybais.mapper;


import com.campus.dao.pojo.Certificate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CertificateMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public CertificateMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Certificate> findCertificateList(){
        return this.sqlSessionTemplate.selectList("findAllCertificate");
    }

    public Certificate findCertificateById(Integer certificateId) {
        Map<Object, Object> map = new HashMap<>();
        map.put("certificateId",certificateId);
        return this.sqlSessionTemplate.selectOne("findCertificateById",map);
    }
}