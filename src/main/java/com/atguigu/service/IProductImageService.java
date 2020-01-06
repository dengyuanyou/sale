package com.atguigu.service;

import com.atguigu.domain.ProductImage;

import java.util.List;

public interface IProductImageService {

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    ProductImage selectByPrimaryKey(Integer id);

    List<ProductImage> selectAll();

    int updateByPrimaryKey(ProductImage record);
}
