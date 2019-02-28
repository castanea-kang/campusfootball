package com.campus.model;

public class OpinionModel {
    /** */
    private Integer id;

    /** 提交时间*/
    private Long pubdate;

    /** 反馈人*/
    private Integer trainerId;

    /** 意见内容*/
    private String content;
    /** 反馈人名*/
    private String trainerName;
    /** 反馈人手机号*/
    private String phone;
    /** 反馈时间*/
    private String pubdateStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPubdate() {
        return pubdate;
    }

    public void setPubdate(Long pubdate) {
        this.pubdate = pubdate;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPubdateStr() {
        return pubdateStr;
    }

    public void setPubdateStr(String pubdateStr) {
        this.pubdateStr = pubdateStr;
    }
}