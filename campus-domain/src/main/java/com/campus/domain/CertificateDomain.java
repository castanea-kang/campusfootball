package com.campus.domain;

import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.Certificate;
import com.campus.dao.pojo.Grade;
import com.campus.domain.respository.CertificateDomainRepository;
import com.campus.domain.respository.GradeDomainRepository;
import com.campus.model.CertificateModel;
import com.campus.model.param.GradeParam;

import java.util.List;

/**
 * Created by likang on 2018/8/6.
 */
public class CertificateDomain {

    private CertificateDomainRepository certificateDomainRepository;

    public CertificateDomain(CertificateDomainRepository certificateDomainRepository){
        this.certificateDomainRepository = certificateDomainRepository;
    }
    public CallResult getCertificateList(){
        List<Certificate> certificateList = this.certificateDomainRepository.findCertificateList();
        return CallResult.success(certificateList);
    }

    /**
     * @Description:通过ID查询证书信息
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 17:30 2018-09-03
     */
    public Certificate findCertificateById(Integer certificateId) {
       Certificate certificate =  certificateDomainRepository.findCertificateById(certificateId);
        return certificate;
    }
}
