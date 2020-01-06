package com.atguigu.service.impl;

import com.atguigu.domain.Order;
import com.atguigu.domain.OrderInfo;
import com.atguigu.mapper.OrderInfoMapper;
import com.atguigu.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public void save_list_info(List<OrderInfo> list_info, Order order) {

    }
}
