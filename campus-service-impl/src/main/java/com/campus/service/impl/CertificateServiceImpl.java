package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.CertificateDomain;
import com.campus.domain.respository.CertificateDomainRepository;
import com.campus.service.AbstractService;
import com.campus.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class CertificateServiceImpl extends AbstractService implements CertificateService {

    @Autowired
    CertificateDomainRepository certificateDomainRepository;

    @Override
    public CallResult getCertificateList() {
        synchronized (this){
            final CertificateDomain certificateDomain = certificateDomainRepository.createDomain();
            return  this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult doAction() {
                    return certificateDomain.getCertificateList();
                }
            });
        }
    }
}
