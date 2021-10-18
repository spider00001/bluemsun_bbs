package com.bluemsun.service;

import com.bluemsun.entity.Blog;

import java.util.Map;

public interface BlogService {

    //博客分页(|全部类型)
    Map getBlogsPage(int pageNum, int pageSize);

    //板块内博客分页
    Map getBlogsOfPlatePage(int pageNum, int pageSize,int id);

    //删除博客
    Map deleteBlog(Blog blog);

    //获取置顶博客
    Map getBlogsHomeTop();

    //新增置顶博客
    Map toppingBlog(Blog blog);

    //修改置顶位置
    Map modifyBlogTop(Blog blog);

    //取消置顶
    Map cancelToppingBlog(Blog blog);

}
