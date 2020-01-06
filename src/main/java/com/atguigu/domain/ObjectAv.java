package com.atguigu.domain;

/**
 * 封装属性和属性值
 */
public class ObjectAv {

    private String attr_name;      //属性名
    private String value_value;     //属性值

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getValue_value() {
        return value_value;
    }

    public void setValue_value(String value_value) {
        this.value_value = value_value;
    }
}
