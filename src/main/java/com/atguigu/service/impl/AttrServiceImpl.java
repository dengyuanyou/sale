package com.atguigu.service.impl;

import com.atguigu.domain.Attr;
import com.atguigu.domain.ModelAttr;
import com.atguigu.domain.ObjectAttr;
import com.atguigu.domain.Value;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.ValueMapper;
import com.atguigu.service.IAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttrServiceImpl implements IAttrService {

    @Autowired
    private AttrMapper attrMapper;
    @Autowired
    private ValueMapper valueMapper;

    //根据分类id查询所有属性
    public List<ObjectAttr> findAttrsByClassTwoId(Integer classTwoId) {
        return attrMapper.findAttrsByClassTwoId(classTwoId);
    }

    public void saveAttrByClassTwoId(Integer classTwoId, Attr attr) {
        attrMapper.saveAttrByClassTwoId(classTwoId,attr);
    }

    //保存属性和属性值
    public void save(ModelAttr modelAttr, Integer classTwoId) {
        for (ObjectAttr attr:modelAttr.getList_attr()){
            attr.setCreateTime(new Date());
            attrMapper.saveAttrByClassTwoId(classTwoId,attr);
            for (Value v:attr.getList_value()){
                v.setCreateTime(new Date());
                valueMapper.save(attr.getId(),v);
            }
        }
    }
}
