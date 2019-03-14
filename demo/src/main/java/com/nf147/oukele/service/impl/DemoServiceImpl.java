package com.nf147.oukele.service.impl;

import com.nf147.oukele.dao.DemoMapper;
import com.nf147.oukele.entity.Demo;
import com.nf147.oukele.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    /**
     * 删除操作 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return demoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param demo
     * @return
     */
    @Override
    public int insert(Demo demo) {
        return demoMapper.insert(demo);
    }

    /**
     * 根据id查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Demo selectByPrimaryKey(Integer id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Demo> selectAll() {
        return demoMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param demo
     * @return
     */
    @Override
    public int updateByPrimaryKey(Demo demo) {
        return demoMapper.updateByPrimaryKey(demo);
    }
}
