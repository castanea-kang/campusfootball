package com.campus.config;

import com.campus.dao.pojo.User;
import com.campus.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        User user  = (User)principals.getPrimaryPrincipal();
//        for(Role role:roleService.findByUserId(user.getUserId())){
//            authorizationInfo.addRole(role.getName());
//            for(com.greenstar.dao.pojo.Resource p:resourceService.findByRoleId(role.getId())){
//                authorizationInfo.addStringPermission(p.getCode());
//            }
//        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.findByUserName(username);
        System.out.println("----->>userInfo="+userInfo+"=="+getName());
        if(userInfo == null){
            return null;
        }
        Object obj = new SimpleHash("md5",userInfo.getPassword(),ByteSource.Util.bytes(userInfo.getUsername()),2);
        System.out.println(obj);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getUsername()),
                getName()  //realm name
        );
        return authenticationInfo;
    }

}