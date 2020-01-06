package com.atguigu.domain;

import java.util.Date;

/**
 * 送货清单
 */
public class Flow {

    private Integer id;
    private String deliver;     //配送方式
    private Date deliverTime;      //配送时间
    private String deliverDesc;    //配送描述
    private Integer userId;        //用户id
    private Date createTime;       //创建时间
    private Integer orderId;       //订单id
    private String currentPlace;   //当前地点
    private String goalPlace;      //送货地址
    private String business;        //业务员
    private String phoneNumber;    //电话号码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverDesc() {
        return deliverDesc;
    }

    public void setDeliverDesc(String deliverDesc) {
        this.deliverDesc = deliverDesc;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(String currentPlace) {
        this.currentPlace = currentPlace;
    }

    public String getGoalPlace() {
        return goalPlace;
    }

    public void setGoalPlace(String goalPlace) {
        this.goalPlace = goalPlace;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}