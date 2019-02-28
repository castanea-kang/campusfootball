package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.BannerDomain;
import com.campus.domain.NewsDomain;
import com.campus.domain.respository.BannerDomainRepository;
import com.campus.domain.respository.NewsDomainRepository;
import com.campus.model.param.BannerParam;
import com.campus.model.param.NewsParam;
import com.campus.service.AbstractService;
import com.campus.service.BannerService;
import com.campus.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class BannerServiceImpl extends AbstractService implements BannerService {

    @Autowired
    BannerDomainRepository bannerDomainRepository;

    @Override
    public CallResult getBannerList(BannerParam bannerParam) {
        synchronized (this){
            final BannerDomain bannerDomain = bannerDomainRepository.createDomain(bannerParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**操作**/
                @Override
                public CallResult doAction() {
                    return bannerDomain.getBannerList();
                }
            });
        }
    }

    @Override
    public CallResult addBanner(BannerParam bannerParam) {
        synchronized (this){
            final BannerDomain bannerDomain = bannerDomainRepository.createDomain(bannerParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return bannerDomain.checkParamParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return bannerDomain.addBanner();
                }
            });
        }
    }

    @Override
    public CallResult editBanner(BannerParam bannerParam) {
        synchronized (this){
            final BannerDomain bannerDomain = bannerDomainRepository.createDomain(bannerParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return bannerDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return bannerDomain.editBanner();
                }
            });
        }
    }

    @Override
    public CallResult getBanner(BannerParam bannerParam) {
        synchronized (this){
            final BannerDomain bannerDomain = bannerDomainRepository.createDomain(bannerParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return bannerDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return bannerDomain.getBanner();
                }
            });
        }
    }

    @Override
    public CallResult deleteBanner(BannerParam bannerParam) {
        synchronized (this){
            final BannerDomain bannerDomain = bannerDomainRepository.createDomain(bannerParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return bannerDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return bannerDomain.deleteBanner();
                }
            });
        }
    }
}
