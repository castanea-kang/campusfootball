package com.campus.model;

import com.campus.data.Cnst;

/**
 * TO DO :足球知识参数
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-08-24 15:12
 */
public class FootballKnowModel {

    /** */
    private Integer id;

    /** 标题*/
    private String title;
    /** 内容涉及图片*/
    private String imgs;
    /** 知识类别[1:足球概况，2:足球基本知识，3:各国足球组织，4:国际足坛大记事，5中国足球概况]*/
    private Byte type;
    private String typeStr;
    /** 收藏*/
    private Long collection;

    /** 浏览量*/
    private Long views;
    /** 内容*/
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
        this.title = title;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgs() {
        imgs = Cnst.imgFormat(imgs);
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    public String getTypeStr() {
        if(this.type == 2){
            typeStr = "足球基本知识";
        }else if(this.type == 3){
            typeStr = "各国足球组织";
        }else if(this.type == 4){
            typeStr = "国际足坛大记事";
        }else if(this.type == 5){
            typeStr = "中国足球概况";
        }else{
            typeStr = "足球概况";
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
