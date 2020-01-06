package com.atguigu.domain;

import java.util.List;

/**
 * 封装商品详情信息
 */
public class DetailSku extends Sku{

    private Product product;    //spu
    private List<ProductImage> list_image;   //spu图片列表
    private List<ObjectAv> list_av_name;    //属性和属性值列表

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductImage> getList_image() {
        return list_image;
    }

    public void setList_image(List<ProductImage> list_image) {
        this.list_image = list_image;
    }

    public List<ObjectAv> getList_av_name() {
        return list_av_name;
    }

    public void setList_av_name(List<ObjectAv> list_av_name) {
        this.list_av_name = list_av_name;
    }
}
