package com.atguigu.domain;

import java.util.Date;

/**
 * 商品sku
 */
public class Sku {

    private Integer id;
    private Integer productId;   //商品id
    private Integer number;     //库存数量
    private Double price;       //价格
    private Date createTime;    //创建时间
    private String skuName;     //sku名称
    private Integer skuSales;   //sku销量
    private String address;     //库存地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getSkuSales() {
        return skuSales;
    }

    public void setSkuSales(Integer skuSales) {
        this.skuSales = skuSales;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}