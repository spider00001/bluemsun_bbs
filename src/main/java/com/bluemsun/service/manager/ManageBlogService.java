package com.bluemsun.service.manager;

import com.bluemsun.entity.Blog;

import java.util.Map;

public interface ManageBlogService {

    //博客分页(|全部类型)
    Map getBlogsPage(int pageNum, int pageSize);

    Map deleteBlog(Blog blog);

    Map toppingBlog(Blog blog);

    Map modifyBlogTop(Blog blog);

    Map cancelToppingBlog(Blog blog);

}
