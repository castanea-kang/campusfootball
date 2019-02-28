package com.campus.model.param;

import java.util.Map;

/**
 * Created by likang on 2018/8/8.
 */
public class CourseParam {
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

    private Map<String,Object> pmap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
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

    public Map<String, Object> getPmap() {
        return pmap;
    }

    public void setPmap(Map<String, Object> pmap) {
        this.pmap = pmap;
    }
}
