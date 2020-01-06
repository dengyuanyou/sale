package com.atguigu.domain;

import java.util.Date;

/**
 * 商品
 */
public class Product {

    private Integer id;
    private String name;
    private String imageUrl;
    private Integer classOneId;  //分类一
    private Integer classTwoId;  //分类二
    private Integer brandId;     //品牌
    private Date createTime;
    private String descr;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getClassOneId() {
        return classOneId;
    }

    public void setClassOneId(Integer classOneId) {
        this.classOneId = classOneId;
    }

    public Integer getClassTwoId() {
        return classTwoId;
    }

    public void setClassTwoId(Integer classTwoId) {
        this.classTwoId = classTwoId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
