package com.atguigu.domain;

import java.util.List;

/**
 * 商品属性值集合
 */
public class ObjectAttr extends Attr{

    private List<Value> list_value;

    public List<Value> getList_value() {
        return list_value;
    }

    public void setList_value(List<Value> list_value) {
        this.list_value = list_value;
    }
}
