package com.atguigu.service;

import com.atguigu.domain.ObjectFlow;
import com.atguigu.domain.ObjectOrder;

public interface IFlowService {

    void save_flow(ObjectFlow objectFlow, ObjectOrder order);
}
