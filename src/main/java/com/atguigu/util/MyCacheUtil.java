package com.atguigu.util;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyCacheUtil {

    public static String interKeys(String ... keys){

        Jedis jedis = null;

        try {
            jedis = JedisPoolUtils.getJedis();
        }catch (Exception e){
            return null;
        }
        String k0 = "combin";
        //注意key在真实开发中需要体现搜索的条件
        for (int i=0;i<keys.length;i++){
            k0 = k0 + "_" + keys[i];
        }

        //如果key不存在
        if (!MyCacheUtil.if_key(k0)){
            //key是在Redis中做交集生成的
            jedis.zinterstore("k0", keys);
        }

        return "k0";
    }

    //从Redis获取集合
    public static <T> List<T> getList(String key, Class<T> t) {

        Jedis jedis = null;

        try {
            jedis = JedisPoolUtils.getJedis();
        }catch (Exception e){
            return null;
        }

        List<T> list = new ArrayList<T>();

        Set<String> list_sku = jedis.zrange(key, 0, -1);
        Iterator<String> iterator = list_sku.iterator();
        while (iterator.hasNext()){
            String sku_json = iterator.next();
            T objectSku = MyJsonUtil.json_to_object(sku_json,t);
            list.add(objectSku);

        }
        return list;
    }

    public static <T> void setKey(String key, List<T> list) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
        }catch (Exception e){
            //记录日志
        }

        //清除当前key
        jedis.del(key);
        for (int i=0;i<list.size();i++){

            jedis.zadd(key,i,MyJsonUtil.object_to_json(list.get(i)));

        }
    }

    public static boolean if_key(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
        }catch (Exception e){

        }
        return jedis.exists(key);
    }
}
