package com.campus.api;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.Trainer;
import com.campus.domain.TrainClassDomain;
import com.campus.model.param.TrainerParam;
import com.campus.service.TrainerService;
import com.sun.org.apache.regexp.internal.RE;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-07 17:36
 */
@RestController
@RequestMapping("/trainerAPI")
public class TrainerAPI {
    /**
     * @Description:训练师登录
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:43 2018-08-07
     */
    @Autowired
    TrainerService trainerService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CallResult login(@RequestBody TrainerParam param){
        return trainerService.login(param);
    }

    /**
     * @Description:注册
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:45 2018-08-07
     */
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public  CallResult register(@RequestBody TrainerParam param){
        return trainerService.register(param);
    }

    /**
     * @Description:发送注册验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 14:44 2018-08-27
     */
    @RequestMapping(value = "/sendRegisterCode" , method = RequestMethod.POST)
    public CallResult sendRegisterCode(@RequestBody TrainerParam param){
        return trainerService.sendRegisterCode(param);
    }
    /**
     * @Description:修改密码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:48 2018-08-07
     */
    @RequestMapping(value = "/editPassword" , method = RequestMethod.POST)
    public  CallResult editPassword(@RequestBody TrainerParam param){
        return trainerService.editPassword(param);
    }

    /**
     * @Description:忘记密码发送验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 13:57 2018-08-27
     */
    @RequestMapping(value = "/forgetPasswordSendCode",method = RequestMethod.POST)
    public CallResult forgetPasswordSendCode(@RequestBody TrainerParam param){
        return trainerService.forgetPasswordSendCode(param);
    }
    /**
     * @Description:更换手机号
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:21 2018-08-24
     */
    @RequestMapping(value = "/replacePhone",method = RequestMethod.POST)
    public  CallResult replacePhone(@RequestBody TrainerParam param){
       return  trainerService.replacePhone(param);
    }

    /**
     * @Description:修改手机号发送验证码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 10:00 2018-09-05
     */
    @RequestMapping(value = "/replacePhoneSendCode")
    public CallResult replacePhoneSendCode(@RequestBody TrainerParam param){
        return  trainerService.replacePhoneSendCode(param);
    }
    /**
     * @Description:更换登录密码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:30 2018-08-24
     */
    @RequestMapping(value = "/replacePassword",method = RequestMethod.POST)
    public  CallResult replacePassword(@RequestBody TrainerParam param){
        return trainerService.replacePassword(param);
    }
    /**
     * @Description:查看个人信息(包含证书信息)
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:17 2018-08-24
     */
    @RequestMapping(value = "/getTrainerMessage" , method = RequestMethod.POST)
    public CallResult getTrainerMessage(@RequestBody TrainerParam param){
        return trainerService.getTrainer(param);
    }

    /**
     * @Description:我的收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:54 2018-09-03
     */
    @RequestMapping(value = "/getMyCollection",method = RequestMethod.POST)
    public CallResult getMyCollection(@RequestBody TrainerParam param){
        return  trainerService.getMyCollection(param);
    }

    /**
     * @Description:添加收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 14:28 2018-09-05
     */
    @RequestMapping(value = "/addMyCollection" , method = RequestMethod.POST)
    public CallResult  addMyCollection(@RequestBody TrainerParam param){
        return trainerService.addMyCollection(param);
    }

    /**
     * @Description:我的培训
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 10:50 2018-09-04
     */
    @RequestMapping(value = "/getMyCourse",method = RequestMethod.POST)
    public  CallResult getMyCourse(@RequestBody TrainerParam param){
        return  trainerService.getMyCourse(param);
    }

    /**
     * @Description:意见反馈
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 10:28 2018-09-06
     */
    @RequestMapping(value = "/sendAdvice",method = RequestMethod.POST)
    public  CallResult sendAdvice(@RequestBody TrainerParam param){
        return trainerService.sendAdvice(param);
    }
}
