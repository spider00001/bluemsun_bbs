package com.bluemsun.dao;

import com.bluemsun.entity.Blog;
import com.bluemsun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {

    //全部
    int getBlogCount();

    List<Blog> getBlogsLimit(Map map);

    List<Blog> selectBlogsByUser(User user);

}
