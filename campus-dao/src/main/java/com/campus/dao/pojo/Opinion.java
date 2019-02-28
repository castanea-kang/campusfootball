package com.campus.dao.pojo;

public class Opinion {
    /** */
    private Integer id;

    /** 提交时间*/
    private Long pubdate;

    /** 反馈人*/
    private Integer trainerId;

    /** 意见内容*/
    private String content;

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
}