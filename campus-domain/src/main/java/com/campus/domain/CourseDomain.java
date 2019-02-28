package com.campus.domain;

import cn.com.hisee.common.model.BusinessCodeEnum;
import cn.com.hisee.common.model.CallResult;
import com.campus.dao.pojo.Course;
import com.campus.domain.respository.CourseDomainRepository;
import com.campus.domain.utils.ParamCheckUtils;
import com.campus.model.param.CourseParam;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;


public class CourseDomain {

    private static final BeanCopier p2m = BeanCopier.create(CourseParam.class, Course.class, false);
    private CourseDomainRepository courseDomainRepository;
    private CourseParam courseParam;
    private Course course;

    /**构造器*/
    public CourseDomain(CourseDomainRepository courseDomainRepository, CourseParam courseParam){
        this.courseDomainRepository = courseDomainRepository;
        this.courseParam  = courseParam;
    }
    public CourseDomain(CourseDomainRepository courseDomainRepository, Course course){
        this.courseDomainRepository = courseDomainRepository;
        this.course  = course;
    }

    /**copy 实体 到 model*/
    private Course copy(CourseParam param) {
        if (param ==  null){
            return null;
        }
        Course course = new Course();
        p2m.copy(param,course,null);
        return  course;
    }

    public CallResult checkParam() {
        Integer cid = courseParam.getTrainClassId();
        if (ParamCheckUtils.isBlank(cid)){
            return CallResult.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT);
        }else {
            return CallResult.success();
        }
    }

    public Integer addCourse(){
        return this.courseDomainRepository.addCourse(course);
    }

    public Integer editCourse(){
        return this.courseDomainRepository.editCourse(course);
    }

    public Integer deleteCourseByClassId(){
        return this.courseDomainRepository.deleteCourseByClassId(courseParam.getTrainClassId());
    }

    public CallResult getClassCourseList(){
        List<Course> courseList  = this.courseDomainRepository.findCourseListByClassId(courseParam.getTrainClassId());
        return CallResult.success(courseList);
    }

    public Course findCourseByClassIdAndTime(){
        return this.courseDomainRepository.findCourseByClassIdAndTime(courseParam.getTrainClassId(),courseParam.getTime());
    }

    public Integer deleteCourseByParam(){
        return this.courseDomainRepository.deleteCourseByParam(courseParam.getPmap());
    }

    /**
     * @Description:通过trainerId查询日程信息
     * @Author:睿动体育|wangzhiqiang
     * @Params * @param null
     * @Date 15:06 2018-09-04
     */
    public List<Course> findCourseMessageByTrainerId(Integer trainClassId) {
        return courseDomainRepository.findCourseListByClassId(trainClassId);
    }
}
