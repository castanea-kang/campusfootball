package com.campus.domain.respository;

import com.campus.dao.pojo.LoginLog;
import com.campus.domain.LoginLogDomain;
import com.campus.model.param.LoginLogParam;
import com.campus.mybais.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by likang on 2018/8/6.
 */
@Component
public class LoginLogDomainRepository {

    @Autowired
    private LoginLogMapper loginLogMapper;

    public LoginLogDomain createDomain(){
        return new LoginLogDomain(this);
    }

    public LoginLogDomain createDomain(LoginLogParam loginLogParam){
        return new LoginLogDomain(this,loginLogParam);
    }

    public Integer addLoginLog(LoginLog loginLog){
        return loginLogMapper.addLoginLog(loginLog);
    }
    public List<LoginLog> findLoginLogList(Integer userId){
        return loginLogMapper.findLoginLogList(userId);
    }

}
