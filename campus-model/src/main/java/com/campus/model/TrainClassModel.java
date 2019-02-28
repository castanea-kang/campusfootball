package com.campus.model;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by likang on 2018/8/8.
 */
public class TrainClassModel {
    /**班级ID**/
    private Integer id;
    /**培训名称**/
    private String name;
    /** 班长id*/
    private Integer trainerId;
    /*leaderName 班长name**/
    private String leaderName;
    /** */
    private Long startTime;

    /** */
    private Long endTime;
    /** 班主任名称*/
    private String masterName;

    /**培训时间**/
    private String trainTime;
    /**班级人数**/
    private Integer classNum;
    /**考试时间**/
    private String examTime;
    /**培训状态**/
    private String statusStr;
    /** 培训状态【1:报名中，2:培训中，3:考试结束，4:获得证书】*/
    private Byte status;
    /** 培训介绍*/
    private String introduction;

    public Byte getStatus() {

        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainTime() {
        if (StringUtils.isEmpty(trainTime)){
            return "";
        }
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public Integer getClassNum() {
        if (classNum == null){
            return  0;
        }
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getExamTime() {
        if (StringUtils.isEmpty(examTime)){
            return "";
        }
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getStatusStr() {
        if (StringUtils.isEmpty(statusStr)){
            return  "";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    public Integer getTrainerId() {
        if (trainerId == null){
            return -1;
        }
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
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

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
