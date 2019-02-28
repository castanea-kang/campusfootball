package com.campus.domain.respository;

import com.campus.dao.pojo.Course;
import com.campus.dao.pojo.TrainClass;
import com.campus.domain.CourseDomain;
import com.campus.domain.TrainClassDomain;
import com.campus.model.param.CourseParam;
import com.campus.model.param.TrainClassParam;
import com.campus.mybais.mapper.CourseMapper;
import com.campus.mybais.mapper.TrainClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class CourseDomainRepository {

    @Autowired
    private CourseMapper courseMapper;

    public CourseDomain createDomain(CourseParam courseParam){
        return new CourseDomain(this,courseParam);
    }
    public CourseDomain createDomain(Course course){
        return new CourseDomain(this,course);
    }
    public Integer addCourse(Course course){
        return courseMapper.addCourse(course);
    }
    public Integer editCourse(Course course){
        return courseMapper.editCourse(course);
    }
    public Integer deleteCourseByClassId(Integer classId){
        return courseMapper.deleteCourseByClassId(classId);
    }
    public List<Course> findCourseListByClassId(Integer classId){
        return courseMapper.findCourseListByClassId(classId);
    }
    public Course findCourseByClassIdAndTime(Integer classId,Long time){
        return courseMapper.findCourseByClassIdAndTime(classId,time);
    }
    public Integer deleteCourseByParam(Map<String,Object> pmap){
        return courseMapper.deleteCourseByParam(pmap);
    }
   /***通过参训者ID 获取相应的培训信息**/
    public List<Course> getCourseMessageByTrainClassId(Integer trainClassId) {
        return createDomain(new Course()).findCourseMessageByTrainerId(trainClassId);
    }


}
