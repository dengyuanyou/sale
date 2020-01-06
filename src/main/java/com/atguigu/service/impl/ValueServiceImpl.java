package com.atguigu.service.impl;

import com.atguigu.domain.Value;
import com.atguigu.mapper.ValueMapper;
import com.atguigu.service.IValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueServiceImpl implements IValueService {

    @Autowired
    private ValueMapper valueMapper;

    public void save(Integer attId,Value v) {
        valueMapper.save(attId,v);
    }
}
