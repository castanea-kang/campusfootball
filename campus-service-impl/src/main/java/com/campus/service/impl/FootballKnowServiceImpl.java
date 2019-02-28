package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.dao.pojo.FootballKnow;
import com.campus.domain.FootballKnowDomain;
import com.campus.domain.respository.FootballKnowDomainRepository;
import com.campus.model.param.FootballKnowParam;
import com.campus.service.AbstractService;
import com.campus.service.FootballKnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 15:15
 */
@Service
public class FootballKnowServiceImpl extends AbstractService implements FootballKnowService{
    @Autowired
    FootballKnowDomainRepository footballKnowDomainRepository;
    @Override
    public CallResult getFootBallKnowList(FootballKnowParam param) {
        synchronized (this){
            final FootballKnowDomain footballKnowDemain = footballKnowDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return footballKnowDemain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return footballKnowDemain.getFootBallKnowList();
                }
            });
        }

    }

    @Override
    public CallResult getFootballKnow(FootballKnowParam param) {
        synchronized (this){
            final FootballKnowDomain footballKnowDemain = footballKnowDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return footballKnowDemain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return footballKnowDemain.getFootballKnow();
                }
            });
        }
    }

    @Override
    public CallResult addFootballKnow(FootballKnowParam param) {
        synchronized (this){
            final FootballKnowDomain footballKnowDemain = footballKnowDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return footballKnowDemain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return footballKnowDemain.addFootballKnow();
                }
            });
        }
    }

    @Override
    public CallResult editFootballKnow(FootballKnowParam param) {
        synchronized (this){
            final FootballKnowDomain footballKnowDemain = footballKnowDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return footballKnowDemain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return footballKnowDemain.editFootballKnow();
                }
            });
        }
    }

    @Override
    public CallResult deleteFootballKnow(FootballKnowParam param) {
        synchronized (this){
            final FootballKnowDomain footballKnowDemain = footballKnowDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return footballKnowDemain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return footballKnowDemain.deleteFootballKnow();
                }
            });
        }
    }

    @Override
    public CallResult addViews(FootballKnowParam param) {
        synchronized (this){
            final FootballKnowDomain footballKnowDemain = footballKnowDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return footballKnowDemain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return footballKnowDemain.addViews();
                }
            });
        }
    }
}
