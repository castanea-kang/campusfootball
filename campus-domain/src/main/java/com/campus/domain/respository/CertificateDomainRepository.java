package com.campus.domain.respository;

import com.campus.dao.pojo.Certificate;
import com.campus.domain.CertificateDomain;
import com.campus.domain.GradeDomain;
import com.campus.model.CertificateModel;
import com.campus.model.param.CertificateParam;
import com.campus.model.param.GradeParam;
import com.campus.mybais.mapper.CertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class CertificateDomainRepository {

    @Autowired
    private CertificateMapper certificateMapper;

    public CertificateDomain createDomain(){
        return new CertificateDomain(this);
    }

    public List<Certificate> findCertificateList(){
        return certificateMapper.findCertificateList();
    }
    /**通过证书ID　查询证书信息**/
    public Certificate getCertificateById(Integer certificateId) {
        return createDomain().findCertificateById(certificateId);
    }
    /**通过证书ID　查询证书信息 实现**/
    public Certificate findCertificateById(Integer certificateId) {
        return certificateMapper.findCertificateById(certificateId);
    }
}
