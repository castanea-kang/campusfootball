package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.GradeDomain;
import com.campus.domain.respository.GradeDomainRepository;
import com.campus.service.AbstractService;
import com.campus.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class GradeServiceImpl extends AbstractService implements GradeService{

    @Autowired
    GradeDomainRepository gradeDomainRepository;

    @Override
    public CallResult findGradeList() {
        synchronized (this){
            final GradeDomain gradeDomain = gradeDomainRepository.createDomain();
            return  this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult doAction() {
                    return gradeDomain.findGradeList();
                }
            });
        }
    }
}
