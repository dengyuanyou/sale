package com.atguigu.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
public class Order {

    private Integer id;
    private String receive;         //收货人
    private BigDecimal totalAmout;        //总金额
    private Integer scheduleId;     //进度号
    private Integer userId;         //用户id
    private Date createTime;
    private Date planeTime;         //预计送达时间
    private Integer addressId;      //地址id
    private String addressName;     //地址名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public BigDecimal getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(BigDecimal totalAmout) {
        this.totalAmout = totalAmout;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    public Date getPlaneTime() {
        return planeTime;
    }

    public void setPlaneTime(Date planeTime) {
        this.planeTime = planeTime;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}