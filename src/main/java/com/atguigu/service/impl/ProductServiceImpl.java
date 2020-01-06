package com.atguigu.service.impl;

import com.atguigu.domain.ObjectSku;
import com.atguigu.domain.Product;
import com.atguigu.mapper.ProductImageMapper;
import com.atguigu.mapper.ProductMapper;
import com.atguigu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    public int deleteByPrimaryKey(Integer id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    public int insert(Product product) {
        return productMapper.insert(product);
    }

    public Product selectByPrimaryKey(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    public int updateByPrimaryKey(Product product) {
        return productMapper.updateByPrimaryKey(product);
    }

    //保存图片和商品
    public void save(List<String> imageUrls, Product product) {
        product.setImageUrl(imageUrls.get(0));
        product.setCreateTime(new Date());
        //保存商品的基本信息
        productMapper.insert(product);
        //批量插入图片
        productImageMapper.batchInsertImages(imageUrls,product.getId());
    }

    //根据品牌id和分类二id查询spu
    public List<Product> findByBrandIdAndClassTwoId(Integer brandId, Integer classTwoId) {
        return productMapper.findByBrandIdAndClassTwoId(brandId,classTwoId);
    }

    public List<ObjectSku> findObjectSkuByClassTwoId(Integer classTwoId) {
        return productMapper.findObjectSkuByClassTwoId(classTwoId);
    }
}
