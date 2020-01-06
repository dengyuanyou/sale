package com.atguigu.domain;


/**
 * 封装spu、sku、品牌
 */
public class ObjectSku extends Sku {

    private Product product;
    private Brand  brand;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
