package com.nf147.oukele.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nf147.oukele.entity.Demo;
import com.nf147.oukele.service.impl.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author OUKELE
 * @create 2019-03-14 14:10
 */

@Controller
public class TestController {

    @Autowired
    private DemoServiceImpl demoService;

    @RequestMapping(path = "/")
    public ModelAndView index(@RequestParam(defaultValue = "1")int page) {
        ModelAndView mv = new ModelAndView("index");
        PageHelper.startPage(page,2);
        List<Demo> demos = demoService.selectAll();
        mv.addObject("list",demos);
        mv.addObject("pageInfo",new PageInfo<>(demos));
        return mv;
    }
}
