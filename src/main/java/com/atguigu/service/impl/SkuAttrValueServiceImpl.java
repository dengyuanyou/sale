package com.atguigu.service.impl;

import com.atguigu.mapper.SkuAttrValueMapper;
import com.atguigu.service.ISkuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuAttrValueServiceImpl implements ISkuAttrValueService {

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

}
