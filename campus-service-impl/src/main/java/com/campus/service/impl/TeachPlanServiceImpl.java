package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.TeachPlanDomain;
import com.campus.domain.respository.TeachPlanDomainRepository;
import com.campus.model.param.TeachPlanParam;
import com.campus.service.AbstractService;
import com.campus.service.TeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class TeachPlanServiceImpl extends AbstractService implements TeachPlanService {

    @Autowired
    TeachPlanDomainRepository teachPlanDomainRepository;

    /**
     * @Description:获取banner新闻列表
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:07 2018-08-24
     */
    @Override
    public CallResult getTeachPlanList(TeachPlanParam teachPlanParam) {
        synchronized (this){
                final TeachPlanDomain teachPlanDomain = teachPlanDomainRepository.createDomain(teachPlanParam);
                return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                    @Override
                    public CallResult checkParam() {
                        return teachPlanDomain.checkParam();
                    }
                    /**操作**/
                    @Override
                    public CallResult doAction() {
                        return teachPlanDomain.getTeachPlanList();
                    }
                });
        }
    }

    /**
     * @Description:搜索文件
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 16:08 2018-08-24
     */
    @Override
    public CallResult searchTeachPlanList(TeachPlanParam param) {
        synchronized (this){
            final TeachPlanDomain teachPlanDomain = teachPlanDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return teachPlanDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return teachPlanDomain.searchTeachPlanList();
                }
            });
        }
    }

    @Override
    public CallResult addTeachPlan(TeachPlanParam teachPlanParam) {
        synchronized (this){
            final TeachPlanDomain teachPlanDomain = teachPlanDomainRepository.createDomain(teachPlanParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return teachPlanDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return teachPlanDomain.addTeachPlan();
                }
            });
        }
    }

    @Override
    public CallResult deleteTeachPlan(TeachPlanParam teachPlanParam) {
        synchronized (this){
            final TeachPlanDomain teachPlanDomain = teachPlanDomainRepository.createDomain(teachPlanParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return teachPlanDomain.checkDeleteParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return teachPlanDomain.deleteTeachPlan();
                }
            });
        }
    }

    @Override
    public CallResult editTeachPlan(TeachPlanParam teachPlanParam) {
        synchronized (this){
            final TeachPlanDomain teachPlanDomain = teachPlanDomainRepository.createDomain(teachPlanParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return teachPlanDomain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return teachPlanDomain.editTeachPlan();
                }
            });
        }
    }

    @Override
    public CallResult addViews(TeachPlanParam param) {
        synchronized (this){
            final TeachPlanDomain teachPlanDomain = teachPlanDomainRepository.createDomain(param);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                @Override
                public CallResult checkParam() {
                    return teachPlanDomain.checkIdParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return teachPlanDomain.addViews();
                }
            });
        }
    }

}
