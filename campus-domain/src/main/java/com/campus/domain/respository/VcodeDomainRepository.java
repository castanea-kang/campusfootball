package com.campus.domain.respository;

import cn.com.hisee.common.model.CallResult;
import com.campus.data.Cnst;
import com.campus.domain.VcodeDomain;
import com.campus.domain.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TO DO :验证码相关操作
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-08 15:41
 */
@Component
public class VcodeDomainRepository {
    @Autowired
    RedisUtils redisUtils;
    public VcodeDomain createDomain(){
        return  new VcodeDomain(this);
    }
    /**获取验证码**/
    public String getVcode(String phone, Integer type) {
        return createDomain().getVcode(phone, type);
    }
    /**获取验证码 实现**/
    public String findVcode(String phone, Integer type) {
        String cache = redisUtils.getCache(Cnst.LOGIN_SMS_KEY+type+"_"+phone);
        return  cache;
    }
    /**添加缓存**/
    public void addCache(String smsKey, String s, int i) {
        redisUtils.addCache(smsKey, s,i);
    }
     /**发送验证码**/
    public CallResult sendCode(String phone, int smsCodeType) {
        return createDomain().sendCode(phone,smsCodeType);
    }
    /*验证验证码是否正确**/
    public  CallResult codeVerify(String phone,Integer type, String verifyCode){
        return createDomain().codeVerify(phone,type,verifyCode);
    }
}
