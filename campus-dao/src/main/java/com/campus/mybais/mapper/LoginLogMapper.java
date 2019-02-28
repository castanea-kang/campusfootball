package com.campus.mybais.mapper;


import com.campus.dao.pojo.LoginLog;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LoginLogMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public LoginLogMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Integer addLoginLog(LoginLog loginLog){
        return this.sqlSessionTemplate.insert("insertLoginLog",loginLog);
    }

    public List<LoginLog> findLoginLogList(Integer userId){
        return this.sqlSessionTemplate.selectList("findLoginLogList",userId);
    }

}