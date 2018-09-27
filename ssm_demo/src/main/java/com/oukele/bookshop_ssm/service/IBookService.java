package com.oukele.bookshop_ssm.service;

import java.util.List;

public interface IBookService<T> {
    List<T> listAll();
    int getBookName(String name);
    int insert(T book);
    int update(T book);
}
