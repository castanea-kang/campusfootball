package com.campus.dao.pojo;

public class News {
    /** */
    private Integer id;

    /** 新闻标题*/
    private String title;

    /** 新闻图片*/
    private String imgs;

    /** 新闻类型[1:教育新闻，2:培训新闻，3:banner新闻]*/
    private Byte type;

    /** 发送时间*/
    private Long createTime;

    /** 收藏*/
    private Long collection;

    /** 浏览量*/
    private Long views;

    /** 是否显示[1：显示，2：不显示]*/
    private Byte isShow;

    /** 新闻内容*/
    private String content;

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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}