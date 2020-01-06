package com.atguigu.service;

import com.atguigu.domain.Address;
import com.atguigu.domain.ObjectOrder;
import com.atguigu.exception.OverSaleException;

public interface IOrderService {

    void save_order(Address addr, ObjectOrder order);

    void pay_success(ObjectOrder order) throws OverSaleException;
}
