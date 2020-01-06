package com.atguigu.service.impl;

import com.atguigu.domain.Address;
import com.atguigu.domain.ObjectFlow;
import com.atguigu.domain.ObjectOrder;
import com.atguigu.domain.OrderInfo;
import com.atguigu.exception.OverSaleException;
import com.atguigu.mapper.*;
import com.atguigu.service.IOrderService;
import com.atguigu.util.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;
    @Autowired
    private SkuMapper skuMapper;

    public void save_order(Address addr, ObjectOrder order) {

        //购物车id集合
        List<Integer> list_id = new ArrayList<Integer>();

        //保存订单
        orderMapper.save_order(addr,order);

        for (int i=0;i<order.getList_flow().size();i++){

            order.getList_flow().get(i).setGoalPlace(addr.getUserAddress());

            //保存送货清单
            flowMapper.insert_flow(order.getList_flow().get(i),order);

            ObjectFlow objectFlow = order.getList_flow().get(i);

            //保存订单详情order_info
            orderInfoMapper.save_list_info(objectFlow.getList_info(),order,objectFlow.getId());

            for (int j=0;j<objectFlow.getList_info().size();j++){
                OrderInfo info = objectFlow.getList_info().get(j);
                list_id.add(info.getShoppingCarId());
            }

        }

        Map<Object, Object> map_cart = new HashMap<Object, Object>();
        map_cart.put("list_id",list_id);
        //删除数据库中已经生成订单的购物车
        shoppingCarMapper.delect_carts(map_cart);

    }

    //修改订单参数
    public void pay_success(ObjectOrder order) throws OverSaleException {

        //修改订单进度号,已支付
        order.setScheduleId(2);
        orderMapper.update_order(order);
        for (int i=0;i<order.getList_flow().size();i++){
            ObjectFlow flow = order.getList_flow().get(i);
            //修改订单物流
            flow.setDeliverTime(MyDateUtil.getMyTime(1));
            flow.setDeliverDesc("商品正在出库");
            flow.setBusiness("老佟");
            flow.setPhoneNumber("12436712545");
            flowMapper.update_desc(flow);

            for (int j=0;j<flow.getList_info().size();j++){
                OrderInfo info = flow.getList_info().get(j);
                 //查询库存数量警戒线
                 int count = skuMapper.select_kc_count(info);
                 int kc;
                 if (count==0){
                     //执行带锁查询SQL
                     kc = skuMapper.select_number_by_skuId(info.getSkuId());
                     update_info(info,kc);
                 }else {
                     //执行不带锁查询SQL
                     kc = skuMapper.select_number(info.getSkuId());
                     update_info(info,kc);
                 }

            }

        }
        //修改订单进度号，已出库
        order.setScheduleId(3);
        order.setPlaneTime(MyDateUtil.getMyTime(3));
        orderMapper.update_order(order);

    }

    private void update_info(OrderInfo info, int kc) throws OverSaleException {
        if (kc>=info.getSkuNumber()){
            //修改商品sku库存数量
            skuMapper.updata_number(info);
        }else {
            throw new OverSaleException("over sale");
        }
    }
}
