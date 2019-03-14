package com.nf147.oukele.dao;

import com.nf147.oukele.entity.Demo;
import java.util.List;

public interface DemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    Demo selectByPrimaryKey(Integer id);

    List<Demo> selectAll();

    int updateByPrimaryKey(Demo record);
}