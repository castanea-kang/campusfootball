package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.data.Cnst;
import com.campus.domain.respository.VcodeDomainRepository;
import com.campus.domain.utils.HttpSender;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-08 15:41
 */
public class VcodeDomain {
    private VcodeDomainRepository vcodeDomainRepository;
    public VcodeDomain(VcodeDomainRepository vcodeDomainRepository) {
        this.vcodeDomainRepository = vcodeDomainRepository;
    }
    /**
     * @Description: 发送短信
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:41 2018-08-08
     */
    public CallResult sendCode(String phone,Integer type) {
        Log log = LogFactory.getLog(this.getClass());
        try {
            if (!phone.matches(Cnst.Regex.phone)) {
                return CallResult.fail("手机号输入错误，请重新输入");
            }
            if (type != Cnst.SmsCodeType.editPassword &&
                    type != Cnst.SmsCodeType.thirdBinding &&
                    type != Cnst.SmsCodeType.register &&
                    type != Cnst.SmsCodeType.changePhone) {
                return CallResult.fail("未获取到正确的验证码发送类型");
            }
            StringBuilder sb = new StringBuilder();
            String smsKey = vcodeDomainRepository.getVcode(phone, type);
            if (StringUtils.isBlank(smsKey)) {
                for (int i = 0; i < 6; i++) {
                    sb.append(new Random().nextInt(10));
                }
                smsKey = sb.toString();
            } else {
                sb.append(smsKey);
            }
            vcodeDomainRepository.addCache(smsKey,Cnst.LOGIN_SMS_KEY + type + "_" + phone,  3 * 60);
            if (type == Cnst.SmsCodeType.register) {
                sb.append("（注册验证码），用于手机号").append(phone.substring(0, 3)).append("******").append(phone.substring(9)).append("，三分钟有效内，请勿转发。");
            } else if (type == Cnst.SmsCodeType.editPassword) {
                sb.append("（重置密码验证码），用于手机号").append(phone.substring(0, 3)).append("******").append(phone.substring(9)).append("，三分钟有效内，请勿转发。");
            } else if (type == Cnst.SmsCodeType.thirdBinding) {
                sb.append("（绑定第三方登录），用于手机号").append(phone.substring(0, 3)).append("******").append(phone.substring(9)).append("，三分钟有效内，请勿转发。");
            } else if (type == Cnst.SmsCodeType.changePhone) {
                sb.append("（变更手机号），用于手机号").append(phone.substring(0, 3)).append("******").append(phone.substring(9)).append("，三分钟有效内，请勿转发。");
            }
            /*正式环境打开**/
           // HttpSender.batchSend(Cnst.smsUrl, Cnst.smsUsername, Cnst.smsPassword, phone, sb.toString(), false, null);
            log.debug(type + "手机号" + phone.substring(0, 3) + "******" + phone.substring(9) + "验证码已发送" + smsKey);
        } catch (Exception e) {
            log.error(e.getMessage());
            return CallResult.fail("短信发送失败");
        }
        return CallResult.success(60);
    }

    /**
     * @Description:确认验证码是否正确
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 10:14 2018-09-05
     */
    public CallResult codeVerify(String phone,Integer type, String verifyCode) {
        if(type == null || phone == null || verifyCode == null){
            return CallResult.fail("参数错误！");
        }
        String smsKey = null;
        try{
            smsKey = vcodeDomainRepository.findVcode(phone,type);
        }catch (Exception e){
            return CallResult.fail("验证失败！");
        }
        /**测试*/
        if ("987456".equals(verifyCode)){
            return CallResult.success();
        }else {
            return CallResult.fail();
        }
        /**正式**/

        /*if(smsKey != null && !smsKey.equals(verifyCode)){
            return CallResult.fail("验证码错误！");
        }else if(smsKey == null){
            return CallResult.fail("验证失败！");
        }
        return CallResult.success("验证成功!");*/
    }

    /**
     * @Description:获取验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:33 2018-08-08
     */
    public String getVcode(String phone, Integer type) {
      return   vcodeDomainRepository.findVcode(phone,type);
    }
}
