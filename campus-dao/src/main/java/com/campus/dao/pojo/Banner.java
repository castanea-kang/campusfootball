package com.campus.dao.pojo;

public class Banner {

    public static final Byte NOT_SHOW = 0;
    public static final Byte  IS_SHOW = 1;
    /** */
    private Integer id;

    /** 图片*/
    private String img;

    /** 开始时间*/
    private Long startTime;

    /** 结束时间*/
    private Long endTime;

    /** 新闻地址*/
    private String newsLink;
    /** 是否显示*/
    private Byte isShow;
    /**新闻id**/
    private Integer newsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
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

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink == null ? null : newsLink.trim();
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}