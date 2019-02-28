package com.campus.service;

import cn.com.hisee.common.model.CallResult;
import com.campus.model.param.CourseParam;

/**
 * Created by likang on 2018/8/6.
 */
public interface CourseService {

    CallResult getClassCourseList(CourseParam courseParam);
}
