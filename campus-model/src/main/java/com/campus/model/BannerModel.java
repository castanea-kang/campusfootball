package com.campus.model;

import com.campus.data.Cnst;

public class BannerModel {
    /** */
    private Integer id;

    /** 图片*/
    private String img;

    private String newsTitle;

    /** 开始时间*/
    private Long startTime;

    /** 结束时间*/
    private Long endTime;

    /** 新闻地址*/
    private String newsLink;
    /** 是否显示*/
    private Byte isShow;
    private String isShowStr;
    /**新闻id**/
    private Integer newsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        if(this.img != null && !this.img.isEmpty() && !this.img.contains("http")){
            img = Cnst.QINIU_BASE_URL.concat(img);
        }
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

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getIsShowStr() {
        if(this.isShow == 1){
            isShowStr = "显示";
        }else{
            isShowStr = "不显示";
        }
        return isShowStr;
    }

    public void setIsShowStr(String isShowStr) {
        this.isShowStr = isShowStr;
    }
}