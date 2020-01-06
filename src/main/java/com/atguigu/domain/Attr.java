package com.atguigu.domain;

import java.util.Date;

/**
 * 商品属性
 */
public class Attr {

    private Integer id;
    private String name;
    private String enable;
    private Integer classTwoId;  //分类二id
    private Date createTime;    //创建时间

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

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getClassTwoId() {
        return classTwoId;
    }

    public void setClassTwoId(Integer classTwoId) {
        this.classTwoId = classTwoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}