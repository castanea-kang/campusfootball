package com.campus.domain.respository;

import com.campus.dao.pojo.Course;
import com.campus.dao.pojo.TrainClass;
import com.campus.dao.pojo.Trainer;
import com.campus.domain.CourseDomain;
import com.campus.domain.TrainClassDomain;
import com.campus.domain.TrainerDomain;
import com.campus.model.TrainClassModel;
import com.campus.model.param.CourseParam;
import com.campus.model.param.TrainClassParam;
import com.campus.model.param.TrainerParam;
import com.campus.mybais.mapper.TrainClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/6.
 */
@Component
public class TrainClassDomainRepository {

    @Autowired
    private TrainClassMapper trainClassMapper;
    @Autowired
    CourseDomainRepository courseDomainRepository;
    @Autowired
    TrainerDomainRepository trainerDomainRepository;

    public TrainClassDomain createDomain(TrainClassParam trainClassParam){
        return new TrainClassDomain(this,trainClassParam);
    }
    public TrainClass findTrainerClassById(Integer classId){
        return trainClassMapper.findTrainClassById(classId);
    }

    public List<TrainClass> findTrainerList(Map<String,Object> params){
        return trainClassMapper.findTrainClassList(params);
    }
    public TrainClass findTrainClassByNameAndMaster(String name,String masterName){
        return trainClassMapper.findTrainClassByNameAndMaster(name,masterName);
    }
    public Integer addTrainClass(TrainClass trainClass){
        return trainClassMapper.addTrainClass(trainClass);
    }
    public Integer addCourse(Course course){
        CourseDomain courseDomain = courseDomainRepository.createDomain(course);
        return courseDomain.addCourse();
    }
    public Integer editClassCourse(Course course){
        CourseDomain courseDomain = courseDomainRepository.createDomain(course);
        return courseDomain.editCourse();
    }
    public Integer editTrainClass(TrainClass trainClass){
        return  trainClassMapper.editTrainClass(trainClass);
    }
    public Integer deleteTrainClass(Integer classId){
        return trainClassMapper.deleteTrainClass(classId);
    }
    public Integer deleteCourseByClassId(Integer classId){
        CourseParam courseParam = new CourseParam();
        courseParam.setTrainClassId(classId);
        CourseDomain courseDomain = this.courseDomainRepository.createDomain(courseParam);
        return courseDomain.deleteCourseByClassId();
    }
    public Course findCourseByClassIdAndTime(Integer classId,Long time){
        CourseParam courseParam = new CourseParam();
        courseParam.setTrainClassId(classId);
        courseParam.setTime(time);
        CourseDomain courseDomain = this.courseDomainRepository.createDomain(courseParam);
        return courseDomain.findCourseByClassIdAndTime();
    }
    public  Integer deleteCourseByParam(Map<String,Object> pmap){
        CourseParam courseParam = new CourseParam();
        courseParam.setPmap(pmap);
        CourseDomain courseDomain = this.courseDomainRepository.createDomain(courseParam);
        return courseDomain.deleteCourseByParam();
    }

    public Trainer findTrainerByTrainerId(Integer trainerId){
        TrainerParam trainerParam = new TrainerParam();
        trainerParam.setTrainerId(trainerId);
        TrainerDomain trainerDomain = this.trainerDomainRepository.createDomain(trainerParam);
        return trainerDomain.findTrainerByTrainerId();
    }
    public Integer editTrainer(Trainer trainer){
        TrainerDomain trainerDomain = trainerDomainRepository.createDomain(trainer);
        return trainerDomain.editTrainerClassId();
    }
    /**通过trainerId 查询班级信息**/
    public TrainClassModel getTrainClassByTrainerId(Integer trainerId) {
        return createDomain(new TrainClassParam()).findTrainClassByTrainerId(trainerId);
    }
    /***通过trainerId查询班级信息**/
    public TrainClass findTrainClassByTrainerId(Integer trainerId) {
        return trainClassMapper.findTrainClassByTrainerId(trainerId);
    }
}
