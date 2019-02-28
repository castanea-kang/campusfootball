package com.campus.mybais.mapper;



import com.campus.dao.pojo.Course;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CourseMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public CourseMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Integer addCourse(Course course){
        return sqlSessionTemplate.insert("insertCourseSelective",course);
    }
    public Integer editCourse(Course course){
        return sqlSessionTemplate.update("updateCourseByIdSelective",course);
    }
    public Integer deleteCourseByClassId(Integer classId){
        return sqlSessionTemplate.delete("deleteCourseByClassId",classId);
    }
    public List<Course> findCourseListByClassId(Integer classId){
        return sqlSessionTemplate.selectList("findCourseListByClassId",classId);
    }
    public Course findCourseByClassIdAndTime(Integer classId,Long time){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("classId", classId);
        paramMap.put("time", time);
        return sqlSessionTemplate.selectOne("findCourseByClassIdAndTime",paramMap);
    }
    public Integer deleteCourseByParam(Map<String, Object> pmap){
        return sqlSessionTemplate.delete("deleteCourseByParam",pmap);
    }
}