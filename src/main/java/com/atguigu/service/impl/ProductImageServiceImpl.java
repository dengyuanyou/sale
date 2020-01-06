package com.atguigu.service.impl;

import com.atguigu.domain.ProductImage;
import com.atguigu.mapper.ProductImageMapper;
import com.atguigu.service.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements IProductImageService {

    @Autowired
    private ProductImageMapper productImageMapper;

    public int deleteByPrimaryKey(Integer id) {
        return productImageMapper.deleteByPrimaryKey(id);
    }

    public int insert(ProductImage record) {
        return productImageMapper.insert(record);
    }

    public ProductImage selectByPrimaryKey(Integer id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    public List<ProductImage> selectAll() {
        return productImageMapper.selectAll();
    }

    public int updateByPrimaryKey(ProductImage record) {
        return productImageMapper.updateByPrimaryKey(record);
    }
}
