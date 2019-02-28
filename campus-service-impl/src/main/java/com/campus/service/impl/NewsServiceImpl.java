package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.CourseDomain;
import com.campus.domain.NewsDomain;
import com.campus.domain.respository.NewsDomainRepository;
import com.campus.model.param.NewsParam;
import com.campus.service.AbstractService;
import com.campus.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class NewsServiceImpl extends AbstractService implements NewsService {

    @Autowired
    NewsDomainRepository newsDomainRepository;

    @Override
    public CallResult getNewsList(NewsParam newsParam) {
        synchronized (this){
            final NewsDomain newsDomain = newsDomainRepository.createDomain(newsParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**操作**/
                @Override
                public CallResult doAction() {
                    return newsDomain.getNewsList();
                }
            });
        }
    }

    @Override
    public CallResult addNews(NewsParam newsParam) {
        final NewsDomain newsDomain = newsDomainRepository.createDomain(newsParam);
        return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
            @Override
            public CallResult checkParam() {
                return newsDomain.checkParam();
            }
            /**操作**/
            @Override
            public CallResult doAction() {
                return newsDomain.addNews();
            }
        });
    }

    @Override
    public CallResult editNews(NewsParam newsParam) {
        final NewsDomain newsDomain = newsDomainRepository.createDomain(newsParam);
        return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
            @Override
            public CallResult checkParam() {
                return newsDomain.checkNewsIdParam();
            }
            /**操作**/
            @Override
            public CallResult doAction() {
                return newsDomain.editNews();
            }
        });
    }

    @Override
    public CallResult deleteNews(NewsParam newsParam) {
        final NewsDomain newsDomain = newsDomainRepository.createDomain(newsParam);
        return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
            @Override
            public CallResult checkParam() {
                return newsDomain.checkNewsIdParam();
            }
            /**操作**/
            @Override
            public CallResult doAction() {
                return newsDomain.deleteNews();
            }
        });
    }

    @Override
    public CallResult getNews(NewsParam newsParam) {
        final NewsDomain newsDomain = newsDomainRepository.createDomain(newsParam);
        return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
            @Override
            public CallResult checkParam() {
                return newsDomain.checkNewsIdParam();
            }

            /** 操作 **/
            @Override
            public CallResult doAction() {
                return newsDomain.getNews();
            }
        });
    }
    /*查询新闻咨询详情*/
    @Override
    public CallResult getNewsDetail(NewsParam param) {
        synchronized (this){
            final NewsDomain newsDomain = newsDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return newsDomain.checkPrimaryKeyParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return newsDomain.getNewsDetail();
                }
            });
        }
    }
   /***查询新闻list**/
    @Override
    public CallResult getNewsListDetail(NewsParam param) {
        synchronized (this){
            final NewsDomain newsDomain = newsDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return CallResult.success();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return newsDomain.getNewsList();
                }
            });
        }

    }
}
