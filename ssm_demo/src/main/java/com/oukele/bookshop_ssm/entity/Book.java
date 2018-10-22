package com.oukele.bookshop_ssm.entity;

public class Book {
    private int bookid;
    private String name;
    private int cnt;

    public Book() {
    }

    public Book(int bookid, String name, int cnt) {
        this.bookid = bookid;
        this.name = name;
        this.cnt = cnt;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
