package com.oukele.bookshop_ssm.web;

import com.oukele.bookshop_ssm.entity.Book;
import com.oukele.bookshop_ssm.service.BookService;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list")
    private ModelAndView getList(){
        ModelAndView mv = new ModelAndView("book_main");
        List<Book> list = bookService.listAll();
        mv.addObject("books",list);
        return mv;
    }

    //跳转 添加页面
    @RequestMapping(value = "/btn_add")
    private ModelAndView view_add(){
        ModelAndView mv = new ModelAndView("book_add");
        return mv;
    }

    //添加书籍数据
    @RequestMapping(value = "/insert")
    private ModelAndView insert(String bookname , int cnt){
        String result= bookService.book_insert(bookname, cnt);
        System.out.println(result);
        return  getList();
    }


}
