package com.atguigu.service;

import com.atguigu.domain.*;

import java.util.List;

public interface ISkuService {

    void save(Product product, Sku sku, ModelSkuAttrValue list_attr);

    List<ObjectSku> get_list_by_attr(List<SkuAttrValue> list_attr, Integer classTwoId);

    DetailSku get_sku_detail(Integer skuId);

    List<Sku> get_sku_list_by_productId(Integer productId);
}
