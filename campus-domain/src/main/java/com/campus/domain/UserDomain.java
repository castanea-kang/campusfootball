package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.LoginLog;
import com.campus.dao.pojo.News;
import com.campus.dao.pojo.User;
import com.campus.dao.pojo.UserRoleRel;
import com.campus.domain.respository.UserDomainRepository;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.UserModel;
import com.campus.model.param.UserParam;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDomain {

    private static final BeanCopier p2m = BeanCopier.create(User.class, UserModel.class, false);

    private UserDomainRepository sysUserDomainRepository;
    private UserParam userParam;

    /**构造器*/
    public UserDomain(UserDomainRepository sysUserDomainRepository, UserParam userParam){
        this.sysUserDomainRepository = sysUserDomainRepository;
        this.userParam  = userParam;
    }

    public CallResult checkParam(){
        Integer userId = userParam.getId();
        if (ParamCheckUtils.isBlank(userId)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }
    /**copy 实体 到 model*/
    private UserModel copy(User sysUser) {
        if (sysUser ==  null){
            return null;
        }
        UserModel model = new UserModel();
        p2m.copy(sysUser,model,null);
        return  model;
    }
    public CallResult findById(){

        System.out.print(userParam.getId()  + "------");

        User sysUser = this.sysUserDomainRepository.findbyId(userParam.getId());
        UserModel sysUserModel =  copy(sysUser);
        List<Integer> roleTypes = this.sysUserDomainRepository.findRolesByUserId(userParam.getId());
        sysUserModel.setRoleList(roleTypes);
        return CallResult.success(sysUserModel);
    }
    public CallResult getUserList(){
        List<User> userList;
        List<UserModel> userModelList = new ArrayList<>();
        Map<String,Object> params = new HashMap<String,Object>();
        if(!ParamCheckUtils.isBlank(userParam.getKeyword())){
            params.put("keyword",userParam.getKeyword());
        }
        userList = this.sysUserDomainRepository.findUserList(params);
        for(User user : userList){
            List<LoginLog> loginLogs = this.sysUserDomainRepository.findLoginLogList(user.getId());
            UserModel userModel = copy(user);
            if(loginLogs != null){
                userModel.setLoginCount(loginLogs.size());
                if(loginLogs.size()>0){
                    userModel.setLastLogin(loginLogs.get(0).getLoginTime());
                }
            }
            userModelList.add(userModel);
        }
        return CallResult.success(userModelList);
    }

    public CallResult addUser(){
        User oldUser = this.sysUserDomainRepository.findUserByPhone(userParam.getPhone());
        if(oldUser != null){
            return CallResult.fail("此用户已存在");
        }
        User user = new User();
        if(!ParamCheckUtils.isBlank(userParam.getUsername())){
            user.setUsername(userParam.getUsername());
        }
        if(!ParamCheckUtils.isBlank(userParam.getRealName())){
            user.setRealName(userParam.getRealName());
        }
        if(!ParamCheckUtils.isBlank(userParam.getPhone())){
            user.setPhone(userParam.getPhone());
        }
        if(!ParamCheckUtils.isBlank(userParam.getPassword()) && !userParam.getPassword().equals("000000")){
            Object obj = new SimpleHash("md5",userParam.getPassword(), ByteSource.Util.bytes(userParam.getUsername()),2);
            user.setPassword(obj.toString());
        }
        if(!ParamCheckUtils.isBlank(userParam.getArea())){
            user.setArea(userParam.getArea());
        }
        Integer ret = this.sysUserDomainRepository.addUser(user);
        if(ret < 1){
            return CallResult.fail("添加失败");
        }
        User su = this.sysUserDomainRepository.findUserByPhone(userParam.getPhone());
        if(!ParamCheckUtils.isBlank(userParam.getRoleList())){
            List<Integer> roles = userParam.getRoleList();
            for(int i=0;i<roles.size();i++){
                UserRoleRel userRoleRel = new UserRoleRel();
                userRoleRel.setUserId(su.getId());
                userRoleRel.setRoleId(roles.get(i));
                this.sysUserDomainRepository.addUserRoleRel(userRoleRel);
            }
        }
        return CallResult.success("添加成功！");
    }
    public CallResult editUser(){
        User oldUser = this.sysUserDomainRepository.findbyId(userParam.getId());
        if(oldUser == null){
            return CallResult.fail("此用户不存在");
        }
        if(!ParamCheckUtils.isBlank(userParam.getUsername())){
            oldUser.setUsername(userParam.getUsername());
        }
        if(!ParamCheckUtils.isBlank(userParam.getRealName())){
            oldUser.setRealName(userParam.getRealName());
        }
        if(!ParamCheckUtils.isBlank(userParam.getPhone())){
            oldUser.setPhone(userParam.getPhone());
        }
        if(!ParamCheckUtils.isBlank(userParam.getPassword()) && !userParam.getPassword().equals("000000")){
            Object obj = new SimpleHash("md5",userParam.getPassword(), ByteSource.Util.bytes(userParam.getUsername()),2);
            oldUser.setPassword(obj.toString());
        }
        if(!ParamCheckUtils.isBlank(userParam.getArea())){
            oldUser.setArea(userParam.getArea());
        }
        Integer ret = this.sysUserDomainRepository.editUser(oldUser);
        if(ret < 1){
            return CallResult.fail("编辑失败");
        }
        if(!ParamCheckUtils.isBlank(userParam.getRoleList())){
            List<Integer> roles = userParam.getRoleList();
            for(int i=0;i<roles.size();i++){
                UserRoleRel oldUrr = this.sysUserDomainRepository.findUserRoleRel(oldUser.getId(),roles.get(i));
                if(oldUrr == null){
                    UserRoleRel userRoleRel = new UserRoleRel();
                    userRoleRel.setUserId(oldUser.getId());
                    userRoleRel.setRoleId(roles.get(i));
                    this.sysUserDomainRepository.addUserRoleRel(userRoleRel);
                }
            }
            this.sysUserDomainRepository.deleteUserRoleRelByUserIdAndRoleList(oldUser.getId(),roles);
        }
        return CallResult.success("编辑成功！");
    }

    public CallResult deleteUser(){
        Integer ret = this.sysUserDomainRepository.deleteUser(userParam.getId());
        if(ret < 1){
            return CallResult.fail("删除失败");
        }
        this.sysUserDomainRepository.deleteUserRoleRelByUserId(userParam.getId());
        return CallResult.success("删除成功！");
    }
}
