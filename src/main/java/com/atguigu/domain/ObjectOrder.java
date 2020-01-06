package com.atguigu.domain;

import java.util.List;

/**
 * 封装送货清单集合
 */
public class ObjectOrder extends Order {

    private List<ObjectFlow> list_flow;

    public List<ObjectFlow> getList_flow() {
        return list_flow;
    }

    public void setList_flow(List<ObjectFlow> list_flow) {
        this.list_flow = list_flow;
    }
}
