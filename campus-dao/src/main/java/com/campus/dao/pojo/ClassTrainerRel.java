package com.campus.dao.pojo;

public class ClassTrainerRel {
    /** */
    private Integer id;

    /** 班级ID*/
    private Integer classId;

    /** 训练员ID*/
    private Integer trainerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }
}