package com.atguigu.service;

import com.atguigu.domain.Order;
import com.atguigu.domain.OrderInfo;

import java.util.List;

public interface IOrderInfoService {

    void save_list_info(List<OrderInfo> list_info, Order order);
}
