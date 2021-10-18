package com.bluemsun.dao.impl;

import com.bluemsun.dao.BlogMapper;
import com.bluemsun.entity.Blog;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class BlogMapperImpl extends SqlSessionDaoSupport implements BlogMapper {

    @Override
    public int getBlogCount() {
        return getSqlSession().getMapper(BlogMapper.class).getBlogCount();
    }

    @Override
    public List<Blog> getBlogsLimit(Map map) {
        return getSqlSession().getMapper(BlogMapper.class).getBlogsLimit(map);
    }

    @Override
    public int getBlogOfPlateCount(int id) {
        return getSqlSession().getMapper(BlogMapper.class).getBlogOfPlateCount(id);
    }

    @Override
    public List<Blog> getBlogsOfPlateLimit(Map map) {
        return getSqlSession().getMapper(BlogMapper.class).getBlogsOfPlateLimit(map);
    }

    @Override
    public int deleteBlog(Blog blog) {
        return getSqlSession().getMapper(BlogMapper.class).deleteBlog(blog);
    }

    @Override
    public List<Blog> getBlogsHomeTop() {
        return getSqlSession().getMapper(BlogMapper.class).getBlogsHomeTop();
    }

    @Override
    public int toppingBlog(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).toppingBlog(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int modifyBlogTop(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).modifyBlogTop(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int cancelToppingBlog(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).cancelToppingBlog(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }


}
