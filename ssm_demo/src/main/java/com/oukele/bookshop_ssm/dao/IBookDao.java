package com.oukele.bookshop_ssm.dao;

import java.util.List;

public interface IBookDao<T> {
    List<T> listAll();
    int getBookName(String name);
    int insert(T book);
    int update(T book);

}
