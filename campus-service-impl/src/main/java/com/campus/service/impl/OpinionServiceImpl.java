package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.OpinionDomain;
import com.campus.domain.respository.OpinionDomainRepository;
import com.campus.model.param.OpinionParam;
import com.campus.service.AbstractService;
import com.campus.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class OpinionServiceImpl extends AbstractService implements OpinionService {

    @Autowired
    OpinionDomainRepository opinionDomainRepository;

    @Override
    public CallResult getOpinionList(OpinionParam opinionParam) {
        synchronized (this){
            final OpinionDomain opinionDomain = opinionDomainRepository.createDomain(opinionParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**操作**/
                @Override
                public CallResult doAction() {
                    return opinionDomain.getOpinionList();
                }
            });
        }
    }

    @Override
    public CallResult deleteOpinion(OpinionParam opinionParam) {
        synchronized (this){
            final OpinionDomain opinionDomain = opinionDomainRepository.createDomain(opinionParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return opinionDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return opinionDomain.deleteOpinion();
                }
            });
        }
    }

    @Override
    public CallResult getOpinion(OpinionParam opinionParam) {
        synchronized (this){
            final OpinionDomain opinionDomain = opinionDomainRepository.createDomain(opinionParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return opinionDomain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return opinionDomain.getOpinion();
                }
            });
        }
    }
}
