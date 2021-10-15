package com.bluemsun.entity;

import java.util.List;

public class Page<T> {

    private int pageNum;//当前第几页
    private int pageSize;//一页多少数据
    private int totalRecord;//数据库一共多少数据
    List<T> list;//用于存放数据库中的数据结果集，使用泛型，增强通用性
    private int totalPage;//总页数
    private int startIndex;//数据记录起始位置

    public Page() {
    }

    public Page(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        //计算totalPage
        if(totalRecord % pageSize == 0) {
            this.totalPage = totalRecord / pageSize;
        } else {
            this.totalPage = totalRecord / pageSize + 1;
        }
        //计算startIndex，当前第几页 * 一页多少条数据 - 一页多少条数据
        //比如说当前页是1，一页有5条数据，就是1*5-5 = 0,startIndex起始位置就是0
        startIndex = pageNum * pageSize - pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
}
