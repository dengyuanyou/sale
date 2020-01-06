package com.atguigu.service.impl;

import com.atguigu.domain.*;
import com.atguigu.mapper.SkuAttrValueMapper;
import com.atguigu.mapper.SkuMapper;
import com.atguigu.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl implements ISkuService {

    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    //保存sku
    public void save(Product product, Sku sku, ModelSkuAttrValue list_attr) {
        Date date = new Date();
        sku.setProductId(product.getId());
        //先保存sku
        sku.setCreateTime(date);
        skuMapper.saveSku(sku);
        //然后保存sku_attr_value
        for (SkuAttrValue skuAttrValue:list_attr.getList_attr()){
            skuAttrValue.setCreateTime(date);
            skuAttrValueMapper.save(product.getId(),sku.getId(),skuAttrValue);
        }
    }

    //根据属性和分类查询列表
    public List<ObjectSku> get_list_by_attr(List<SkuAttrValue> list_attr, Integer classTwoId) {

        //动态拼接查询商品列表的sql语句
        StringBuffer subSql = new StringBuffer("");

        subSql.append(" and s.id IN ( SELECT sku0.sku_id FROM ");

        if (list_attr!=null&&list_attr.size()>0){
            for (int i=0;i<list_attr.size();i++){
                subSql.append(" ( select sku_id from sku_attr_value where attr_id = "+list_attr.get(i).getAttrId()+" " +
                            " and value_id = "+list_attr.get(i).getValueId()+" ) sku"+i+" ");
                //最后一个不用拼接逗号
                if ((i+1)<list_attr.size()&&list_attr.size()>1){
                    subSql.append(" , ");
                }

            }
            //拼接where
            if (list_attr.size()>1){
                subSql.append(" where ");
                //拼接连接条件
                for (int i=0;i<list_attr.size();i++){
                    //最后一次不用拼接
                    if ((i+1)<list_attr.size()){
                        subSql.append(" sku"+i +".sku_id="+"sku"+(i+1)+".sku_id ");

                        //拼接and
                        if (list_attr.size()>2 && i < (list_attr.size()-2)){
                            subSql.append(" and ");
                        }
                    }

                }
            }
        }
        //最后拼接)
        subSql.append(" ) ");

        Map<Object,Object> map = new HashMap<Object, Object>();
        map.put("classTwoId",classTwoId);
        map.put("subSql",subSql);

        List<ObjectSku> skuList = skuMapper.select_list_by_attr(map);
        return skuList;
    }

    //查询sku详情
    public DetailSku get_sku_detail(Integer skuId) {
        DetailSku detailSku = skuMapper.select_detail_sku(skuId);
        return detailSku;
    }

    //根据商品id查询该商品下的所有的ksu列表
    public List<Sku> get_sku_list_by_productId(Integer productId) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("productId",productId);
        return skuMapper.select_sku_list_by_productId(map);
    }
}
