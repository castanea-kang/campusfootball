package com.campus.mybais.mapper;


import com.campus.dao.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public User findUserByUsername(String userName){
        return sqlSessionTemplate.selectOne("findUserByUserName",userName);
    }
    public List<User> findUserList(Map<String,Object> params){
        return sqlSessionTemplate.selectList("findUserList",params);
    }
    public User findUserByPhone(String phone){
        return sqlSessionTemplate.selectOne("findUserByPhone",phone);
    }
    public Integer addUser(User user){
        return sqlSessionTemplate.insert("insertUserSelective",user);
    }
    public User findById(Integer id){
        return sqlSessionTemplate.selectOne("findUserById",id);
    }
    public Integer editUser(User user){
        return sqlSessionTemplate.update("updateUserByIdSelective",user);
    }
    public Integer deleteUser(Integer id){
        return  sqlSessionTemplate.delete("deleteUserById",id);
    }
}