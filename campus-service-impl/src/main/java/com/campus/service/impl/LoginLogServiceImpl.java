package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.LoginLogDomain;
import com.campus.domain.respository.LoginLogDomainRepository;
import com.campus.model.param.LoginLogParam;
import com.campus.service.AbstractService;
import com.campus.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class LoginLogServiceImpl extends AbstractService implements LoginLogService {

    @Autowired
    LoginLogDomainRepository loginLogDomainRepository;

    @Override
    public CallResult addLoginLog(LoginLogParam loginLogParam) {
        synchronized (this){
            final LoginLogDomain loginLogDomain = loginLogDomainRepository.createDomain(loginLogParam);
            return  this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult doAction() {
                    return loginLogDomain.addLoginLog();
                }
            });
        }
    }
}
