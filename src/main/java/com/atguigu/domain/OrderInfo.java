package com.atguigu.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 */
public class OrderInfo {

    private Integer id;
    private Integer orderId;    //订单id
    private Integer skuId;      //skuid
    private Date createTime;
    private String skuName;      //sku名称
    private String productImage;    //商品图片
    private BigDecimal skuPrice;          //sku价格
    private Integer skuNumber;      //sku数量
    private String skuAddress;      //sku库存地址
    private Integer flowId;         //送货清单id
    private Integer shoppingCarId;  //购物车id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(Integer skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getSkuAddress() {
        return skuAddress;
    }

    public void setSkuAddress(String skuAddress) {
        this.skuAddress = skuAddress;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getShoppingCarId() {
        return shoppingCarId;
    }

    public void setShoppingCarId(Integer shoppingCarId) {
        this.shoppingCarId = shoppingCarId;
    }
}