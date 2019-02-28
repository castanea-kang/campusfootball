package com.campus.dao.pojo;

import com.campus.data.Cnst;

public class TeachPlan {
    /** */
    private Integer id;

    /** 标题*/
    private String title;

    /** 文档地址*/
    private String url;

    /** 适用年纪*/
    private Integer gradeId;

    /** 下载*/
    private Integer download;

    /** 收藏*/
    private Long collection;

    /** 浏览量*/
    private Long views;
    /** 教案类型*/
    private Short type;
    /**等级**/
    private Short level;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Long getCollection() {
        return collection;
    }

    public void setCollection(Long collection) {
        this.collection = collection;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }
}