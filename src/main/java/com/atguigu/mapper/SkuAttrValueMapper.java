package com.atguigu.mapper;

import com.atguigu.domain.SkuAttrValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuAttrValueMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SkuAttrValue record);

    SkuAttrValue selectByPrimaryKey(Integer id);

    List<SkuAttrValue> selectAll();

    int updateByPrimaryKey(SkuAttrValue record);

    void save(@Param("productId") Integer productId,@Param("skuId") Integer skuId,@Param("skuAttrValue") SkuAttrValue skuAttrValue);
}