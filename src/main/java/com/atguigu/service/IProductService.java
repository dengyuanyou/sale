package com.atguigu.service;

import com.atguigu.domain.ObjectSku;
import com.atguigu.domain.Product;

import java.util.List;

public interface IProductService {

    int deleteByPrimaryKey(Integer id);

    int insert(Product product);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product product);

    void save(List<String> imageUrls, Product product);

    List<Product> findByBrandIdAndClassTwoId(Integer brandId, Integer classTwoId);

    List<ObjectSku> findObjectSkuByClassTwoId(Integer classTwoId);
}
