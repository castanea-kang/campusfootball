package com.campus.model.param;

import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/8/8.
 */
public class TrainClassParam {

    /** */
    private Integer classId;

    /** 训练名称*/
    private String name;

    /** */
    private Long startTime;

    /** */
    private Long endTime;

    /** 班级人数*/
    private Integer pNum;

    /** 班主任名称*/
    private String masterName;

    /** 班长id*/
    private Integer trainerId;

    /** 考试时间*/
    private Long examTime;

    /** 培训状态【1:报名中，2:培训中，3:考试结束】*/
    private Byte status;

    /** 培训介绍*/
    private String introduction;

    /**课程列表**/
    private List<Object> courseList;
    /**班级球员id列表**/
    private List<Integer> trainerIds;
    /**是否开始报名**/
    private boolean enroll = false;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName == null ? null : masterName.trim();
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Long getExamTime() {
        return examTime;
    }

    public void setExamTime(Long examTime) {
        this.examTime = examTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public List<Object> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Object> courseList) {
        this.courseList = courseList;
    }

    public List<Integer> getTrainerIds() {
        return trainerIds;
    }

    public void setTrainerIds(List<Integer> trainerIds) {
        this.trainerIds = trainerIds;
    }

    public boolean isEnroll() {
        return enroll;
    }

    public void setEnroll(boolean enroll) {
        this.enroll = enroll;
    }
}
