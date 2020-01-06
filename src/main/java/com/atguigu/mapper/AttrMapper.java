package com.atguigu.mapper;

import com.atguigu.domain.Attr;
import com.atguigu.domain.ObjectAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttrMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Attr record);

    Attr selectByPrimaryKey(Integer id);

    List<Attr> selectAll();

    int updateByPrimaryKey(Attr record);

    List<ObjectAttr> findAttrsByClassTwoId(Integer classTwoId);

    void saveAttrByClassTwoId(@Param("classTwoId") Integer classTwoId,@Param("attr") Attr attr);
}