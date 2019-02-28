package com.campus.model.param;

/**
 * Created by likang on 2018/8/7.
 */
public class GradeParam {

    /** */
    private Integer id;

    /** 年级名称*/
    private String name;

    /** 父级ID*/
    private Integer parentId;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
