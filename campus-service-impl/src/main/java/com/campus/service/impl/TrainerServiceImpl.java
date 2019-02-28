package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.TrainerDomain;
import com.campus.domain.respository.TrainerDomainRepository;
import com.campus.model.param.TrainerParam;
import com.campus.service.AbstractService;
import com.campus.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl extends AbstractService implements TrainerService {
    @Autowired
    TrainerDomainRepository trainerDomainRepository;

    @Override
    public CallResult getTrainerList(TrainerParam trainerParam) {
        synchronized (this){
             final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(trainerParam);
             return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult doAction() {
                    return trainerDomain.getTrainerList();
                }
            });
        }
    }

    @Override
    public CallResult addTrainer(TrainerParam trainerParam) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(trainerParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.addTrainer();
                }
            });
        }
    }

    @Override
    public CallResult login(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkLoginParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.login();
                }
            });
        }
    }

    @Override
    public CallResult register(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkRGParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.register();
                }
            });
        }
    }

    @Override
    public CallResult editPassword(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkEditPasswordParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.editPassword();
                }
            });
        }
    }

    @Override
    public CallResult editTrainer(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.editTrainer();
                }
            });
        }
    }

    @Override
    public CallResult deleteTrainer(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.deleteTrainer();
                }
            });
        }
    }

    @Override
    public CallResult getTrainer(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.getTrainer();
                }
            });
        }
    }

    @Override
    public CallResult getClassTrainerList(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkClassIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.getClassTrainerList();
                }
            });
        }
    }

    @Override
    public CallResult addClassTrainer(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkCTrainerParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.addClassTrainer();
                }
            });
        }
    }

    /**
     * @Description:查看我的收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:23 2018-08-24
     */
    @Override
    public CallResult getMyCollection(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.getMyCollection();
                }
            });
        }
    }

    /**
     * @Description:更换手机号
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:22 2018-08-24
     */
    @Override
    public CallResult replacePhone(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.replacePhone();
                }
            });
        }
    }

    /**
     * @Description:更换登录密码
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:31 2018-08-24
     */
    @Override
    public CallResult replacePassword(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.replacePassword();
                }
            });
        }
    }
    /**注册时发送手机验证码**/
    @Override
    public CallResult sendRegisterCode(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkPhoneParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.sendRegisterCode();
                }
            });
        }
    }
    /*忘记密码发送验证码*/
    @Override
    public CallResult forgetPasswordSendCode(TrainerParam param) {

        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkPhoneParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.forgetPasswordSendCode();
                }
            });
        }

    }
    /**我的培训**/
    @Override
    public CallResult getMyCourse(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.getMyCourse();
                }
            });
        }
    }
    /**更换手机号发送验证码**/
    @Override
    public CallResult replacePhoneSendCode(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkPhoneParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.replacePhoneSendCode();
                }
            });
        }
    }

    /**
     * @Description:添加收藏
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 14:44 2018-09-05
     */
    @Override
    public CallResult addMyCollection(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.addMyCollection();
                }
            });
        }
    }

    @Override
    public CallResult sendAdvice(TrainerParam param) {
        synchronized (this){
            final TrainerDomain trainerDomain = trainerDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public  CallResult checkParam(){
                    return trainerDomain.checkTrainerIdParam();
                }
                @Override
                public CallResult doAction() {
                    return trainerDomain.sendAdvice();
                }
            });
        }
    }
}
