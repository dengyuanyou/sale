package com.atguigu.domain;

/**
 * 商品图片
 */
public class ProductImage {

    private Integer id;
    private String effect;   //作用
    private Integer productId;  //商品id
    private String url;        //地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEffect() {
        return effect;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}