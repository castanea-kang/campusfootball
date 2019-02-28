package com.campus.mybais.mapper;



import com.campus.dao.pojo.UserRoleRel;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRoleRelMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserRoleRelMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    public Integer addUserRoleRel(UserRoleRel userRoleRel){
        return sqlSessionTemplate.insert("insertUrrSelective",userRoleRel);
    }
    public UserRoleRel findUserRoleRel(Integer userId,Integer roleId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("roleId", roleId);
        return sqlSessionTemplate.selectOne("findUserRoleRelByUserIdAndRoleId",paramMap);
    }
    public Integer deleteUserRoleRelByUserId(Integer userId){
        return sqlSessionTemplate.delete("deleteUserRoleRelByUserId",userId);
    }
    public List<Integer> findRolesByUserId(Integer userId){
        return sqlSessionTemplate.selectList("findRolesByUserId",userId);
    }
    public Integer deleteUserRoleRelByUserIdAndRoleList(Integer userId,List<Integer> roleIds){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("roleIds", roleIds);
        return sqlSessionTemplate.delete("deleteUserRoleRelByUserIdAndRoleList",paramMap);
    }
}