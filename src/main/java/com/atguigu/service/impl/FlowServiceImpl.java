package com.atguigu.service.impl;

import com.atguigu.domain.ObjectFlow;
import com.atguigu.domain.ObjectOrder;
import com.atguigu.mapper.FlowMapper;
import com.atguigu.service.IFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl implements IFlowService {

    @Autowired
    private FlowMapper flowMapper;

    public void save_flow(ObjectFlow objectFlow, ObjectOrder order) {

        flowMapper.insert_flow(objectFlow,order);
    }
}
