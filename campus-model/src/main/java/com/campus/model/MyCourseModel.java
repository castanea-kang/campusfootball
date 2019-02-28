package com.campus.model;

import com.campus.dao.pojo.Certificate;
import com.campus.dao.pojo.Course;

import java.util.List;

/**
 * TO DO :我的培训
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-09-04 10:59
 */
public class MyCourseModel  {
    /**课程信息**/
    private List<Course> courses;
    /**班级信息**/
    private TrainClassModel trainClassModel;
    /***证书信息**/
    private Certificate certificate;
    /**名称**/
    private String  trainerName;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public TrainClassModel getTrainClassModel() {
        return trainClassModel;
    }

    public void setTrainClassModel(TrainClassModel trainClassModel) {
        this.trainClassModel = trainClassModel;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}
