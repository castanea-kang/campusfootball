package com.campus.domain;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.Grade;
import com.campus.dao.pojo.LoginLog;
import com.campus.domain.respository.GradeDomainRepository;
import com.campus.domain.respository.LoginLogDomainRepository;
import com.campus.model.param.GradeParam;
import com.campus.model.param.LoginLogParam;

import java.util.Date;
import java.util.List;

/**
 * Created by likang on 2018/8/6.
 */
public class LoginLogDomain {

    private LoginLogDomainRepository loginLogDomainRepository;
    private LoginLogParam loginLogParam;

    public LoginLogDomain(LoginLogDomainRepository loginLogDomainRepository){
        this.loginLogDomainRepository = loginLogDomainRepository;
    }
    public LoginLogDomain(LoginLogDomainRepository loginLogDomainRepository, LoginLogParam loginLogParam){
        this.loginLogDomainRepository = loginLogDomainRepository;
        this.loginLogParam = loginLogParam;
    }

    public CallResult addLoginLog(){
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginTime(new Date().getTime());
        loginLog.setUserId(loginLogParam.getUserId());
        Integer ret = this.loginLogDomainRepository.addLoginLog(loginLog);
        if(ret < 1){
            return CallResult.fail("失败");
        }
        return CallResult.success("成功");
    }

    public List<LoginLog> findLoginLogList(){
        return this.loginLogDomainRepository.findLoginLogList(loginLogParam.getUserId());
    }

}
