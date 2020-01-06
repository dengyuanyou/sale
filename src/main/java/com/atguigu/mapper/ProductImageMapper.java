package com.atguigu.mapper;

import com.atguigu.domain.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductImageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    ProductImage selectByPrimaryKey(Integer id);

    List<ProductImage> selectAll();

    int updateByPrimaryKey(ProductImage record);

    void batchInsertImages(@Param("imageUrls") List<String> imageUrls,@Param("id") Integer id);
}