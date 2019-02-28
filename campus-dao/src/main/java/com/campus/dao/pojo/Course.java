package com.campus.dao.pojo;

import com.alibaba.druid.util.StringUtils;

public class Course {
    /** */
    private Integer id;

    /** 课程标题*/
    private String title;

    /** 课程时间*/
    private Long time;

    /** 所属班级id*/
    private Integer trainClassId;

    /** 课程内容*/
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        if (StringUtils.isEmpty(title)){return "";}
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getTrainClassId() {
        return trainClassId;
    }

    public void setTrainClassId(Integer trainClassId) {
        this.trainClassId = trainClassId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}