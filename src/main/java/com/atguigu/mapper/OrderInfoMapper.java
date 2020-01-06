package com.atguigu.mapper;

import com.atguigu.domain.Order;
import com.atguigu.domain.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    List<OrderInfo> selectAll();

    int updateByPrimaryKey(OrderInfo record);

    void save_list_info(@Param("list_info") List<OrderInfo> list_info, @Param("order") Order order,@Param("flowId") Integer flowId);
}