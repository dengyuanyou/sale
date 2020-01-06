package com.atguigu.domain;

import java.util.List;

/**
 * 封装订单详情集合的送货清单模型
 */
public class ObjectFlow extends Flow {

    private List<OrderInfo> list_info;

    public List<OrderInfo> getList_info() {
        return list_info;
    }

    public void setList_info(List<OrderInfo> list_info) {
        this.list_info = list_info;
    }
}
