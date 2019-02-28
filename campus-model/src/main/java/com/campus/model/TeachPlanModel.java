package com.campus.model;

import com.campus.data.Cnst;

public class TeachPlanModel {
    /** */
    private Integer id;

    /** 标题*/
    private String title;

    /** 文档地址*/
    private String url;

    /** 适用年纪*/
    private Integer gradeId;
    private String gradeStr;

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
    private String levelStr;

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
        if(this.url != null && !this.url.isEmpty() && !this.url.contains("http")){
            this.url = Cnst.QINIU_BASE_URL.concat(url);
        }
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

    public String getGradeStr() {
        return gradeStr;
    }

    public void setGradeStr(String gradeStr) {
        this.gradeStr = gradeStr;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getLevelStr() {
        levelStr = "初级";
        if(level == null){
            levelStr = "初级";
        } else if(this.level == 2){
            levelStr = "中级";
        }else if(this.level == 3){
            levelStr = "高级";
        }
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }
}