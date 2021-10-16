package com.bluemsun.service.manager.Impl;

import com.bluemsun.dao.BlogMapper;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Page;
import com.bluemsun.service.manager.ManageBlogService;

import java.util.HashMap;
import java.util.Map;

public class ManageBlogServiceImpl implements ManageBlogService {


    private final BlogMapper blogMapper;

    public ManageBlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public Map<String,Object> getBlogsPage(int pageNum, int pageSize) {
        int totalRecord = blogMapper.getBlogCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        page.setList(blogMapper.getBlogsLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("mag","帖子分页成功");
            map.put("status",1);
            map.put("blogList",page.getList());
        } else {
            map.put("mag","帖子分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteBlog(Blog blog) {
        int row = blogMapper.deleteBlog(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除帖子成功");
            map.put("status",1);
        } else {
            map.put("msg","删除帖子失败");
            map.put("status",2);
        }
        return map;
    }

    //新增置顶博客
    @Override
    public Map toppingBlog(Blog blog) {
        int row = blogMapper.toppingBlog(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","置顶博客成功");
            map.put("status",1);
        } else {
            map.put("msg","置顶博客失败,该位置已有板块");
            map.put("status",2);
        }
        return map;
    }

    //修改博客置顶位置
    @Override
    public Map modifyBlogTop(Blog blog) {
        int row = blogMapper.modifyBlogTop(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","修改位置成功");
            map.put("status",1);
        } else {
            map.put("msg","修改位置失败,该位置已有博客");
            map.put("status",2);
        }
        return map;
    }

    //取消博客置顶
    @Override
    public Map cancelToppingBlog(Blog blog) {
        int row = blogMapper.cancelToppingBlog(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","取消博客置顶成功");
            map.put("status",1);
        } else {
            map.put("msg","取消博客置顶失败");
            map.put("status",2);
        }
        return map;
    }
}
