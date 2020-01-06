package com.atguigu.controller;

import com.atguigu.domain.DetailSku;
import com.atguigu.domain.Sku;
import com.atguigu.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品详情
 */

@Controller
public class ItemController {

    @Autowired
    private ISkuService skuService;

    /**
     * 根据skuId查询sku详情信息
     * @return
     */
    @RequestMapping("/goto_sku_detail")
    public String goto_sku_detail(Integer skuId, Integer productId, ModelMap map){

        //查询商品详情
        DetailSku detailSku = skuService.get_sku_detail(skuId);

        //根据skuid和商品id查询其他同类的商品列表
        List<Sku> skuList = skuService.get_sku_list_by_productId(productId);
        map.put("obj_sku",detailSku);
        map.put("list_sku",skuList);

        return "skuDetail";
    }

}
