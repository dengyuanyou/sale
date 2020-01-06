package com.atguigu.domain;

/**
 * 用户地址
 */
public class Address {

    private Integer id;
    private String userAddress;     //用户地址
    private String dzzt;
    private Integer userId;         //用户id
    private String receiver;        //收件人
    private String phone;           //联系方式

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDzzt() {
        return dzzt;
    }

    public void setDzzt(String dzzt) {
        this.dzzt = dzzt;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}