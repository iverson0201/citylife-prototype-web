package com.citylife.trackup.backend.common;

import java.util.List;

public class Pager<T> {
    //总记录数
    private int total;
    //每页显示的记录数
    private int pegeSize;
    //要显示的号码数
    private int showPages;
    //每页显示的具体内容
    private List<T> pageList;
    //总页数
    private long pageNum;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPegeSize() {
        return pegeSize;
    }

    public void setPegeSize(int pegeSize) {
        this.pegeSize = pegeSize;
    }

    public int getShowPages() {
        return showPages;
    }

    public void setShowPages(int showPages) {
        this.showPages = showPages;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }
}
