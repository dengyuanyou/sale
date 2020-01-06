package com.atguigu.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.FactoryBean;

/**
 * 远程调用工具类,
 * 实现spring的FactoryBean接口的三个方法来构造ws的实例对象
 */
public class  MyWsFactoryBean <T>  implements FactoryBean<T>{

    //属性注入
    private String url;
    private Class<T> t;

    public static <T> T getMyWs(String url, Class<T> t) {

        JaxWsProxyFactoryBean jwfb = new JaxWsProxyFactoryBean();
        jwfb.setAddress(url);
        jwfb.setServiceClass(t);
        T create = (T) jwfb.create();
        return create;
    }

    public T getObject() throws Exception {
        return getMyWs(url,this.t);
    }

    public Class<?> getObjectType() {
        return this.t;
    }

    public boolean isSingleton() {
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<T> getT() {
        return t;
    }

    public void setT(Class<T> t) {
        this.t = t;
    }
}
