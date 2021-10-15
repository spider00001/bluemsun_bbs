package com.bluemsun.dao;

import com.bluemsun.entity.Blog;
import com.bluemsun.entity.User;

import java.util.List;

public interface BolgMapper {

    List<Blog> selectBlogsByUser(User user);

}
