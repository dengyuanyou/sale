package com.atguigu.mapper;

import com.atguigu.domain.Value;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ValueMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Value record);

    Value selectByPrimaryKey(Integer id);

    List<Value> selectAll();

    int updateByPrimaryKey(Value record);

    void save(@Param("attId")Integer attId,@Param("v") Value v);

    List<Value> selectValueByAttrId(Integer attrId);
}