package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.User;
import com.campus.model.param.UserParam;

public interface UserService {
     CallResult findById(UserParam userParam);
     User findByUserName(String userName);
     CallResult getUserList(UserParam userParam);
     CallResult addUser(UserParam userParam);
     CallResult editUser(UserParam userParam);
     CallResult deleteUser(UserParam userParam);
}
