package com.atguigu.service;

import com.atguigu.domain.Attr;
import com.atguigu.domain.ModelAttr;
import com.atguigu.domain.ObjectAttr;

import java.util.List;

public interface IAttrService {

    List<ObjectAttr> findAttrsByClassTwoId(Integer classTwoId);

    void saveAttrByClassTwoId(Integer classTwoId, Attr attr);

    void save(ModelAttr modelAttr, Integer classTwoId);
}
