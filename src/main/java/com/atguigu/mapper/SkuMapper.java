package com.atguigu.mapper;

import com.atguigu.domain.DetailSku;
import com.atguigu.domain.ObjectSku;
import com.atguigu.domain.OrderInfo;
import com.atguigu.domain.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SkuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Sku record);

    Sku selectByPrimaryKey(Integer id);

    List<Sku> selectAll();

    int updateByPrimaryKey(Sku record);

    void saveSku(@Param("sku") Sku sku);

    List<ObjectSku> select_list_by_attr(Map<Object, Object> map);

    DetailSku select_detail_sku(Integer skuId);

    List<Sku> select_sku_list_by_productId(Map<Object,Object> map);

    int select_number_by_skuId(Integer skuId);

    void updata_number(OrderInfo info);

    int select_kc_count(OrderInfo info);

    int select_number(Integer skuId);
}