package com.oukele.bookshop_ssm.service;

import com.oukele.bookshop_ssm.dao.IBookDao;
import com.oukele.bookshop_ssm.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService<Book> {

    @Autowired
    private IBookDao<Book> bookDao;

    @Override
    public List<Book> listAll() {
        return bookDao.listAll();
    }

    @Override
    public int getBookName(String name) {
        return bookDao.getBookName(name);
    }

    @Override
    public int insert(Book book) {
        return bookDao.insert(book);
    }

    @Override
    public int update(Book book) {
        return bookDao.update(book);
    }

    public String book_insert(String name , int cnt){
        int result =0;
        if(getBookName(name) == 1){
            result =update(new Book(0,name,cnt));
           return result(result,1);
        }else{
            result = insert(new Book(0,name,cnt));
            return result(result,0);
        }
    }

    public String result(int result,int state){
        if(state == 1 ) {
            if (result > 0) {
                return "修改成功!";
            } else {
                return "修改失败";
            }
        }else if(state == 0 ){
            if (result > 0) {
                return "修改成功!";
            } else {
                return "修改失败";
            }
        }else{
            return "";
        }
    }



}
