package com.campus.service.impl;

import cn.com.hisee.common.model.CallResult;
import cn.com.hisee.common.service.AbstractTemplateAction;
import com.campus.domain.CourseDomain;
import com.campus.domain.GradeDomain;
import com.campus.domain.respository.CourseDomainRepository;
import com.campus.domain.respository.GradeDomainRepository;
import com.campus.model.param.CourseParam;
import com.campus.service.AbstractService;
import com.campus.service.CourseService;
import com.campus.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by likang on 2018/8/6.
 */
@Service
public class CourseServiceImpl extends AbstractService implements CourseService {

    @Autowired
    CourseDomainRepository courseDomainRepository;

    @Override
    public CallResult getClassCourseList(CourseParam courseParam) {
        synchronized (this){
            final CourseDomain courseDomain = courseDomainRepository.createDomain(courseParam);
            return this.serviceTemplate.exeInTransaction(new AbstractTemplateAction() {
                /**检查参数**/
                @Override
                public CallResult checkParam() {
                    return courseDomain.checkParam();
                }
                /**操作**/
                @Override
                public CallResult doAction() {
                    return courseDomain.getClassCourseList();
                }
            });
        }
    }
}
