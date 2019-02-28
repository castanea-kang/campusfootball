package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.dao.pojo.User;
import com.campus.domain.UserDomain;
import com.campus.domain.respository.UserDomainRepository;
import com.campus.model.param.UserParam;
import com.campus.mybais.mapper.UserMapper;
import com.campus.service.AbstractService;
import com.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService implements UserService {
    @Autowired
    UserDomainRepository userDomainRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public CallResult findById(UserParam userParam) {
        synchronized (this){
             final UserDomain userDomain = userDomainRepository.createDomain(userParam);
             return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                 @Override
                 public CallResult checkParam() {
                     return userDomain.checkParam();
                 }
                 @Override
                 public CallResult doAction() {
                     return userDomain.findById();
                 }
             });
        }
    }
    @Override
    public User findByUserName(String userName) {
        return userMapper.findUserByUsername(userName);
    }

    @Override
    public CallResult getUserList(UserParam userParam) {
        synchronized (this){
            final UserDomain userDomain = userDomainRepository.createDomain(userParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult doAction() {
                    return userDomain.getUserList();
                }
            });
        }
    }

    @Override
    public CallResult addUser(UserParam userParam) {
        final UserDomain userDomain = userDomainRepository.createDomain(userParam);
        return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
            @Override
            public CallResult doAction() {
                return userDomain.addUser();
            }
        });
    }

    @Override
    public CallResult editUser(UserParam userParam) {
        synchronized (this){
            final UserDomain userDomain = userDomainRepository.createDomain(userParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return userDomain.checkParam();
                }
                @Override
                public CallResult doAction() {
                    return userDomain.editUser();
                }
            });
        }
    }

    @Override
    public CallResult deleteUser(UserParam userParam) {
        synchronized (this){
            final UserDomain userDomain = userDomainRepository.createDomain(userParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return userDomain.checkParam();
                }
                @Override
                public CallResult doAction() {
                    return userDomain.deleteUser();
                }
            });
        }
    }
}
