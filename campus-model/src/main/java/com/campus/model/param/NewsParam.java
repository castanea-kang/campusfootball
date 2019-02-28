package com.campus.model.param;

/**
 * Created by likang on 2018/8/13.
 */
public class NewsParam {
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
    /**搜索关键词**/
    private String keyword;

    private Integer page = 1;

    private Integer pagesize = 10;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}
