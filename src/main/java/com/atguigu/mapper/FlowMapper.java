package com.atguigu.mapper;

import com.atguigu.domain.Flow;
import com.atguigu.domain.ObjectFlow;
import com.atguigu.domain.ObjectOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Flow record);

    Flow selectByPrimaryKey(Integer id);

    List<Flow> selectAll();

    int updateByPrimaryKey(Flow record);

    void insert_flow(@Param("flow") ObjectFlow objectFlow, @Param("order") ObjectOrder order);

    void update_desc(ObjectFlow flow);
}