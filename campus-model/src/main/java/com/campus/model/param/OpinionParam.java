package com.campus.model.param;


import java.util.List;

public class OpinionParam {
    /** */
    private Integer id;

    /** 提交时间*/
    private Long pubdate;

    /** 反馈人*/
    private Integer trainerId;

    /** 意见内容*/
    private String content;

    private Long startTime;

    private Long endTime;

    private String keyword;

    private List<Integer> ids;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}

