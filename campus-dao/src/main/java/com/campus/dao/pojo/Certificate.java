package com.campus.dao.pojo;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by likang on 2018/8/28.
 */
public class Certificate {

    private Integer id;
    /**名称**/
    private String name;
    /**地址url**/
    private String url;
    /**级别**/
    private Short level;
    /*成绩**/
    private Short score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        if (StringUtils.isEmpty(url)){
            return "";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getScore() {
        if (score == null){
            return  100;
        }
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }
}
