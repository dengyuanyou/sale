package com.atguigu.mapper;

import com.atguigu.domain.Brand;
import java.util.List;

public interface BrandMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Integer id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);
}