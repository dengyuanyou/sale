package com.atguigu.controller;

import com.atguigu.domain.ModelSkuAttrValue;
import com.atguigu.domain.ObjectAttr;
import com.atguigu.domain.ObjectSku;
import com.atguigu.domain.SkuAttrValue;
import com.atguigu.service.IAttrService;
import com.atguigu.service.IProductService;
import com.atguigu.service.ISkuService;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyCacheUtil;
import com.atguigu.util.MyJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ListController {

    @Autowired
    private ISkuService skuService;
    @Autowired
    private IAttrService attrService;
    @Autowired
    private IProductService productService;

    /**
     * 查询二级分类下的spu、sku和属性列表
     * @param map
     * @param classTwoId
     * @return
     */
    @RequestMapping("/goto_list")
    public String goto_list(ModelMap map,Integer classTwoId){

        List<ObjectSku> objectSkus = new ArrayList<ObjectSku>();

        //属性列表
        List<ObjectAttr> objectAttrs = attrService.findAttrsByClassTwoId(classTwoId);

        //从Redis中根据二级分类查询sku商品列表
        String key = "class_2_"+classTwoId;
        objectSkus = MyCacheUtil.getList(key,ObjectSku.class);

        if (objectSkus.size()<1||objectSkus==null){
            //如果获取value为null则从数据库查询
            objectSkus = productService.findObjectSkuByClassTwoId(classTwoId);
            //将数据同步到Redis中
            MyCacheUtil.setKey(key,objectSkus);
        }

        map.put("list_attr",objectAttrs);
        map.put("list_sku",objectSkus);
        map.put("classTwoId",classTwoId);
        return "list";
    }

    /**
     * 根据属性和属性值查询sku商品列表
     * @param map
     * @param jsonArray
     * @param classTwoId
     * @return
     */
    @RequestMapping("/get_list_by_attr")
    public String get_list_by_attr(ModelMap map, ModelSkuAttrValue jsonArray,Integer classTwoId){

        List<SkuAttrValue> list_attr = jsonArray.getList_attr();

        List<ObjectSku> skuList = new ArrayList<ObjectSku>();

        String[] keys = new String[jsonArray.getList_attr().size()];

        for (int i=0;i<jsonArray.getList_attr().size();i++){
            SkuAttrValue skuAttrValue = jsonArray.getList_attr().get(i);
            keys[i] = "attr_"+classTwoId+"_"+skuAttrValue.getAttrId()+"_"+skuAttrValue.getValueId();

        }

        String interKeys = MyCacheUtil.interKeys(keys);

        skuList = MyCacheUtil.getList(interKeys, ObjectSku.class);

        //如果缓存为空
        if (skuList==null||skuList.size()<1){
            //最终的交叉查询的sku集合
            skuList = skuService.get_list_by_attr(jsonArray.getList_attr(),classTwoId);

            //循环传递过来的属性和属性值集合
            for (int i=0;i<list_attr.size();i++){
                String key = keys[i];

                boolean if_key = MyCacheUtil.if_key(key);
                //如果不key存在
                if (!if_key){
                    //attr_value对象
                    SkuAttrValue attr_value_for_redis = new SkuAttrValue();
                    attr_value_for_redis.setAttrId(list_attr.get(i).getAttrId());
                    attr_value_for_redis.setValueId(list_attr.get(i).getValueId());

                    //attr_value集合
                    List<SkuAttrValue> list_attr_for_redis = new ArrayList<SkuAttrValue>();
                    list_attr_for_redis.add(attr_value_for_redis);

                    //根据属性和属性值查询sku集合
                    List<ObjectSku> list_sku_for_redis = skuService.get_list_by_attr(list_attr_for_redis, classTwoId);
                    //同步到Redis中
                    MyCacheUtil.setKey(key,list_sku_for_redis);
                }

            }

        }

        map.put("list_sku",skuList);
        return "skuList";
    }


}
