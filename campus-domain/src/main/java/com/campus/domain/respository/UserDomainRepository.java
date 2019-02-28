package com.campus.domain.respository;


import com.campus.dao.pojo.LoginLog;
import com.campus.dao.pojo.User;
import com.campus.dao.pojo.UserRoleRel;
import com.campus.domain.LoginLogDomain;
import com.campus.domain.UserDomain;
import com.campus.model.param.LoginLogParam;
import com.campus.model.param.UserParam;
import com.campus.mybais.mapper.UserMapper;
import com.campus.mybais.mapper.UserRoleRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserDomainRepository {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRelMapper userRoleRelMapper;
    @Autowired
    LoginLogDomainRepository loginLogDomainRepository;

    public UserDomain createDomain(UserParam userParam){
        return  new UserDomain(this, userParam);
    }
    public User findbyId(Integer id){
        return  userMapper.findById(id);
    }
    public List<User> findUserList(Map<String,Object> params){
        return  userMapper.findUserList(params);
    }

    public List<LoginLog> findLoginLogList(Integer userId){
        LoginLogParam loginLogParam = new LoginLogParam();
        loginLogParam.setUserId(userId);
        LoginLogDomain loginLogDomain = loginLogDomainRepository.createDomain(loginLogParam);
        return loginLogDomain.findLoginLogList();
    }
    public User findUserByPhone(String phone){
        return userMapper.findUserByPhone(phone);
    }
    public Integer addUser(User user){
        return userMapper.addUser(user);
    }
    public Integer addUserRoleRel(UserRoleRel userRoleRel){
        return userRoleRelMapper.addUserRoleRel(userRoleRel);
    }
    public Integer editUser(User user){
        return userMapper.editUser(user);
    }
    public Integer deleteUser(Integer userId){
        return userMapper.deleteUser(userId);
    }
    public UserRoleRel findUserRoleRel(Integer userId,Integer roleId){
        return userRoleRelMapper.findUserRoleRel(userId,roleId);
    }
    public Integer deleteUserRoleRelByUserId(Integer userId){
        return userRoleRelMapper.deleteUserRoleRelByUserId(userId);
    }
    public Integer deleteUserRoleRelByUserIdAndRoleList(Integer userId,List<Integer> roles){
        return userRoleRelMapper.deleteUserRoleRelByUserIdAndRoleList(userId,roles);
    }
    public List<Integer> findRolesByUserId(Integer userId){
        return userRoleRelMapper.findRolesByUserId(userId);
    }

}
