package com.bluemsun.dao.impl;

import com.bluemsun.dao.BolgMapper;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class BlogMapperImpl extends SqlSessionDaoSupport implements BolgMapper {

    @Override
    public List<Blog> selectBlogsByUser(User user) {
        return getSqlSession().getMapper(BolgMapper.class).selectBlogsByUser(user);
    }



}
