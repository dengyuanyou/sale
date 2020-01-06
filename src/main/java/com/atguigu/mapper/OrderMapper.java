package com.atguigu.mapper;

import com.atguigu.domain.Address;
import com.atguigu.domain.ObjectOrder;
import com.atguigu.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    void save_order(@Param("addr") Address addr, @Param("order") ObjectOrder order);

    void update_order(ObjectOrder order);
}